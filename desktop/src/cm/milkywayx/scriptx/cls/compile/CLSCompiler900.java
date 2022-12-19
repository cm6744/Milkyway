package cm.milkywayx.scriptx.cls.compile;

import cm.milkywayx.scriptx.cls.method.Method;
import cm.milkywayx.scriptx.cls.statement.IfManage;
import cm.milkywayx.scriptx.cls.statement.IfState;
import cm.milkywayx.scriptx.cls.statement.WhileState;
import cm.milkywayx.scriptx.cls.value.*;
import cm.milkyway.lang.container.List;
import cm.milkyway.lang.text.Data;
import cm.milkyway.lang.text.DataType;

//a totally shitty code
//the next day I will cannot understand it

//not in maintenance range!
//if something wrong, users need to implement themselves.
public class CLSCompiler900 extends CLSCompiler
{

    //just a cache, storage the now line's running conditions.
    List<IfState> nowIfCaches = new List<>();
    List<WhileState> nowWhiles = new List<>();

    protected void flushCache()
    {
        nowIfCaches.clear();
        nowWhiles.clear();
    }

    protected void compile(CLSState state, String s, int ln)
    {
        if(Data.isEmpty(s) || Data.startWith(s, "//")) {
            return;
        }
        if(Data.startWith(s, "#")) {
            return;
        }

        if(Data.startIgnSpace(s, "var")) {
            String next = Data.subString(s, "=", ";");
            String last = Data.subString(Data.compress(s), ":", "=");
            CLSValue v = strToValue(state, next);
            state.vars.origin(last, v);
        }
        else if(Data.startIgnSpace(s, "if")) {
            CLSBoolExpr expr;
            CLSMethodArg params = getParams(state, s, ':');
            expr = new CLSBoolExpr(params);
            nowIfCaches.add(new IfState(expr));
        }
        else if(Data.startIgnSpace(s, "else")) {
            IfState rev = nowIfCaches.remove(nowIfCaches.last()).getReverse();
            nowIfCaches.add(rev);
        }
        else if(Data.startIgnSpace(s, "endif")) {
            nowIfCaches.remove(nowIfCaches.last());
        }
        else if(Data.startIgnSpace(s, "while")) {
            CLSBoolExpr expr;
            CLSMethodArg params = getParams(state, s, ':');
            expr = new CLSBoolExpr(params);
            nowWhiles.add(new WhileState(new IfState(expr)));
        }
        else if(Data.startIgnSpace(s, "endwhile")) {
            nowWhiles.remove(nowWhiles.last());
        }
        else if(Data.startIgnSpace(s, "compile")) {
            //a builtin method, put compiled CLS in a map.
            CLSMethodArg params = getParams(state, s, ',');
            state.links.put(params.vString(0), compileFile(params.vString(1)));
        }
        else if(Data.startIgnSpace(s, "run")) {
            //another builtin method, run a compiled CLS in map.
            CLSMethodArg params = getParams(state, s, ',');
            state.lines.add(() -> {
                state.links.get(params.vString(0)).run();
            });
            callNewLine(state);
        }
        else if(catchToken(state, s, ln)) {
            runToken(state, s, ln);
        }
        else {
            Method<?> method = getMethod(s);
            if(method != null) {
                //method invoke
                CLSMethodArg params = getParams(state, s, ',');
                state.lines.add(() -> {
                    method.invoke(params);
                });
            }
            //variable operate
            //removed, for bugs
            callNewLine(state);
        }
    }

    protected void callNewLine(CLSState state)
    {
        IfManage manage = state.ifStates.get(state.lines.last());
        if(manage == null) {
            manage = new IfManage();
            state.ifStates.add(manage);
        }
        manage.append(nowIfCaches);
        nowWhiles.iterate((o, i) -> {
            state.whileStates.add(o);
        }, false);
    }

    //arg: compressed, an arg str
    //origin: the not-compressed
    protected CLSValue strToValue(CLSState state, String arg)
    {
        String trim = Data.compress(arg);
        if(DataType.isNumber(trim)) {
            //if is var a = 1; like
            return new CLSNum(DataType.parseDouble(trim));
        }
        else if(DataType.isBool(trim)) {
            //if is var a = false; like
            return new CLSBool(DataType.parseBoolean(trim));
        }
        //string:use origin to keep spaces
        else if(Data.contain(arg, "\"")) {
            //if is var a = "hello"; like
            return new CLSString(Data.subString2Dr(arg, "\"", "\""));
        }
        else {
            Method<?> mt = getMethod(trim);
            if(mt != null) {
                return new CLSReferenceMethod(mt, getParams(state, arg, ','));
            }
            else if(state.vars.varExists.contains(trim)) {
                return new CLSReferenceVar(trim, state);
            }
            else {
                //not any type, return an unsigned type
                //like >=, < ,etc. Operators are saved in this type.
                return new CLSUnsigned(trim);
            }
        }
    }

    //get method from:
    //invoke(1, 2, 3, invoke(4, 1, 2, 3));
    //-> "invoke"'s method instance
    protected Method<?> getMethod(String fullMethod)
    {
        String last = Data.lastString(Data.compress(fullMethod), "(");
        return libs.get(last);
    }

    //get params from:
    //invoke(1, 2, 3, invoke(4, 1, 2, 3));
    //-> [1, 2, 3, invoke(4, 1, 2, 3)]
    protected CLSMethodArg getParams(CLSState state, String fullMethod, char cut)
    {
        List<CLSValue> values = new List<>();
        //the bracket depth,
        //meet a left bracket and increase.
        int layers = 0;
        int lastInd = -1;
        for(int i = 0; i < fullMethod.length(); i++) {
            char c = fullMethod.charAt(i);
            if(c == '(') {
                if(layers == 0) {
                    if(lastInd == -1) {
                        lastInd = i;
                    }
                    else {
                        values.add(strToValue(state, Data.subString(fullMethod, lastInd, i)));
                        lastInd = -1;
                    }
                }
                layers++;
            }
            else if(c == ')') {
                layers--;
                if(layers == 0) {
                    if(lastInd == -1) {
                        lastInd = i;
                    }
                    else {
                        values.add(strToValue(state, Data.subString(fullMethod, lastInd, i)));
                        lastInd = -1;
                    }
                }
            }
            if(layers == 1 && c == cut) {
                if(lastInd != -1) {
                    values.add(strToValue(state, Data.subString(fullMethod, lastInd, i)));
                }
                lastInd = i;
            }
        }
        return new CLSMethodArg(values, state);
    }

}
