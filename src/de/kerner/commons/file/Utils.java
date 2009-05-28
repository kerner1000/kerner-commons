package de.kerner.commons.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

public class Utils {

    public static int DEFAULT_BUFFER = 1024;

    public static long readerToWriter(Reader reader, Writer writer) throws IOException {
        char[] buffer = new char[DEFAULT_BUFFER];
        long count = 0;
        int n = 0;
        while ((n = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, n);
            count += n;
        }
        writer.flush();
        return count;
    }

    public static long inputStreamToOutputStream(InputStream in, OutputStream out)
        throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER];
        long count = 0;
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public static boolean isBinary(File file) throws IOException {
        boolean isbin = false;
        java.io.InputStream in = null;
        in = new FileInputStream(file);
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        char[] cc = new char[255]; // do a peek
        r.read(cc, 0, 255);
        double probBin = 0;
        for (int i = 0; i < cc.length; i++) {
            int j = (int) cc[i];
            if (j < 32 || j > 127) {
                probBin++;
            }
        }
        in.close();
        double pb = probBin / 255;
        if (pb > 0.5) {
            isbin = true;
        }
        return isbin;

    }

    public static long inputStreamToWriter(InputStream in, Writer writer) throws IOException {
        InputStreamReader inr = new InputStreamReader(in);
        return readerToWriter(inr, writer);
    }

    public static long outputStreamToReader(OutputStream out, Reader reader) throws IOException {
        OutputStreamWriter outw = new OutputStreamWriter(out);
        return readerToWriter(reader, outw);
    }
    
    public static void objectToFile(Serializable s, File file) throws IOException{
    OutputStream fos = new FileOutputStream( file );
    ObjectOutputStream outStream = 
	    new ObjectOutputStream( fos );
	     outStream.writeObject( s );
	     outStream.flush();
    }
    
    public static <V> V fileToObject(Class<V> c, File file) throws IOException, ClassNotFoundException{
    	InputStream fis = new FileInputStream( file );
	    ObjectInputStream inStream = new ObjectInputStream( fis );
	    V v = c.cast(inStream.readObject());
	    return  v;
    }
}
