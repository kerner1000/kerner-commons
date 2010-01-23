/**********************************************************************
Copyright (c) 2010 Alexander Kerner. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 ***********************************************************************/

package de.kerner.commons;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * A {@code TimePeriod} represents a period of time, which has a start point, a
 * stop point and a duration.
 * </p>
 * 
 * <p>
 * TODO example
 * </p>
 * 
 * @author Alexander Kerner
 * @threadSave members final or volatile primitives
 * @see java.util.concurrent.TimeUnit
 * 
 */
public class TimePeriod {

	/**
	 * <p> Default time unit for this time period.
	 */
	public final static TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MILLISECONDS;
	private volatile long start, stop;
	private final TimeUnit timeUnit;

	// Constructors //

	/**
	 * <p>
	 * Constructs a new {@code TimePeriod} with given start point, stop
	 * point and time unit.
	 * </p>
	 * 
	 * @param start start point of this {@code TimePeriod}
	 * @param stop stop point of this {@code TimePeriod}
	 * @param timeUnit time unit of start and stop points
	 * @see java.util.concurrent.TimeUnit
	 */
	public TimePeriod(long start, long stop, TimeUnit timeUnit) {
		this.start = start;
		this.stop = stop;
		this.timeUnit = timeUnit;
	}

	/**
	 * <p>
	 * Constructs a new {@code TimePeriod} with given start point and stop point.
	 * </p>
	 *  
	 * @param start start point of this {@code TimePeriod}
	 * @param stop stop point of this {@code TimePeriod}
	 */
	public TimePeriod(long start, long stop) {
		this.start = start;
		this.stop = stop;
		this.timeUnit = DEFAULT_TIME_UNIT;
	}
	
	/**
	 * <p>
	 * Returns the start point of this time period. Time unit is {@link TimePeriod#DEFAULT_TIME_UNIT}.
	 * </p>
	 * @return start point of this time period
	 */
	public long getStart(){
		return getStart(DEFAULT_TIME_UNIT);
	}
	
	/**
	 * <p>
	 * Returns the stop point of this time period. Time unit is {@link TimePeriod#DEFAULT_TIME_UNIT}.
	 * </p>
	 * @return stop point of this time period
	 */
	public long getStop(){
		return getStop(DEFAULT_TIME_UNIT);
	}

	/**
	 * <p>
	 * Returns the start point of this time period in given time period.
	 * </p> 
	 * @param tu {@link java.util.concurrent.TimeUnit} for start point 
	 * @return start point of this time period.
	 */
	public long getStart(TimeUnit tu) {
		return tu.convert(start, timeUnit);
	}

	/**
	 * <p>
	 * Returns the stop point of this time period in given time period.
	 * </p> 
	 * @param tu {@link java.util.concurrent.TimeUnit} for stop point 
	 * @return stop point of this time period.
	 */
	public long getStop(TimeUnit tu) {
		return tu.convert(stop, timeUnit);
	}
	
	/**
	 * <p>
	 * Get duration of this {@code TimePeriod}. Time unit is {@link TimePeriod#DEFAULT_TIME_UNIT}.
	 * </p>
	 * 
	 * @return duration of this time period. Meaning stop point - start point.
	 */
	public long getDuration(){
		return getDuration(DEFAULT_TIME_UNIT);
	}

	/**
	 * <p>
	 * Get duration of this {@code TimePeriod}, converted to given {@code
	 * TimeUnit}.
	 * </p>
	 * 
	 * @param tu
	 *            {@link java.util.concurrent.TimeUnit} in which duration is measured.
	 * @return duration of this time period. Meaning stop point - start point.
	 */
	public long getDuration(TimeUnit tu) {
		return tu.convert((stop - start), timeUnit);
	}

	@Override
	public String toString() {
		return Long.toString(getDuration());
	}
}
