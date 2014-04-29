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

import java.util.Set;

import wisedevil.test.result.AbstractResultManager;

/**
 * Builder class for the TestCaseInfo class.
 *
 * @see TestCaseInfo
 */
public class TestCaseInfoBuilder {
	private boolean _isTimed;
	private int _flows;
	private long _linger;
	private String _name, _desc;
	private Set<Class<? extends AbstractResultManager>> _mgrs;
	private Class<?> _cls;
	
	/**
	 * Initializes a new instance fo this class.
	 *
	 * <p>The default TestCaseInfo class returned by this builder has the following features:
	 * <ul>
	 *  <li><code>class = Object.class</code>
	 *  <li><code>timed = false</code>
	 *  <li><code>flows = 1</code>
	 *  <li><code>name = null</code>
	 *  <li><code>description = null</code>
	 *  <li><code>result managers = {wisedevil.test.result.ConsoleResultManager}</code>
	 * </ul></p>
	 */	
	public TestCaseInfoBuilder() {
		_cls = Object.class;
		_isTimed = false;
		_flows = 1;
		_linger = 10_000;
		_name = null;
		_desc = null;
		_mgrs = new java.util.LinkedHashSet<Class<? extends AbstractResultManager>>();
		
		// Populate default managers
		_mgrs.add(wisedevil.test.result.ConsoleResultManager.class);
	}

	/**
	 * Sets whether the tests should be timed.
	 *
	 * @param t True if the tests should be timed
	 */
	public TestCaseInfoBuilder isTimed(boolean t) { _isTimed = t; return this; }
	
	/**
	 * Sets the number of test flows (threads) to be run at once.
	 *
	 * @param f The number of test flows to be run at once
	 */
	public TestCaseInfoBuilder setFlows(int f) { _flows = f; return this; }
	
	/**
	 * Sets the maximum number of msecs to wait before killing a test flow.
	 *
	 * @param l The maximum number of msecs to wait before killing a test flow
	 */
	public TestCaseInfoBuilder setMaxLingerTime(long l) { _linger = l; return this; }
	
	/**
	 * Sets the name of the test case.
	 *
	 * @param n The name of the test case
	 */
	public TestCaseInfoBuilder setName(String n) { _name = n; return this; }
	
	/**
	 * Sets the description of the test case.
	 *
	 * @param d The description of the test case
	 */
	public TestCaseInfoBuilder setDescription(String d) { _desc = d; return this; }

	/**
	 * Sets the result managers for the test case.
	 *
	 * @param m The result manager collection
	 */
	public TestCaseInfoBuilder setResultManagers(java.util.Collection<Class<? extends AbstractResultManager>> m) {
		_mgrs = new java.util.LinkedHashSet<Class<? extends AbstractResultManager>>(m);
		return this;
	}
	
	/**
	 * Sets the test case class.
	 *
	 * @param c The test case class
	 */
	public TestCaseInfoBuilder setTestCaseClass(Class<?> c) {
		_cls = c;
		return this;
	}
	
	/**
	 * Builds a new TestCaseInfo instance.
	 *
	 * @return A new TestCaseInfo instance
	 */
	public TestCaseInfo build() {
		return new TestCaseInfo(
			_cls,
			_name,
			_desc,
			_flows,
			_linger,
			_isTimed,
			_mgrs
		);
	}
}

