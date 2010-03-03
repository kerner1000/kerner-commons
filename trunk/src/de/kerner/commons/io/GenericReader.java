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
 * @author Alexander Kerner
 * @see File
 * @see Reader
 * @see InputStream
 * 
 */
public interface GenericReader {
	
	public void read(File file) throws IOException;
	
	public void read(Reader reader) throws IOException;
	
	public void read(InputStream stream) throws IOException; 

}
