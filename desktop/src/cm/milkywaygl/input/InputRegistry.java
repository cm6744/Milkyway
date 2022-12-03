package cm.milkywaygl.input;

public interface InputRegistry
{

    void register(InputCallback callback);

    void unregister(InputCallback callback);

}
