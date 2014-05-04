import wisedevil.test.annotation.*;

import static wisedevil.test.Assert.*;

@Name("FileTest")
@Description("This test saves a text file with testcase results")
@ResultManager(wisedevil.test.result.TextFileResultManager.class)
@ResultManager(wisedevil.test.result.ConsoleResultManager.class)
public class FileTest {

	@Test public void passTest() {}
	@Test public void failTest1() { fail(); }
	@Test public void failTest2() { fail("Failure with message"); }
}
