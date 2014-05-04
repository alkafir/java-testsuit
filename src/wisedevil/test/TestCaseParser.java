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

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import java.util.Set;

import wisedevil.test.annotation.*;

import wisedevil.test.result.AbstractResultManager;
import wisedevil.test.result.ConsoleResultManager;

/**
 * This class is a parser for a test case class.
 *
 * <blockquote>Instances of this class are immutable</blockquote>
 */
public class TestCaseParser {
	/**
	 * The test case class.
	 */
	private final Class<?> _cls;
	
	/**
	 * The test case information.
	 */
	 private TestCaseInfo _info;
	
	/**
	 * Initializes a new instance of this class.
	 *
	 * @param c The test case class to parse
	 *
	 * @throws java.lang.NullPointerException If <code>c</code> is null
	 */
	public TestCaseParser(Class<?> c) throws NullPointerException {
		if(c == null)
			throw new NullPointerException();
			
		_cls = c;
		
		_parseTestCaseInfo();
	}
	
	/**
	 * Returns the cleanup() method (if defined).
	 *
	 * @param inst The test case instance
	 *
	 * @return The cleanup() method of the test case, or null if none has been defined.
	 */
	public CleanupMethod getCleanup(Object inst) {
		try {
			return new CleanupMethod(inst, _cls.getMethod("cleanup"));
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Returns the setup() method (if defined).
	 *
	 * @param inst The test case instance
	 *
	 * @return The setup() method of the test case, or null if none has been defined.
	 */
	public SetupMethod getSetup(Object inst) {
		try {
			return new SetupMethod(inst, _cls.getMethod("setup"));
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Returns all the public non-static methods annotated with <code>@Test</code>
	 * and not annotated with <code>@Ignore</code>, of type <code>void</code>
	 * and with no arguments.
	 *
	 * @param instance The test case instance to retrieve the methods for
	 *
	 * @return The test methods
	 */
	public Set<TestMethod> getTestMethods(Object instance) {
		final Method[] all = _cls.getDeclaredMethods();
		final LinkedHashSet<TestMethod> meths = new LinkedHashSet<TestMethod>();
		
		Arrays.stream(all).forEach(m -> {
			final Test aTest = m.getAnnotation(Test.class);
			
			if(aTest != null)
				if(aTest.value()
					&& m.getReturnType() == Void.TYPE
					&& m.getParameterTypes().length == 0
					&& isPublic(m.getModifiers())
					&& !isStatic(m.getModifiers())) {
						final Timed time = m.getAnnotation(Timed.class);
						boolean timed;
						
						if(time != null)
							timed = time.value();
						else
							timed = _info.isTimed();
						
						meths.add(new TestMethod(instance, m, timed));
					}
		});
		
		return meths;
	}
	
	/**
	 * Parses the test case metadata.
	 */
	private void _parseTestCaseInfo() {
		final Timed time = _cls.getAnnotation(Timed.class); // Timing information
		final Flows flows = _cls.getAnnotation(Flows.class); // Executin information
		final Name name = _cls.getAnnotation(Name.class); // Identification information
		final Description desc = _cls.getAnnotation(Description.class); // Description information
		final TestCaseInfoBuilder builder = new TestCaseInfoBuilder();
		final ResultManager[] results = _cls.getAnnotationsByType(ResultManager.class); // The result manager information
		
		if(time != null)
			builder.isTimed(time.value());
		
		if(flows != null)
			builder
				.setFlows(flows.value())
				.setMaxLingerTime(flows.time());
		
		if(name != null)
			builder.setName(name.value());
		
		if(desc != null)
			builder.setDescription(desc.value());
				
		if(results != null) {
			final LinkedList<Class<? extends AbstractResultManager>> r = new LinkedList<Class<? extends AbstractResultManager>>();
			
			Arrays.stream(results).forEach(rm -> r.add(rm.value()));
			
			builder.setResultManagers(r);
		}
		
		builder.setTestCaseClass(_cls);
		
		_info = builder.build();
	}
	
	/**
	 * Returns the meta-information for the test case.
	 *
	 * <p><b>NOTE:</b> multiple calls to this method on the same instance, return the same object.</p>
	 *
	 * @return The meta-information for the test case
	 */
	public TestCaseInfo getTestCaseInfo() {
		return _info;
	}
	
	/**
	 * Parses and returns a new test case.
	 *
	 * @param inst The test case instance
	 *
	 * @return A new TestCase instance
	 */
	public TestCase parseNew(Object inst) {
		return new TestCase(getTestCaseInfo(), getTestMethods(inst), getSetup(inst), getCleanup(inst));
	}
}
