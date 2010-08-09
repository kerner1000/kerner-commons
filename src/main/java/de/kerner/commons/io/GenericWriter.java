package de.kerner.commons.io;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * <p>
 * An implementation of {@code GenericWriter} can be written to disk/file, to a
 * {@code Writer} or to an {@code OutputStream}.
 * </p>
 * 
 * @author Alexander Kerner
 * @see File
 * @see Writer
 * @see OutputStream
 * 
 */
public interface GenericWriter {

	void write(File file) throws IOException;

	void write(Writer writer) throws IOException;

	void write(OutputStream stream) throws IOException;

}
