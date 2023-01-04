package cm.milkyway.opengl.input;

public interface InputCache
{

    default void bind()
    {
        InputMap.cache = this;
    }

    boolean isOn(Key code);

    int downTime(Key code);

    boolean isClick(Key code);

    double x();

    double y();

    double scroll();

}
