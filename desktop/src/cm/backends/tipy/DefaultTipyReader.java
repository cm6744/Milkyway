package cm.backends.tipy;

import cm.milkyway.lang.io.Access;
import cm.milkyway.tipy.Tipy;
import cm.milkyway.tipy.TipyReader;

public class DefaultTipyReader implements TipyReader
{

    public Tipy read(Access access)
    {
        return new JsonTipy(access);
    }

}
