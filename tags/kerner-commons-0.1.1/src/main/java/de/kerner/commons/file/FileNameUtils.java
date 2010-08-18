/**********************************************************************
Copyright (c) 2009-2010 Alexander Kerner. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 ***********************************************************************/

package de.kerner.commons.file;

import java.io.File;
import java.io.FileNotFoundException;

public class FileNameUtils {

	private FileNameUtils() {
	}

	/**
	 * <p>
	 * Renames a file
	 * <p>
	 * 
	 * @param file
	 *            file that is to be renamed
	 * @param newName
	 *            new name for the file
	 * @param keepExtension
	 *            if true, file extension will be appended to new name, if there
	 *            was any.
	 * @return true, if operation was successful, false otherwise
	 * @throws FileNotFoundException
	 */
	public static boolean renameFile(File file, String newName,
			boolean keepExtension) throws FileNotFoundException {
		synchronized (file) {

			if (!file.isFile()) {
				throw new FileNotFoundException("cannot access file \"" + file
						+ "\"");
			}

			// final String fileName = file.getName();
			// final String fileNameRaw = getRawFileName(file);
			final String fileNameExtension = getFileExtension(file);

			if (keepExtension) {
				return file.renameTo(new File(file.getParentFile(), newName + fileNameExtension));
			} else {
				return file.renameTo(new File(file.getParentFile(), newName));
			}
		}
	}

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

	public static File appendToFileName(File file, String string) {
		final String raw = getRawFileName(file);
		final String nameNew = new StringBuilder().append(raw).append(string)
				.append(getFileExtension(file)).toString();
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
