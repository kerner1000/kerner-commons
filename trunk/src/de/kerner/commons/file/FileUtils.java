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
		return readerToWriter(reader, writer, 0);
	}

	public static long readerToWriter(final Reader reader, final Writer writer,
			int buffer) throws IOException {
		if (buffer == 0)
			buffer = DEFAULT_BUFFER;
		final char[] charBuffer = new char[buffer];
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

	public static long inputStreamToOutputStream(final InputStream in,
			final OutputStream out, int buffer) throws IOException {
		if (buffer == 0)
			buffer = DEFAULT_BUFFER;
		final byte[] byteBuffer = new byte[buffer];
		long count = 0;
		int n = 0;
		while ((n = in.read(byteBuffer)) != -1) {
			out.write(byteBuffer, 0, n);
			count += n;
		}
		out.flush();
		return count;
	}

	public static long inputStreamToWriter(InputStream in, Writer writer)
			throws IOException {
		InputStreamReader inr = new InputStreamReader(in);
		return readerToWriter(inr, writer);
	}

	public static long inputStreamToWriter(final InputStream in,
			final Writer writer, final int buffer) throws IOException {
		InputStreamReader inr = new InputStreamReader(in);
		return readerToWriter(inr, writer, buffer);
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
		if (o == null || file == null)
			throw new NullPointerException();
		XStream xstream = new XStream();
		String xml = xstream.toXML(o);
		new LazyStringWriter(xml).toFile(file);
	}

	public static <V> V XMLToObject(Class<V> c, File file) throws IOException {
		XStream xstream = new XStream();
		return c.cast(xstream.fromXML(new LazyStringReader(file).getString()));
	}

	public static boolean fileCheck(File file, boolean createIfAbsend) {
		if (createIfAbsend) {
			if (file.exists())
				return (file.canRead() && file.isFile() && file.length() != 0);
			else {
				try {
					final boolean b = file.createNewFile();
					return b;
				} catch (IOException e) {
					return false;
				}
			}
		} else {
			return (file.exists() && file.canRead() && file.isFile() && file
					.length() != 0);
		}
	}

	public static boolean dirCheck(File dir, boolean createIfAbsend) {
		if (createIfAbsend) {
			if (dir.exists())
				return (dir.canRead() && dir.isDirectory() && dir.length() != 0);
			else {
				final boolean b = dir.mkdirs();
				return b;
			}
		} else {
			return (dir.exists() && dir.canRead() && dir.isDirectory() && dir
					.length() != 0);
		}
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

	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		// Get the size of the file
		long length = file.length();
		// You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			throw new IOException("File too large");
		}
		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];
		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		// Close the input stream and return bytes
		is.close();
		return bytes;
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
