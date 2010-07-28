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
 * @see GenericReader
 * @author Alexander Kerner
 *
 */
public abstract class AbstractGenericReader<T> implements GenericReader<T> {

	// Implement //

	public T read(File file) throws IOException {
		if (FileUtils.fileCheck(file, false))
			return read(FileUtils.getInputStreamFromFile(file));
		else
		throw new IOException("cannot access file \"" + file + "\"");
	}

	public T read(InputStream stream) throws IOException {
		if (stream == null)
			throw new NullPointerException();
		return read(IOUtils.inputStreamToReader(stream));
	}

}
