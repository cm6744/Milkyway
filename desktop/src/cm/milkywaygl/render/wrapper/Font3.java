package cm.milkywaygl.render.wrapper;

import cm.milkywaygl.render.GL;
import cm.milkywaygl.text.Data2;
import cm.milkywaygl.util.IntBuffer;
import cm.milkywaygl.util.container.VarList;

public class Font3
{

    static final int ALL = 10;

    VarList numbers = new VarList();

    IntBuffer fontsAll;
    Area[] eachFont = new Area[ALL];

    boolean zeroFilling;
    int sizeOfNumber;

    public Font3(IntBuffer fonts, boolean fillWithZero, int size)
    {
        fontsAll = fonts;
        zeroFilling = fillWithZero;
        sizeOfNumber = size;

        for(int i = 0; i < ALL; i++) {
            Area area = Area.dim01(fonts, 1.0 / ALL * i, 0, 1.0 / ALL, 1);
            eachFont[i] = area;
        }
    }

    public void render(double x, double y, int eachWidth, int height)
    {
        for(int i = 0; i < numbers.size(); i++) {
            GL.gl2.dim(eachFont[numbers.get(i)], x, y, eachWidth, height);
        }
    }

    public void update(int num)
    {
        numbers.clear();

        int length = Data2.lengthOf(num);

        if(length < sizeOfNumber && zeroFilling) {
            for(int i = 0; i < sizeOfNumber - length; i++) {
                numbers.add(num);//fill with 0 when no digit
            }
        }

        for(int i = 0; i < length; i++) {
            numbers.add(Data2.intAt(num, i));
        }
    }

}
