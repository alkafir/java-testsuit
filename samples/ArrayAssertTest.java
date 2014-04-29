import wisedevil.test.annotation.*;

import java.util.Arrays;

import static wisedevil.test.Assert.*;

@Name("Array assertion test")
public class ArrayAssertTest {
	@Test
	public void arrayTest() {
		char[] a = {1, 2, 3, 4};
		char[] b = {1, 2, 3, 4};
		char[] c = {1, 2, 3, 5};
		
		assert Arrays.equals(a, b) && !Arrays.equals(a, c);
	}
}

