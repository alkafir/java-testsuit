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
package wisedevil.test.result;

/**
 * Text result manager that exports results to the screen.
 */
public class ConsoleResultManager extends TextResultManager {

	/**
	 * Initializes a new instance of this class.
	 */
	public ConsoleResultManager() {
		super();
	}
	
	/**
	 * Exports the results to the console output stream.
	 * @see wisedevil.test.result.AbstractResultManager#exportResults()
	 */
	@Override
	public void exportResults() {
		// Export results to variable
		super.exportResults();
		
		// Export results to console
		System.out.println(textResults);
		
		// Padding space
		System.out.println();
		System.out.println();
	}
}
