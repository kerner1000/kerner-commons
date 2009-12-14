package de.kerner.commons.workflow;

public abstract class WorkFlowException extends Exception {

	private static final long serialVersionUID = 412379162803535778L;

	public WorkFlowException() {
		
	}

	public WorkFlowException(String message) {
		super(message);
		
	}

	public WorkFlowException(Throwable cause) {
		super(cause);
		
	}

	public WorkFlowException(String message, Throwable cause) {
		super(message, cause);
		
	}
	
	public abstract void doSomething();

}
