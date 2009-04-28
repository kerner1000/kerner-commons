package de.kerner.commons.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class Utils {
	
	public static int DEFAULT_BUFFER = 1024;
	
	public static long readerToWriter(Reader reader, Writer writer) throws IOException{
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
	
	public static long inputStreamToOutputStream(InputStream in, OutputStream out) throws IOException{
		byte[] buffer = new byte[DEFAULT_BUFFER];
        long count = 0;
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
            count += n;
        }
        return count;
	}
	
	public static long inputStreamToWriter(InputStream in, Writer writer) throws IOException{
		InputStreamReader inr = new InputStreamReader(in);
		return readerToWriter(inr, writer);
	}

	public static long outputStreamToReader(OutputStream out, Reader reader) throws IOException{
		OutputStreamWriter outw = new OutputStreamWriter(out);
		return readerToWriter(reader, outw);
	}
	
}
