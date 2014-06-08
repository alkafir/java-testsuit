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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * This class provides support for fast method inspection.
 */
public class MethodInspector {
	/**
	 * The object containing the method to inspect.
	 */
	private final Object obj;

	/**
	 * The method to inspect.
	 */
	private final Method meth;

	/**
	 * Initializes a new instance of this class.
	 * 
	 * @param obj The object containing the method to inspect
	 * @param methname The name of the method to inspect
	 * @param parms The parameter classes (in order) of the method to inspect
	 *
	 * @throws IllegalArgumentException if the method could not be found due to invalid arguments passed to this constructor
	 * @throws NullPointerException if any of the arguments is null
	 */
	public MethodInspector(Object obj, String methname, Class<?>... parms) {
		if(obj == null || Arrays.stream(parms).filter(x -> x == null).count() > 0)
			throw new NullPointerException();

		Class<?> cls = obj.getClass();
		
		try {
			meth = cls.getDeclaredMethod(methname, parms);
			meth.setAccessible(true);
		} catch(NoSuchMethodException e) {
			throw new IllegalArgumentException(e);
		}

		this.obj = obj;
	}

	/**
	 * Invokes the inspected method.
	 *
	 * @param parms The method parameters
	 *
	 * @return The method result as an object
	 *
	 * @throws IllegalArgumentException If the number of arguments is wrong, if they are not in the right order or if any of them isn't the right type
	 * @throws InvocationTargetException If the inspected method throws an exception
	 */
	public Object invoke(Object... parms) throws InvocationTargetException {
		try {
			return meth.invoke(obj, parms);
		} catch(IllegalAccessException e) {
			e.printStackTrace();
			System.exit(-1);
			return null;
		}
	}
}

