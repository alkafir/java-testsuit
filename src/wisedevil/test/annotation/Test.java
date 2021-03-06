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
package wisedevil.test.annotation;

import java.lang.annotation.*;

/**
 * Represents a test method.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {

	/**
	 * Whether to run the test or not (<code>false</code> = ignore the test).
	 *
	 * @return <code>true</code> if the test should be run, <code>false</code> if not
	 */
	boolean value() default true;
}

