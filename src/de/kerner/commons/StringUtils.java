package de.kerner.commons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Alexander Kerner
 * @lastVisit 2009-11-09
 * 
 */
public class StringUtils {

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
		final String finalPattern = "(.+?)" + pattern + "(.*)";
		final Pattern p = Pattern.compile(finalPattern);
		final Matcher m = p.matcher(string);
		if (m.matches()) {
			return m.group(1).trim();
		}
		return string;
	}

	public static String removeAllBevoreFirstOccurence(String pattern,
			String string) {
		final String finalPattern = "(.*?)(" + pattern + ".+)";
		final Pattern p = Pattern.compile(finalPattern);
		final Matcher m = p.matcher(string);
		if (m.matches()) {
			System.err.println(m.group(0));
			System.err.println(m.group(1));
			return m.group(2).trim();
		}
		return string;
	}
	
	public static String replaceAllNonMatching(String pattern, String string){
		 Pattern p = Pattern.compile(pattern);
	     Matcher m = p.matcher(string);
	     return m.replaceAll("");

	}

	public static void main(String[] args) {
		String s1 = "[^0-9]";
		String s2 = ">supercont3.1_1002";
		System.out.println(replaceAllNonMatching(s1, s2));
	}

}
