package de.kerner.commons.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;

public class LazyStringReader {

	private final InputStream in;
	private final OutputStream defaultOutputStream= new ByteArrayOutputStream(); 

	public LazyStringReader(File file) throws IOException {
		this.in = getInputStreamFromFile(file);
	}

	public LazyStringReader(String fileName) throws IOException {
		this.in = getInputStreamFromFile(new File(fileName));
	}

	public LazyStringReader(InputStream stream) {
		this.in = stream;
	}
	
	public String getString() throws IOException {
		InputStreamReader reader = new InputStreamReader(in);
		StringWriter sw = new StringWriter();
		long charactersRead = Utils.readerToWriter(reader, sw);
		return sw.toString();
	}

	public OutputStream getStream(OutputStream streamImpl) throws IOException {
		long bytesTransferred = Utils.inputStreamToOutputStream(in, streamImpl);
		return streamImpl;
	}

	public OutputStream getStream() throws IOException {
		return getStream(defaultOutputStream);
	}

	private InputStream getInputStreamFromFile(File file) throws IOException {
		readableFileCheck(file);
		return new FileInputStream(file);
	}

	private void readableFileCheck(File file) throws IOException {
		if (!file.exists() || !file.isFile() || !file.canRead())
			throw new IOException("Cannot read file " + file);
	}

}
