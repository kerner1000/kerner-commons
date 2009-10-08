package de.kerner.commons.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	/**
	 * 
	 * Copies the content of a <code>Reader</code> to a <code>Writer</code>.
	 * Will flush the <code>Writer</code>, but won't close <code>Reader</code>
	 * or <code>Writer</code>.
	 * 
	 * @param reader
	 *            <code>Reader</code> from which data is read.
	 * @param writer
	 *            <code>Writer</code> to which data is written.
	 * @return number of bytes read/written.
	 * @throws IOException
	 */
	public static long readerToWriter(Reader reader, Writer writer)
			throws IOException {
		return readerToWriter(reader, writer, 0);
	}

	/**
	 * Copies the content of a <code>Reader</code> to a <code>Writer</code>.
	 * Will flush the <code>Writer</code>, but won't close <code>Reader</code>
	 * or <code>Writer</code>.
	 * 
	 * @param reader
	 *            <code>Reader</code> from which data is read.
	 * @param writer
	 *            <code>Writer</code> to which data is written.
	 * @param buffer
	 *            the number of bytes to buffer reading.
	 * @return number of bytes read/written.
	 * @throws IOException
	 */
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

	/**
	 * Copies the content of an <code>InputStream</code> to an
	 * <code>OutputStream</code>. Will flush the <code>OutputStream</code>, but
	 * won't close <code>InputStream</code> or <code>OutputStream</code>.
	 * 
	 * @param in
	 *            <code>InputStream</code> from which data is read.
	 * @param out
	 *            <code>OutputStream</code> to which data is written.
	 * @return number of bytes read/written.
	 * @throws IOException
	 */
	public static long inputStreamToOutputStream(InputStream in,
			OutputStream out) throws IOException {
		return inputStreamToOutputStream(in, out, DEFAULT_BUFFER);
	}

	/**
	 * Copies the content of an <code>InputStream</code> to an
	 * <code>OutputStream</code>. Will flush the <code>OutputStream</code>, but
	 * won't close <code>InputStream</code> or <code>OutputStream</code>.
	 * 
	 * @param in
	 *            <code>InputStream</code> from which data is read.
	 * @param out
	 *            <code>OutputStream</code> to which data is written.
	 * @param buffer
	 *            the number of bytes to buffer reading.
	 * @return number of bytes read/written.
	 * @throws IOException
	 */
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

	/**
	 * Copies the content of an <code>InputStream</code> to a
	 * <code>Writer</code>. Will flush the <code>Writer</code>, but won't close
	 * <code>InputStream</code> or <code>Writer</code>.
	 * 
	 * @param in
	 *            <code>InputStream</code> from which data is read.
	 * @param out
	 *            <code>Writer</code> to which data is written.
	 * @return number of bytes read/written.
	 * @throws IOException
	 */
	public static long inputStreamToWriter(InputStream in, Writer out)
			throws IOException {
		InputStreamReader inr = new InputStreamReader(in);
		return readerToWriter(inr, out);
	}

	/**
	 * Copies the content of an <code>InputStream</code> to a
	 * <code>Writer</code>. Will flush the <code>Writer</code>, but won't close
	 * <code>InputStream</code> or <code>Writer</code>.
	 * 
	 * @param in
	 *            <code>InputStream</code> from which data is read.
	 * @param out
	 *            <code>Writer</code> to which data is written.
	 * @param buffer
	 *            the number of bytes to buffer reading.
	 * @return number of bytes read/written.
	 * @throws IOException
	 */
	public static long inputStreamToWriter(final InputStream in,
			final Writer out, final int buffer) throws IOException {
		InputStreamReader inr = new InputStreamReader(in);
		return readerToWriter(inr, out, buffer);
	}

	public static long outputStreamToReader(OutputStream out, Reader reader)
			throws IOException {
		OutputStreamWriter outw = new OutputStreamWriter(out);
		return readerToWriter(reader, outw);
	}

	public static Writer outputStreamToWriter(OutputStream out) {
		return new OutputStreamWriter(out);
	}

	public static Reader inputStreamToReader(InputStream in) {
		return new InputStreamReader(in);
	}

	/**
	 * Reads a file an returns an <code>InputStream</code> from it.
	 * 
	 * @param file
	 *            <code>File</code> from which the <code>InputStream</code> is
	 *            created.
	 * @return the <code>InputStream</code>.
	 * @throws IOException
	 */
	public static InputStream getInputStreamFromFile(File file)
			throws IOException {
		// TODO: what about closing this stream?
		return new FileInputStream(file);
	}

	public static BufferedInputStream getBufferedInputStreamFromFile(File file)
			throws IOException {
		// TODO: what about closing this stream?
		return new BufferedInputStream(new FileInputStream(file));
	}

	public static OutputStream getOutputStreamForFile(File file)
			throws FileNotFoundException {
		// TODO: what about closing this stream?
		return new FileOutputStream(file);
	}

	public static BufferedOutputStream getBufferedOutputStreamForFile(File file)
			throws FileNotFoundException {
		// TODO: what about closing this stream?
		return new BufferedOutputStream(new FileOutputStream(file));
	}

	/**
	 * 
	 * <p>
	 * Given a {@code File} named "hans.txt". newName is "peter". Returning
	 * {@code String} will be "peter.txt".
	 * </p>
	 * 
	 * <p>
	 * Original name is "hans.txt.tex". newName is "peter". Returning {@code
	 * String} will be "peter.tex".
	 * </p>
	 * 
	 * <p>
	 * Original name is "hans.txt.tex". newName is "peter.txt". Returning
	 * {@code String} will be "peter.txt.tex".
	 * </p>
	 * 
	 * @param file
	 *            {@code File}, for which new name is wanted.
	 * @param newName
	 *            the new filename discarding extension.
	 * @return the new filename including extension.
	 */
	public static String getNewFileName(File file, String newName) {
		final String fileName = file.getName();
		final int posOfExt = fileName.lastIndexOf(".");
		if (posOfExt < 0) {
			return newName;
		}
		// final String rawNameOld = fileName.substring(0, posOfExt);
		// System.err.println("rawNameOld="+rawNameOld);
		final String ext = fileName.substring(posOfExt, fileName.length());
		// System.err.println("ext="+ext);
		return newName + ext;
	}

	public static String getRawFileName(File file) {
		final String fileName = file.getName();
		final int posOfExt = fileName.lastIndexOf(".");
		if (posOfExt < 0) {
			return fileName;
		}
		return fileName.substring(0, posOfExt);
	}

	/**
	 * Extended accessibility test, if a file is available for reading. <br>
	 * it consists of following tests:
	 * <p>
	 * {@code file.exists() && file.canRead() && file.isFile() && file
	 * .length()!= 0}
	 * </p>
	 * 
	 * @param file
	 *            file, that is checked.
	 * @param createIfAbsend
	 *            if {@code !file.exists()}, it will be created.
	 * @return true, if file is accessible, false otherwise.
	 */
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

	/**
	 * Extended accessibility test, if a directory is available for reading. <br>
	 * it consists of following tests:
	 * <p>
	 * {@code dir.exists() && dir.canRead() && dir.isDirectory() && dir.length()
	 * != 0}
	 * <p>
	 * 
	 * @param dir
	 *            directory, that is checked.
	 * @param createIfAbsend
	 *            if {@code !dir.exists()}, it will be created.
	 * @return true, if dir is accessible, false otherwise.
	 */
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

	public static void objectToXML(Object o, File file) throws IOException {
		if (o == null || file == null)
			throw new NullPointerException();
		XStream xstream = new XStream();
		String xml = xstream.toXML(o);
		new LazyStringWriter(xml).write(file);
	}

	public static <V> V XMLToObject(Class<V> c, File file) throws IOException {
		XStream xstream = new XStream();
		return c.cast(xstream.fromXML(new LazyStringReader(file).getString()));
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

	public static void main(String[] args) {
		File file = new File("hans.txt");
		System.out.println(getRawFileName(file));
	}

}
