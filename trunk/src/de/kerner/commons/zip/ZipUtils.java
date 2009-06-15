package de.kerner.commons.zip;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import de.kerner.commons.file.FileUtils;

public class ZipUtils {

	private final static Logger LOGGER = Logger.getLogger(ZipUtils.class);

	private ZipUtils() {
	}

	public static void extractToDir(final File archive, final File destDir)
			throws ZipException, IOException {
		extractToDir(archive, destDir, 0);
	}

	public static void extractToDir(final File archive, final File destDir,
			final int bufferSize) throws ZipException, IOException {
		if (archive == null || destDir == null)
			throw new NullPointerException("archive" + archive
					+ ", destinationDir=" + destDir);
		LOGGER.debug("extracting archive=" + archive + " to " + destDir);
		if (!destDir.exists()) {
			destDir.mkdir();
		}

		final ZipFile zipFile = new ZipFile(archive);
		final Enumeration<? extends ZipEntry> entries = zipFile.entries();

		while (entries.hasMoreElements()) {
			final ZipEntry entry = entries.nextElement();
			final String entryFileName = entry.getName();
			final File dir = getFullPathForEntryName(entryFileName, destDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			if (!entry.isDirectory()) {
				LOGGER.debug(entryFileName);
				final File file = new File(destDir, entryFileName);
				final FileWriter writer = new FileWriter(file);
				final InputStream inStream = zipFile.getInputStream(entry);
				FileUtils.inputStreamToWriter(inStream, writer, bufferSize);
				inStream.close();
				writer.close();
			}
		}
	}

	public static void createZip(final File dir, final File zipFile)
			throws IOException {
		final ZipCreator creator = new ZipCreator(zipFile);
		create(dir, zipFile, creator);
	}

	public static void createRunnableJarFromDirectory(final File dir,
			final File jarFile, final String mainClassName) throws IOException {
		final JarCreator creator = new JarCreator(jarFile, mainClassName);
		create(dir, jarFile, creator);
	}
	
	public static Manifest getManifestFromJar(final File archive) throws IOException{
		final JarFile jarFile = new JarFile(archive);
		return jarFile.getManifest();
	}
	
	private static void create(final File dir, final File zipFile,
			final AbstractArchiveCreator creator) throws IOException {
		if (dir == null || zipFile == null)
			throw new NullPointerException("dir=" + dir + ", ArchiveFile="
					+ zipFile);
		if (!dir.exists() || !dir.canRead())
			throw new IOException("cannot read " + dir);
		LOGGER.debug("creating archive=" + zipFile + " from " + dir);
		creator.create(dir);
	}
	
	public static final void main(String[] args) {
		PropertyConfigurator
				.configure("/home/pcb/kerner/Dropbox/log.properties");
		final File file1 = new File(
				"/home/pcb/kerner/Desktop/ringversuch-v.3.1.1.jar");
		final File file2 = new File(
				"/home/pcb/kerner/Desktop/ringversuchExtract/");
		final File file3 = new File(
				"/home/pcb/kerner/Desktop/ringversuchPacked.jar/");
		final File file4 = new File("/home/pcb/kerner/Desktop/pipelinetest/plugins/de.mpg.mpiz.koeln.kerner.conrad_0.0.1.jar");
		final File file5 = new File("/home/pcb/kerner/Desktop/tmpDir2");
		final File file6= new File("/home/pcb/kerner/Desktop/tmpDir2.jar");
//		try {
			//extractToDir(file4, file5);
			// createZip(file2, file3);
//			createRunnableJarFromBundle(file4, file5);
//		} catch (ZipException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private static File getFullPathForEntryName(final String entryName,
			final File destDir) {
		final int lastIndex = entryName.lastIndexOf('/');
		// String entryFileName = entryName.substring(lastIndex + 1);
		final String internalPathToEntry = entryName
				.substring(0, lastIndex + 1);
		final File file = new File(destDir, internalPathToEntry);
		// LOGGER.debug("destinationDir="+destDir + FileUtils.NEW_LINE +
		// "entryName="+entryName + FileUtils.NEW_LINE + "full path="+file);
		return file;
	}
}
