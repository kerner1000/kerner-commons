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

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import de.kerner.commons.io.IOUtils;
import de.kerner.commons.logging.Log;

/**
 * <p>
 * Simple implementation of {@link ProcessRunner}.
 * </p>
 * <p>
 * Example:
 * 
 * <pre>
 * final AbstractProcessRunner r = new AbstractProcessRunner(new File(&quot;/&quot;)) {
 * 	public List&lt;String&gt; getCommandList() {
 * 		return new CommandStringBuilder(&quot;ls&quot;).addFlagCommand(&quot;-lh&quot;).build();
 * 	}
 * };
 * int exitCode = r.createAndRun();
 * </pre>
 * 
 * This {@code AbstractProcessRunner} will execute {@code ls -lh} on the unix
 * root directory. Exit code of command will be stored in {@code exitCode}.
 * </p>
 * 
 * @author Alexander Kerner
 * @threadSave
 * @see AbstractProgrammRunner
 * 
 */
public abstract class AbstractProcessRunner implements ProcessRunner {

	private final static Log log = new Log(AbstractProcessRunner.class);
	private final File workingDir;

	// Constructor //

	/**
	 * <p>
	 * Constructs a new {@code AbstractProcessRunnner}. Process working
	 * directory is set to {@code workingDir}. {@code workingDir} may be {@code
	 * null} -- this means to use the working directory of the current Java
	 * process, usually the directory named by the system property user.dir, as
	 * the working directory of the child process.
	 * </p>
	 * 
	 * @param workingDir
	 *            process' working directory
	 * 
	 * @see ProcessBuilder#directory(File)
	 */
	public AbstractProcessRunner(File workingDir) {
		this.workingDir = workingDir;
	}

	// Implement //

	public int createAndRun(final OutputStream out, final OutputStream err)
			throws Exception {
		final List<String> processCommandList = getCommandList();
		final ProcessBuilder processBuilder = new ProcessBuilder(
				processCommandList);
		log.debug("creating process " + processBuilder.command());
		processBuilder.directory(workingDir);
		log.debug("working dir of process: " + processBuilder.directory());
		processBuilder.redirectErrorStream(true);
		final Process p = processBuilder.start();
		log.debug("started process " + p);
		IOUtils.inputStreamToOutputStream(p.getInputStream(), out);
		IOUtils.inputStreamToOutputStream(p.getErrorStream(), err);
		final int exit = p.waitFor();
		log.debug("process " + p + " exited with exit code " + exit);
		return exit;
	}

	/**
	 * <p>
	 * Equivalent to
	 * {@link ProcessRunner#createAndRun(OutputStream, OutputStream)}, with
	 * {@link System#out} and {@link System#err} as parameters.
	 * </p>
	 * 
	 * @return exit code of process
	 * @throws Exception
	 *             if anything goes wrong
	 * 
	 */
	public int createAndRun() throws Exception {
		return createAndRun(System.out, System.err);
	}
}
