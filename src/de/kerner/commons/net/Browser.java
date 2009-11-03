package de.kerner.commons.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Browser {

	// FIREFOX: Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.0.14)
	// Gecko/2009090217 Ubuntu/9.04 (jaunty) Firefox/3.0.14

	UNKNOWN, FIREFOX;

	private final static Logger LOGGER = LoggerFactory.getLogger(Browser.class);
	private final static Pattern FIREFOX_PATTERN = Pattern.compile(
			".*[\\s+]firefox/.*", Pattern.CASE_INSENSITIVE);
	private final static Pattern FIREFOX_VERSION_PATTERN = Pattern.compile(
			".*firefox/(.+)", Pattern.CASE_INSENSITIVE);

	String version = "unknown";

	private static void warn(Object msg) {
		if (LOGGER.isWarnEnabled()) {
			LOGGER.warn(msg.toString());
		}
	}

	public static Browser parseBrowser(String string) {
		Browser result = UNKNOWN;
		result = checkFirefox(string);
		// if (result.equals(UNKNOWN))
		// result = checkMozilla(string);
		return result;
	}

	private static Browser checkFirefox(String string) {
		Browser result = UNKNOWN;
		final Matcher m1 = FIREFOX_PATTERN.matcher(string);
		final Matcher m2 = FIREFOX_VERSION_PATTERN.matcher(string);
		if (m1.matches()) {
			result = FIREFOX;
			if (m2.matches())
				result.version = m2.group(1);
		}
		return result;
	}

}
