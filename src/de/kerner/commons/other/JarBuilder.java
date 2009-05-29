package de.kerner.commons.other;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import de.kerner.commons.file.AbstractDirectoryWalker;

public class JarBuilder {
	
	private static class JarFileFilter implements FileFilter{
		public boolean accept(File file) {
			return file.getName().endsWith(".class");
		}
	}
	
	private class Walker extends AbstractDirectoryWalker {
		final FileOutputStream fos;
		final JarOutputStream os;
		final String jarName;
		Walker(String jarName) throws IOException{
			this.jarName = jarName;
			fos = new FileOutputStream(jarName);
			os = new JarOutputStream(fos);
		}
		public void handleDir(File file) throws IOException {
			// TODO Auto-generated method stub
			
		}
		public void handleFile(File file) throws IOException {
			System.out.printf("Now: %s", file.getName());
		    System.out.println();
			final Class<?> myClass = file.getClass();
		    final String myClassName = myClass.getSimpleName();
		    final Package myPackage = myClass.getPackage();
		    final String myPackageName = myPackage.getName();
		    final String myPackageFileName = myPackageName.replace('.', '/');
		    final String name = myPackageFileName + '/' + file.getName();
		    System.out.printf("Adding: %s", name);
		    System.out.println();
			final JarEntry entry = new JarEntry(name);
			os.putNextEntry(entry);
			final InputStream is = new BufferedInputStream(new FileInputStream(file));
		      int aByte;
		      while ((aByte = is.read()) != -1) {
		        os.write(aByte);
		      }
		      System.out.println("----------------------------");
		      System.out.printf("Wrote: %s", jarName);
		}
		
		@Override
		protected void lastAction(boolean wasCancelled) throws IOException {
			super.lastAction(wasCancelled);
			os.flush();
			os.close();
		}
	}
	
	public void buildJar(final File classFolder, final String jarName) throws IOException{
		Walker walker = new Walker(jarName);
		walker.addFilter(new JarFileFilter());
		walker.walk(classFolder);
	}
	
	public static void main(String[] args){
		try {
			new JarBuilder().buildJar(new File("/home/proj/kerner/workspace/de.mpg.mpizkoeln.kerner.anna.sequencesreader/bin"), "/home/pcb/kerner/Desktop/test.jar");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
