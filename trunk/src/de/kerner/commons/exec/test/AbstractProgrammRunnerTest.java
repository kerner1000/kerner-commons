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

package de.kerner.commons.exec.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.kerner.commons.exec.AbstractProcessRunner;
import de.kerner.commons.exec.AbstractProgrammRunner;
import de.kerner.commons.exec.CommandStringBuilder;

/**
 * @author Alexander Kerner
 *
 */
public class AbstractProgrammRunnerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link de.kerner.commons.exec.AbstractProgrammRunner#setWaitDelay(long)}.
	 */
	@Test
	@Ignore
	public final void testSetWaitDelay() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.exec.AbstractProgrammRunner#addShortCutFile(java.io.File)}.
	 */
	@Test
	@Ignore
	public final void testAddShortCutFile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.exec.AbstractProgrammRunner#addResultFileToWaitFor(java.io.File)}.
	 */
	@Test
	@Ignore
	public final void testAddResultFileToWaitFor() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.exec.AbstractProgrammRunner#redirectOutStreamToFile(java.io.File)}.
	 */
	@Test
	@Ignore
	public final void testRedirectOutStreamToFile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.exec.AbstractProgrammRunner#redirectErrStreamToFile(java.io.File)}.
	 */
	@Test
	@Ignore
	public final void testRedirectErrStreamToFile() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.exec.AbstractProgrammRunner#createAndRun(java.io.OutputStream, java.io.OutputStream)}.
	 */
	@Test
	@Ignore
	public final void testCreateAndRunOutputStreamOutputStream() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.kerner.commons.exec.AbstractProgrammRunner#createAndRun()}.
	 */
	@Test
	public final void testCreateAndRun() {
		final AbstractProgrammRunner p = new AbstractProgrammRunner(new File("/home/alex/Desktop/test")) {
			
			public List<String> getCommandList() {
				return new CommandStringBuilder("touch").addFlagCommand("toll").build();
			}
			
			@Override
			public void prepare() throws Exception {
				new AbstractProcessRunner(new File("/home/alex/Desktop/")) {
					public List<String> getCommandList() {
						return new CommandStringBuilder("mkdir").addFlagCommand("test").build();
					}
				}.createAndRun();
			}
			
			@Override
			public void finsishSuccess() throws Exception {
				System.out.println("toll!");
			}
			
			@Override
			public void finishError() throws Exception {
				System.out.println("nicht toll");
			}
		};
		int i = -1;
		p.addShortCutFile(new File("/home/alex/Desktop/test/toll"));
		
		try {
			i = p.createAndRun();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(0, i);
	}

}
