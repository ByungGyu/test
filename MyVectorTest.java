

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Test;

public class MyVectorTest {
	
	@Test
	public void setTest() {
		MyVector myVector=new MyVector();
		myVector.ensureCapacity(10);
		myVector.setElementAt("Data 1", 0);
		assertEquals(myVector.elementAt(0),"Data 1");

		myVector.setElementAt("Data 2", 1);
		assertEquals(myVector.elementAt(1),"Data 2");
		
		myVector.setElementAt("Data 3", 3);
		assertEquals(myVector.contains("Data 3"),false);
		
	}
	@Test
	public void insertAndDeleteTest() {
		MyVector myVector=new MyVector();
		assertEquals(myVector.capacity(),100);
		myVector.addElement("Data 1");
		assertEquals(myVector.size(),1);
		assertEquals(myVector.elementAt(0),"Data 1");
		myVector.addElement("Data 2");
		myVector.addElement("Data 3");
		myVector.insertElementAt("Data 4",1);
		assertEquals(myVector.elementAt(1),"Data 4");
		assertEquals(myVector.elementAt(2),"Data 2");
		
		myVector.removeElement("Data 4");
		assertEquals(myVector.elementAt(1),"Data 2");
		myVector.trimToSize();
		assertEquals(myVector.size(),3);
		myVector.removeElementAt(1);
		assertEquals(myVector.size(),2);
		assertEquals(myVector.elementAt(1),"Data 3");
		myVector.removeAllElements();
		assertEquals(myVector.isEmpty(),true);
	}
	@Test
	public void capacityTest() {
		MyVector myVector2=new MyVector(20);
		MyVector myVector3=new MyVector(20,10);
		MyVector myVector4=new MyVector(20,'D');
		MyVector myVector5=new MyVector(20,10,'C');
		MyVector myVector6=new MyVector(20,10,'O');
		
		myVector2.addElement("Data 1");
		assertEquals(myVector2.size(),1);
		assertEquals(myVector2.elementAt(0),"Data 1");
		myVector2.addElement("Data 2");
		myVector2.addElement("Data 3");
		myVector2.insertElementAt("Data 4",1);
		assertEquals(myVector2.elementAt(1),"Data 4");
		assertEquals(myVector2.elementAt(2),"Data 2");
		
		
		myVector2.ensureCapacity(10);
		assertEquals(myVector2.capacity(),20);
		myVector3.ensureCapacity(40);
		assertEquals(myVector3.capacity(),40);
		
		myVector4.ensureCapacity(50);
		assertEquals(myVector4.capacity(),80);
		
		myVector5.ensureCapacity(50);
		assertEquals(myVector5.capacity(),80);
		myVector6.ensureCapacity(50);
		assertEquals(myVector6.capacity(),60);
	}
}


