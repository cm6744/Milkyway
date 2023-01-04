package cm.milkyway.lang.io;

import cm.milkyway.lang.Platform;
import cm.milkyway.lang.container.list.List;
import cm.milkyway.lang.text.Data;

import java.io.*;
import java.nio.charset.StandardCharsets;

public abstract class Access
{

    String path;

    public Access(String absolution)
    {
        path = absolution;
    }

    public String path()
    {
        return path;
    }

    public boolean isExist()
    {
        return new File(path).exists();
    }

    public void write(List<String> list)
    {
        String path = path();
        try {
            FileWriter fr = BasePath.writer(path);
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

    public List<String> read()
    {
        List<String> texts = new List<>();
        String temp;
        String path = path();

        InputStream is;

        try {
            is = BasePath.reader(path);
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

    public String readPass()
    {
        return Data.listToOne(read());
    }

}
