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

import java.lang.reflect.Method;

/**
 * This class represents a generic method of a test case.
 */
public abstract class AbstractTestMethod implements Runnable {
	/**
	 * The cleanup method.
	 */
	protected final Method _meth;
	
	/**
	 * The test case instance.
	 */
	protected final Object _inst;
	
	/**
	 * Initializes a new instance of this class.
	 *
	 * @param inst The test case instance
	 * @param meth The test method
	 *
	 * @throws java.lang.NullPointerException if any of the arguments is null
	 */
	protected AbstractTestMethod(Object inst, Method meth) {
		if(inst == null || meth == null)
			throw new NullPointerException();
		
		_inst = inst;
		_meth = meth;
	}
	
	public abstract void run();
}

