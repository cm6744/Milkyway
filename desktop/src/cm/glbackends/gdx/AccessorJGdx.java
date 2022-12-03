package cm.glbackends.gdx;

import cm.milkywaygl.Platform;
import cm.glbackends.BasePath;
import cm.milkywaygl.io.Access;
import cm.milkywaygl.io.Accessor;
import cm.milkywaytool.container.List;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class AccessorJGdx implements Accessor
{

    public Access access(String path)
    {
        return new AccessJGdx().set(path);
    }

    public void write(List<String> list, Access access)
    {
        String path = access.path();
        try {
            FileWriter fr = BasePath.writerJar(path);
            if(fr == null) {
                Platform.error("BasePath not found: " + path);
                return;
            }
            BufferedWriter bw = new BufferedWriter(fr);

            for(int i = 0; i < list.size(); i++) {
                bw.write(list.get(i));
                bw.newLine();
            }

            bw.close();//close buffer first, or throw a IOException
            fr.close();
        }
        catch(IOException e) {
            Platform.error("BasePath not found: " + path);
        }
    }

    public List<String> read(Access access)
    {
        List<String> texts = new List<>();
        String temp;
        String path = access.path();

        InputStream is;

        try {
            is = BasePath.readerJar(path);
            if(is == null) {
                Platform.error("BasePath not found: " + path);
                return new List<>();
            }
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);

            while((temp = br.readLine()) != null) {
                texts.add(temp);
            }

            br.close();//close buffer first, or throw a IOException
            is.close();
            isr.close();

            return texts;
        }
        catch(IOException e) {
            Platform.error("BasePath not found: " + path);
        }

        return new List<>();
    }

}
