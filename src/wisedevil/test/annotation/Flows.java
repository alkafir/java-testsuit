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
 * Sets the number of flows that should be run in parallel and the timeout
 * after which they get killed.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Flows {
	/**
	 * The maximum number of threads to execute.
	 */
	int value() default 4;
	
	/**
	 * The maximum number of milliseconds to wait before forcing
	 * a test sequence to stop (0 = no timeout).
	 *
	 * <b>NOTE</b>: This value MUST be positive.
	 */
	long time() default 10_000;
}

