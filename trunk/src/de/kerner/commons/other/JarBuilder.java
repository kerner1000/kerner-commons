package de.kerner.commons.other;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import de.kerner.commons.file.AbstractDirectoryWalker;
import de.kerner.commons.file.FileUtils;

/**
 * 
 * BROKEN
 * DO NOT USE
 * 
 */
public class JarBuilder {

	private static class JarFileFilter implements FileFilter {
		public boolean accept(File file) {
			return file.getName().endsWith(".class");
		}
	}

	private class Walker extends AbstractDirectoryWalker {
		final FileOutputStream fos;
		final JarOutputStream os;
		final String jarName;

		Walker(String jarName) throws IOException {
			this.jarName = jarName;
			fos = new FileOutputStream(jarName);
			os = new JarOutputStream(fos);
		}

		public void handleDir(File file) throws IOException {
			// TODO Auto-generated method stub

		}

		public void handleFile(File file) throws IOException {
			if (file.isDirectory()) {
				String dirName = getRelName(file).replace(File.separatorChar, '/');
				if (!dirName.endsWith("/"))
					dirName += "/";
				JarEntry entry = new JarEntry(dirName);
				os.putNextEntry(entry);
				os.closeEntry();
			} else {
				JarEntry entry = new JarEntry(file.getName().replace(
						File.separatorChar, '/'));
				entry.setSize(file.length());
				entry.setTime(file.lastModified());
				os.putNextEntry(entry);
				FileInputStream in = new FileInputStream(file);
				int cnt = in.read(new byte[1024]);
				while (cnt > 0) {
					os.write(new byte[1024], 0, cnt);
					cnt = in.read(new byte[1024]);
				}
				os.closeEntry();
				in.close();
			}
		}

		private String getRelName(File file) {
			// TODO Auto-generated method stub
			final String s = "de.mpg.mpiz.koeln.kerner.sequencereader/bin".replaceAll(".", File.pathSeparator);
			System.err.println("returning " + s);
			return s;
		}

		@Override
		protected void lastAction(boolean wasCancelled) throws IOException {
			super.lastAction(wasCancelled);
			os.flush();
			os.close();
		}
	}

	public void buildJar(final File classFolder, final String jarName)
			throws IOException {
		Walker walker = new Walker(jarName);
		walker.addFilter(new JarFileFilter());
		walker.walk(classFolder);
	}

	public static void main(String[] args) {
		try {
			new JarBuilder()
					.buildJar(
							new File(
									"/home/proj/kerner/workspace/de.mpg.mpiz.koeln.kerner.sequencereader/bin"),
							"/home/pcb/kerner/Desktop/test.jar");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
