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
