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
	
	public CommandStringBuilder(List<String> template) {
		for(String s : template){
			sb.add(s);
		}
	}
	
	public CommandStringBuilder(CommandStringBuilder template) {
		for(String s : template.getCommandList()){
			sb.add(s);
		}
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
	public CommandStringBuilder addValueCommand(String identifier, String value) {
		sb.add(identifier);
		sb.add(value);
		return this;
	}

	/**
	 * <p> Add all command line arguments from a collection, which follow pattern
	 * <blockquote> <code>identifier</code> <code>value</code></blockquote>
	 * for instance
	 * <blockquote> <code>--verbose</code> <code>true</code></blockquote>
	 * </p>
	 * @param all Collection of command line arguments
	 */
	public CommandStringBuilder addAllValueCommands(Map<String, String> all) {
		for (Entry<String, String> e : all.entrySet()) {
			sb.add(e.getKey());
			sb.add(e.getValue());
		}
		return this;
	}

	public CommandStringBuilder addAllFlagCommands(String... all) {
		sb.addAll(Arrays.asList(all));
		return this;
	}

	public CommandStringBuilder addAllFlagCommands(List<String> all) {
		sb.addAll(all);
		return this;
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
	public CommandStringBuilder addFlagCommand(String flagCommand) {
		sb.add(flagCommand);
		return this;
	}

	public List<String> getCommandList() {
		return sb;
	}

	public String toString() {
		return sb.toString();
	}
}
