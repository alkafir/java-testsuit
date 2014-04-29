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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

import wisedevil.test.TestProcessAbortedException;

/**
 * This class provides methods for loading class files
 * directly from file.
 */
public class FileClassLoader extends ClassLoader {
	private static Map<String, Class<?>> cache = new HashMap<String, Class<?>>();
	private static Set<File> path_cache = new HashSet<File>();
	
	/**
	 * Initializes a new instance of this class.
	 */
	public FileClassLoader() {
		super();
		setDefaultAssertionStatus(true);
	}
	
	/**
	 * Loads a class from file.
	 *
	 * @param cPath The class file path
	 *
	 * @return A <code>Class&amp;?&amp;</code> instance representing the loaded class
	 *
	 * @throws java.lang.ClassFormatError If the file is not a valid Java class file
	 * @throws java.io.IOException If there's an input error loading the file
	 */
	public Class<?> loadClassFromFile(File cPath) throws ClassFormatError, IOException, TestProcessAbortedException {
		try {
			FileInputStream cStream = new FileInputStream(cPath);
			byte[] cCode = new byte[(int)cPath.length()];
			
			cStream.read(cCode);
			
			Class<?> res = defineClass(null, cCode, 0, cCode.length);
			
			cache.put(res.getName(), res);
			path_cache.add(cPath.getParentFile());

			return res;
		} catch(SecurityException e) {
			e.printStackTrace();
			throw new TestProcessAbortedException(ErrorCode.ERR_INTERNAL);
		}
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Class<?> cached = cache.get(name);
		
		if(cached != null) // Try loading from cache
			return cached;
		else { // Try loading from cached paths
			String pname = name.replace(".", File.pathSeparator);
			
			for(File p: path_cache) {
				File f = new File(p, pname + ".class");
				
				try {
					cached = loadClassFromFile(f);
				} catch(Exception e) {}
				
				if(cached != null)
					return cached;
			}
			
			return super.loadClass(name);
		}
	}
}
