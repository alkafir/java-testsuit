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

/**
 * This class represents a setup method for a test case.
 *
 * <blockquote>Instances of this class are immutable</blockquote>
 */
public class SetupMethod extends AbstractTestMethod {
	/**
	 * Initializes a new instance of this class.
	 *
	 * @throws java.lang.NullPointerException if any of the arguments is null
	 */
	public SetupMethod(Object inst, java.lang.reflect.Method meth) {
		super(inst, meth);
	}
	
	/**
	 * Runs the setup method.
	 */
	public void run() {
		try {
			_meth.invoke(_inst);
		} catch(Exception e) {
			System.out.println("Error: Unhandled exception thrown in cleanup() method");
			e.printStackTrace();
		}
	}
}

