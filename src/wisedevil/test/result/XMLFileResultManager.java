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

import java.util.Calendar;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;

import javax.xml.transform.dom.DOMSource;

import javax.xml.transform.stream.StreamResult;

/**
 * Test result manager that exports the results in an XML file.
 */
public class XMLFileResultManager extends XMLResultManager {
	/**
	 * Initializes a new instance of this class.
	 */
	public XMLFileResultManager() { super(); }
	
	/**
	 * Exports the results in an XML file.
	 *
	 * @see wisedevil.test.result.AbstractResultManager#exportResults()
	 */
	@Override
	public void exportResults() {
		super.exportResults();
		
		try {
			final TransformerFactory tFactory = TransformerFactory.newInstance();
			final Transformer t = tFactory.newTransformer();
			final FileOutputStream fos = new FileOutputStream(String.format("%1s.xml", getTestCaseInfo().getTestCaseClass().getSimpleName()), false);
			
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			t.setOutputProperty(OutputKeys.INDENT, "no");
			
			t.transform(new DOMSource(xmlResults), new StreamResult(fos));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
