package de.kerner.commons.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

public class LazyStringWriter {

	private final String string;
	
	public LazyStringWriter(String string){
		this.string = string;
	}
	
	public long toFile(File file) throws IOException{
		StringReader reader = new StringReader(string);
		FileWriter writer = new FileWriter(file);
		return FileUtils.readerToWriter(reader, writer);
	}
}
