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

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import de.kerner.commons.io.IOUtils;

public class LazyFileWriter {

	private final File file;

	public LazyFileWriter(File file) {
		this.file = file;
	}

	public void write(Collection<String> lines) throws IOException {
		if (lines == null || lines.isEmpty())
			throw new NullPointerException("lines null or empty");
		BufferedWriter bw = null;
		try {
			final OutputStream os = FileUtils.getOutputStreamForFile(file);
			bw = new BufferedWriter(IOUtils.outputStreamToWriter(os));
			for (String s : lines) {
				bw.append(s);
				bw.append(FileUtils.NEW_LINE);
			}
			bw.flush();
		} finally {
			if (bw != null)
				bw.close();
		}
	}

}
