package cm.milkywaygl.io;

import cm.milkywaytool.container.List;

public interface Accessor
{

    Access access(String path);

    //STRING ACCESS
    void write(List<String> list, Access access);

    //STRING ACCESS
    List<String> read(Access access);

    //STRING ACCESS
    default void write(List<String> list, String access)
    {
        write(list, access(access));
    }

    //STRING ACCESS
    default List<String> read(String access)
    {
        return read(access(access));
    }

}
