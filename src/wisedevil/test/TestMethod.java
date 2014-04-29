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

import wisedevil.test.result.TestResult;

/**
 * Represents a test function.
 *
 * <blockquote>Instances of this class are immutable</blockquote>
 */
public class TestMethod extends AbstractTestMethod {
	/**
	 * The test result (<code>null</code> if the test has not yet been run).
	 */
	private TestResult _result = null;
	
	/**
	 * True if the test must be timed.
	 */
	private final boolean _timed;
	
	/**
	 * Initializes a new instance of this class.
	 *
	 * <p>Same as calling <code>TestMethod(inst, meth, null)</code>.</p>
	 *
	 * @param inst The test case instance
	 * @param meth The test method
	 */
	public TestMethod(Object inst, java.lang.reflect.Method meth) {
		this(inst, meth, false);
	}
	
	/**
	 * Initializes a new instance of this class.
	 *
	 * @param inst The test case instance
	 * @param meth The test method
	 * @param timed True if the test must be timed
	 */
	public TestMethod(Object inst, java.lang.reflect.Method meth, boolean timed) {
		super(inst, meth);
		
		_timed = timed;
	}
	
	/**
	 * Runs the test.
	 */
	 public void run() {
		boolean tResult = false; // FAILED
		String message = null;
		long time = TestResult.NOT_TIMED;
		
		try { // Execute test
			if(_timed)
				time = System.nanoTime();
			
			_meth.invoke(_inst);
			
			if(_timed)
				time = System.nanoTime() - time;
			
			tResult = true; // PASSED
		} catch (Exception e) {
			if(!(e.getCause() instanceof TestFailedException) && !(e.getCause() instanceof AssertionError)) {
				System.out.println("UNHANDLED EXCEPTION THROWN");
				e.printStackTrace();
			} else {
				message = e.getCause().getMessage();
				time = TestResult.NOT_TIMED; // Don't track time on failure
			}
		}
		
		_result = new TestResult(_meth.getName(), tResult, message, time);
	 }
	 
	 /**
	  * Returns the test result or <code>null</code> if the test has not yet
	  * been run.
	  *
	  * @return The test result
	  */
	 public TestResult getResult() { return _result; }
}

