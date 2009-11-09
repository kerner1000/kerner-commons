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
	
	public static String removeAllAfterFirstWhiteSpace(String string){
		return removeAllAfterFirstOccurence("[\\s+]", string);
	}

	public static String removeAllAfterFirstOccurence(String pattern, String string) {
		final String finalPattern = "(.+?)" + pattern + "(.*)";
		final Pattern p = Pattern.compile(finalPattern);
		final Matcher m = p.matcher(string);
		if(m.matches()){
			return m.group(1);
		}
		return string;
	}
	
	public static void main(String[] args){
		String s = "hans peter ulf";
		String s2 = "hans";
		System.out.println(removeAllAfterFirstWhiteSpace(s));
	}

}
