package de.kerner.commons.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import de.kerner.commons.io.AbstractGenericReader;

/**
 * <p>
 * Read a string-based file line by line.
 * </p>
 * <p>
 * A line is considered to be terminated by any one of a line feed ('\n'), a
 * carriage return ('\r'), or a carriage return followed immediately by a
 * linefeed.
 * </p>
 * <p>
 * {@code BufferedStringReader} will use a {@link BufferedReader} internally for buffered reading.
 * </p>
 * 
 * @author Alexander Kerner
 * @see BufferedReader
 * 
 */
public abstract class BufferedStringReader extends AbstractGenericReader {

	// Implement //

	public void read(Reader reader) throws IOException {
		if (reader == null)
			throw new NullPointerException();
		BufferedReader br = new BufferedReader(reader);
		try {
			String strLine;
			while ((strLine = br.readLine()) != null) {
				try{
				handleLine(strLine);
				}catch(Exception e){
					throw new IOException(e.getLocalizedMessage());
				}
			}
		} finally {
			// cannot happen
			// if(stream != null)
			reader.close();
		}
	}

	public abstract void handleLine(String line) throws Exception;

}
