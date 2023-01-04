package cm.backends.tipy;

import cm.milkyway.lang.io.Access;
import cm.milkyway.tipy.Tipy;
import cm.milkyway.tipy.TipyWriter;

public class DefaultTipyWriter implements TipyWriter
{

    JsonTipy tipy;

    public Tipy createSaveData()
    {
        return tipy = new JsonTipy();
    }

    public void write(Access access)
    {
        tipy.write(access);
    }

}
