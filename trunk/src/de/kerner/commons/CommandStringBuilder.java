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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <p>
 * Helper Class to build a Command line argument string, which can easily passed
 * to constructor of {@link java.lang.ProcessBuilder}
 * </p>
 * <p>
 * Example: TODO
 * </p>
 * 
 * @author Alexander Kerner
 * @see ProcessBuilder
 * @threadSave
 */
public class CommandStringBuilder {

	private final List<String> sb = new ArrayList<String>();

	// Constructor //

	/**
	 * <p>
	 * Constructor TODO
	 * </p>
	 * 
	 * @param executable
	 *            absolute or relative path to executable binary.
	 */
	public CommandStringBuilder(String executable) {
		sb.add(executable);
	}

	/**
	 * <p>
	 * Constructor TODO
	 * </p>
	 * 
	 * @param template
	 *            {@code template} on which this {@code CommandStringBuilder}
	 *            is initially created.
	 */
	public CommandStringBuilder(List<String> template) {
		for (String s : template) {
			sb.add(s);
		}
	}

	/**
	 * <p>
	 * Constructor TODO
	 * </p>
	 * 
	 * @param template
	 *            {@code template} on which this {@code CommandStringBuilder}
	 *            is initially created.
	 */
	public CommandStringBuilder(CommandStringBuilder template) {
		for (String s : template.getCommandList()) {
			sb.add(s);
		}
	}
	
	// Public //

	/**
	 * <p>
	 * Add a command line argument that follows pattern <blockquote>
	 * <code>identifier</code> <code>value</code></blockquote> for instance
	 * <blockquote> <code>--verbose</code> <code>true</code></blockquote>
	 * </p>
	 * 
	 * @param identifier
	 *            name of the command line argument
	 * @param value
	 *            value if the command line argument
	 */
	public CommandStringBuilder addValueCommand(String identifier, String value) {
		sb.add(identifier);
		sb.add(value);
		return this;
	}

	/**
	 * <p>
	 * Add all command line arguments from a collection, which follow pattern
	 * <blockquote> <code>identifier</code> <code>value</code></blockquote> for
	 * instance <blockquote> <code>--verbose</code> <code>true</code>
	 * </blockquote>
	 * </p>
	 * 
	 * @param all
	 *            Collection of command line arguments
	 */
	public CommandStringBuilder addAllValueCommands(Map<String, String> all) {
		for (Entry<String, String> e : all.entrySet()) {
			sb.add(e.getKey());
			sb.add(e.getValue());
		}
		return this;
	}

	/**
	 * <p> TODO </p>
	 * @param all TODO
	 */
	public CommandStringBuilder addAllFlagCommands(String... all) {
		sb.addAll(Arrays.asList(all));
		return this;
	}

	/**
	 * <p> TODO </p>
	 * @param all TODO
	 */
	public CommandStringBuilder addAllFlagCommands(List<String> all) {
		sb.addAll(all);
		return this;
	}

	/**
	 * <p>
	 * Add a command line argument that does not expect any value. </p><p>
	 * Pattern: <blockquote> <code>identifier</code></blockquote> for instance
	 * <blockquote> <code>--help</code></blockquote>
	 * </p>
	 * 
	 * @param flagCommand
	 *            command line argument
	 */
	public CommandStringBuilder addFlagCommand(String flagCommand) {
		sb.add(flagCommand);
		return this;
	}

	/**
	 * TODO
	 * @return TODO
	 */
	public List<String> getCommandList() {
		return sb;
	}
	
	// Override //

	@Override
	public String toString() {
		return sb.toString();
	}
}
