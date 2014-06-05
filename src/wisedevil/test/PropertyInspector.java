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

import java.lang.reflect.Field;

/**
 * This class provides support for fast object inspection.
 */
public class PropertyInspector {
	/**
	 * The object's class.
	 */
	private final Class<?> cls;
	
	/**
	 * The object to inspect.
	 */
	private final Object obj;
	
	/**
	 * Initializes a new instance of this class.
	 *
	 * @param obj The object to inspect
	 */
	public PropertyInspector(Object obj) {
		cls = obj.getClass();
		this.obj = obj;
	}
	
	/**
	 * Returns the value of a property inside the inspected object.
	 *
	 * @param name The name of the property
	 *
	 * @return The value of the property as an <code>Object</code>
	 *
	 * @throws IllegalArgumentException if the name of the object does not exist
	 */
	public Object get(String name) throws IllegalArgumentException {
		try {
			Field f = cls.getDeclaredField(name);
			f.setAccessible(true);
			
			return f.get(obj);
		} catch(NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Sets the value of a property inside the inspected object.
	 *
	 * @param name The name of the property
	 * @param value The new value to set
	 *
	 * @throws IllegalArgumentException if the name of the object does not exist
	 */
	public void set(String name, Object value) throws IllegalArgumentException {
		try {
			Field f = cls.getDeclaredField(name);
			f.setAccessible(true);
			
			f.set(obj, value);
		} catch(NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
