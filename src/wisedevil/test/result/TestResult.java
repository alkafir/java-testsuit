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

import java.io.Serializable;

/**
 * Contains a test result.
 *
 * <blockquote>Instances of this class are immutable.</blockquote>
 */
public class TestResult implements Comparable<TestResult>, Serializable {
	private static final long serialVersionUID = 0;
	
	public static final long NOT_TIMED = -1L;
	
	/**
	 * Test name
	 */
	public final String name;
	
	/**
	 * Test result (<code>true</code> = passed, <code>false</code> = failed)
	 */
	public final boolean result;
	
	/**
	 * Fail message (<code>null</code> if none)
	 */
	public final String message;
	
	/**
	 * Test execution time.
	 */
	public final long time;
	
	/**
	 * Initializes a new instance of this class.
	 * <blockquote>Same as calling <code>TestResult(name, result, null)</code></blockquote>.
	 *
	 * @param name The test function name
	 * @param result The test result (passed = <code>true</code>)
	 */
	public TestResult(String name, boolean result) {
		this(name, result, null);
	}
	
	/**
	 * Initializes a new instance of this class.
	 *
	 * <blockquote>Same as calling <code>TestResult(name, result, message, NOT_TIMED)</code></blockquote>
	 *
	 * @param name The test function name
	 * @param result The test result (passed = <code>true</code>)
	 * @param message An optional failure message or <code>null</code>
	 */
	public TestResult(String name, boolean result, String message) {
		this(name, result, message, NOT_TIMED);
	}
	
	/**
	 * Initializes a new instance of this class.
	 *
	 * <blockquote>Same as calling <code>TestResult(name, result, null, time)</code></blockquote>
	 *
	 * @param name The test function name
	 * @param result The test result (passed = <code>true</code>)
	 * @param time The test execution time (or <code>TestResult.NOT_TIMED</code>).
	 */
	public TestResult(String name, boolean result, long time) {
		this(name, result, null, time);
	}
	
	/**
	 * Initializes a new instance of this class.
	 *
	 * @param name The test function name
	 * @param result The test result (passed = <code>true</code>)
	 * @param message An optional failure message or <code>null</code>
	 * @param time The test execution time (or <code>TestResult.NOT_TIMED</code>).
	 */
	public TestResult(String name, boolean result, String message, long time) {
		this.name = name;
		this.result = result;
		this.message = message;
		this.time = time;
	}
	
	/**
	 * Returns the hascode of the test name.
	 *
	 * @return The hashcode of the test (from its name)
	 *
	 * @see java.lang.String#hashCode()
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * Compare two tests with regard to their name.
	 *
	 * @see java.lang.String#compareTo(java.lang.String)
	 */
	public int compareTo(TestResult o) {
		return name.compareTo(o.name);
	}
}
