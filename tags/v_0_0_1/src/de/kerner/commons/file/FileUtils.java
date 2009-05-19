package de.kerner.commons.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class FileUtils {
	
	public static final int DEFAULT_BUFFER = 1024;
	
	public static final File WORKING_DIR = new File(System.getProperty("user.dir"));
	
	public static long readerToWriter(Reader reader, Writer writer) throws IOException{
		return readerToWriter(reader, writer, DEFAULT_BUFFER);
	}
	
	public static long readerToWriter(Reader reader, Writer writer, int buffer) throws IOException{
		char[] charBuffer = new char[buffer];
        long count = 0;
        int n = 0;
        while ((n = reader.read(charBuffer)) != -1) {
            writer.write(charBuffer, 0, n);
            count += n;
        }
        writer.flush();
        return count;
	}
	
	public static long inputStreamToOutputStream(InputStream in, OutputStream out) throws IOException{
		return inputStreamToOutputStream(in, out, DEFAULT_BUFFER);
	}
	
	public static long inputStreamToOutputStream(InputStream in, OutputStream out, int buffer) throws IOException{
		byte[] byteBuffer = new byte[buffer];
        long count = 0;
        int n = 0;
        while ((n = in.read(byteBuffer)) != -1) {
            out.write(byteBuffer, 0, n);
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
