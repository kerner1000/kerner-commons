package de.kerner.commons.file;

import java.io.File;

public class FileNameUtils {
	
	//public static final String DEFAULT_DELIM = "_";
	
	private FileNameUtils(){}
	
//	public static File appendToFileName(File file, String string){
//		return appendToFileName(file, string, DEFAULT_DELIM);
//	}
	
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
