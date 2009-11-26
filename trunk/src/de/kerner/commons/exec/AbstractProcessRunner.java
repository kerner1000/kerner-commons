package de.kerner.commons.exec;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import de.kerner.commons.file.FileUtils;
import de.kerner.commons.logging.Log;

public abstract class AbstractProcessRunner {

	private final static Log log = new Log(AbstractProcessRunner.class);
	protected final File executableDir;
	protected final File workingDir;

	public AbstractProcessRunner(File executableDir, File workingDir) {
		this.executableDir = executableDir;
		this.workingDir = workingDir;
	}

	// Abstract //

	protected abstract List<String> getCommandList();

	// Public //

	public int createAndStartProcess(final OutputStream out,
			final OutputStream err) throws Throwable {
		final List<String> processCommandList = getCommandList();
		final ProcessBuilder processBuilder = new ProcessBuilder(
				processCommandList);
		log.debug("creating process " + processBuilder.command());
		processBuilder.directory(executableDir);
		log.debug("executable dir of process: " + processBuilder.directory());
		processBuilder.redirectErrorStream(true);

		final Process p = processBuilder.start();
		log.debug("started process " + p);
		FileUtils.inputStreamToOutputStream(p.getInputStream(), out);
		FileUtils.inputStreamToOutputStream(p.getErrorStream(), err);
		final int exit = p.waitFor();
		log.debug("process " + p + " exited with exit code " + exit);
		return exit;

	}
}
