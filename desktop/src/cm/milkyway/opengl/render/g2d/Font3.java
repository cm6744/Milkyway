package cm.milkyway.opengl.render.g2d;

import cm.milkyway.lang.container.list.NonnullList;
import cm.milkyway.lang.text.DataType;
import cm.milkyway.opengl.render.graphics.Graphics2D;

public class Font3
{

    static final int ALL = 10;

    NonnullList<Integer> numbers = new NonnullList<>(0);

    Tex fontsAll;
    AreaStatic[] eachFont = new AreaStatic[ALL];

    boolean zeroFilling;
    int sizeOfNumber;

    public Font3(Tex fonts, boolean fillWithZero, int size)
    {
        fontsAll = fonts;
        zeroFilling = fillWithZero;
        sizeOfNumber = size;

        for(int i = 0; i < ALL; i++) {
            AreaStatic area = AreaStatic.dim01(fonts, 1.0 / ALL * i, 0, 1.0 / ALL, 1);
            eachFont[i] = area;
        }
    }

    public void render(Graphics2D g, double x, double y, int eachWidth, int height)
    {
        for(int i = 0; i < numbers.size(); i++) {
            eachFont[numbers.get(i)].render(g, x, y, eachWidth, height);
        }
    }

    public void update(int num)
    {
        numbers.clear();

        int length = DataType.lengthOf(num);

        if(length < sizeOfNumber && zeroFilling) {
            for(int i = 0; i < sizeOfNumber - length; i++) {
                numbers.add(num);//fill with 0 when no digit
            }
        }

        for(int i = 0; i < length; i++) {
            numbers.add(DataType.intAt(num, i));
        }
    }

}
