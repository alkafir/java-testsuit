import wisedevil.test.PropertyInspector;
import wisedevil.test.annotation.*;

import static wisedevil.test.Assert.*;

@Name("Inspection test case")
@ResultManager(wisedevil.test.result.ConsoleResultManager.class)
public class ObjectInspectionTest {
	public class TestClass {
		private int myProperty = 5;
	}
	
	@Test
	public void property_get_and_set() {
		PropertyInspector oi = new PropertyInspector(new TestClass(), "myProperty");
		
		assert (int)oi.get() == 5;
		
		oi.set(6);
		
		assert (int)oi.get() == 6;
	}
}
