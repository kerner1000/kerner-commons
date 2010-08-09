package de.kerner.commons;

import java.util.Locale;

public class Utils {
	
	public static final Locale USER_LOCALE = new Locale(System.getProperty("user.language"));
	
	public static final int NUM_CPUS = Runtime.getRuntime().availableProcessors();
	
	private Utils(){
		
	}

}
