package de.kerner.commons.other;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class LocalExecutor {

	private final ProcessBuilder builder;

	public LocalExecutor(String binary, String... binaryArgs) {
		List<String> fullCommandString = Arrays.asList(binaryArgs);
		fullCommandString.add(0, binary);
		builder = new ProcessBuilder(fullCommandString);
	}

	public void setWorkingDir(File dir) throws FileNotFoundException {
		if (!dir.exists() || dir.isFile())
			throw new FileNotFoundException("cannot access directory " + dir);
		builder.directory(dir);
	}

	/**
	 * If you dont like your process, invoke it and forget about it!
	 * @throws IOException
	 */
	public void fireAndForget() throws IOException {
		builder.start();
	}

	/**
	 * This method will invoke the process, wait for it to terminate and then
	 * return its exit code.
	 * 
	 * @param in
	 *            <code>InputStream</code>, to which process will print its
	 *            STDOUT.
	 * @param err
	 *            <code>InputStream</code>, to which process will print its
	 *            STDERR.
	 * @param out
	 *            <code>OutputStream</code>, from which process will read its
	 *            input.
	 * @return the exit value of process.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public int fireAndWaitforExit(InputStream in, InputStream err,
			OutputStream out) throws IOException, InterruptedException {
		Process p = builder.start();
		out = p.getOutputStream();
		in = p.getInputStream();
		err = p.getErrorStream();
		return p.waitFor();
	}

}
