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
 * A {@code HumanReadableSizePrinter} can be used to get a human readable string
 * based expression from TODO
 * </p>
 * <p>
 * Example: <br>
 * Print a file size of 1024 bytes in a human readable format.
 * 
 * <pre>
 * System.out.println(new HumanReadableAmountPrinter(1024));
 * </pre>
 * 
 * Will print the string "1K".
 * </p>
 * 
 * @author Alexander Kerner
 * @threadSave stateless / all final
 * 
 */
public class HumanReadableAmountPrinter {

	private static final String POST_FIX_B = "B";
	private static final String POST_FIX_K = "K";
	private static final String POST_FIX_M = "M";
	private static final String POST_FIX_G = "G";
	private final long oneK;
	private final long oneM;
	private final long oneG;
	private final long amount;

	/**
	 * <p>
	 * Construct a new {@code HumanReadableAMountPrinter}.
	 * </p>
	 * 
	 * @param amount
	 *            TODO
	 * @param usePowerOf1000
	 *            TODO
	 */
	public HumanReadableAmountPrinter(long amount, boolean usePowerOf1000) {
		if (usePowerOf1000)
			oneK = 1000L;
		else
			oneK = 1024L;

		oneM = oneK * oneK;
		oneG = oneK * oneM;
		this.amount = amount;
	}

	/**
	 * <p>
	 * Construct a new {@code HumanReadableAMountPrinter}.
	 * </p>
	 * 
	 * @param amount
	 *            TODO
	 */
	public HumanReadableAmountPrinter(long amount) {
		oneK = 1024L;
		oneM = oneK * oneK;
		oneG = oneK * oneM;
		this.amount = amount;
	}

	public String toString() {
		String postFix = POST_FIX_B;
		long sizeCp = amount;
		if (sizeCp / oneG > 0) {
			postFix = POST_FIX_G;
			sizeCp = sizeCp / oneG;
		} else if (sizeCp / oneM > 0) {
			postFix = POST_FIX_M;
			sizeCp = sizeCp / oneM;
		} else if (sizeCp / oneK > 0) {
			postFix = POST_FIX_K;
			sizeCp = sizeCp / oneK;
		} else {
			// nothing
		}
		return sizeCp + postFix;
	}
}
