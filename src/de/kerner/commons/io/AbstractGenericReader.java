/**
 * 
 */
package de.kerner.commons.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import de.kerner.commons.file.FileUtils;

/**
 * <p>
 * TODO description
 * </p>
 * @author Alexander Kerner
 *
 */
public abstract class AbstractGenericReader implements GenericReader {

	// Implement //

	public void read(File file) throws IOException {
		if (FileUtils.fileCheck(file, false))
			read(FileUtils.getInputStreamFromFile(file));
		throw new IOException("cannot access file \"" + file + "\"");
	}

	public void read(InputStream stream) throws IOException {
		if (stream == null)
			throw new NullPointerException();
		read(IOUtils.inputStreamToReader(stream));
	}

}
