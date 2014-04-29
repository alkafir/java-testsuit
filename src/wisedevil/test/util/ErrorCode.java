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
package wisedevil.test.util;

/**
 * This enumeration represents program error codes for the test suit.
 */
public enum ErrorCode {
	/**
	 * Unknown error code.
	 */
	ERR_UNKNOWN,
	
	/**
	 * Argument error code.
	 */
	ERR_ARGUMENT,
	
	/**
	 * Invalid file error code.
	 */
	ERR_INVALID_FILE,
	
	/**
	 * Internal error code.
	 */
	ERR_INTERNAL,
	
	/**
	 * Class error (unable to get constructor, unable to instantiate, constructor thrown exception).
	 */
	ERR_CLASS
}

