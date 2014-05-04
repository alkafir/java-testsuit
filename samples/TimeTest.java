
import wisedevil.test.annotation.*;

import wisedevil.test.result.ConsoleResultManager;
import wisedevil.test.result.TextFileResultManager;
import wisedevil.test.result.XMLFileResultManager;

/**
 * This test case does loops and other junky code for time feature testing.
 */
@Name("Time test")
@Description("Time feature testing test case")
@ResultManager(ConsoleResultManager.class)
@ResultManager(TextFileResultManager.class)
@ResultManager(XMLFileResultManager.class)
//@Timed(false)
public class TimeTest {
	@Test
	public void loopTest() {
		for(long i = 0; i < 1024 * 65_535L; i++);
	}
	
	@Test
	public void failedTest() {
		assert 0 == 1: "This test should not be timed";
	}
	
	@Test
	@Timed(false)
	public void notTimedTest() {
		// This test should never be timed
	}
	
	@Test
	@Timed(true)
	public void timedTest() {
		assert Math.sqrt(144) == 12; // This test should always be timed
	}
}

