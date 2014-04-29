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
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

import wisedevil.test.TestCaseInfo;

/**
 * Generic test result manager that exports the results in a protected Document variable.
 *
 * @see org.w3c.dom.Document
 */
public abstract class XMLResultManager extends AbstractResultManager {
	/**
	 * After calling <code>exportResults()</code>, contains the test results as an XML document.
	 *
	 * @see #exportResults()
	 */
	protected Document xmlResults;
	
	/**
	 * Ininitializes a new instance of this class.
	 */
	public XMLResultManager() {
		super();
	}
	
	/**
	 * Exports the results in a protected variable as an XML Document.
	 *
	 * @see wisedevil.test.result.AbstractResultManager#exportResults()
	 */
	public void exportResults() {
		final Set<TestResult> res = getResults();
		final TestCaseInfo info = getTestCaseInfo();
		
		try {
			final DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder xmlBuilder = xmlFactory.newDocumentBuilder();
			final Document xml = xmlBuilder.newDocument();
			
			xml.setXmlVersion("1.0");
			
			final Element xRoot = xml.createElement("testcase");
			
			if(info.getName() != null) { // Set test name (if any)
				xRoot.setAttribute("name", info.getName());
				
				if(info.getDescription() != null) { // Set test description (if any)
					final Element xName = xml.createElement("description");
					
					xName.appendChild(xml.createTextNode(info.getDescription()));
					xRoot.appendChild(xName);
				}
			}
			
			// Set date
			final Element xDate = xml.createElement("date");
			
			xDate.appendChild(xml.createTextNode(Calendar.getInstance().getTime().toString()));
			
			xRoot.appendChild(xDate);
			
			// Set test results
			for(TestResult t: res) {
				final Element xTest = xml.createElement("test");
				
				xTest.setAttribute("name", t.name);
				xTest.setAttribute("passed", t.result? "passed": "failed");
				
				if(t.message != null) {
					final Element xMsg = xml.createElement("message");
					
					xMsg.appendChild(xml.createTextNode(t.message));
					
					xTest.appendChild(xMsg);
				}
				
				if(t.time != TestResult.NOT_TIMED) {
					final Element xTime = xml.createElement("time");
					
					xTime.setAttribute("unit", "ns");
					xTime.appendChild(xml.createTextNode(Long.toString(t.time)));
					
					xTest.appendChild(xTime);
				}
				
				xRoot.appendChild(xTest);
			}
			
			xml.appendChild(xRoot);
			xml.normalize();
			
			xmlResults = xml;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
