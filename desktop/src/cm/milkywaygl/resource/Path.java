package cm.milkywaygl.resource;

import cm.milkywaygl.Platform;
import cm.milkywaygl.text.Data;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.*;

public class Path
{

    public static FileHandle _libJar(String path)
    {
        return Gdx.files.absolute(jar(path));
    }

    public static String jar(String path)
    {
        return jarFile(path).getPath();
    }

    public static java.io.File jarFile(String path)
    {
        return new java.io.File(jarFileBase(path).getPath());
    }

    public static java.io.File jarFileBase(String path)
    {
        String out = System.getProperty("basepath");
        if(Data.isEmpty(out)) {
            out = Path.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        }

        String par = new java.io.File(out).getParentFile().getPath();
        String end = (par + "/" + path + "/");

        return new java.io.File(end);
    }

    public static InputStream readerJar(String path)
    {
        try {
            return new FileInputStream(jarFile(path));
        }
        catch(FileNotFoundException i) {
            Platform.throwExc(i);
        }
        return null;
    }

    public static FileWriter writerJar(String path)
    {
        try {
            return new FileWriter(jarFile(path));
        }
        catch(IOException i) {
            Platform.throwExc(i);
        }
        return null;
    }

}
