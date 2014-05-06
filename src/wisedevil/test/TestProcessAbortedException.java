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

import wisedevil.test.util.ErrorCode;

/**
 * Thrown to indicate that a test has failed.
 */
public class TestProcessAbortedException extends Exception {
	private static final long serialVersionUID = 0;
	
	private static final String[] _abortMessages = new String[] {
		"Unkown error",
		"Argument error",
		"Invalid file error",
		"Internal error"
	};
	
	/**
	 * The abort code.
	 */
	private final int _code;
	
	/**
	 * Initializes a new instance of this class, setting its error code.
	 *
	 * @param code The error code
	 */
	 public TestProcessAbortedException(ErrorCode code) {
	 	 _code = code.ordinal();
	 }
	
	/**
	 * @see java.lang.Exception
	 */
	public TestProcessAbortedException() {
		super();
		_code = 0;
	}
	
	/**
	 * @param message The detail message
	 *
	 * @see java.lang.Exception
	 */
	public TestProcessAbortedException(String message) {
		super(message);
		_code = -1;
	}
	
	/**
	 * @param message The detail message
	 * @param cause The cause
	 *
	 * @see java.lang.Exception
	 */
	public TestProcessAbortedException(String message, Throwable cause) {
		super(message, cause);
		_code = 0;
	}
	
	/**
	 * @param message The detail message
	 * @param cause The cause
	 * @param enableSuppression Whether or not suppression is enabled
	 * @param writableStackTrace Whether or not the stack trace should be writable
	 *
	 * @see java.lang.Exception
	 */
	public TestProcessAbortedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		_code = 0;
	}
	
	/**
	 * @param cause The cause
	 *
	 * @see java.lang.Exception
	 */
	public TestProcessAbortedException(Throwable cause) {
		super(cause);
		_code = 0;
	}
	
	/**
	 * Returns the test abort code.
	 *
	 * @return The abort code
	 */
	public int getAbortCode() { return _code; }
	
	
	/**
	 * Returns the message associated with the abort code (or the custom
	 * specified message). An empty string is returned for unknown codes.
	 *
	 * @return The message for the error code
	 */
	@Override
	public String getMessage() {
		if(_code == 0)
			return super.getMessage();
		else try {
			return _abortMessages[_code];
		} catch(IndexOutOfBoundsException e) {
			return new String();
		}
	}
}

