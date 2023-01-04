package cm.milkyway.lang.io;

import cm.milkyway.lang.Platform;
import cm.milkyway.lang.text.Data;

import java.io.*;

public class BasePath
{

    public static String jar(String path)
    {
        return jarFile(path).getPath();
    }

    public static File jarFile(String path)
    {
        return new File(jarFileBase(path).getPath());
    }

    public static File jarFileBase(String path)
    {
        String out = System.getProperty("basepath");
        if(Data.isEmpty(out)) {
            out = BasePath.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        }

        String par = new java.io.File(out).getParentFile().getPath();
        String end = (par + "/" + path + "/");

        return new java.io.File(end);
    }

    public static InputStream reader(String path)
    {
        try {
            return new FileInputStream(path);
        }
        catch(FileNotFoundException i) {
            Platform.error("BasePath not found: " + path);
        }
        return null;
    }

    public static FileWriter writer(String path)
    {
        try {
            return new FileWriter(path);
        }
        catch(IOException i) {
            Platform.error("BasePath not found: " + path);
        }
        return null;
    }

}
