import wisedevil.test.MethodInspector;
import wisedevil.test.PropertyInspector;
import wisedevil.test.annotation.*;

import static wisedevil.test.Assert.*;

@Name("Inspection test case")
@ResultManager(wisedevil.test.result.ConsoleResultManager.class)
public class ObjectInspectionTest {
	public class TestClass {
		private int myProperty = 5;
		private void myVoidMethod() {
			System.out.println("myVoidMethod() called");
		}

		private int mySumMethod(int a, int b) {
			return a + b;
		}
	}
	
	@Test
	public void property_get_and_set() {
		PropertyInspector oi = new PropertyInspector(new TestClass(), "myProperty");
		
		assert (int)oi.get() == 5;
		
		oi.set(6);
		
		assert (int)oi.get() == 6;
	}

	@Test
	public void method_invoke() {
		TestClass tc = new TestClass();
		MethodInspector mi = new MethodInspector(tc, "myVoidMethod");
		MethodInspector mi_sum = new MethodInspector(tc, "mySumMethod", int.class, int.class);

		try {
			mi.invoke();

			assert (int)mi_sum.invoke(2, 3) == 5;
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
