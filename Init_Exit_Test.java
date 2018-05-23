

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

public class Init_Exit_Test {
	private final ByteArrayOutputStream outContent= new ByteArrayOutputStream();
	
	@Test
	public void createErrorTest() throws IOException {
	System.setOut(new PrintStream(outContent));
	Init_Exit init_exit=new Init_Exit(new String[] {"",""},new File_Buffer());		
	assertTrue(outContent.toString().equals("PROGRAM INVOCATION ARGUMENT ERROR(S): Terminating the program...\r\n"));
}
	@Test
	public void createErrorTest2() throws IOException {
	System.setOut(new PrintStream(outContent));
	Init_Exit init_exit=new Init_Exit(new String[] {""},new File_Buffer());
	assertTrue(outContent.toString().equals("USER EDIT FILE EMPTY:  No information read - file created.\r\n"));
}
	@Test
	public void createTest() throws IOException {
	System.setOut(new PrintStream(outContent));
	File_Buffer file_buffer=new File_Buffer();
	Init_Exit init_exit=new Init_Exit(new String[] {"C:\\Users\\lg\\Desktop\\test.txt"},file_buffer);
	assertTrue(file_buffer.GetLine(1).equals("첫 줄입니다."));
	assertEquals(file_buffer.NumLins(), 14);
}
	@Test
	public void doUpdateTest() throws IOException {
		File_Buffer file_buffer=new File_Buffer();
		File_Buffer file_buffer2=new File_Buffer();
		
		Init_Exit Rinit_exit=new Init_Exit(new String[] {"C:\\Users\\lg\\Desktop\\test.txt"},file_buffer);
		Init_Exit Winit_exit=new Init_Exit(new String[] {"C:\\Users\\lg\\Desktop\\test3.txt"},file_buffer);
		Winit_exit.Do_Update(file_buffer);
		Init_Exit reRinit_exit=new Init_Exit(new String[] {"C:\\Users\\lg\\Desktop\\test3.txt"},file_buffer2);
		assertTrue(file_buffer2.GetLine(1).equals("첫 줄입니다."));
		assertEquals(file_buffer2.NumLins(), 14);
	}
	@Test
	public void doUpdateErrorTest() throws IOException {
		File_Buffer file_buffer=new File_Buffer();
		System.setOut(new PrintStream(outContent));
		Init_Exit Winit_exit=new Init_Exit(new String[] {"C:\\Users\\lg\\Desktop\\test4.txt"},file_buffer);
		Winit_exit.Do_Update(file_buffer);
		assertTrue(outContent.toString().equals("USER EDIT FILE EMPTY:  File written but it will be an empty file.\r\n"));
	}
}
