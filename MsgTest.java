

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

public class MsgTest {
	private final ByteArrayOutputStream outContent= new ByteArrayOutputStream();
	@Test
	public void ErrorTest() throws IOException {
		System.setOut(new PrintStream(outContent));
		Msg.ERROR(0);
		assertTrue(outContent.toString().equals("PROGRAM INVOCATION ARGUMENT ERROR(S): Terminating the program...\r\n"));
	}
	@Test
	public void error1() {
		System.setOut(new PrintStream(outContent));
		Msg.ERROR(1);
		assertTrue(outContent.toString().equals("USER EDIT FILE EMPTY:  No information read - file created.\r\n"));
	}
	@Test
	public void error2() {
		System.setOut(new PrintStream(outContent));
		Msg.ERROR(2);
		assertTrue(outContent.toString().equals("USER EDIT FILE EMPTY:  File written but it will be an empty file.\r\n"));
	}
	@Test
	public void error3() {
		System.setOut(new PrintStream(outContent));
		Msg.ERROR(3);
		assertTrue(outContent.toString().equals("SYNTAX ERROR IN COMMAND: \r\n"));
	}
	@Test
	public void error4() {
		System.setOut(new PrintStream(outContent));
		Msg.ERROR(4);
		assertTrue(outContent.toString().equals("NUMBER LINES VALUE MUST BE POSITIVE & NONZERO.  No action taken.\r\n"));
	}
	@Test
	public void error5() {
		System.setOut(new PrintStream(outContent));
		Msg.ERROR(5);
		assertTrue(outContent.toString().equals("ILLEGAL COMMAND WHEN NO EDIT FILE LINES EXIST:  No action taken.\r\n"));
	}
	@Test
	public void error6() {
		System.setOut(new PrintStream(outContent));
		Msg.ERROR(6);
		assertTrue(outContent.toString().equals("A NULL (0 LENGTH) STRING HAS NO MEANING HERE:  No action taken.\r\n"));
	}
	@Test
	public void error7() {
		System.setOut(new PrintStream(outContent));
		Msg.ERROR(7);
		assertTrue(outContent.toString().equals("NO LINES IN YANK BUFFER TO PUT:  No action taken.\r\n"));
	}
	@Test
	public void error8() {
		System.setOut(new PrintStream(outContent));
		Msg.ERROR(8);
		assertTrue(outContent.toString().equals("THERE ARE NO KEYWORDS AT TOP OF FILE TO INDEX:  No action taken.\r\n"));
	
	}
	@Test
	public void error9() {
		System.setOut(new PrintStream(outContent));
		Msg.ERROR(9);
		assertTrue(outContent.toString().equals("THIS KEYWORD DOES NOT EXIST:  No action taken.\r\n"));
	}

	@Test
	public void error10() {
		System.setOut(new PrintStream(outContent));
		Msg.ERROR(10);
		assertTrue(outContent.toString().equals("COLUMN VALUES MUST BE POSITIVE & NONZERO:  No action taken.\r\n"));
	}
	@Test
	public void error11() {
		System.setOut(new PrintStream(outContent));
		Msg.ERROR(11);
		assertTrue(outContent.toString().equals("REVERSED OR BACKWARDS COLUMN RANGES ARE ILLEGAL:  No action taken.\r\n"));
	}
	@Test
	public void errorDefault() {
		System.setOut(new PrintStream(outContent));
		Msg.ERROR(12);
		assertTrue(outContent.toString().equals("<<<<< INTERNAL PROGRAM ERROR => Unknown error code used. >>>>>\r\n"));
	}
}
