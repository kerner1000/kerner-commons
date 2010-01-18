package de.kerner.commons;

/**
 * 
 * @author Alexander Kerner
 * @deprecated use {@link AbstractCounter} instead
 *
 */
public abstract class AbstractProgressMonitor implements ProgressMonitor {

	private final int total;
	private boolean percent = false;
	private String prefix = "";
	private String postfix = "";
	private int cnt = 0;
	private int delayCnt = 0;
	private int delay = 0;

	AbstractProgressMonitor(int total, int delay, boolean percent) {
		this.total = total;
		this.delay = delay;
		this.percent = percent;
	}

	public synchronized void next() {
		if (delayCnt >= delay) {
			if (percent)
				printPercent(cnt, total, prefix, postfix);
			else
				printTotal(cnt, total, prefix, postfix);
			delayCnt = 0;
		}
		delayCnt++;
		cnt++;
	}

	abstract void printTotal(int cnt, int total, String prefix,
			String postfix);

	abstract void printPercent(int cnt, int total, String prefix,
			String postfix);

	public synchronized void setDelay(int delay) {
		this.delay = delay;
	}

	public synchronized void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public synchronized void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
