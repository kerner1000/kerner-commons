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

package de.kerner.commons.exec;

import java.io.OutputStream;
import java.util.List;

/**
 * <p>
 * An implementation of this interface is used to create and execute operating
 * system processes.
 * </p>
 * <p>
 * Usually and implementation of {@code ProcessRunner} will be a wrapper class
 * for {@link ProcessBuilder}. It will unify and simplify usage of
 * {@link ProcessBuilder} by standardizing the redirection of {@code stdout} and
 * {@code stderr}, as well as waiting for end of execution. Canceling or
 * asynchronous execution of processes is not possible via a {@code
 * ProcessRunner}.
 * </p>
 * 
 * @author Alexander Kerner
 * @see Process
 * @see ProcessBuilder
 * @see CommandStringBuilder
 * 
 */
public interface ProcessRunner {

	/**
	 * <p>
	 * Returns the command line string that is used to execute the operating
	 * system process.
	 * </p>
	 * <p>
	 * Example: <blockquote> find / -iname "*kern*" </blockquote> Will execute
	 * {@code find} command with parameters. Splitting this command string at
	 * whitespaces will give all list elements.
	 * </p>
	 * 
	 * @return a {@link java.util.List} of Strings, that contains the command
	 *         line strings
	 * @see java.util.List
	 */
	List<String> getCommandList();

	/**
	 * <p>
	 * Creates and executes the process. {@code stdout} and {@code stderr} are
	 * redirected to {@code out} and {@code err}.
	 * </p>
	 * 
	 * @param out
	 *            {@link java.io.OutputStream}, to which {@code stdout} of
	 *            process is redirected to
	 * @param err
	 *            {@link java.io.OutputStream}, to which {@code stderr} of
	 *            process is redirected to
	 * @return exit code of process
	 * @throws Exception
	 *             if anything goes wrong
	 * 
	 */
	int createAndRun(final OutputStream out, final OutputStream err)
			throws Exception;

	/**
	 * <p>
	 * Creates and executes the process.
	 * </p>
	 * 
	 * @return exit code of process
	 * @throws Exception
	 *             if anything goes wrong
	 * 
	 */
	int createAndRun() throws Exception;

}
