/**********************************************************************
Copyright (c) 2010 Alexander Kerner. All rights reserved.
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

package de.kerner.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.kerner.commons.file.FileUtils;

/**
 * <p>
 * An utility class that provides static methods for all kind of string
 * operations.
 * </p>
 * 
 * @author Alexander Kerner
 * @lastVisit 2010-08-03
 * 
 */
public class StringUtils {

	private StringUtils() {

	}

	public static List<String> splitToList(String pattern, String string) {
		return splitToList(pattern, string, true);
	}

	public static List<String> splitToList(String pattern, String string,
			boolean trimElements) {
		final ArrayList<String> result = new ArrayList<String>();
		final String[] sa = splitToArray(pattern, string);
		for (String s : sa) {
			String s2 = s.trim();
			if (trimElements)
				result.add(s2);
			else
				result.add(s);
		}
		return result;
	}

	public static String[] splitToArray(String pattern, String string) {
		if (pattern == null || pattern.length() == 0)
			throw new NullPointerException("empty pattern");
		if (string == null || string.length() == 0)
			throw new NullPointerException("empty string");
		return string.split(pattern);
	}

	/**
	 * <p>
	 * TODO description
	 * </p>
	 * <p>
	 * TODO example
	 * </p>
	 */
	public static String getString(Object... objects) {
		if (objects == null || objects.length == 0)
			return "";
		final StringBuilder sb = new StringBuilder(objects.length);
		for (Object o : objects) {
			sb.append(o);
		}
		return sb.toString();
	}

	/**
	 * 
	 * <p>
	 * TODO description
	 * </p>
	 * <p>
	 * Example: {@code string} = "hans peter ulf": <br>
	 * return "hans".
	 * </p>
	 * 
	 * <p>
	 * Example: {@code string} = "hans": <br>
	 * return "hans".
	 * </p>
	 */
	public static String removeAllAfterFirstWhiteSpace(String string) {
		return removeAllAfterFirstOccurence("[\\s+]", string);
	}

	/**
	 * <p>
	 * TODO description
	 * </p>
	 * <p>
	 * Example: {@code string} = "hans peter ulf", {@code pattern} = "[1-3]": <br>
	 * return "hans peter ulf".
	 * </p>
	 * 
	 * <p>
	 * Example: {@code string} = "hans1peter2ulf", {@code pattern} = "[1-3]": <br>
	 * return "hans".
	 * </p>
	 */
	public static String removeAllAfterFirstOccurence(String pattern,
			String string) {
		final String finalPattern = "(.+?)" + pattern + "(.*?)";
		final Pattern p = Pattern.compile(finalPattern);
		final Matcher m = p.matcher(string);
		if (m.matches()) {
			// System.err.println(m.group(0));
			// System.err.println(m.group(1));
			return m.group(1).trim();
		}
		// System.err.println(string);
		return string;
	}

	/**
	 * <p>
	 * TODO description
	 * </p>
	 * <p>
	 * TODO example
	 * </p>
	 */
	public static String removeAllBevoreFirstOccurence(String pattern,
			String string) {
		final String finalPattern = "(.+?)" + pattern + "(.*?)";
		final Pattern p = Pattern.compile(finalPattern);
		final Matcher m = p.matcher(string);
		if (m.matches()) {
			// System.err.println(m.group(0));
			// System.err.println(m.group(1));

			return m.group(2).trim();

		}
		// System.err.println(string);
		return string;
	}

	public static String removeAllAfterLastOccurence(String pattern,
			String string) {

		// maybe use other regex:
		// pattern: (\d+)$
		// string: abc56abc66
		// outcome: 66

		final String finalPattern = "(.+)" + pattern + "(.*?)";
		final Pattern p = Pattern.compile(finalPattern);
		final Matcher m = p.matcher(string);
		if (m.matches()) {
			// System.err.println(m.group(0));
			// System.err.println(m.group(1));

			return m.group(1).trim();

		}
		// System.err.println(string);
		return string;
	}

	public static String removeAllBeforeLastOccurence(String pattern,
			String string) {

		// maybe use other regex:
		// pattern: (\d+)$
		// string: abc56abc66
		// outcome: 66

		final String finalPattern = "(.+)" + pattern + "(.*?)";
		final Pattern p = Pattern.compile(finalPattern);
		final Matcher m = p.matcher(string);
		if (m.matches()) {
			// System.err.println(m.group(0));
			// System.err.println(m.group(1));

			return m.group(2).trim();

		}
		// System.err.println(string);
		return string;
	}

	public static int firstIndexOf(String pattern, String string1,
			boolean inclusive) {

		// System.err.println(string1);

		final String finalPattern;
		if (inclusive)
			finalPattern = "(.*?)(" + pattern + ")(.*)";
		else
			finalPattern = "(.*?)(" + pattern + ")(.*)";
		
		
		final Pattern p = Pattern.compile(finalPattern);
		final Matcher m = p.matcher(string1);
		if (m.matches()) {
			// System.err.println(inclusive + " firstIndexOf: 0: " +
			// m.group(0));
			// System.err.println(inclusive + " firstIndexOf: 1: " +
			// m.group(1));
			// System.err.println(inclusive + " firstIndexOf: 2: " +
			// m.group(2));
			// System.err.println(inclusive + " firstIndexOf: 3: " +
			// m.group(3));
			if (inclusive)
				return m.toMatchResult().end(2) - 1;
			else {
				// System.err.println("group: " + m.group(2));
				return m.toMatchResult().start(2);
			}

		}
		return -1;
	}

	public static int lastIndexOf(String pattern, String string1,
			boolean inclusive) {
		// System.err.println(string1);
		
		String s = reverse(string1);

		final String finalPattern;
		if (inclusive)
			finalPattern = "(.*?)(" + pattern + ")(.*)";
		else
			finalPattern = "(.*?)(" + pattern + ")(.*)";
		
		
		final Pattern p = Pattern.compile(finalPattern);
		final Matcher m = p.matcher(s);
		if (m.matches()) {
//			 System.err.println(inclusive + " 0: " +
//			 m.group(0));
//			 System.err.println(inclusive + " 1: " +
//			 m.group(1));
//			 System.err.println(inclusive + " 2: " +
//			 m.group(2));
//			 System.err.println(inclusive + " 3: " +
//			 m.group(3));
			if (inclusive)
				return s.length() - m.toMatchResult().start(2) - 1;
			else {
				// System.err.println("group: " + m.group(2));
				return s.length() - m.toMatchResult().end(2);
			}

		}
		return -1;

	}

	public static String reverse(String string1) {
		return new StringBuilder(string1).reverse().toString();
	}

	/**
	 * <p>
	 * TODO description
	 * </p>
	 * <p>
	 * TODO example
	 * </p>
	 */
	public static String removeAllNonMatching(String pattern, String string) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(string);
		return m.replaceAll("");

	}

	/**
	 * <p>
	 * TODO description
	 * </p>
	 * <p>
	 * TODO example
	 * </p>
	 */
	public static String replace(String pattern, String string,
			String replacement) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(string);
		return m.replaceAll(replacement);

	}

	public static String formatMultiLineStringToLength(String string, int length) {
		string = string.replaceAll("\\s", "").trim();
		StringBuilder sb = new StringBuilder();
		while (string.length() > length) {
			int endIndex = length;
			sb.append(string.subSequence(0, endIndex).toString()
					+ FileUtils.NEW_LINE);
			string = string.substring(endIndex);
		}
		if (string != null && !string.equals(FileUtils.NEW_LINE))
			sb.append(string);
		// sb.append(FileUtils.NEW_LINE);
		return sb.toString();
	}

}
