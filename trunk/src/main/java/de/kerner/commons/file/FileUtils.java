/**********************************************************************
Copyright (c) 2009-2010 Alexander Kerner. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 ***********************************************************************/

package de.kerner.commons.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import com.thoughtworks.xstream.XStream;

import de.kerner.commons.StringUtils;
import de.kerner.commons.io.IOUtils;

/**
 * <p>
 * Utility class for commonly used File operations.
 * </p>
 * 
 * @author Alexander Kerner
 * @lastVisit 2009-12-14
 * 
 */
public class FileUtils {

	public static final File WORKING_DIR = new File(System
			.getProperty("user.dir"));
	
	public static final File USER_DIR = new File(System
			.getProperty("user.home"));

	// TODO move to commons.io
	public final static String NEW_LINE = System.getProperty("line.separator");

	/**
	 * <p>
	 * Copies one file to another.
	 * </p>
	 * 
	 * @param source
	 *            file that is copied
	 * @param dest
	 *            destination file to which is copied
	 * @return number of bytes that where copied
	 * @throws IOException
	 */
	public static long copyFile(File source, File dest) throws IOException {

		if (source.isDirectory() || dest.isDirectory())
			throw new IOException("wont copy directories");
		InputStream i = null;
		try {
			i = getInputStreamFromFile(source);

			// create parent directories
			if (dest.getParentFile().mkdirs()) {
				// all good
			} else
				throw new IOException("\"" + dest + "\" cannot be created");
			return writeStreamToFile(i, dest);

		} finally {
			if (i != null)
				i.close();
		}
	}

	/**
	 * 
	 * Copies the content of a <code>InputStream</code> to a <code>File</code>.
	 * 
	 * @param stream
	 *            <code>InputStream</code> from which data is read.
	 * @param file
	 *            <code>File</code> to which data is written.
	 * @return number of bytes written.
	 * @throws IOException
	 */
	public static long writeStreamToFile(InputStream stream, File file)
			throws IOException {
		FileOutputStream f = null;
		try {
			f = new FileOutputStream(file);
			final long result = IOUtils.inputStreamToOutputStream(stream, f);
			return result;
		} finally {
			stream.close();
			if (f != null)
				f.close();
		}
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
		return new FileInputStream(file);
	}

	/**
	 * Reads a file an returns an <code>BufferedInputStream</code> from it.
	 * 
	 * @param file
	 *            <code>File</code> from which the
	 *            <code>BufferedInputStream</code> is created.
	 * @return the <code>BufferedInputStream</code>.
	 * @throws IOException
	 */
	public static BufferedInputStream getBufferedInputStreamFromFile(File file)
			throws IOException {
		return new BufferedInputStream(new FileInputStream(file));
	}

	/**
	 * Reads a file an returns an <code>OutputStream</code> from it.
	 * 
	 * @param file
	 *            <code>File</code> from which the <code>OutputStream</code> is
	 *            created.
	 * @return the <code>OutputStream</code>.
	 * @throws IOException
	 */
	public static OutputStream getOutputStreamForFile(File file)
			throws FileNotFoundException {
		return new FileOutputStream(file);
	}

	/**
	 * Reads a file an returns an <code>BufferedOutputStream</code> from it.
	 * 
	 * @param file
	 *            <code>File</code> from which the
	 *            <code>BufferedOutputStream</code> is created.
	 * @return the <code>BufferedOutputStream</code>.
	 * @throws IOException
	 */
	public static BufferedOutputStream getBufferedOutputStreamForFile(File file)
			throws FileNotFoundException {
		return new BufferedOutputStream(new FileOutputStream(file));
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
			synchronized (file) {
				if (file.exists())
					return (file.canRead() && file.isFile() && file.length() != 0);
				else {
					try {
						return file.createNewFile();
					} catch (Exception e) {
						return false;
					}
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

	/**
	 * <p>
	 * Write an {@code Object} that implements {@link Serializable} to a file.
	 * </p>
	 * <p>
	 * Serialisation is buffered: Internally a {@link BufferedOutputStream} is
	 * used.
	 * </p>
	 * 
	 * @see Serializable
	 * @param s
	 *            {@code Object} that will be serialized
	 * @param file
	 *            file to write to
	 * @throws IOException
	 *             if anything goes wrong
	 */
	public static void objectToFile(Serializable s, File file)
			throws IOException {
		if (s == null || file == null)
			throw new NullPointerException();
		objectToStream(s, new FileOutputStream(file));
	}

	/**
	 * <p>
	 * Write an {@code Object} that implements {@link Serializable} to an
	 * {@link OutputStream}.
	 * </p>
	 * <p>
	 * Serialisation is buffered: Internally a {@link BufferedOutputStream} is
	 * used.
	 * </p>
	 * 
	 * @see Serializable
	 * @param s
	 *            {@code Object} that will be serialized
	 * @param sstream
	 *            stream to write to
	 * @throws IOException
	 *             if anything goes wrong
	 */
	public static void objectToStream(Serializable s, OutputStream stream)
			throws IOException {
		if (s == null || stream == null)
			throw new NullPointerException();
		ObjectOutputStream outStream = null;
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(stream);
			outStream = new ObjectOutputStream(bos);
			outStream.writeObject(s);
		} finally {
			if (outStream != null)
				outStream.close();
		}
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
		new BufferedStringWriter(xml).write(file);
	}

	public static <V> V XMLToObject(Class<V> c, File file) throws IOException {
		XStream xstream = new XStream();
		return c.cast(xstream.fromXML(new BufferedStringWriter(file).toString()));
	}

	/**
	 * <p>
	 * Tries to check whether file content is binary or not.
	 * </p>
	 * 
	 * @param file
	 *            File which is tested.
	 * @return true, if this file is a binary file; else otherwise. Returning of
	 *         <code>false</code> is reliable, whereas a returning of
	 *         <code>false</code> might be incorrect.
	 * @throws IOException
	 */
	public static boolean isBinary(File file) throws IOException {
		InputStream in = null;
		try {
			in = getInputStreamFromFile(file);
			byte[] cc = new byte[IOUtils.DEFAULT_BUFFER]; // do a peek
			in.read(cc, 0, IOUtils.DEFAULT_BUFFER);
			for (int i = 0; i < cc.length; i++) {
				int j = (int) cc[i];
				if (j < 32 || j > 127) {
					System.out.println((char) j);
					return false;
				} else {
					// all good
				}
			}
		} finally {
			if (in != null)
				in.close();
		}
		return true;
	}
	
	public static String getFileExtension(File file){
		return getFileExtension(file.getAbsolutePath());
	}
	
	public static String getFileExtension(String string){
		return StringUtils.removeAllBeforeLastOccurence("\\.", string);
	}
	
	public static String getFileNameWithoutExtension(File file){
		return getFileExtension(file.getAbsolutePath());
	}
	
	public static String getFileNameWithoutExtension(String string){
		return StringUtils.removeAllAfterLastOccurence("\\.", string);
	}
	
	public static String appendToFileName(String string, String append){
		String name = getFileNameWithoutExtension(string);
		String ex = getFileExtension(string);
		name = name + append;
		name = name + "." + ex;
		return name;
	}

	public static void appendToFileName(File file, String append) throws IOException {
		if (fileCheck(file, false)) {
			String name = file.getAbsolutePath();
			String suffix = StringUtils.removeAllBeforeLastOccurence("\\.", name);
			name = StringUtils.removeAllAfterLastOccurence("\\.", name);
			name = name + append;
			File f = new File(name + "." + suffix);
//				System.err.println(f);
			if (file.renameTo(f)) {
				// all good
			} else
				throw new IOException("could not rename file \"" + file + "\"");
		} else
			throw new IOException("cannot access file \"" + file + "\"");
	}

}
