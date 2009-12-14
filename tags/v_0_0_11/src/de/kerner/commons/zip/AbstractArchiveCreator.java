package de.kerner.commons.zip;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import de.kerner.commons.file.AbstractDirectoryWalker;

abstract class AbstractArchiveCreator extends AbstractDirectoryWalker {
	
	protected final File zipFile;
	
	AbstractArchiveCreator(final File zipFile) {
		this.zipFile = zipFile;
	}
	
	@Override
	public void handleDir(File file) throws IOException {
		// do nothing
	}

	public void create(File dir) throws IOException {
		super.walk(dir);
	}
	
	protected String getEntryName(final File file, final File archiveFile) {
		final URI relativePath = archiveFile.getParentFile().toURI()
				.relativize(file.toURI());
		final int firstIndex = relativePath.toString().indexOf('/');
		final String result = relativePath.toString().substring(firstIndex + 1);
		return result;
	}
}

