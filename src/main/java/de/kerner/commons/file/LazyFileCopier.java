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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.kerner.commons.io.IOUtils;

public class LazyFileCopier {

	private final InputStream inStream;
	private final File outFile;

	public LazyFileCopier(File in, File out) throws IOException {
		if (!FileUtils.fileCheck(in, false))
			throw new FileNotFoundException("cannot read " + in);
		this.inStream = FileUtils.getInputStreamFromFile(in);
		this.outFile = out;
	}

	public LazyFileCopier(String inFilePath, String outFilePath)
			throws IOException {
		final File in = new File(inFilePath);
		if (!FileUtils.fileCheck(in, false))
			throw new FileNotFoundException("cannot read " + in);
		this.inStream = FileUtils.getInputStreamFromFile(in);
		this.outFile = new File(outFilePath);
	}

	public LazyFileCopier(File inFile, File outDir, String outFileName)
			throws IOException {
		if (!FileUtils.fileCheck(inFile, false))
			throw new FileNotFoundException("cannot read " + inFile);
		this.inStream = FileUtils.getInputStreamFromFile(inFile);
		this.outFile = new File(outDir, outFileName);
	}

	public long copy() throws IOException {
		final InputStreamReader reader = new InputStreamReader(inStream);
		final BufferedReader br = new BufferedReader(reader);
		final FileWriter writer = new FileWriter(outFile);
		final BufferedWriter bw = new BufferedWriter(writer);
		long r = 0;
		try {
			r = IOUtils.readerToWriter(br, bw);
		} finally {
			bw.flush();
			bw.close();
			br.close();
		}
		return r;
	}
}
