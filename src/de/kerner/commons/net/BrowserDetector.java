package de.kerner.commons.net;

import javax.servlet.http.HttpServletRequest;

public class BrowserDetector {
	
	public Browser getBrowser(HttpServletRequest request) {
		final String string = NetUtils.getFullHeader(request).get("user-agent");
		return Browser.parseBrowser(string);
	}

	

}
