/**
 * 
 */
package de.kerner.commons.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

import de.kerner.commons.io.AbstractGenericWriter;

/**
 * <p>
 * Write a string-based file line by line.
 * </p>
 * <p>
 * A line is considered to be terminated by system dependend line separator.
 * </p>
 * <p>
 * {@code BufferedStringWriter} will use a {@link BufferedWriter} internally for buffered writing.
 * </p>
 * 
 * @author Alexander Kerner
 * @see BufferedWriter
 * 
 */
public abstract class BufferedStringWriter extends AbstractGenericWriter {
	
	// Implement //
	
	public void write(Writer writer) throws IOException {
		writer.write(provideLine());
	}
	
	public abstract String provideLine();
	
}
