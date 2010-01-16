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

/**
 * <p>
 * description // TODO
 * </p>
 * 
 * @author Alexander Kerner
 * @threadSave primitive class members volatile
 * @lastVisit 2010-01-16
 * 
 */
public abstract class AbstractCounter {

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
	 * {@link AbstractCounter#perform()}.
	 * </p>
	 */
	public void stop() {
		interval = 0;
		intervalHelper = 0;
		cnt = 0;
		perform();
	}

	/**
	 * <p>
	 * Sets {@code interval} for this {@code AbstractCounter}.
	 * </p>
	 * 
	 * @param interval
	 *            number of counts {@link AbstractCounter#count()} has to be
	 *            called before {@link AbstractCounter#perform()} is called.
	 */
	public void setInterval(int interval) {
		this.interval = interval;
	}

	/**
	 * <p>
	 * Returns the number of {@link AbstractCounter#count()} has been called.
	 * </p>
	 * 
	 * @return current counts
	 */
	public int getCnt() {
		return cnt;
	}

	// Abstract //

	/**
	 * <p>
	 * Performs action.
	 * </p>
	 */
	public abstract void perform();

}
