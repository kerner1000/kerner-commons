/**********************************************************************
Copyright (c) 2009-2010 Alexander Kerner. All rights reserved.
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

/**
 * <p>
 * description // TODO
 * </p>
 * 
 * <p>
 * Example:
 * 
 * <pre>
 * AbstractCounter cnt = new AbstractCounter() {
 * 	&#064;Override
 * 	public void perform() {
 * 		System.out.println(&quot;performed!&quot;);
 * 	}
 * };
 * cnt.setInterval(2);
 * for (int i = 0; i &lt; 10; i++) {
 * 	cnt.count();
 * }
 * </pre>
 * 
 * </p>
 * 
 * @author Alexander Kerner
 * @threadSave
 * 
 */
public abstract class AbstractCounter {
	
	// lastVisit 2010-08-12

	private volatile int interval = 0;
	private volatile int intervalHelper = 0;
	private volatile int cnt = 0;

	// Private //

	private void checkPerform() {
		if (intervalHelper >= interval) {
			perform();
			intervalHelper = 0;
		}
	}

	// Public //

	/**
	 * <p>
	 * Increases counter by 1.
	 * </p>
	 */
	public void count() {
		cnt++;
		intervalHelper++;
		checkPerform();
	}

	/**
	 * <p>
	 * Resets this {@code AbstractCounter} and calls
	 * {@link AbstractCounter#perform()} if there has been any counts after last
	 * call of {@link AbstractCounter#perform()}.
	 * </p>
	 */
	public void finish() {
		if (interval != 0)
			perform();
		intervalHelper = 0;
		cnt = 0;
		interval = 0;
	}

	/**
	 * <p>
	 * Sets {@code interval} for this {@code AbstractCounter}.
	 * </p>
	 * 
	 * @param interval
	 *            number of counts {@link AbstractCounter#count()} has to be
	 *            called before {@link AbstractCounter#perform()} is called.
	 * @throws NumberFormatException
	 *             if {@code interval} < 1
	 */
	public void setInterval(int interval) {
		if (interval < 1)
			throw new NumberFormatException("interval must be > 0");
		this.interval = interval;
	}

	/**
	 * @return current interval for this {@code AbstractCounter}.
	 */
	public int getInterval() {
		return interval;
	}

	/**
	 * <p>
	 * Returns the number of {@link AbstractCounter#count()} has been called.
	 * </p>
	 * 
	 * @return current counts
	 */
	public int getCount() {
		return cnt;
	}

	public void setCount(int count) {

		// TODO not unit tested

		this.cnt = count;
		this.intervalHelper = count;
	}

	// Abstract //

	/**
	 * <p>
	 * Performs action.
	 * </p>
	 */
	public abstract void perform();

	@Override
	public String toString() {
		return Integer.toString(getCount());
	}

}
