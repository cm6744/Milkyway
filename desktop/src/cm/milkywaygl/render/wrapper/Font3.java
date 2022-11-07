package cm.milkywaygl.render.wrapper;

import cm.milkywaygl.render.GL;
import cm.milkywaygl.text.Data2;
import cm.milkywaygl.util.IntBuffer;
import cm.milkywaygl.util.container.VarList;

public class Font3
{

    VarList numbers = new VarList();

    IntBuffer fontsAll;

    boolean zeroFilling;
    int sizeOfNumber;

    public Font3(IntBuffer fonts, boolean fillWithZero, int size)
    {
        fontsAll = fonts;
        zeroFilling = fillWithZero;
        sizeOfNumber = size;
    }

    public void render(double x, double y, int eachWidth, int height)
    {
        double texEach = GL.gl2.texw(fontsAll) / 10;
        double texHeight = GL.gl2.texw(fontsAll);

        for(int i = 0; i < numbers.size(); i++) {
            GL.gl2.dim(fontsAll, x + (i * eachWidth), y, eachWidth, height, texEach * numbers.get(i), 0, texEach, texHeight);
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
