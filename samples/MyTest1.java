import wisedevil.test.annotation.*;

import static wisedevil.test.Assert.*;

@Name("MyTest1")
public class MyTest1 {
	public MyTest1() {
		System.out.println("Running the constructor...");
	}
	
	public void setup() {
		System.out.println("Called the setup() method");
	}
	
	@Test
	public void test1() {
		System.out.println("Test 1");
	}
	
	@Test
	public void test2() {
		System.out.println("Test 2");
	}
	
	@Test(false)
	public void test3() {
		System.out.println("ERROR");
	}
	
	@Test(value = true)
	public int test4() {
		System.out.println("ERROR");
		return 0;
	}
	
	@Test
	public void test5(int a) {
		System.out.println("ERROR");
	}
	
	@Test
	private void test6() {
		System.out.println("ERROR");
	}
	
	@Test
	protected void test7() {
		System.out.println("ERROR");
	}
	
	@Test
	public static void test8() {
		System.out.println("ERROR");
	}
	
	@Test
	public void failMePlease() {
		fail();
	}
	
	public void cleanup() {
		System.out.println("Called the cleanup() method");
	}
}
