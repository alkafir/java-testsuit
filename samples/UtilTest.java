import wisedevil.test.annotation.*;

import static wisedevil.test.Assert.*;

@Name("Utility test")
@Flows(5)
public class UtilTest {
	@Test
	public void failTest() {
		//fail();
	}
	
	@Test
	public void assertTest() {
		assert 1 == 0;
	}
	
	@Test
	public void assertTest2() {
		assert 1 == 0: "1 will never be equal to 0";
	}
	
	@Test public void assertTestPass() {
		assert 1 == 1;
	}
}
