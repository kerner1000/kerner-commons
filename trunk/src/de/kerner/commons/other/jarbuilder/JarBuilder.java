package de.kerner.commons.other.jarbuilder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

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

			final String clazzName = clazz.getSimpleName();
			final Package myPackage = clazz.getPackage();
			final String myPackageName = myPackage.getName();
			final String myPackageFileName = myPackageName.replace('.', '/');
			System.out.println("package file name " + myPackageFileName);
			final File clazzFolder = getClassFolder(clazz);
			System.out.println("class folder " + clazzFolder);
			final File pkgFile = new File(getClassFolder(clazz),
					myPackageFileName);
			System.out.println("package file " + pkgFile);
			final File[] classFiles = pkgFile.listFiles(new FileFilter() {
				public boolean accept(File file) {
					return file.getName().endsWith(".class")
							|| file.getName().equalsIgnoreCase("MANIFEST.MF");
				}
			});

			final FileOutputStream fos = new FileOutputStream(jarPath);
			final JarOutputStream os = new JarOutputStream(fos);
			for (File f : classFiles) {
				final String name = myPackageFileName + '/' + f.getName();
				System.out.printf("Adding: %s", name);
				System.out.println();
				addFileToJar(f, name, os);
			}

			final File manifest = getManifest(clazz);
			System.out.printf("Adding manifest: %s", manifest.toString());
			addFileToJar(manifest, manifest.getParent() + "/"
					+ manifest.getName(), os);
			os.close();
			System.out.println("----------------------------");
			System.out.printf("Wrote: %s", jarPath);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 
	 * Add a file to a jar file.
	 * {@code OutPutStream} will be flushed after writing, but not closed.
	 * 
	 * @param file File, that will be written to the {@code JarOutPutStream}
	 * @param jarEntryName full path within the jar-file. Directory separator is "/"
	 * @param os {@code JarOutPutStream}, to which is written
	 * @throws IOException
	 */
	private static void addFileToJar(final File file, final String jarEntryName, final JarOutputStream os) throws IOException{
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

	private static File getClassFolder(final Class<?> clazz)
			throws URISyntaxException {
		return new File(clazz.getProtectionDomain().getCodeSource()
				.getLocation().toURI().getPath());
	}

	public static void main(String[] args) {
		File outFile = new File("/home/pcb/kerner/Desktop/test.jar");
		buildJar(JarBuilder.class, outFile);

	}

}
