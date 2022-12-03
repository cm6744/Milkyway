实例代码：

#CLS 900
//无实际作用，标记脚本版本

compile("c1", "scripts/cls1.cls");
//编译阶段提前编译一个其它的脚本，名称为c1
//路径为jar路径同级下
//例如此情况，idea测试时实际路径为：
//build/classes/java/scripts/cls1.cls

run("c1");
//运行名称为c1的脚本

var: a = 100;
//变量赋值

var: b = rand(0, 10);
//变量赋值成函数引用，需要注意的是每次引用变量都会重新计算函数值
//所以变量并不一定每次引用都一样

if(a : == : 100)
//if语句，必须用：分割
    while(a : >= : 0)
    //while语句，和if同理
        a = add(a, -1);
    endwhile;
    print(a);
endif;
//必须用这两个标识符结束if、while作用域


