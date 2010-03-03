/**
 * 
 */
package de.kerner.commons.io;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import de.kerner.commons.file.FileUtils;

/**
 * <p>
 * TODO description
 * </p>
 * @author Alexander Kerner
 *
 */
public abstract class AbstractGenericWriter implements GenericWriter {

	// Implement //
	
	public void write(File file) throws IOException {
		if(FileUtils.fileCheck(file, false))
			throw new IOException("cannot access file \"" + file + "\"");
		write(FileUtils.getOutputStreamForFile(file));
		}

	public void write(OutputStream stream) throws IOException {
		if(stream == null)
			throw new NullPointerException();
		write(IOUtils.outputStreamToWriter(stream));
	}

}
