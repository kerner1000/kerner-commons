package de.kerner.commons.workflow;

public interface WorkFlow {
	
	void work() throws WorkFlowException;
	
	void addElement(WorkFlowElement element);

}
