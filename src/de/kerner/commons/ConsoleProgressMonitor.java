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
public class ConsoleProgressMonitor extends AbstractProgressMonitor {

	public ConsoleProgressMonitor(int total, int delay, boolean percent) {
		super(total, delay, percent);
	}

	@Override
	void printPercent(int cnt, int total, String prefix, String postfix) {
		System.out.print(prefix);
		System.out.printf(" %5.2f", (double) cnt / total * 100);
		System.out.print("%");
		System.out.println(postfix);
	}

	@Override
	void printTotal(int cnt, int total, String prefix, String postfix) {
		System.out.print(prefix);
		System.out.printf(" %d/%d", cnt, total);
		System.out.println(postfix);
	}

}
