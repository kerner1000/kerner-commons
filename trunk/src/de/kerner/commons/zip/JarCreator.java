package de.kerner.commons.zip;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

import de.kerner.commons.file.FileUtils;

class JarCreator extends AbstractArchiveCreator {
	
	private class Filter implements FileFilter {
		public boolean accept(File file) {
			return (file.isFile() && (file.getName().endsWith(".class") || file
					.getName().endsWith(".jar")));
		}
	}

	private final static Logger LOGGER = Logger
	.getLogger(JarCreator.class);
	protected final JarOutputStream out;
	
	JarCreator(File zipFile, String mainClassName) throws FileNotFoundException, IOException {
		super(zipFile);
		out = new JarOutputStream(
 				new FileOutputStream(zipFile), getManifest(mainClassName));
		super.addFilter(new Filter());
	}

	@Override
	public void handleFile(File file) throws IOException {
		final FileInputStream fi = new FileInputStream(file);
		final String entryName = getEntryName(file, zipFile);
		final ZipEntry entry = new ZipEntry(entryName);
		LOGGER.debug(entryName);
		out.putNextEntry(entry);
		FileUtils.inputStreamToOutputStream(fi, out);
		fi.close();
	}
	
	@Override
	protected void lastAction(boolean wasCancelled) throws IOException {
		super.lastAction(wasCancelled);
		out.close();
	}
	
	private Manifest getManifest(String mainClassName) {
		final Manifest manifest = new Manifest();
		Attributes mainAttributes = manifest.getMainAttributes();
 		mainAttributes.put(Attributes.Name.MAIN_CLASS, mainClassName);
 		mainAttributes.put(Attributes.Name.MANIFEST_VERSION, "1.0");
 		mainAttributes.put(Attributes.Name.CLASS_PATH, ".");
		return manifest;
	}

}
