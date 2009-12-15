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

import de.kerner.commons.file.FileUtils;
import de.kerner.commons.io.IOUtils;
import de.kerner.commons.logging.Log;

/**
 * 
 * @author Alexander Kerner
 * 
 */
public class ZipUtils {

	private final static Log log = new Log(ZipUtils.class);

	private ZipUtils() {
	}

	/**
	 * 
	 * @param archive
	 *            Archive file to extract
	 * @param destDir
	 *            Destination directory in archive files are extracted
	 * @throws ZipException
	 * @throws IOException
	 */
	public static void extractToDir(final File archive, final File destDir)
			throws ZipException, IOException {
		extractToDir(archive, destDir, 0);
	}

	/**
	 * 
	 * @param archive
	 *            Archive file to extract
	 * @param destDir
	 *            Destination directory in archive files are extracted
	 * @param bufferSize
	 *            Buffer size to use while extracting.
	 * @throws ZipException
	 * @throws IOException
	 */
	public static void extractToDir(final File archive, final File destDir,
			final int bufferSize) throws ZipException, IOException {
		if (archive == null || destDir == null)
			throw new NullPointerException("archive" + archive
					+ ", destinationDir=" + destDir);
		log.debug("extracting archive=" + archive + " to " + destDir);
		if (!destDir.exists()) {
			if (destDir.mkdirs()) {
				// all good
			} else
				throw new IOException(
						"creating of destination dir (and parents) failed!");
		}

		final ZipFile zipFile = new ZipFile(archive);
		final Enumeration<? extends ZipEntry> entries = zipFile.entries();

		while (entries.hasMoreElements()) {
			final ZipEntry entry = entries.nextElement();
			final String entryFileName = entry.getName();
			final File dir = getFullPathForEntryName(entryFileName, destDir);
			if (!dir.exists()) {
				if (dir.mkdirs()) {
					// all good
				} else
					throw new IOException(
							"error while creating dir (and parents) \"" + dir
									+ "\"");
			}
			if (!entry.isDirectory()) {
				log.debug(entryFileName);
				InputStream inStream = null;
				FileWriter writer = null;
				try {
					final File file = new File(destDir, entryFileName);
					writer = new FileWriter(file);
					inStream = zipFile.getInputStream(entry);
					IOUtils.inputStreamToWriter(inStream, writer, bufferSize);
				} finally {
					if (inStream != null)
						inStream.close();
					if (writer != null)
						writer.close();
				}
			}
		}
	}

	@Deprecated // probably buggy
	public static void createZip(final File dir, final File zipFile)
			throws IOException {
		final ZipCreator creator = new ZipCreator(zipFile);
		create(dir, zipFile, creator);
	}

	@Deprecated // probably buggy
	public static void createRunnableJarFromDirectory(final File dir,
			final File jarFile, final String mainClassName) throws IOException {
		final JarCreator creator = new JarCreator(jarFile, mainClassName);
		create(dir, jarFile, creator);
	}

	public static Manifest getManifestFromJar(final File archive)
			throws IOException {
		final JarFile jarFile = new JarFile(archive);
		return jarFile.getManifest();
	}

	@Deprecated // probably buggy
	private static void create(final File dir, final File zipFile,
			final AbstractArchiveCreator creator) throws IOException {
		if (dir == null || zipFile == null)
			throw new NullPointerException("dir=" + dir + ", ArchiveFile="
					+ zipFile);
		if (!dir.exists() || !dir.canRead())
			throw new IOException("cannot read " + dir);
		log.debug("creating archive=" + zipFile + " from " + dir);
		creator.create(dir);
	}

	public static final void main(String[] args) {
		final File file1 = new File(
				"/home/pcb/kerner/Desktop/ringversuch-v.3.1.1.jar");
		final File file2 = new File(
				"/home/pcb/kerner/Desktop/ringversuchExtract/");
		final File file3 = new File(
				"/home/pcb/kerner/Desktop/ringversuchPacked.jar/");
		final File file4 = new File(
				"/home/pcb/kerner/Desktop/pipelinetest/plugins/de.mpg.mpiz.koeln.kerner.conrad_0.0.1.jar");
		final File file5 = new File("/home/pcb/kerner/Desktop/tmpDir2");
		final File file6 = new File("/home/pcb/kerner/Desktop/tmpDir2.jar");
		// try {
		// extractToDir(file4, file5);
		// createZip(file2, file3);
		// createRunnableJarFromBundle(file4, file5);
		// } catch (ZipException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }
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
