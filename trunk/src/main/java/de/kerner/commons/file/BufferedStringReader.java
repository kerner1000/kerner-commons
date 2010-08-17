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
package de.kerner.commons.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import de.kerner.commons.io.AbstractGenericReader;

/**
 * <p>
 * Read a string-based file line by line.
 * </p>
 * <p>
 * A line is considered to be terminated by any one of a line feed ('\n'), a
 * carriage return ('\r'), or a carriage return followed immediately by a
 * linefeed.
 * </p>
 * <p>
 * {@code BufferedStringReader} will use a {@link BufferedReader} internally for
 * buffered reading.
 * </p>
 * 
 * @author Alexander Kerner
 * @see BufferedReader
 * 
 */
public abstract class BufferedStringReader extends AbstractGenericReader<Void> {

	// Implement //

	public Void read(Reader reader) throws IOException {
		if (reader == null)
			throw new NullPointerException();
		BufferedReader br = new BufferedReader(reader);
		try {
			String strLine;
			while ((strLine = br.readLine()) != null) {
				handleLine(strLine);
			}
		} finally {
			// cannot happen
			// if(stream != null)
			reader.close();
		}
		return null;
	}

	public abstract void handleLine(String line);

}
