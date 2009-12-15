package de.kerner.commons.file;

import java.io.File;

public class FileNameUtils {
	
	private FileNameUtils(){}
	
	/**
	 * 
	 * <p>
	 * Given a {@code File} named "hans.txt". newName is "peter". Returning
	 * {@code String} will be "peter.txt".
	 * </p>
	 * 
	 * <p>
	 * Original name is "hans.txt.tex". newName is "peter". Returning {@code
	 * String} will be "peter.tex".
	 * </p>
	 * 
	 * <p>
	 * Original name is "hans.txt.tex". newName is "peter.txt". Returning
	 * {@code String} will be "peter.txt.tex".
	 * </p>
	 * 
	 * @param file
	 *            {@code File}, for which new name is wanted.
	 * @param newName
	 *            the new filename discarding extension.
	 * @return the new filename including extension.
	 */
	public static String getNewFileName(File file, String newName) {
		final String fileName = file.getName();
		final int posOfExt = fileName.lastIndexOf(".");
		if (posOfExt < 0) {
			return newName;
		}
		// final String rawNameOld = fileName.substring(0, posOfExt);
		// System.err.println("rawNameOld="+rawNameOld);
		final String ext = fileName.substring(posOfExt, fileName.length());
		// System.err.println("ext="+ext);
		return newName + ext;
	}
	
	public static File appendToFileName(File file, String string){
		final String raw = getRawFileName(file);
		final String nameNew = new StringBuilder().append(raw).append(string).append(getFileExtension(file)).toString();
		final File result = new File(file.getParent(), nameNew);
		return result;
	}
	
	public static String getRawFileName(File file) {
		final String fileName = file.getName();
		final int posOfExt = fileName.lastIndexOf(".");
		if (posOfExt < 0) {
			return fileName;
		}
		return fileName.substring(0, posOfExt);
	}
	
	public static String getFileExtension(File file) {
		final String fileName = file.getName();
		final int posOfExt = fileName.lastIndexOf(".");
		if (posOfExt < 0) {
			return "";
		}
		return fileName.substring(posOfExt, fileName.length());
	}

}
