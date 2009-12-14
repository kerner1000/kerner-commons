package de.kerner.commons.exec;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.kerner.commons.file.FileUtils;
import de.kerner.commons.logging.Log;

public abstract class AbstractProgrammRunner {
	
	private class ThreaddedProgramm implements Callable<Integer> {

		private final AbstractProcessRunner p;
		private final OutputStream out, err;
		
		public ThreaddedProgramm(File exeDir, File workingDir,
				OutputStream out, OutputStream err) {
			this.out = out;
			this.err = err;
			p = new AbstractProcessRunner(exeDir, workingDir) {
				@Override
				protected List<String> getCommandList() {
					return getComdList();
				}
			};
		}

		public Integer call() throws Exception {
			try {
				return p.createAndStartProcess(out, err);
			} catch (Throwable e) {
				throw new ExecutionException(e);
			}
		}
		
	}

	private final static Log log = new Log(AbstractProgrammRunner.class);
	private final ExecutorService exe = Executors.newSingleThreadExecutor();
	private volatile long timeout = 1000;
	private final List<File> shortCutFiles = new ArrayList<File>();
	private final List<File> resultFilesToWaitFor = new ArrayList<File>();
	private volatile File outFile = null;
	private volatile File errFile = null;
	protected volatile File exeDir = FileUtils.WORKING_DIR;
	protected volatile File workingDir = FileUtils.WORKING_DIR;
	
	// Private //
	
	private boolean takeShortCut(){
		log.debug("checking for shortcut available");
		if (shortCutFiles.isEmpty()) {
			log.debug("no shortcut files defined");
			return false;
		}
		for (File f : shortCutFiles) {
			final boolean fileCheck = FileUtils.fileCheck(f, false);
			log.debug("file " + f.getAbsolutePath() + " there=" + fileCheck);
			if (!(fileCheck)) {
				log.debug("cannot skip");
				return false;
			}
		}
		log.debug("skip available");
		return true;
	}
	
	private int doItFinally() throws Exception {
		int exitCode = 0;
		OutputStream out = System.out;
		OutputStream err = System.err;
		if (outFile != null) {
			out = FileUtils.getBufferedOutputStreamForFile(outFile);
		}
		if (errFile != null) {
			err = FileUtils.getBufferedOutputStreamForFile(errFile);
		}
		exitCode = exe.submit(
				new ThreaddedProgramm(exeDir, workingDir, out, err))
				.get();
		exe.shutdown();
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

	// Abstract //

	public abstract List<String> getComdList();

	public abstract void prepare() throws Exception;

	public abstract void finsishSuccess() throws Exception;
	
	public abstract void finishError() throws Exception;
	
	// Public //
	
	public void setWaitDelay(long millisecs){
		this.timeout = millisecs;
	}
	
	public long getWaitDelay(){
		return timeout;
	}
	
	public synchronized void addShortCutFile(File file) {
		shortCutFiles.add(file);
	}

	public synchronized void addResultFileToWaitFor(File file) {
		resultFilesToWaitFor.add(file);
	}
	
	public void redirectOutStreamToFile(File file) {
		this.outFile = file;
	}

	public void redirectErrStreamToFile(File file) {
		this.errFile = file;
	}
	
	public void setExeDir(File exeDir){
		this.exeDir = exeDir;
	}
	
	public void setWorkingDir(File workingDir){
		this.workingDir = workingDir;
	}
	
	public int run() throws Exception {
		int exitCode = 0;
		if (!FileUtils.dirCheck(workingDir, true))
			throw new IOException("cannot access working dir \"" + workingDir + "\"");
		if (!FileUtils.dirCheck(exeDir, true))
			throw new IOException("cannot access executable dir \"" + exeDir + "\"");
		prepare();
		log.debug("created, properties:" + FileUtils.NEW_LINE
				+ "\tstepWorkingDir=" + workingDir + FileUtils.NEW_LINE
				+ "\texeDir=" + exeDir);
		if (takeShortCut()) {
			return exitCode;
		} else {
			exitCode = doItFinally();
		}
		if (exitCode == 0) {
			waitForFiles();
			finsishSuccess();
		} else {
			finishError();
		}
		return exitCode;
	}

}
