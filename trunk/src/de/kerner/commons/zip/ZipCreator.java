package de.kerner.commons.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import de.kerner.commons.file.FileUtils;

class ZipCreator extends AbstractArchiveCreator {
	
	protected final ZipOutputStream out;

//	private final static Logger LOGGER = Logger.getLogger(ZipCreator.class);
	
	ZipCreator(File zipFile) throws FileNotFoundException {
		super(zipFile);
		out = new ZipOutputStream(
 				new FileOutputStream(zipFile));
	}

	@Override
	public void handleFile(File file) throws IOException {
		final FileInputStream fi = new FileInputStream(file);
		final String entryName = getEntryName(file, zipFile);
		final ZipEntry entry = new ZipEntry(entryName);
//		LOGGER.debug(entryName);
		out.putNextEntry(entry);
		FileUtils.inputStreamToOutputStream(fi, out);
		fi.close();
	}
	
	@Override
	protected void lastAction(boolean wasCancelled) throws IOException {
		super.lastAction(wasCancelled);
		out.close();
	}

}
