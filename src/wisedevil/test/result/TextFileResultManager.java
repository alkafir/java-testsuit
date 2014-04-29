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

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Test result manager that exports the results in a text file.
 */
public class TextFileResultManager extends TextResultManager {

	/**
	 * Initializes a new instance of this class.
	 */
	public TextFileResultManager() {
		super();
	}
	
	/**
	 * Exports the results in a text file.
	 *
	 * @see wisedevil.test.result.AbstractResultManager#exportResults()
	 */
	@Override
	public void exportResults() {
		super.exportResults();
		
		try {
			(new PrintStream(
				new FileOutputStream(String.format("%1s.log", getTestCaseInfo().getTestCaseClass().getSimpleName()), false)
				)
			).println(textResults);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
