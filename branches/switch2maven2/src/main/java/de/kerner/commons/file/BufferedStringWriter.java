/**
 * 
 */
package de.kerner.commons.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;

import de.kerner.commons.io.AbstractGenericWriter;
import de.kerner.commons.io.IOUtils;

/**
 * <p>
 * Write a string-based file line by line.
 * </p>
 * <p>
 * System dependend line separator is used.
 * </p>
 * <p>
 * {@code BufferedStringWriter} will use a {@link BufferedWriter} internally for
 * buffered writing.
 * </p>
 * 
 * @author Alexander Kerner
 * @see BufferedWriter
 * 
 */
public class BufferedStringWriter extends AbstractGenericWriter {

	private final String string;

	public BufferedStringWriter(Object o) {
		// TODO performance issue !!
		this.string = o.toString();
	}

	// Implement //

	public void write(Writer writer) throws IOException {
		StringReader reader = null;
		BufferedReader br = null;
		try {
			reader = new StringReader(string);
			br = new BufferedReader(reader);
			BufferedWriter bw = new BufferedWriter(writer);
			IOUtils.readerToWriter(br, bw);
		} finally {
			IOUtils.closeProperly(writer);
			IOUtils.closeProperly(br);
			IOUtils.closeProperly(reader);
		}
	}
}
