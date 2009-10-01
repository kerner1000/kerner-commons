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
 * 
 * @author Alexander Kerner
 * @lastVisit 2009-10-01
 */
public class CommandStringBuilder {

	private final List<String> sb = new ArrayList<String>();

	/**
	 * 
	 * @param executable absolute or relative path to executable binary.
	 */
	public CommandStringBuilder(String executable) {
		sb.add(executable);
	}

	/**
	 * <p> Add a command line argument that follows pattern
	 * <blockquote> <code>identifier</code> <code>value</code></blockquote>
	 * for instance
	 * <blockquote> <code>--verbose</code> <code>true</code></blockquote>
	 * </p>
	 * @param identifier name of the command line argument
	 * @param value value if the command line argument
	 */
	public void addValueCommand(String identifier, String value) {
		sb.add(identifier);
		sb.add(value);
	}

	/**
	 * <p> Add all command line arguments from a collection, which follow pattern
	 * <blockquote> <code>identifier</code> <code>value</code></blockquote>
	 * for instance
	 * <blockquote> <code>--verbose</code> <code>true</code></blockquote>
	 * </p>
	 * @param all Collection of command line arguments
	 */
	public void addAllValueCommands(Map<String, String> all) {
		for (Entry<String, String> e : all.entrySet()) {
			sb.add(e.getKey());
			sb.add(e.getValue());
		}
	}

	public void addAllFlagCommands(String... all) {
		sb.addAll(Arrays.asList(all));
	}

	public void addAllFlagCommands(List<String> all) {
		sb.addAll(all);
	}

	/**
	 * <p> Add a command line argument, that does not expect any value. <br>
	 * Pattern:
	 * <blockquote> <code>identifier</code></blockquote>
	 * for instance
	 * <blockquote> <code>--help</code></blockquote>
	 * </p>
	 * @param flagCommand command line argument
	 */
	public void addFlagCommand(String flagCommand) {
		sb.add(flagCommand);
	}

	public List<String> getCommandList() {
		return sb;
	}

	public String toString() {
		return sb.toString();
	}
}
