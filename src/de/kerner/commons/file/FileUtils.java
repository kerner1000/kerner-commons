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

import com.thoughtworks.xstream.XStream;

public class FileUtils {

	public static final int DEFAULT_BUFFER = 1024;

	public static final File WORKING_DIR = new File(System
			.getProperty("user.dir"));
	
	public final static String NEW_LINE = System.getProperty("line.separator"); 

	public static long readerToWriter(Reader reader, Writer writer)
			throws IOException {
		return readerToWriter(reader, writer, DEFAULT_BUFFER);
	}

	public static long readerToWriter(Reader reader, Writer writer, int buffer)
			throws IOException {
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

	public static long inputStreamToOutputStream(InputStream in,
			OutputStream out) throws IOException {
		return inputStreamToOutputStream(in, out, DEFAULT_BUFFER);
	}

	public static long inputStreamToOutputStream(InputStream in,
			OutputStream out, int buffer) throws IOException {
		byte[] byteBuffer = new byte[buffer];
		long count = 0;
		int n = 0;
		while ((n = in.read(byteBuffer)) != -1) {
			out.write(byteBuffer, 0, n);
			count += n;
		}
		return count;
	}

	public static long inputStreamToWriter(InputStream in, Writer writer)
			throws IOException {
		InputStreamReader inr = new InputStreamReader(in);
		return readerToWriter(inr, writer);
	}

	public static long outputStreamToReader(OutputStream out, Reader reader)
			throws IOException {
		OutputStreamWriter outw = new OutputStreamWriter(out);
		return readerToWriter(reader, outw);
	}

	public static void objectToFile(Serializable s, File file)
			throws IOException {
		if (s == null || file == null)
			throw new NullPointerException(s + " + " + file
					+ " must not be null");
		OutputStream fos = new FileOutputStream(file);
		ObjectOutputStream outStream = new ObjectOutputStream(fos);
		outStream.writeObject(s);
		outStream.close();
		fos.close();
	}

	public static <V> V fileToObject(Class<V> c, File file) throws IOException,
			ClassNotFoundException {
		if (c == null || file == null)
			throw new NullPointerException(c + " + " + file
					+ " must not be null");
		InputStream fis = new FileInputStream(file);
		ObjectInputStream inStream = new ObjectInputStream(fis);
		V v = c.cast(inStream.readObject());
		inStream.close();
		fis.close();
		return v;
	}

	public static void objectToXML(Object o, File file) throws IOException {
		if(o == null || file == null)
			throw new NullPointerException();
		XStream xstream = new XStream();
		String xml = xstream.toXML(o);
		new LazyStringWriter(xml).toFile(file);
	}

	public static <V> V XMLToObject(Class<V> c, File file) throws IOException {
		XStream xstream = new XStream();
		return c.cast(xstream.fromXML(new LazyStringReader(file).getString()));
	}

	public static void main(String[] args) {
		File file = new File("/home/pcb/kerner/Desktop", "out.xml");
		try {
			objectToXML(new String("hans ist toll"), file);
			String hans = XMLToObject(String.class, file);
			System.out.println(hans);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

}
