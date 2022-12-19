package cm.milkywayx.scriptx.cls.value;

import cm.milkyway.lang.text.Data;

public class CLSBoolExpr extends CLSValue
{

    public enum Type
    {
        LEFT_BIG,
        RIGHT_BIG,
        EQUAL,
        UNEQUAL,
        LEFT_BIG_EQUAL,
        RIGHT_BIG_EQUAL;

        public Type reverse()
        {
            switch(this) {
                case EQUAL:
                    return UNEQUAL;
                case UNEQUAL:
                    return EQUAL;
                case LEFT_BIG:
                    return RIGHT_BIG_EQUAL;
                case RIGHT_BIG:
                    return LEFT_BIG_EQUAL;
                case LEFT_BIG_EQUAL:
                    return RIGHT_BIG;
                case RIGHT_BIG_EQUAL:
                    return LEFT_BIG;
            }
            return null;
        }
    }

    //a == b like type
    CLSValue left, right;
    Type type;
    CLSMethodArg copy;
    //

    //if this is nonnull, directly return it
    CLSValue nonnullIsDirect;

    public CLSBoolExpr(CLSMethodArg params)
    {
        left = params.get(0);
        if(left.type() == CLSValue.Type.BOOLEAN) {
            nonnullIsDirect = left;
        }
        right = params.get(2);
        copy = params;
        switch(params.vString(1)) {
            case "==":
                type = Type.EQUAL;
                break;
            case "!=":
                type = Type.UNEQUAL;
                break;
            case ">=":
                type = Type.LEFT_BIG_EQUAL;
                break;
            case "<=":
                type = Type.RIGHT_BIG_EQUAL;
                break;
            case ">":
                type = Type.LEFT_BIG;
                break;
            case "<":
                type = Type.RIGHT_BIG;
                break;
        }
    }

    public CLSBoolExpr getReverse()
    {
        CLSBoolExpr state = new CLSBoolExpr(copy);
        state.type = type.reverse();
        return state;
    }

    public CLSValue.Type type()
    {
        return CLSValue.Type.BOOLEAN;
    }

    public String vString()
    {
        return Data.toString(vBool());
    }

    public boolean vBool()
    {
        if(nonnullIsDirect != null) {
            return nonnullIsDirect.vBool();
        }
        double a = left.vDouble();
        double b = right.vDouble();
        switch(type) {
            case EQUAL:
                return a == b;
            case UNEQUAL:
                return a != b;
            case LEFT_BIG:
                return a > b;
            case RIGHT_BIG:
                return a < b;
            case LEFT_BIG_EQUAL:
                return a >= b;
            case RIGHT_BIG_EQUAL:
                return a <= b;
        }
        return false;
    }

}
