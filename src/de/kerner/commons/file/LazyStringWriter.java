package de.kerner.commons.file;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;

public class LazyStringWriter {
	
	private final OutputStream out;
	
	public LazyStringWriter(OutputStream out){
		this.out = out;
	}
	
	

}
