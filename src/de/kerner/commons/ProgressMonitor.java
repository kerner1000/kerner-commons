package de.kerner.commons;

public interface ProgressMonitor {
	
	void setDelay(int delay);
	
	void setPrefix(String prefix);
	
	void setPostfix(String postfix);
	
	void next();

}
