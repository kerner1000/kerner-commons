package de.kerner.commons.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;

public class LazyStringWriter {

	private final String string;
	
	public LazyStringWriter(String string){
		this.string = string;
	}
	
	public long write(File file) throws IOException{
		final StringReader reader = new StringReader(string);
		final BufferedReader br = new BufferedReader(reader);
		final FileWriter writer = new FileWriter(file);
		final BufferedWriter bw = new BufferedWriter(writer);
		return FileUtils.readerToWriter(br, bw);
	}
	
	public long write(Writer writer) throws IOException{
		final StringReader reader = new StringReader(string);
		final BufferedReader br = new BufferedReader(reader);
		final BufferedWriter bw = new BufferedWriter(writer);
		return FileUtils.readerToWriter(br, bw);
	}
	
	public long write(OutputStream stream) throws IOException{
		final StringReader reader = new StringReader(string);
		final BufferedReader br = new BufferedReader(reader);
		final OutputStreamWriter writer = new OutputStreamWriter(stream);
		final BufferedWriter bw = new BufferedWriter(writer);
		return FileUtils.readerToWriter(br, bw);
	}
}
