/**********************************************************************
Copyright (c) 2009-2010 Alexander Kerner. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 ***********************************************************************/
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
