import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class totalTest {
	private final ByteArrayOutputStream outContent= new ByteArrayOutputStream();
	private final File_Buffer file_buffer=new File_Buffer();
	private final File_Buffer file_buffer2=new File_Buffer();
	private  Init_Exit init_exit;
	private  Init_Exit init_exit2;
	private final DLL dll=new DLL();
	
	@Before
	public void setInit_exit() throws IOException {
		init_exit=new Init_Exit(new String[] {"C:\\Users\\lg\\Desktop\\test.txt"},file_buffer);
		init_exit2=new Init_Exit(new String[] {"C:\\Users\\lg\\Desktop\\test2.txt"},file_buffer2);	
	}
	@Test
	public void CommandTTest() throws IOException {
	Cmd_Driver cmd_driver=new Cmd_Driver();
	assertEquals(file_buffer.GetCLN(),1);
	file_buffer.SetCLN(3);
	cmd_driver.Cmd_T(file_buffer);
	assertEquals(file_buffer.GetCLN(),1);
	}
	@Test
	public void CommandETest() throws IOException {
	Cmd_Driver cmd_driver=new Cmd_Driver();
	file_buffer.SetCLN(0);
	cmd_driver.Cmd_E(file_buffer);
	assertEquals(file_buffer.GetCLN(),14);
	}
	@Test
	public void CommandNTest() throws IOException {
	Cmd_Driver cmd_driver=new Cmd_Driver();
	cmd_driver.Cmd_N(file_buffer,2);
	assertEquals(file_buffer.GetCLN(),3);
	cmd_driver.Cmd_N(file_buffer,2);
	assertEquals(file_buffer.GetCLN(),5);
	cmd_driver.Cmd_N(file_buffer,20);
	assertEquals(file_buffer.GetCLN(),14);
	}
	@Test
	public void CommandBTest() throws IOException {
	Cmd_Driver cmd_driver=new Cmd_Driver();
	file_buffer.SetCLN(14);
	cmd_driver.Cmd_B(file_buffer,2);
	assertEquals(file_buffer.GetCLN(),12);
	cmd_driver.Cmd_B(file_buffer,3);
	assertEquals(file_buffer.GetCLN(),9);
	cmd_driver.Cmd_B(file_buffer,10);
	assertEquals(file_buffer.GetCLN(),1);
	}
	@Test
	public void CommandWTest() throws IOException {
	Cmd_Driver cmd_driver=new Cmd_Driver();
	System.setOut(new PrintStream(outContent));
	file_buffer.SetCLN(5);
	cmd_driver.Cmd_W(file_buffer);
	assertTrue(outContent.toString().equals("At Edit File Line 5\r\n"));
	}
	@Test
	public void CommandCTest() throws IOException {
	Cmd_Driver cmd_driver=new Cmd_Driver();
	System.setOut(new PrintStream(outContent));
	cmd_driver.Cmd_C(file_buffer);
	assertTrue(outContent.toString().equals("Total Edit File Lines: 14\r\n"));
	}
	@Test
	public void CommandLTest() throws IOException {
	Cmd_Driver cmd_driver=new Cmd_Driver();
	System.setOut(new PrintStream(outContent));
	cmd_driver.Cmd_L(file_buffer,-1);
	assertTrue(outContent.toString().equals("NUMBER LINES VALUE MUST BE POSITIVE & NONZERO.  No action taken.\r\n"));
	outContent.reset();
	
	cmd_driver.Cmd_L(file_buffer,3);
	assertTrue(outContent.toString().equals("첫 줄입니다.\r\n"+
											"두번째 줄입니다.\r\n"+
											"세번째 줄입니다.\r\n"));
	outContent.reset();
	cmd_driver.Cmd_L(file_buffer,3);
	assertTrue(outContent.toString().equals("세번째 줄입니다.\r\n"+
											"네번째 줄입니다.\r\n"+
											"다섯번째 줄입니다.\r\n"));
	}
	
	@Test
	public void CommandSTest() throws IOException {
	Cmd_Driver cmd_driver=new Cmd_Driver();
	System.setOut(new PrintStream(outContent));
	cmd_driver.Cmd_S(file_buffer,-1);
	assertTrue(outContent.toString().equals("NUMBER LINES VALUE MUST BE POSITIVE & NONZERO.  No action taken.\r\n"));
	outContent.reset();

	cmd_driver.Cmd_S(file_buffer,3);
	assertTrue(outContent.toString().equals("첫 줄입니다.\r\n"+
											"두번째 줄입니다.\r\n"+
											"세번째 줄입니다.\r\n"));
	outContent.reset();
	file_buffer.SetCLN(5);
	cmd_driver.Cmd_S(file_buffer,3);
	assertTrue(outContent.toString().equals("다섯번째 줄입니다.\r\n"+
											"여섯번째 줄입니다.\r\n"+
											"일곱번째 줄입니다.\r\n"));
	}
	@Test
	public void CommandDTest() throws IOException {
		Cmd_Driver cmd_driver=new Cmd_Driver();
		System.setOut(new PrintStream(outContent));
		cmd_driver.Cmd_D(file_buffer,-1);
		assertTrue(outContent.toString().equals("NUMBER LINES VALUE MUST BE POSITIVE & NONZERO.  No action taken.\r\n"));
		outContent.reset();
		file_buffer.SetCLN(5);
		cmd_driver.Cmd_D(file_buffer,3);
		cmd_driver.Cmd_S(file_buffer,3);
		assertTrue(outContent.toString().equals("여덟번째 줄입니다.\r\n"+
												"아홉번째 줄입니다.\r\n"+
												"열번째 줄입니다.\r\n"));	
	}

	public void CommandATest() throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream("hi".getBytes());
	 	System.setIn(in);
		Cmd_Driver cmd_driver=new Cmd_Driver();
		cmd_driver.Cmd_A(file_buffer);
		System.setOut(new PrintStream(outContent));
		cmd_driver.Cmd_S(file_buffer,3);
		assertTrue(outContent.toString().equals("hi\r\n"+
												"첫 줄입니다.\r\n"+
												"두번째 줄입니다.\r\n"));	
	}
	@Test
	public void CommandFTest() throws IOException {
		Cmd_Driver cmd_driver=new Cmd_Driver();
		System.setOut(new PrintStream(outContent));
		cmd_driver.Cmd_F(file_buffer,3,"");
		assertTrue(outContent.toString().equals("A NULL (0 LENGTH) STRING HAS NO MEANING HERE:  No action taken.\r\n"));
		outContent.reset();
		cmd_driver.Cmd_F(file_buffer,10,"다섯");
		assertTrue(outContent.toString().equals("5: 다섯번째 줄입니다.\r\n"));
		
		outContent.reset();
		file_buffer.SetCLN(10);
		cmd_driver.Cmd_F(file_buffer,2,"열");
		assertTrue(outContent.toString().equals("10: 열번째 줄입니다.\r\n"+
												"11: 열한번째 줄입니다.\r\n"+
												"12: 열두번째 줄입니다.\r\n"));	
	}
	@Test
	public void CommandRTest() throws IOException {
		Cmd_Driver cmd_driver=new Cmd_Driver();
		System.setOut(new PrintStream(outContent));
		cmd_driver.Cmd_R(file_buffer,3,"","");
		assertTrue(outContent.toString().equals("A NULL (0 LENGTH) STRING HAS NO MEANING HERE:  No action taken.\r\n"));
		outContent.reset();
		file_buffer.SetCLN(10);
		cmd_driver.Cmd_R(file_buffer,1,"줄입니다.","욜");
		assertTrue(outContent.toString().equals("열번째 욜\r\n"));
	}
	@Test
	public void CommandYZPTest() throws IOException {
		Cmd_Driver cmd_driver=new Cmd_Driver();
		System.setOut(new PrintStream(outContent));
		cmd_driver.Cmd_P(file_buffer);
		assertTrue(outContent.toString().equals("NO LINES IN YANK BUFFER TO PUT:  No action taken.\r\n"));
		outContent.reset();
		file_buffer.SetCLN(10);
		cmd_driver.Cmd_Y(file_buffer, 5);
		cmd_driver.Cmd_P(file_buffer);
		assertEquals(file_buffer.GetLine(15),"열번째 줄입니다.");
	
		file_buffer.SetCLN(10);
		cmd_driver.Cmd_Z(file_buffer,2);
		assertEquals(file_buffer.GetLine(10),"열두번째 줄입니다.");
		assertEquals(file_buffer.GetLine(15),"열두번째 줄입니다.");
	}
	@Test
	public void CommandIKTest() throws IOException {
		Cmd_Driver cmd_driver=new Cmd_Driver();
		System.setOut(new PrintStream(outContent));
		cmd_driver.Cmd_I(file_buffer);
		assertTrue(outContent.toString().equals("THERE ARE NO KEYWORDS AT TOP OF FILE TO INDEX:  No action taken.\r\n"));
		outContent.reset();
		cmd_driver.Cmd_K("empty");
		assertTrue(outContent.toString().equals("THIS KEYWORD DOES NOT EXIST:  No action taken.\r\n"));
		outContent.reset();
		
		cmd_driver.Cmd_I(file_buffer2);
		cmd_driver.Cmd_K("키워드");
		assertTrue(outContent.toString().equals("1  2  \r\n"));
		
		outContent.reset();
		cmd_driver.Cmd_K("키워드2");
		assertTrue(outContent.toString().equals("2  \r\n"));
		
		outContent.reset();
		cmd_driver.Cmd_K("empty");
		assertTrue(outContent.toString().equals("THIS KEYWORD DOES NOT EXIST:  No action taken.\r\n"));
	}
	@Test
	public void CommandOTest() throws IOException {
		Cmd_Driver cmd_driver=new Cmd_Driver();
		System.setOut(new PrintStream(outContent));
		cmd_driver.Cmd_O(file_buffer,10);
		assertEquals(file_buffer.GetLine(1),"네번째 줄입니다.");
		assertEquals(file_buffer.GetLine(2),"다섯번째 줄입니다.");
		assertEquals(file_buffer.GetLine(11),"열한번째 줄입니다.");
		outContent.reset();
		cmd_driver.Cmd_O(file_buffer,15);
		assertEquals(file_buffer.GetLine(14),"첫 줄입니다.");
	}
	@Test
	public void CommandMTest() throws IOException {
		Cmd_Driver cmd_driver=new Cmd_Driver();
		System.setOut(new PrintStream(outContent));
		cmd_driver.Cmd_M(-1,-1);
		assertTrue(outContent.toString().equals("COLUMN VALUES MUST BE POSITIVE & NONZERO:  No action taken.\r\n"+
												"COMMAND NOT IMPLEMENTED (for F, R, O) YET.\r\n"));
		outContent.reset();
		cmd_driver.Cmd_M(3,2);
		assertTrue(outContent.toString().equals("REVERSED OR BACKWARDS COLUMN RANGES ARE ILLEGAL:  No action taken.\r\n"+
												"COMMAND NOT IMPLEMENTED (for F, R, O) YET.\r\n"));
		outContent.reset();
		cmd_driver.Cmd_M(2,3);
		assertTrue(outContent.toString().equals("COMMAND NOT IMPLEMENTED (for F, R, O) YET.\r\n"));
		
	}
	@Test
	public void CommandHTest() throws IOException {
		Cmd_Driver cmd_driver=new Cmd_Driver();
		System.setOut(new PrintStream(outContent));
		cmd_driver.Cmd_H("");
		assertTrue(outContent.toString().equals("Q :Update and Quit\r\n" + 
				"X :Don't Update and Quit\r\n" + 
				"T :Position CLN to line 1\r\n" + 
				"E :Position CLN to last line\r\n" + 
				"N :Move forward nl lines\r\n" + 
				"B :Move backwards nl lines\r\n" + 
				"W :Print/Show CLN value\r\n" + 
				"C :Print/Show # \\fle lines\r\n" + 
				"L :CLN at last listed\r\n" + 
				"S :CLN remain unchanged\r\n" + 
				"D :Delete line\r\n" + 
				"A :Quit w/return on new line\r\n" + 
				"F :str delimited\r\n" + 
				"R :oldstr & newstr delimited\r\n" + 
				"Y :Copy lines to buffer\r\n" + 
				"Z :Delete lines to buffer\r\n" + 
				"P :Puts buffer lines after CLN\r\n" + 
				"I :Regist Keyword\r\n" + 
				"K :keyword delimited\r\n" + 
				"O :Sorts L-H lines\r\n" + 
				"M :Sets column positions, c1 <= c2\r\n"));
		
	}
	@Test
	public void parseCmdLineTest1() throws IOException {
		//버퍼에 미리 사용자가 입력할 값을 줘서 테스트 할 수 있도록 설정
		ByteArrayInputStream in = new ByteArrayInputStream("T".getBytes());
	 	System.setIn(in);
	    assertEquals(Parser.parseCmdLine().getCmdLine(),"T");
	}
	
	@Test
	public void parseCmdLineTest2() throws IOException {
		System.setOut(new PrintStream(outContent));
		ByteArrayInputStream in = new ByteArrayInputStream("XYZ123".getBytes());
		System.setIn(in);
		Parser.parseCmdLine();
		assertTrue(outContent.toString().equals("EditCmd> \nTHIS IS VAN'S EDITOR!!\n\r\n"));
	}
	
	@Test
	public void parseCmdLineTest3() throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream("N 1".getBytes());
		System.setIn(in);
		assertEquals(Parser.parseCmdLine().getCmdName(),'N');
		ByteArrayInputStream in2 = new ByteArrayInputStream("N 2".getBytes());
		System.setIn(in2);
		assertEquals(Parser.parseCmdLine().getIntArgs(1),2);
		ByteArrayInputStream in3 = new ByteArrayInputStream("N".getBytes());
		System.setIn(in3);
		assertEquals(Parser.parseCmdLine().getIntArgs(1),0);
	}
	@Test
	public void parseCmdLineTest4() throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream("F 1 'STR'".getBytes());
		System.setIn(in);
		assertEquals(Parser.parseCmdLine().getStrArgs(1),"STR");
		ByteArrayInputStream in2 = new ByteArrayInputStream("F 1".getBytes());
		System.setIn(in2);
		assertEquals(Parser.parseCmdLine().getStrArgs(1),null);
	}
	@Test
	public void parseCmdLineTest5() throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream("M 1 3".getBytes());
		System.setIn(in);
		assertEquals(Parser.parseCmdLine().getIntArgs(1),1);
		ByteArrayInputStream in2 = new ByteArrayInputStream("M 1 3".getBytes());
		System.setIn(in2);
		assertEquals(Parser.parseCmdLine().getIntArgs(2),3);
		ByteArrayInputStream in3 = new ByteArrayInputStream("M 1".getBytes());
		System.setIn(in3);
		assertEquals(Parser.parseCmdLine().getIntArgs(2),0);
	}
	@Test
	public void parseCmdLineTest6() throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream("K 'hi'".getBytes());
		System.setIn(in);
		assertEquals(Parser.parseCmdLine().getStrArgs(1),"hi");
		ByteArrayInputStream in2 = new ByteArrayInputStream("K".getBytes());
		System.setIn(in2);
		assertEquals(Parser.parseCmdLine().getStrArgs(1),null);
	}
	
	@Test
	public void parseCmdLineTest7() throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream("R 10 'hi' 'hello'".getBytes());
		System.setIn(in);
		assertEquals(Parser.parseCmdLine().getIntArgs(1),10);
		ByteArrayInputStream in2 = new ByteArrayInputStream("R 10 'hi' 'hello'".getBytes());
		System.setIn(in2);
		assertEquals(Parser.parseCmdLine().getStrArgs(1),"hi");
		ByteArrayInputStream in3 = new ByteArrayInputStream("R 10 'hi' 'hello'".getBytes());
		System.setIn(in3);
		assertEquals(Parser.parseCmdLine().getStrArgs(2),"hello");
		ByteArrayInputStream in4 = new ByteArrayInputStream("R 10 'hi'".getBytes());
		System.setIn(in4);
		assertEquals(Parser.parseCmdLine().getStrArgs(2),null);
	}
	@Test
	public void parseCmdLineTest8() throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream("H T".getBytes());
		System.setIn(in);
		assertEquals(Parser.parseCmdLine().getOptArgs(1),"T");
		ByteArrayInputStream in2 = new ByteArrayInputStream("H".getBytes());
		System.setIn(in2);
		assertEquals(Parser.parseCmdLine().getOptArgs(1),null);
		ByteArrayInputStream in3 = new ByteArrayInputStream("H 'T'".getBytes());
		System.setIn(in3);
		assertEquals(Parser.parseCmdLine().getOptArgs(1),null);
	}
	@Test
	public void parseCmdLineTest9() throws IOException {
		//토큰이 아직 남아있는 경우
		ByteArrayInputStream in = new ByteArrayInputStream("H T F".getBytes());
		System.setIn(in);
		assertEquals(Parser.parseCmdLine().getOptArgs(2),null);
		
		ByteArrayInputStream in2 = new ByteArrayInputStream("N A".getBytes());
		System.setIn(in2);
		assertEquals(Parser.parseCmdLine().getIntArgs(1),0);
		
	}
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

}
