package de.kerner.commons.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;

import de.kerner.commons.io.IOUtils;

/**
 * Simple helper class to read text files
 * @author Alexander Kerner
 *
 */
public class LazyStringReader {

	private final InputStream in;
	private final OutputStream defaultOutputStream = new ByteArrayOutputStream();

	/**
	 * 
	 * @param file Text file, that is read
	 * @throws IOException
	 */
	public LazyStringReader(File file) throws IOException {
		this.in = FileUtils.getInputStreamFromFile(file);
	}

	/**
	 * 
	 * @param fileName Name of file, that is read
	 * @throws IOException
	 */
	public LazyStringReader(String fileName) throws IOException {
		this.in = FileUtils.getInputStreamFromFile(new File(fileName));
	}

	/**
	 * 
	 * @param stream InputStream of file, that is read
	 */
	public LazyStringReader(InputStream stream) {
		this.in = stream;
	}

	/**
	 * 
	 * @return String representation of file content
	 * @throws IOException
	 */
	public String getString() throws IOException {
		final InputStreamReader reader = new InputStreamReader(in);
		final BufferedReader br = new BufferedReader(reader);
		final StringWriter sw = new StringWriter();
		final BufferedWriter bw = new BufferedWriter(sw);
		IOUtils.readerToWriter(br, bw);
		return sw.toString();
	}

	public OutputStream getStream(OutputStream stream) throws IOException {
		IOUtils.inputStreamToOutputStream(in, stream);
		return stream;
	}

	public OutputStream getStream() throws IOException {
		return getStream(defaultOutputStream);
	}
	
	public static void main(String[] args){
		try {
			System.out.println(new LazyStringReader("/home/proj/kerner/workspace/diplomarbeit/diplomarbeit.idx").getString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
