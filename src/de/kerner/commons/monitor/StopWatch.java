package de.kerner.commons.monitor;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Alexander Kerner
 * @lastVisit 2009-10-14
 * @threadSave members primitive / volatile
 *
 */
public class StopWatch {

	private volatile long startTime = 0;
	private volatile long stopTime = 0;
	private volatile boolean running = false;
	
	public boolean isRunning(){
		return running;
	}

	public void start() {
		this.startTime = System.nanoTime();
		this.running = true;
	}

	public void stop() {
		this.stopTime = System.nanoTime();
		this.running = false;
	}

	public TimePeriod getElapsedTime(TimeUnit tu) {
		if (running) {
			return new TimePeriod(tu.convert(startTime, TimeUnit.NANOSECONDS), tu.convert(System.nanoTime(), TimeUnit.NANOSECONDS), tu);
		} else {
			return new TimePeriod(tu.convert(startTime, TimeUnit.NANOSECONDS), tu.convert(stopTime, TimeUnit.NANOSECONDS), tu);
		}
	}
	
	public TimePeriod getElapsedTime() {
		return getElapsedTime(TimeUnit.MILLISECONDS);
	}

	public static void main(String args[]) {
		final TimeUnit u1 = TimeUnit.NANOSECONDS;
		final TimeUnit u2 = TimeUnit.MILLISECONDS;
		final StopWatch watch = new StopWatch();
		watch.start();
		try {
			Thread.sleep(100);
//			System.out.println(watch.getElapsedTime(u1).getStart(u2));
//			System.out.println(watch.getElapsedTime(u1).getStop(u2));
			System.out.println(watch.getElapsedTime(u1).getDuration(u2));
			System.out.println(watch.getElapsedTime(u1));
			System.out.println();
//			System.out.println(watch.getElapsedTime().getStart(u2));
//			System.out.println(watch.getElapsedTime().getStop(u2));
			System.out.println(watch.getElapsedTime().getDuration(u2));
			System.out.println(watch.getElapsedTime());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		watch.stop();
		System.out.println();
//		System.out.println(watch.getElapsedTime(u1).getStart(u2));
//		System.out.println(watch.getElapsedTime(u1).getStop(u2));
		System.out.println(watch.getElapsedTime(u1).getDuration(u2));
		System.out.println(watch.getElapsedTime(u1));
		
	}

	@Deprecated
	public long getElapsedMillis() {
		long elapsed;
		if (running) {
			elapsed = (System.currentTimeMillis() - startTime);
		} else {
			elapsed = (stopTime - startTime);
		}
		return elapsed;
	}

	@Deprecated
	public long getElapsedSecs() {
		long elapsed;
		if (running) {
			elapsed = ((System.currentTimeMillis() - startTime) / 1000);
		} else {
			elapsed = ((stopTime - startTime) / 1000);
		}
		return elapsed;
	}

}
