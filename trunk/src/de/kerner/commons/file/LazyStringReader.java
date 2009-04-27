package de.kerner.commons.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;

public class LazyStringReader {

	private final InputStream in;

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
		long charactersRead = Utils.ReaderToWriter(reader, sw);
		return sw.toString();
	}

	public OutputStream getStream(OutputStream streamImpl) throws IOException {
		long bytesTransferred = Utils.StreamToStream(in, streamImpl);
		return streamImpl;
	}

	public OutputStream toStream() throws IOException {
		return getStream(new ByteArrayOutputStream());
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
