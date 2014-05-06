/* testsuit: Unit test framework for Java
 *	Copyright (C) 2013  Alfredo Mungo
 *	
 *	This program is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	This program is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package wisedevil.test;

import java.io.File;
import java.io.IOException;

import java.util.Set;

import wisedevil.test.result.AbstractResultManager;
import wisedevil.test.result.TestResult;

import wisedevil.test.util.ErrorCode;
import wisedevil.test.util.FileClassLoader;

/**
 * Represents a test case process for a class file.
 *
 * <blockquote>Instances of this class are immutable</blockquote>
 */
public class TestCaseProcess {
	/**
	 * The test case class.
	 */
	private final Class<?> _cls;
	
	/**
	 * Initializes a new instance of this class.
	 *
	 * @param fClass The test class file
	 *
	 * @throws TestProcessAbortedException if an error occurs during class initialization
	 */
	public TestCaseProcess(File fClass) throws TestProcessAbortedException {
		Class<?> cls = null;
		
		if(fClass.exists() && fClass.isFile() && fClass.canRead()) {
			try {
				cls = (new FileClassLoader().loadClassFromFile(fClass));
			} catch(IOException e) {
				throw new TestProcessAbortedException(ErrorCode.ERR_INVALID_FILE);
			} catch(ClassFormatError e) {
				throw new TestProcessAbortedException(ErrorCode.ERR_INVALID_FILE);
			} catch(Exception e) {
				e.printStackTrace();
				throw new TestProcessAbortedException(ErrorCode.ERR_INTERNAL);
			}
		} else {
			throw new TestProcessAbortedException(ErrorCode.ERR_INVALID_FILE);
		}
		_cls = cls;
	}
	
	/**
	 * Runs the test case and export its results.
	 *
	 * @throws TestProcessAbortedException if the test case fails unexpectedly
	 */
	public void run() throws TestProcessAbortedException {
		try {
			final TestCaseParser parser = new TestCaseParser(_cls);
			Object inst = null;

			try {
				inst = _cls.newInstance();
			} catch(InstantiationException e) {
				System.err.println(_cls.getSimpleName() + " class is not instantiable");
				throw new TestProcessAbortedException(ErrorCode.ERR_CLASS);
			} catch(IllegalAccessException e) {
				System.err.println("Cannot access constructor for class " + _cls.getSimpleName());
				throw new TestProcessAbortedException(ErrorCode.ERR_CLASS);
			} catch(ExceptionInInitializerError e) {
				System.err.println("Thrown exception in constructor for class " + _cls.getSimpleName());
				throw new TestProcessAbortedException(ErrorCode.ERR_CLASS);
			} catch(SecurityException e) {
				e.printStackTrace();
				throw new TestProcessAbortedException(ErrorCode.ERR_CLASS);
			}
			
			final TestCase tcase = parser.parseNew(inst);
			final TestCaseRunner runner = new TestCaseRunner(tcase);
			
			// Run the tests
			final Iterable<TestResult> results = runner.run();
			
			// Get the result manager
			final Set<Class<? extends AbstractResultManager>> exporters = tcase.getInfo().getResultManagers();
			
			for(Class<? extends AbstractResultManager> arm: exporters)
				exportResults(arm.newInstance(), tcase.getInfo(), results); // Export the results
		} catch(NullPointerException e) {
			System.err.println("Error: No class returned");
			throw new TestProcessAbortedException(ErrorCode.ERR_INTERNAL);
		} catch(TestProcessAbortedException e) {
			throw e; // To upper level
		} catch(Exception e) {
			e.printStackTrace();
			throw new TestProcessAbortedException(ErrorCode.ERR_INTERNAL);
		}
	}
	
	/**
	 * Exports the test results.
	 *
	 * @param rm The result manager to use for exporting results
	 * @param info The test case info
	 * @param results The test results
	 *
	 * @throws TestProcessAbortedException if the result export fails unexpectedly
	 */
	private void exportResults(AbstractResultManager rm, TestCaseInfo info, Iterable<TestResult> results) throws TestProcessAbortedException {
		try {
			rm.setTestCaseInfo(info);
			rm.add(results);
			rm.exportResults();
		} catch(Exception e) {
			e.printStackTrace();
			throw new TestProcessAbortedException(ErrorCode.ERR_INTERNAL);
		}
	}
}
