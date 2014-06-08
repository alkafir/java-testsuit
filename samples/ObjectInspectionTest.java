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
		PropertyInspector<Integer> oi = new PropertyInspector<Integer>(new TestClass(), "myProperty");
		
		assert oi.get() == 5;
		
		oi.set(6);
		
		assert oi.get() == 6;
	}

	@Test
	public void method_invoke() {
		TestClass tc = new TestClass();
		MethodInspector<Void> mi = new MethodInspector<Void>(tc, "myVoidMethod");
		MethodInspector<Integer> mi_sum = new MethodInspector<Integer>(tc, "mySumMethod", int.class, int.class);

		try {
			mi.invoke();

			assert mi_sum.invoke(2, 3) == 5;
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
