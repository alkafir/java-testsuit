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

import java.util.Collection;

/**
 * This class represents a test case.
 *
 * <blockquote>Instances of this class are immutable</blockquote>
 */
public class TestCase {
	/**
	 * The test methods.
	 */
	private final Collection<TestMethod> _meths;
	
	/**
	 * The cleanup method (or null).
	 */
	private final CleanupMethod _cleanup;
	
	/**
	 * The setup method (or null).
	 */
	private final SetupMethod _setup;
	
	/**
	 * The test case info.
	 */
	private final TestCaseInfo _info;
	
	/**
	 * Initializes a new instance of this class.
	 *
	 * @param info The test case info
	 * @param meths The test methods
	 * @param setup The setup method(or <code>null</code>)
	 * @param cleanup The cleanup method (or <code>null</code>)
	 */
	TestCase(TestCaseInfo info, Collection<TestMethod> meths, SetupMethod setup, CleanupMethod cleanup) {
		if(info == null || meths == null)
			throw new NullPointerException();
		
		_info = info;
		_meths = meths;
		_setup = setup;
		_cleanup = cleanup;
	}
	
	/**
	 * Returns the test case info.
	 *
	 * @return The test case info
	 */
	public TestCaseInfo getInfo() { return _info; }
	
	/**
	 * Returns the cleanup method.
	 *
	 * @return The cleanup method
	 */
	public CleanupMethod getCleanup() { return _cleanup; }
	
	/**
	 * Returns the setup method.
	 *
	 * @return The setup method
	 */
	public SetupMethod getSetup() { return _setup; }
	
	/**
	 * Returns the test methods.
	 *
	 * @return The test methods
	 */
	public java.util.Set<TestMethod> getMethods() { return new java.util.LinkedHashSet<TestMethod>(_meths); }
}

