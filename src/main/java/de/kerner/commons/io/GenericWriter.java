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
