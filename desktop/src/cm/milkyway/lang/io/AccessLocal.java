package cm.milkyway.lang.io;

public class AccessLocal extends Access
{

    public AccessLocal(String local)
    {
        super(BasePath.jar(local));
    }

}
