package cm.milkywaygl.resource;

import cm.milkywaygl.Platform;
import cm.milkywaygl.util.container.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;

public class Resource
{

    public static String textCharset = "UTF-8";

    public static StreamManager<AudioInputStream> audioStm(String path)
    {
        BufferedInputStream stream;
        AudioInputStream aul;

        try {
            FileInputStream st1 = new FileInputStream(Path.jarFile(path));
            stream = new BufferedInputStream(st1);
            aul = AudioSystem.getAudioInputStream(stream);
            return new StreamManager<>(aul, st1);
        }
        catch(IOException | UnsupportedAudioFileException e) {
            Platform.throwExc(e);
        }
        return new StreamManager<>(null);
    }

    public static List<String> read(String path)
    {
        List<String> texts = new List<>();
        String temp;

        InputStream is;

        try {
            is = Path.readerJar(path);
            InputStreamReader isr = new InputStreamReader(is, textCharset);
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
            Platform.throwExc(e);
        }

        return new List<>();
    }

    public static void write(List<String> texts, String path)
    {
        try {
            FileWriter fr = Path.writerJar(path);
            BufferedWriter bw = new BufferedWriter(fr);

            for(int i = 0; i < texts.size(); i++) {
                bw.write(texts.get(i));
                bw.newLine();
            }

            bw.close();//close buffer first, or throw a IOException
            fr.close();
        }
        catch(IOException e) {
            Platform.throwExc(e);
        }
    }

}
