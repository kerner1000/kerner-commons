package de.kerner.commons.net;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetUtils {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(NetUtils.class);

	private static void warn(Object msg) {
		if (LOGGER.isWarnEnabled()) {
			LOGGER.warn(msg.toString());
		}
	}

	private NetUtils() {
	}

	/**
	 * <p>
	 * Returns all available headers mapped to there values.
	 * <p>
	 * <p>
	 * For example:<blockquote> host=localhost:8080 <br>
	 * user-agent=Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.0.14) Gecko <br>
	 * accept=text/html <br>
	 * accept-language=en-us,en;q=0.5 <br>
	 * accept-encoding=gzip,deflate accept-charset=UTF-8,* <br>
	 * keep-alive=300 <br>
	 * connection=keep-alive </blockquote>
	 * </p>
	 * 
	 * @param request
	 *            HTTP request from client
	 * @return a {@code Map} that contains all available headers mapped to there
	 *         values.
	 */
	public static Map<String, String> getFullHeader(
			final HttpServletRequest request) {
		final HashMap<String, String> result = new HashMap<String, String>();
		Enumeration<?> headers = request.getHeaderNames();
		while (headers.hasMoreElements()) {
			final String h = headers.nextElement().toString();
			result.put(h, request.getHeader(h));
		}
		return result;
	}

	public static String getRemoteIP(final HttpServletRequest request) {
		return request.getRemoteAddr();
	}

	public static String getRemoteHost(final HttpServletRequest request) {
		return request.getRemoteHost();
	}

	public static String getBrowser(final HttpServletRequest request) {
		final Browser b = new BrowserDetector().getBrowser(request);
		if (b.equals(Browser.UNKNOWN))
			warn("could not detect browser ("
					+ getFullHeader(request).get("user-agent") + ")");
		if (b.version.equalsIgnoreCase("unknown"))
			warn("could not detect browser version ("
					+ getFullHeader(request).get("user-agent") + ")");
		final StringBuilder result = new StringBuilder();
		result.append(b);
		if (b.equals(Browser.UNKNOWN)) {
			// nothing
		} else {
			result.append(", ");
			result.append(b.version);
		}
		return result.toString();
	}

	public static void main(String[] args) {
		final String firefox = "Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.0.14) Gecko/2009090217 Ubuntu/9.04 (jaunty) Firefox/3.0.14";
		final String mozilla = "Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.0.14) Gecko";
		final Browser b = Browser.parseBrowser(mozilla);
		System.out.println(b);
		System.out.println(b.version);
	}

}
