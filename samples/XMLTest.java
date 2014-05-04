import wisedevil.test.annotation.*;

import static wisedevil.test.Assert.*;

@Name("XMLTest")
@Description("This test saves an XML file with testcase results")
@ResultManager(wisedevil.test.result.XMLFileResultManager.class)
@ResultManager(wisedevil.test.result.ConsoleResultManager.class)
public class XMLTest {

	@Test public void passTest() {}
	@Test public void failTest1() { fail(); }
	@Test public void failTest2() { fail("Failure with message"); }
}
