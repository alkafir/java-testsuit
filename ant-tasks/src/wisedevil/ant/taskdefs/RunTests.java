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
package wisedevil.ant.taskdefs;

import java.io.File;

import java.net.URL;
import java.net.URLDecoder;

import java.util.LinkedHashSet;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.RuntimeConfigurable;

import org.apache.tools.ant.taskdefs.Java;

import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.FileSet;

/**
 * This class represents an Ant task for running test cases
 * powered by Alfredo Mungo's testsuit.
 *
 * XML task structure:
 * <![CDATA[
 * <runtests>
 *  ...
 * </runtests>
 * ]]>
 *
 * Supported elements:
 * - Fileset: For test case class selection
 *
 * Supported attributes:
 * - Classpath: Colon separated list of jar files and directories to be added to the classpath
 *
 * @see https://github.com/wisedevil/testsuit
 */
public class RunTests extends org.apache.tools.ant.Task {
	private Path _cp; // Classpath
	private LinkedHashSet<File> _files = new LinkedHashSet<File>(); // Nested filesets
	
	public RunTests() {
		super();
	}
	
	public void execute() throws BuildException {
		final Path cp = ((_cp != null)? (Path)_cp.clone(): new Path(getProject()));
		
		// Add the classpath of this class
		{
			try {
				URL uccp = new URL(RunTests.class.getResource(RunTests.class.getSimpleName() + ".class").toString());
				uccp = new URL(uccp.getPath());
				String ccp = uccp.getFile();
				int idx_start;

				if(System.getProperty("os.name").toLowerCase().contains("windows"))
					idx_start = 1;
				else
					idx_start = 0;

				ccp = URLDecoder.decode(ccp.substring(idx_start, ccp.lastIndexOf('!')), "UTF-8");

				cp.add(new Path(getProject(), ccp));
			} catch(Exception e) {}
		}
		
		if(_files.size() == 0)
			throw new BuildException("At least one FileSet must be specified.");
		
		//for(File f: _files) { // Run tests
		_files.stream().forEach(f -> {
			if(f.getName().contains("$"))
				return; // Avoid classes containing '$' since by convention, those are anonymous classes declared somewhere else
			
			Java java = new Java(this);
			
			java.setFork(true);
			java.setDir(f.getParentFile());
			java.setClassname("wisedevil.test.EntryPoint");
			java.setClasspath(cp);
			java.createArg().setFile(f);
			java.setFailonerror(true);
			
			java.execute();
		});
	}
	
	public void setClasspath(Path cp) { _cp = cp; }
	
	public void addConfiguredFileset(FileSet fs) {
		final DirectoryScanner scanner = fs.getDirectoryScanner(getProject());
		final File basedir = fs.getDir(getProject());
		
		scanner.scan();
		
		java.util.Arrays.stream(scanner.getIncludedFiles()).forEach(s -> _files.add(new File(basedir.toString(), s)));
	}
}

