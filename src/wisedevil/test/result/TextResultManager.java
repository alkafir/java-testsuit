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

import java.util.Calendar;

import wisedevil.test.TestCaseInfo;

/**
 * Generic test result manager that exports the results in a protected text variable.
 */
public abstract class TextResultManager extends AbstractResultManager {
	/**
	 * After calling <code>exportResults()</code>, contains the test results as text.
	 *
	 * @see #exportResults()
	 */
	protected String textResults;
	
	/**
	 * Initializes a new instance of this class.
	 */
	protected TextResultManager() {
		super();
	}
	
	/**
	 * Exports the results in its protected variable <code>textResults</code>.
	 *
	 * @see wisedevil.test.result.AbstractResultManager#exportResults()
	 */
	public void exportResults() {
		final int minConsoleW = 60; // Minimum console width (in chars)
		final int statusSpace = 7; // max(len("PASSED", len("FAILED"))); Space reserved for status output
		final int nameLen = minConsoleW - statusSpace - 2; // Minimum space reserved for name output (-2 because ": " is appended to the name
		final String nl = System.getProperty("line.separator"); // Newline
		final char[] sep = new char[minConsoleW]; // Text separator
		final TestCaseInfo info = getTestCaseInfo();
		
		for(int i = 0; i < sep.length; i++)
			sep[i] = '-'; // Separator character
		
		StringBuilder b = new StringBuilder();
		
		if(info.getName() != null) { // Append test case name (if any)
			b.append("TESTCASE: " + info.getName() + nl);
		
			if(info.getDescription() != null) { // Append test description name (if any)
				b.append(sep); b.append(nl);
				b.append(info.getDescription()); b.append(nl);
				b.append(sep); b.append(nl);
			}
		}
		
		// Append date
		b.append("DATE: " + Calendar.getInstance().getTime().toString() + nl + nl);
		
		// Append results
		for(TestResult r: getResults()) {
			b.append(r.name);
			
			if(r.name.length() < nameLen) {
				char[] pad = new char[nameLen - r.name.length()];
				
				for(int i = 0; i < pad.length; i++)
					pad[i] = ' ';
					
				b.append(pad);
			}
			
			b.append(": ");
			b.append((r.result? "PASSED": "FAILED") + nl);
			
			if(r.message != null)
				b.append("\tFAIL MESSAGE:\t" + r.message + nl);
			
			if(r.time != TestResult.NOT_TIMED)
				b.append("\tEXECUTION TIME:\t" + r.time + "ns" + nl);
		}
		
		textResults = b.toString();
	}
}
