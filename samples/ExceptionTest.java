import wisedevil.test.annotation.*;

import static wisedevil.test.Assert.*;

@Name("Exception test")
@Description("Exception-related assert tests.")
public class ExceptionTest {
	public ExceptionTest() {
	}
	
	@Test
	public void excTest1() {
		assertException(new Runnable() {
				public void run() {
					throwNPE();
				}
		}, NullPointerException.class);
	}
	
	private void throwNPE() {
		throw new NullPointerException();
	}
}
