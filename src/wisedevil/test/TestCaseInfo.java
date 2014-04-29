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
 * This class contains information about a test case.
 *
 * <blockquote>Instances of this class are immutable</blockquote>
 */
public class TestCaseInfo {
	/**
	 * The maximum number of test flows (threads) to be run at once.
	 */
	private final int _flows;
	
	/**
	 * The maximum number of msecs to wait before killing a test flow.
	 */
	private final long _linger;
	
	/**
	 * True if the test case should be timed.
	 */
	 private final boolean _timed;
	 
	 /**
	  * The name of the test case.
	  */
	 private final String _name;
	 
	 /**
	  * The description of the test case.
	  */
	 private final String _desc;
	 
	 /**
	  * The result manager set of the test case.
	  */
	 private Set<Class<? extends AbstractResultManager>> _mgrs;
	 
	 /**
	  * The test case class.
	  */
	 private Class<?> _cls;
	 
	 /**
	 * Initializes a new instance of this class.
	 *
	 * @param cls The test case class
	 * @param name The test case name
	 * @param desc The test case description
	 * @param flows The number of test flows (threads) to be run at once (only useful if <code>parallel = true</code>)
	 * @param linger The maximum number of msecs to wait before killing a test flow
	 * @param timed True if the test case should be timed
	 * @param mgrs The result manager class set for the test case
	 */
	TestCaseInfo(Class<?> cls, String name, String desc, int flows, long linger, boolean timed, Set<Class<? extends AbstractResultManager>> mgrs) {
		_cls = cls;
		_name = name;
		_desc = desc;
		_flows = flows;
		_linger = Math.abs(linger); // Negative time is not allowed (to be fixed as soon as we learn to rewind time)
		_timed = timed;
		_mgrs = mgrs;
	}

	/**
	 * Returns the maximum number of flows to be executed in parallel.
	 *
	 * <p><b>NOTE:</b> this method always returns 1, if <code>isParallel() == false</code>.</p>
	 *
	 * @return The number of flows to run in parallel
	 */
	public int getFlows() { return _flows; }
	
	
	/**
	 * Returns the linger time.
	 *
	 * @return The maximum number of msecs to wait before killing a test flow (always a positive number)
	 */
	public long getLingerTime() { return _linger; }
	
	/**
	 * Returns true if the test case should be timed.
	 *
	 * @return True if the test case should be timed
	 */
	public boolean isTimed() { return _timed; }
	
	/**
	 * Returns the result manager set.
	 *
	 * @return The result manager set
	 */
	public Set<Class<? extends AbstractResultManager>> getResultManagers() { return new java.util.LinkedHashSet<Class<? extends AbstractResultManager>>(_mgrs); }
	
	/**
	 * Returns the test case name.
	 *
	 * @return The test case name
	 */
	public String getName() { return _name; }
	
	/**
	 * Returns the test case description.
	 *
	 * @return The test case description
	 */
	public String getDescription() { return _desc; }
	
	/**
	 * Returns the test case class.
	 *
	 * @return The test case class
	 */
	public Class<?> getTestCaseClass() { return _cls; }
}

