package de.kerner.commons.monitor;

public class MemoryMonitor {
	
	private volatile long overPeriod;
	
	public static long getCurrentFree(){
		return Runtime.getRuntime().freeMemory();
	}
	
	public static long getCurrentUsed(){
		return Runtime.getRuntime().totalMemory();
	}
	
	public static long getMax(){
		return Runtime.getRuntime().maxMemory();
	}
	
	public void start(){
		overPeriod = getCurrentUsed();
	}
	
	public long getUseSinceStart(){
		return getCurrentUsed() - overPeriod;
	}
	
	

}
