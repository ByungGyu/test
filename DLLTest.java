

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class DLLTest {

	private final DLL dll=new DLL();
	
	@Test
	public void test() {
		assertEquals(dll.emptyDLL(), true);
		dll.putDLL(0,"data 1");
		assertEquals((String)dll.getDLL(0),"data 1");
		assertEquals(dll.numberDLL(),0);
		
		dll.insertDLL(0, "data 2");
		assertEquals((String)dll.getDLL(1),"data 2");
		assertEquals(dll.numberDLL(),1);
		
		dll.deleteDLL(1);
		assertEquals((String)dll.getDLL(1),null);
		assertEquals(dll.numberDLL(),0);
			
		dll.clearDLL();
		assertEquals(dll.numberDLL(),0);
	}
	@Test
	public void errorTest() {
		dll.putDLL(0,"data 1");
		dll.insertDLL(0, "data 2");
		assertEquals(dll.getDLL(15),null);
		assertEquals(dll.getDLL(-1),null);
	}	
}
