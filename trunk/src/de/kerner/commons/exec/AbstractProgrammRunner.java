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
import java.util.ArrayList;
import java.util.List;

import de.kerner.commons.file.FileUtils;
import de.kerner.commons.logging.Log;

/**
 * <p>
 * An implementation of {@code AbstractProgrammRunner} will create and execute
 * operating system processes. It will also help to take care of preconditions
 * and postconditions of execution. Therefore calling
 * {@link AbstractProgrammRunner#createAndRun()} or
 * {@link AbstractProgrammRunner#createAndRun(OutputStream, OutputStream)} will
 * perform as follows:
 * <ul>
 * <li>{@link AbstractProgrammRunner#prepare()} is called to take care of any
 * preconditions.</li>
 * <li>possibility to shortcut execution is checked. That means if all files,
 * that have been previously defined by
 * {@link AbstractProgrammRunner#addShortCutFile(File)} are accessible,
 * operating system process is not executed and an exit value of {@code 0} is
 * returned.</li>
 * <li>if no shortcut is possible, execution of process is triggered. If any
 * files have been defined by
 * {@link AbstractProgrammRunner#redirectOutStreamToFile(File)} or
 * {@link AbstractProgrammRunner#redirectErrStreamToFile(File)}, {@code stdout}
 * respective {@code stderr} are written to coresponding files.</li>
 * <li>if exit code was {@code 0},
 * {@link AbstractProgrammRunner#finsishSuccess()} is called; otherwise
 * {@link AbstractProgrammRunner#finishError()}.</li>
 * </ul>
 * </p>
 * <p>
 * Example: TODO
 * </p>
 * 
 * @author Alexander Kerner
 * @see AbstractProcessRunner
 * @threadSave
 * 
 */
public abstract class AbstractProgrammRunner implements ProcessRunner {

	private final static Log log = new Log(AbstractProgrammRunner.class);
	private volatile long timeout = 1000;
	private final List<File> shortCutFiles = new ArrayList<File>();
	private final List<File> resultFilesToWaitFor = new ArrayList<File>();
	private volatile File outFile = null;
	private volatile File errFile = null;
	protected final File workingDir;

	/**
	 * <p>
	 * Constructs a new {@code AbstractProgrammRunnner}. Process working
	 * directory is set to {@code workingDir}. {@code workingDir} may be {@code
	 * null} -- this means to use the working directory of the current Java
	 * process, usually the directory named by the system property user.dir, as
	 * the working directory of the child process.
	 * </p>
	 * 
	 * @param workingDir
	 */
	public AbstractProgrammRunner(File workingDir) {
		this.workingDir = workingDir;
	}
	
	public AbstractProgrammRunner(String workingDir) {
		this.workingDir = new File(workingDir);
	}

	// Private //

	private boolean takeShortCut() {
		log.debug("checking for shortcut available");
		if (shortCutFiles.isEmpty()) {
			log.debug("no shortcut files defined");
			return false;
		}
		for (File f : shortCutFiles) {
			final boolean fileCheck = FileUtils.fileCheck(f, false);
			log.debug("file " + f.getAbsolutePath() + " there=" + fileCheck);
			if (!(fileCheck)) {
				log.debug("file \"" + f + "\" not there, cannot shortcut");
				return false;
			}
		}
		log.debug("taking shortcut");
		return true;
	}

	/**
	 * <p>
	 * Helper class to be able to access {@link this#getCommandList()}
	 * </p>
	 * 
	 * @author Alexander Kerner
	 * 
	 */
	private class Hans extends AbstractProcessRunner {

		private final AbstractProgrammRunner parent;

		public Hans(File workingDir, AbstractProgrammRunner parent) {
			super(workingDir);
			this.parent = parent;
		}

		public List<String> getCommandList() {
			return parent.getCommandList();
		}

	}

	private int doItFinally(OutputStream out, OutputStream err)
			throws Exception {
		int exitCode = 0;
		if (outFile != null) {
			out = FileUtils.getBufferedOutputStreamForFile(outFile);
		}
		if (errFile != null) {
			err = FileUtils.getBufferedOutputStreamForFile(errFile);
		}
		exitCode = new Hans(workingDir, this).createAndRun(out, err);
		if (outFile != null) {
			out.close();
		}
		if (errFile != null) {
			err.close();
		}
		return exitCode;
	}

	private void waitForFiles() throws InterruptedException {
		if (resultFilesToWaitFor.isEmpty()) {
			log.debug("no files to wait for");
			return;
		}
		for (File f : resultFilesToWaitFor) {
			synchronized (f) {
				while (!f.exists()) {
					log.debug("waiting for file \"" + f + " \"");
					Thread.sleep(timeout);
				}
			}
		}
	}

	// Public //

	/**
	 * <p>
	 * Override this method to meet any preconditions before operation system
	 * process is actually started.
	 * </p>
	 * <p>
	 * By default, this method does nothing.
	 * </p>
	 * 
	 * @throws Exception
	 */
	public void prepare() throws Exception {
		// do nothing
	}

	/**
	 * <p>
	 * This method is called after successful termination of operating system
	 * process ({@code exit code == 0}).
	 * </p>
	 * <p>
	 * By default, this method does nothing.
	 * </p>
	 * 
	 * @throws Exception
	 */
	public void finsishSuccess() throws Exception {
		// do nothing
	}

	/**
	 * <p>
	 * This method is called after unsuccessful termination of operating system
	 * process ({@code exit code != 0}).
	 * </p>
	 * <p>
	 * By default, this method does nothing.
	 * </p>
	 * 
	 * @throws Exception
	 */
	public void finishError() throws Exception {
		// do nothing
	}

	/**
	 * <p>
	 * If any files have been defined via
	 * {@link AbstractProgrammRunner#addResultFileToWaitFor(File)}, current
	 * thread will check accessibility for these files every {@code millisecs}
	 * milliseconds. If one of these files are not accessible jet for any reason
	 * (file system issues, remote execution etc.) current thread will wait
	 * {@code millisecs} milliseconds and then do check again.
	 * </p>
	 * <p>
	 * <b>Attention:</b> if one of these files will never be accessible thread
	 * will wait forever!
	 * </p>
	 * 
	 * 
	 * 
	 * @param millisecs
	 *            milliseconds to wait before next accessibility check
	 */
	public void setWaitDelay(long millisecs) {
		this.timeout = millisecs;
	}

	/**
	 * <p>
	 * Returns current waiting delay in milliseconds.
	 * </p>
	 * 
	 * @return current waiting delay
	 * @see AbstractProgrammRunner#addResultFileToWaitFor(File)
	 * @see AbstractProgrammRunner#setWaitDelay(long)
	 */
	public long getWaitDelay() {
		return timeout;
	}

	/**
	 * <p>
	 * Defines a file for shortcutting execution. That means if all files, that
	 * have been defined via this method are accessible before actually
	 * executing operating system process, this process will not be executed but
	 * {@link AbstractProgrammRunner#createAndRun()} or
	 * {@link AbstractProgrammRunner#createAndRun(OutputStream, OutputStream)}
	 * will return {@code 0}.
	 * </p>
	 * 
	 * @param file
	 *            Shortcut file
	 */
	public synchronized void addShortCutFile(File file) {
		shortCutFiles.add(file);
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @param file
	 */
	public synchronized void addResultFileToWaitFor(File file) {
		resultFilesToWaitFor.add(file);
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @param file
	 */
	public void redirectOutStreamToFile(File file) {
		this.outFile = file;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @param file
	 */
	public void redirectErrStreamToFile(File file) {
		this.errFile = file;
	}

	// Implement //

	public int createAndRun(OutputStream out, OutputStream err)
			throws Exception {
		int exitCode = 0;
		prepare();
		if (takeShortCut())
			return exitCode;
		exitCode = doItFinally(out, err);
		if (exitCode == 0) {
			waitForFiles();
			finsishSuccess();
		} else {
			finishError();
		}
		return exitCode;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 */
	public int createAndRun() throws Exception {
		return createAndRun(System.out, System.err);
	}

}
