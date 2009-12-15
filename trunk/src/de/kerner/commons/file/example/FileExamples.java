package de.kerner.commons.file.example;

import java.io.File;
import java.io.IOException;

import de.kerner.commons.file.FileUtils;

public class FileExamples {

	public static void main(String[] args) {
		
		final File inFile = new File("/home/pcb/kerner/Desktop/hans");
		
		try {
			// check wether file is binary or not
			System.out.println("File \"" + inFile.getName() + "\" is binary: " + FileUtils.isBinary(inFile));
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
