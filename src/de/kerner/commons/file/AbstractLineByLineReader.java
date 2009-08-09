package de.kerner.commons.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class AbstractLineByLineReader {

	public long read(File file) throws IOException {
		long linesRead = 0;
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(file);
			final BufferedReader br = new BufferedReader(new InputStreamReader(
					fstream));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				linesRead++;
				handleLine(strLine);
			}
		} finally {
			fstream.close();
		}
		return linesRead;
	}

	public abstract void handleLine(String line);

}
