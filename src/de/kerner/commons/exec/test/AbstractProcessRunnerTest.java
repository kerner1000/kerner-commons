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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.kerner.commons.exec.AbstractProcessRunner;
import de.kerner.commons.exec.CommandStringBuilder;

/**
 * @author Alexander Kerner
 * 
 */
public class AbstractProcessRunnerTest {

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
	 * Test method for
	 * {@link de.kerner.commons.exec.AbstractProcessRunner#createAndRun()}
	 */
	@Test
	public final void testCreateAndStartProcess01() {
		try {
			final AbstractProcessRunner r = new AbstractProcessRunner(new File(
					"/home/alex/")) {
				public List<String> getCommandList() {
					return new CommandStringBuilder("echo").addFlagCommand(
							"hans").build();
				}
			};
			assertEquals(0, r.createAndRun());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.exec.AbstractProcessRunner#createAndRun()}
	 */
	@Test
	public final void testCreateAndStartProcess02() {
		try {
			final AbstractProcessRunner r = new AbstractProcessRunner(new File(
					"/home/alex/")) {
				public List<String> getCommandList() {
					return new CommandStringBuilder("ls").addFlagCommand("-lh")
							.build();
				}
			};
			assertEquals(0, r.createAndRun());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test method for
	 * {@link de.kerner.commons.exec.AbstractProcessRunner#createAndRun()}
	 */
	@Test
	public final void testCreateAndStartProcess03() {
		try {
			final AbstractProcessRunner r = new AbstractProcessRunner(new File(
					"/")) {
				public List<String> getCommandList() {
					return new CommandStringBuilder("ls").addFlagCommand("-lh")
							.build();
				}
			};
			assertEquals(0, r.createAndRun());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link de.kerner.commons.exec.AbstractProcessRunner#createAndRun()}
	 */
	@Test
	public final void testCreateAndStartProcess04() {
		try {
			final AbstractProcessRunner r = new AbstractProcessRunner(null) {
				public List<String> getCommandList() {
					return new CommandStringBuilder("ls").addFlagCommand("-lh")
							.build();
				}
			};
			assertEquals(0, r.createAndRun());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
