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

package de.kerner.commons.exec;

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
 * This class is also useful in combination of {@link AbstractProcessRunner} and
 * {@link AbstractProgrammRunner}.
 * <p>
 * Example: TODO
 * </p>
 * 
 * @author Alexander Kerner
 * @see ProcessBuilder
 * @see AbstractProcessRunner
 * @see AbstractProgrammRunner
 * 
 */
public class CommandStringBuilder {

	private final List<String> sb = new ArrayList<String>();

	// Constructor //

	/**
	 * <p>
	 * Constructs a new {@code CommandStringBuilder}. First argument for the
	 * command line string to build is passed as argument for this constructor.
	 * </p>
	 * <p>
	 * Notice: First argument is usually name of executable. Passed in either as
	 * an absolute or relative path string.
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
	 * Constructs a new {@code CommandStringBuilder} from given template.
	 * </p>
	 * 
	 * @param template
	 *            {@code template} for this {@code CommandStringBuilder}. All
	 *            elements of this {@code List} are initially added to the
	 *            command line string to build.
	 */
	public CommandStringBuilder(List<String> template) {
		for (String s : template) {
			sb.add(s);
		}
	}

	/**
	 * <p>
	 * Constructs a new {@code CommandStringBuilder} from given template.
	 * </p>
	 * 
	 * @param template
	 *            {@code template} another {@code CommandStringBuilder} from
	 *            which all command line arguments are taken over to this newly
	 *            initiated {@code CommandStringBuilder}.
	 */
	public CommandStringBuilder(CommandStringBuilder template) {
		for (String s : template.build()) {
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
	 * <p>
	 * {@code Collection key} holds option identifier, {@code Collection value} option value. 
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
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @param all
	 *            TODO
	 */
	public CommandStringBuilder addAllFlagCommands(String... all) {
		sb.addAll(Arrays.asList(all));
		return this;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @param all
	 *            TODO
	 */
	public CommandStringBuilder addAllFlagCommands(List<String> all) {
		sb.addAll(all);
		return this;
	}

	/**
	 * <p>
	 * Add a command line argument that does not expect any value.
	 * </p>
	 * <p>
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
	 * <p> Return command line string list.
	 * 
	 * @return this {@code CommandStringBuilder}'s command string list.
	 */
	public List<String> build() {
		return sb;
	}

	// Override //

	@Override
	public String toString() {
		return sb.toString();
	}
}
