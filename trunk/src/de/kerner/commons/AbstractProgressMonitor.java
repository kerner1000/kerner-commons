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
