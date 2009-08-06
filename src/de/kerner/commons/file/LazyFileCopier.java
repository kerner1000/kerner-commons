package de.kerner.commons.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LazyFileCopier {

	private final InputStream inStream;
	private final File outFile;

	public LazyFileCopier(File in, File out) throws IOException {
		if (!FileUtils.fileCheck(in, false))
			throw new FileNotFoundException("cannot read " + in);
		this.inStream = FileUtils.getInputStreamFromFile(in);
		this.outFile = out;
	}

	public LazyFileCopier(String inFilePath, String outFilePath)
			throws IOException {
		final File in = new File(inFilePath);
		if (!FileUtils.fileCheck(in, false))
			throw new FileNotFoundException("cannot read " + in);
		this.inStream = FileUtils.getInputStreamFromFile(in);
		this.outFile = new File(outFilePath);
	}

	public LazyFileCopier(File inFile, File outDir, String outFileName)
			throws IOException {
		if (!FileUtils.fileCheck(inFile, false))
			throw new FileNotFoundException("cannot read " + inFile);
		this.inStream = FileUtils.getInputStreamFromFile(inFile);
		this.outFile = new File(outDir, outFileName);
	}

	public long copy() throws IOException {
		final InputStreamReader reader = new InputStreamReader(inStream);
		final BufferedReader br = new BufferedReader(reader);
		final FileWriter writer = new FileWriter(outFile);
		final BufferedWriter bw = new BufferedWriter(writer);
		long r = 0;
		try {
			r = FileUtils.readerToWriter(br, bw);
		} finally {
			bw.flush();
			bw.close();
			br.close();
		}
		return r;
	}

	public static void main(String[] args) {
		try {
			System.out.println(new LazyFileCopier(new File(
					"/home/pcb/kerner/anna/contradTrain/trainingFile.bin"), new File(
					"/home/pcb/kerner/Desktop/trainingFile.bin")).copy());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
