package de.kerner.commons;

//TODO: move to kerner commons
public abstract class AbstractCounter {

	private volatile int interval = 0;
	private volatile int intervalHelper = 0;
	private volatile int cnt = 0;

	public void count() {
		cnt++;
		intervalHelper++;
		checkPerform();
	}
	
	public void stop() {
		perform();
	}

	public void setPrintInterval(int interval) {
		this.interval = interval;
	}

	private void checkPerform() {
		if (intervalHelper >= interval) {
			perform();
			intervalHelper = 0;
		}
	}

	protected int getCnt(){
		return cnt;
	}
	
	protected abstract void perform();

	

}
