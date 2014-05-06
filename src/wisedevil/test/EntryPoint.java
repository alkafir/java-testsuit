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

import java.io.File;

import wisedevil.test.util.ErrorCode;

/**
 * Application entry point
 */
public final class EntryPoint {
	/**
	 * Main method.
	 *
	 * @param args The command line arguments
	 */
	public static void main(String[] args) {
		if(args.length != 1) {
			printHelp();
			System.exit(ErrorCode.ERR_ARGUMENT.ordinal()); // Argument error
		}
		
		String pClass = args[0];
		File fClass = new File(pClass);
		
		try {
			(new TestCaseProcess(fClass)).run();
		} catch(TestProcessAbortedException e) {
			System.err.println(e.getMessage());
			System.exit(e.getAbortCode());
		}
		
	}
	
	/**
	 * Prints the help message.
	 */
	private static void printHelp() {
		String[] lines = { "Usage:",
			"\tjava -cp <project classes' path> -jar testsuit.jar <test class>"
		};
		
		for(String line: lines)
			System.out.println(line);
	}
}
