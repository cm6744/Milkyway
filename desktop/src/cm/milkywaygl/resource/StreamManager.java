package cm.milkywaygl.resource;

import cm.milkywaygl.Platform;

import java.io.Closeable;
import java.io.IOException;

public class StreamManager<E>
{

    Closeable[] closes;
    E top;

    public StreamManager(E top, Closeable... nat)
    {
        closes = nat;
        this.top = top;
    }

    public E get()
    {
        return top;
    }

    public void close()
    {
        try {
            for(Closeable ca : closes) {
                if(ca != null) {
                    ca.close();
                }
            }
        }
        catch(IOException e) {
            Platform.error("IO closing failed!");
        }
    }

}
