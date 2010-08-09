/**
 * 
 */
package de.kerner.commons.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;


/**
 * <p>
 * Dummy implementation for {@code GenericWriter}.
 * </p>
 * 
 * @author Alexander Kerner
 * 
 */
public abstract class AbstractGenericWriter implements GenericWriter {

	// Implement //
	
	public void write(OutputStream stream) throws IOException {
		write(IOUtils.outputStreamToWriter(stream));
	}

	public void write(File file) throws IOException {
		write(new FileWriter(file));
	}
}
