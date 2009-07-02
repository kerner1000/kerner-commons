package de.kerner.commons.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;

/* (@) LazyStringReader.java */

/* Copyright 2009 Alexander Kerner

 * Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License. */

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
		this.in = getInputStreamFromFile(file);
	}

	/**
	 * 
	 * @param fileName Name of file, that is read
	 * @throws IOException
	 */
	public LazyStringReader(String fileName) throws IOException {
		this.in = getInputStreamFromFile(new File(fileName));
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
		FileUtils.readerToWriter(br, bw);
		return sw.toString();
	}

	public OutputStream getStream(OutputStream stream) throws IOException {
		FileUtils.inputStreamToOutputStream(in, stream);
		return stream;
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
	
	public static void main(String[] args){
		try {
			System.out.println(new LazyStringReader("/home/proj/kerner/workspace/diplomarbeit/diplomarbeit.idx").getString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
