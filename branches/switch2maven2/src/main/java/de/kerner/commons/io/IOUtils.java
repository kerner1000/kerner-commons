package de.kerner.commons.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

/**
 * <p>
 * Utility class for commonly used Input/ Output operations.
 * </p>
 * 
 * @author Alexander Kerner
 * @lastVisit 2009-12-14
 * 
 */
public class IOUtils {

	public static final int DEFAULT_BUFFER = 1024;

	private IOUtils() {

	}

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
	 * @return number of bytes read/ written.
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
		if (buffer < 1)
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
	 * @param buffer
	 *            the number of bytes to buffer reading.
	 * @return number of bytes read/written.
	 * @throws IOException
	 */
	public static long inputStreamToOutputStream(final InputStream in,
			final OutputStream out, int buffer) throws IOException {
		if (buffer < 1)
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
	 * Copies the content of an <code>OutputStream</code> to a
	 * <code>Reader</code>. Will flush the <code>OutputStream</code>, but won't
	 * close <code>Reader</code> or <code>OutputStream</code>.
	 * 
	 * @param out
	 *            <code>OutputStream</code> from which data is read.
	 * @param reader
	 *            <code>Reader</code> to which data is written.
	 * @return number of bytes read/written.
	 * @throws IOException
	 */
	public static long outputStreamToReader(OutputStream out, Reader reader)
			throws IOException {
		OutputStreamWriter outw = new OutputStreamWriter(out);
		return readerToWriter(reader, outw);
	}

	/**
	 * Copies the content of an <code>OutputStream</code> to a
	 * <code>Writer</code>. Will flush the <code>OutputStream</code>, but won't
	 * close <code>Writer</code> or <code>OutputStream</code>.
	 * 
	 * @param out
	 *            <code>OutputStream</code> from which data is read.
	 * @return a new <code>Writer</code>
	 * @throws IOException
	 */
	public static Writer outputStreamToWriter(OutputStream out) {
		return new OutputStreamWriter(out);
	}

	/**
	 * Copies the content of an <code>InputStream</code> to a
	 * <code>Reader</code>. Will flush the <code>InputStream</code>, but won't
	 * close <code>Reader</code> or <code>InputStream</code>.
	 * 
	 * @param in
	 *            <code>InputStream</code> from which data is read.
	 * @return a new <code>Reader</code>
	 * @throws IOException
	 */
	public static Reader inputStreamToReader(InputStream in) {
		return new InputStreamReader(in);
	}

	public static <V> V deepCopy(Class<V> c, Serializable s)
			throws IOException, ClassNotFoundException {
		if (c == null || s == null)
			throw new NullPointerException();
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		new ObjectOutputStream(bs).writeObject(s);
		ByteArrayInputStream bi = new ByteArrayInputStream(bs.toByteArray());
		V v = c.cast(new ObjectInputStream(bi).readObject());
		bs.close();
		bi.close();
		return v;
	}

	public static void closeProperly(Writer writer) {
		if (writer != null)try{
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}

	public static void closeProperly(Reader reader) {
		if (reader != null)try{
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
