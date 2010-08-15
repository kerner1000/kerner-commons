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
