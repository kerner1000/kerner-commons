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
