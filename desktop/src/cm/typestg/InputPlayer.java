package cm.typestg;

import cm.milkyway.opengl.input.InputCallback;

public class InputPlayer extends InputCallback
{

    public Player pl;
    int l, u, d, r;

    public InputPlayer(Player p)
    {
        pl = p;
        l = pl.kl.code();
        u = pl.ku.code();
        d = pl.kd.code();
        r = pl.kr.code();
    }

    public void keyTyped(int code)
    {
        if(code == l || code == u || code == d || code == r) {
            pl.tick();
        }
    }

}
