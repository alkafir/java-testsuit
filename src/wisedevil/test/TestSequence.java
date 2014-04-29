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
 * Represents a runnable linear sequence of test methods.
 */
public class TestSequence implements Runnable {
	
	/**
	 * The test methods.
	 */
	private final TestMethod[] _meths;
	
	/**
	 * The test results.
	 */
	private final TestResult[] _results;
	
	/**
	 * Initializes a new instance of this class.
	 *
	 * @param meths The test methods of the sequence 
	 */
	public TestSequence(TestMethod[] meths) {
		_meths = meths;
		_results = new TestResult[meths.length];
	}
	
	/**
	 * Returns the results of the tests.
	 *
	 * @return The test results
	 */
	public TestResult[] getResults() { return _results; }
	
	/**
	 * Runs the test sequence.
	 */
	public void run() {
		int ridx = 0;
		
		for(TestMethod m: _meths) {
			// Run the method
			m.run();
			
			// Store the result
			_results[ridx++] = m.getResult();
		}
	}
	
	/**
	 * Returns a value stating if the sequence is empty or not.
	 *
	 * @return True if the sequence is empty (contains no methods), false if not.
	 */
	public boolean isEmpty() { return _meths.length == 0; }
}

