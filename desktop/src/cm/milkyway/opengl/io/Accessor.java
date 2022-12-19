package cm.milkyway.opengl.io;

import cm.milkyway.lang.container.List;

public interface Accessor
{

    Access local(String path);

    //STRING ACCESS
    void write(List<String> list, Access access);

    //STRING ACCESS
    List<String> read(Access access);

    //STRING ACCESS
    default void write(List<String> list, String access)
    {
        write(list, local(access));
    }

    //STRING ACCESS
    default List<String> read(String access)
    {
        return read(local(access));
    }

}
