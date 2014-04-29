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
 * This class contains the methods to be used to test the code.
 * <p>Note that there is no <code>assert()</code> method, since you can use the
 * Java <code>assert</code> keyword. If the assertion fails, the test suit
 * will recognize it and fail the test.</p>
 */
public final class Assert {
	private Assert() {}
	
	/**
	 * Immediately end the test.
	*/
	public static void fail() {
		throw new TestFailedException();
	}
	
	/**
	 * Immediately end the test.
	 *
	 * @param msg The fail message
	*/
	public static void fail(String msg) {
		throw new TestFailedException(msg);
	}
	
	/**
	 * Fail the test if the supplied exception is not thrown.
	 *
	 * @param code The code to run
	 * @param exc The expected exception class
	 */
	 public static void assertException(Runnable code, Class<? extends Exception> exc) {
		try {
			code.run();
		} catch(Exception e) {
			if(!(exc.isInstance(e)))
				fail();
		}
	 }
	 
	 /**
	  * Fail the test if any exception is thrown.
	  *
	  * @param code The code to run
	  */
	 public static void assertNoException(Runnable code) {
		try {
			code.run();
		} catch(Exception e) {
			fail();
		}
	 }
	 
	 /**
	  * Fail the test if <code>inst</code> is not an instance of <code>cls</code>
	  *
	  * @param inst The instance to test
	  * @param cls The class to test the instance against
	  */
	 public static void assertInstance(Object inst, Class<?> cls) {
		if(!cls.isInstance(inst))
			fail();
	 }
	 
	 /**
	  * Fail the test if at least one of the <code>args</code>
	  * is not null.
	  *
	  * @param args The arguments to check for nullity
	  */
	 public static<T> void assertNull(Object... args) {
		for(Object arg: args)
			if(arg != null)
				fail();
	 }
	 
	 /**
	  * Fail the test if at least one of the <code>args</code>
	  * is null.
	  *
	  * @param args The arguments to check for nullity
	  */
	 public static<T> void assertNotNull(Object... args) {
		for(Object arg: args)
			if(arg == null)
				fail();
	 }
	 
}
