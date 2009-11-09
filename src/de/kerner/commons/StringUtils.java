package de.kerner.commons;

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

}
