package de.kerner.commons.other.jarbuilder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

import de.kerner.commons.file.FileUtils;
import de.kerner.commons.file.LazyStringWriter;

public class JarBuilder {

	private final static String DEFAULT_MANIFEST_HEADER = "Manifest-Version: 1.0"
			+ FileUtils.NEW_LINE + "Class-Path: .";

	/**
	 * 
	 * @param clazz
	 *            Class, which we want to run in a separate jar-file.
	 * @param jarPath
	 *            Path + name of jar-file that will be written.
	 */
	public static void buildJar(Class<?> clazz, File jarPath) {
		try {
			System.out.println("building jar file for " + clazz);
			System.out.println("will be written to " + jarPath);
			final Package myPackage = clazz.getPackage();
			System.out.println(myPackage);
			final File pkgFile = getFullPath(myPackage, clazz);
			System.out.println("full path " + pkgFile);
			List<File> classes = null;
			if (pkgFile.isDirectory()) {
				System.out.println("file is directory");
				classes = getClassesFromDir(pkgFile);
			} else if (pkgFile.isFile()) {
				System.out.println("file is file");
				classes = getClassesFromJar(pkgFile);
			} else {
				System.out.println(pkgFile.getName());
				throw new RuntimeException("KANN nicht sein");
			}

			addClassesToJar(clazz, classes, jarPath);
			System.out.println("----------------------------");
			System.out.printf("Wrote: %s", jarPath);
			System.out.println();

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void addClassesToJar(final Class<?> clazz,
			final List<File> classes, final File jarPath) throws IOException {
		final FileOutputStream fos = new FileOutputStream(jarPath);
		final JarOutputStream os = new JarOutputStream(fos);
		for (File f : classes) {
			System.out.printf("Adding: %s", f);
			System.out.println();
			addFileToJar(f, f.getName(), os);
		}
		final File manifest = getManifest(clazz);
		System.out.printf("Adding manifest: %s", manifest.toString());
		System.out.println();
		addFileToJar(manifest, manifest.getParent() + "/" + manifest.getName(),
				os);
		os.close();
	}

	private static List<File> getClassesFromJar(final File pkgFile)
			throws IOException {
		final JarFile jarFile = new JarFile(pkgFile);
		final Enumeration<JarEntry> entries = jarFile.entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			System.out.println("content of jar:" + entry);
		}
		return null;
	}

	private static List<File> getClassesFromDir(final File pkgFile) {
		final File[] classFiles = pkgFile.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.getName().endsWith(".class");
			}
		});
		return Arrays.asList(classFiles);
	}

	/**
	 * 
	 * Add a file to a jar file. {@code OutPutStream} will be flushed after
	 * writing, but not closed.
	 * 
	 * @param file
	 *            File, that will be written to the {@code JarOutPutStream}
	 * @param jarEntryName
	 *            full path within the jar-file. Directory separator is "/"
	 * @param os
	 *            {@code JarOutPutStream}, to which is written
	 * @throws IOException
	 */
	private static void addFileToJar(final File file,
			final String jarEntryName, final JarOutputStream os)
			throws IOException {
		final JarEntry entry = new JarEntry(jarEntryName);
		os.putNextEntry(entry);
		final InputStream is = new BufferedInputStream(
				new FileInputStream(file));
		int aByte;
		while ((aByte = is.read()) != -1) {
			os.write(aByte);
		}
		os.flush();
	}

	private static File getManifest(final Class<?> clazz) throws IOException {
		final File dir = new File("META-INF");
		dir.mkdir();
		// final File file = File.createTempFile("MANIFEST", ".MF", dir);
		final File file = new File(dir, "MANIFEST.MF");
		file.createNewFile();
		new LazyStringWriter(DEFAULT_MANIFEST_HEADER + FileUtils.NEW_LINE
				+ "Main-Class: " + clazz.getName() + FileUtils.NEW_LINE)
				.toFile(file);
		file.deleteOnExit();
		return file;
	}

	/**
	 * 
	 * @param clazz
	 *            Class, from which we want to know from where it has been
	 *            loaded
	 * @return Either directory, where the .class file is stored, or the name of
	 *         the jar, from which class has been loaded
	 * @throws URISyntaxException
	 */
	private static File getLocationOfClass(final Class<?> clazz)
			throws URISyntaxException {
		final File result = new File(clazz.getProtectionDomain()
				.getCodeSource().getLocation().toURI().getPath());
		System.out.println("location of class file:" + result);
		return result;
	}

	private static File getFullPath(Package myPackage, Class<?> clazz)
			throws URISyntaxException {
		if (myPackage == null)
			return getLocationOfClass(clazz);
		return new File(getLocationOfClass(clazz), myPackage.getName().replace(
				'.', '/'));
	}

	private static void buildJarFromJar(final File inFile, File outFile)
			throws IOException {
		if (!inFile.isFile() || !inFile.canRead())
			throw new IOException("cannot read jarfile " + inFile);
		final JarFile jar = new JarFile(inFile);
		final FileOutputStream fos = new FileOutputStream(outFile);
		final JarOutputStream os = new JarOutputStream(fos);
		final Enumeration<JarEntry> entries = jar.entries();
		while (entries.hasMoreElements()) {
			final JarEntry entry = entries.nextElement();
			System.out.println("now entry " + entry);
			os.putNextEntry(entry);
			
			os.flush();
			System.out.println("added jar entry " + entry);
		}
		os.close();

	}

	public static void main(String[] args) {

		try {
			File outFile = new File("/home/pcb/kerner/Desktop/test.jar");
			// buildJarFromJar(new File("/home/pcb/kerner/Desktop/commons.jar"),
			// outFile);

			buildJarFromJar(new File(
					"/home/pcb/kerner/Desktop/ringversuch-v.3.1.1.jar"),
					outFile);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
