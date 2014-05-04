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

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import wisedevil.test.result.TestResult;

/**
 * This class runs the tests of a given test case class.
 */
public class TestCaseRunner {
	/**
	 * The test case.
	 */
	private final TestCase _case;
	
	/**
	 * Initializes a new instance of this class.
	 *
	 * @param tcase The test case
	 *
	 * @throws java.lang.NullPointerException If any of the parameters is null
	 */
	public TestCaseRunner(TestCase tcase) {
		if(tcase == null)
			throw new NullPointerException();
		
		_case = tcase;
	}
	
	/**
	 * Runs the tests.
	 *
	 * @return An iterable object containing the test results.
	 */
	public Iterable<TestResult> run() {
		final LinkedList<TestResult> results = new LinkedList<TestResult>();
		
		SetupMethod setup = _case.getSetup();
		
		if(setup != null)
			setup.run();
		
		_run(results);
		
		CleanupMethod cleanup = _case.getCleanup();
		
		if(cleanup != null)
			cleanup.run();
		
		return results;
	}
	
	/**
	 * Runs the tests.
	 *
	 * @param results The test results to store the results in
	 */
	 private synchronized void _run(Collection<TestResult> results) {
		final LinkedList<TestMethod> meths = new LinkedList<TestMethod>(_case.getMethods());
		final int testsPerSequence = (int)Math.floor(meths.size() / _case.getInfo().getFlows());
		final TestSequence[] seq = new TestSequence[_case.getInfo().getFlows()];

		for(int i = 0; i < seq.length; i++) { // Populate the sequences
			final int sz = (i < seq.length - 1)? (testsPerSequence > 0? testsPerSequence: 1): meths.size();
			final TestMethod[] tm = new TestMethod[sz];
			
			for(int j = 0; j < sz; j++) // Populate the test methods
				tm[j] = meths.pop();
			
			seq[i] = new TestSequence(tm);
		}
		
		// Run the useful (not empty) sequences
		final LinkedList<Thread> tlist = new LinkedList<Thread>();
		
		Arrays.stream(seq).forEach((TestSequence s) -> {
			if(!s.isEmpty()) {
				Thread t = new Thread(s);
				
				try {
					t.start();
					tlist.push(t);
				} catch(IllegalThreadStateException e) {
					e.printStackTrace(); // Should never occur
				}
			}
		});
		
		// Wait for termination 
		tlist.stream().forEach((Thread t) -> _joinSequence(t));
		
		// Collect the results
		Arrays.stream(seq).forEach((TestSequence s) -> {
			Arrays.stream(s.getResults()).forEach((TestResult t) -> results.add(t));
		});
	 }
	 
	 /**
	  * Joins a test sequence.
	  *
	  * @param t The test sequence thread
	  */
	 private void _joinSequence(Thread t) {
	 	 try {
			t.join(_case.getInfo().getLingerTime());
			
			if(t.isAlive()) {
				System.err.println("Test sequence exceded permitted life time, killing...");
				t.interrupt();
			}
		} catch(InterruptedException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	 }
}

