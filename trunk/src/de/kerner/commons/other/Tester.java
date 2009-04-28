package de.kerner.commons.other;

import java.io.IOException;

import de.kerner.commons.file.LazyStringReader;

public class Tester {

	public static void main(String[] args) {
		try{
		ProcessBuilder builder = new ProcessBuilder("blastall");
		Process p = builder.start();
		LazyStringReader processOut = new LazyStringReader(p.getInputStream());
		LazyStringReader processErr = new LazyStringReader(p.getErrorStream());
		
		System.out.println(processOut.getString());
		System.err.println(processErr.getString());
		} catch(IOException e){
			e.printStackTrace();
		}

	}

}
