package cm.milkyway.tipy;

import cm.milkyway.lang.io.Access;

public interface TipyWriter
{

    Tipy createSaveData();

    void write(Access access);

}
