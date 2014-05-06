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
package wisedevil.test.result;

import java.util.Set;
import java.util.HashSet;

import wisedevil.test.TestCaseInfo;

/**
 * Abstract class for result managers.
 */
public abstract class AbstractResultManager implements ResultExporter {
	/**
	 * The result set.
	 */
	private HashSet<TestResult> _results;
	
	/**
	 * The test case info.
	 */
	private TestCaseInfo _info;
	
	/**
	 * Initializes a new instance of this class.
	 */
	protected AbstractResultManager() {
		_results = new HashSet<TestResult>();
	}
	
	/**
	 * Adds a test result to the result set.
	 *
	 * @param r The test result to be added
	 */
	public void add(TestResult r) {
		_results.add(r);
	}
	
	/**
	 * Adds an iterable object of test results to the result set.
	 *
	 * @param r A test result iterable object to be added
	 */
	public void add(Iterable<TestResult> r) {
		for(TestResult tr: r)
			_results.add(tr);
	}
	
	/**
	 * Export the test results in the required format.
	 */
	public abstract void exportResults();
	
	/**
	 * Returns the test results.
	 *
	 * @return The result set
	 */
	protected Set<TestResult> getResults() { return _results; }
	   
	/**
	 * Returns the test case information object.
	 *
	 * @return The test case information object
	 */
	protected TestCaseInfo getTestCaseInfo() { return _info; }
	
	/**
	 * Sets the test case information object.
	 *
	 * @param info The test case information object
	 */
	public void setTestCaseInfo(TestCaseInfo info) { _info = info; }
}
