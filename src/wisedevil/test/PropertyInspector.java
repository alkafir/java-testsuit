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
 * This class provides support for fast property inspection.
 */
public class PropertyInspector {
	/**
	 * The object containing the property to inspect.
	 */
	private final Object obj;

	/**
	 * The field to inspect.
	 */
	private final Field fld;
	
	/**
	 * Initializes a new instance of this class.
	 *
	 * @param obj The object containing the property
	 * @param prop The property to inspect
	 *
	 * @throws IllegalArgumentException if the name of the property is invalid
	 * @throws NullPointerException if any of the arguments is <code>null</code>
	 */
	public PropertyInspector(Object obj, String prop) throws IllegalArgumentException, NullPointerException {
		if(obj == null || prop == null)
			throw new NullPointerException();

		this.obj = obj;
		fld = initializeField(obj, prop);
	}
	
	/**
	 * Returns the field of a property inside the inspected object.
	 *
	 * <blockquote>NOTE: The returned field is always an accessible field</blockquote>
	 *
	 * @param obj The object containing the property
	 * @param prop The property to inspect
	 *
	 * @throws IllegalArgumentException if the name of the object does not exist
	 */
	public Field initializeField(Object obj, String prop) throws IllegalArgumentException {
		try {
			Class<?> cls = obj.getClass();
			Field f = cls.getDeclaredField(prop);
			f.setAccessible(true);
			
			return f;
		} catch(Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Sets the value of the inspected property.
	 *
	 * @param value The new value to set
	 *
	 * @throws IllegalArgumentException if the provided value is invalid
	 */
	public void set(Object value) throws IllegalArgumentException {
		try {
			fld.set(obj, value);
		} catch(Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Gets the value of the inspected property.
	 *
	 * @return The value of the inspected property as an <code>Object</code>
	 */
	public Object get() {
		try {
			return fld.get(obj);
		} catch(Exception e) { // Should never happen
			e.printStackTrace();
			System.exit(-1);
			return null;
		}
	}
}
