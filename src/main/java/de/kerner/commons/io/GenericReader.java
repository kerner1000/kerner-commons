/**
 * 
 */
package de.kerner.commons.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * <p>
 * An implementation of {@code GenericReader} can be read from disk/file, from a
 * {@code Reader} or from an {@code InputStream}.
 * </p>
 * 
 * @param T type of returned object
 * @author Alexander Kerner
 * @see File
 * @see Reader
 * @see InputStream
 * 
 */
public interface GenericReader<T> {
	
	public T read(File file) throws IOException;
	
	public T read(Reader reader) throws IOException;
	
	public T read(InputStream stream) throws IOException; 

}
