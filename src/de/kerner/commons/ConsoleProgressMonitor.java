package de.kerner.commons;

public class ConsoleProgressMonitor extends AbstractProgressMonitor {

	public ConsoleProgressMonitor(int total, int delay, boolean percent) {
		super(total, delay, percent);
	}

	@Override
	void printPercent(int cnt, int total, String prefix, String postfix) {
		System.out.print(prefix);
		System.out.printf("%5.2f", (double) cnt / total * 100);
		System.out.print("%");
		System.out.println(postfix);
	}

	@Override
	void printTotal(int cnt, int total, String prefix, String postfix) {
		System.out.print(prefix);
		System.out.printf("%d/%d", cnt, total);
		System.out.println(postfix);
	}

}
