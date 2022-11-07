package cm.milkywaylib.linkdown;

import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.nnat.InputMap;
import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.render.wrapper.Keys;
import cm.milkywaygl.text.JsonFile;
import cm.milkywaygl.util.container.List;
import cm.milkywaylib.linklib.RenderBuffer;

public class BufDialog extends RenderBuffer
{

    List<String> text = new List<>();
    Color4 color;
    int key = Keys.Z;

    boolean disposed = false;

    public void setExitKey(int code)
    {
        key = code;
    }

    public void tickThen()
    {
        super.tickThen();

        if(InputMap.keyClick(key)) {
            disposed = true;
        }
    }

    public void renderThen()
    {
        if(disposed) {
            return;
        }

        if(color != null) {
            GL.gl.curState().color(color);
            GL.gl4.dim(box4);
        }
        else if(texture != null) {
            GL.gl2.dim(texture, box4);
        }

        if(text != null) {
            for(int i = 0; i < text.size(); i++) {
                GL.gl2f.text(
                        text.get(i),
                        box4.xc() + box4.width() * 0.2,
                        box4.yc() + box4.height() * 0.2 + i * GL.gl.curState().fontNow.size,
                        false
                );
            }
        }
    }

    public void append(String txt)
    {
        text.add(txt);
    }

    public void from(JsonFile txt)
    {
        String k;
        int times = 0;
        while((k = txt.entry("line." + times).toString()) != null) {
            times++;
            append(k);
        }
    }

    public void setColor(Color4 c4)
    {
        color = c4;
    }

}
