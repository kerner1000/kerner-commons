package de.kerner.commons.monitor;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * A {@code TimePeriod} represents a period of time, which has a start point,
 * a stop point and a duration.
 * </p>
 * 
 * @author Alexander Kerner
 * @threadSave members final
 * @lastVisit 2009-10-14
 * @see TimeUnit
 * 
 */
public class TimePeriod {

	private volatile long start, stop;
	private final TimeUnit timeUnit;

	public TimePeriod(long start, long stop, TimeUnit timeUnit) {
		this.start = start;
		this.stop = stop;
		this.timeUnit = timeUnit;
	}
	
	public TimePeriod(long start, long stop) {
		this.start = start;
		this.stop = stop;
		this.timeUnit = TimeUnit.MILLISECONDS;
	}

	public long getStart(TimeUnit tu) {
		return tu.convert(start, timeUnit);
	}

	public long getStop(TimeUnit tu) {
		return tu.convert(stop, timeUnit);
	}

	/**
	 * <p> Get duration of this {@code TimePeriod}, converted to given {@code TimeUnit}. </p>
	 * @param tu {@code TimeUnit} in which duration is measured.
	 * @return duration of this time period. Meaning stop point - start point.
	 */
	public long getDuration(TimeUnit tu) {
		return tu.convert((stop - start), timeUnit);
	}
	
	@Override
	public String toString() {
		return Long.toString(getDuration(TimeUnit.MILLISECONDS));
	}
}
