

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class Cmd_DriverTest {
	private final ByteArrayOutputStream outContent= new ByteArrayOutputStream();
	private final File_Buffer file_buffer=new File_Buffer();
	private final File_Buffer file_buffer2=new File_Buffer();
	private  Init_Exit init_exit;
	private  Init_Exit init_exit2;
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
	public void CommandHTest() throws IOException {
		Cmd_Driver cmd_driver=new Cmd_Driver();
		System.setOut(new PrintStream(outContent));
		cmd_driver.Cmd_H(null);
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
	assertTrue(outContent.toString().equals("ù ���Դϴ�.\r\n"+
											"�ι�° ���Դϴ�.\r\n"+
											"����° ���Դϴ�.\r\n"));
	outContent.reset();
	cmd_driver.Cmd_L(file_buffer,3);
	assertTrue(outContent.toString().equals("����° ���Դϴ�.\r\n"+
											"�׹�° ���Դϴ�.\r\n"+
											"�ټ���° ���Դϴ�.\r\n"));
	}
	
	@Test
	public void CommandSTest() throws IOException {
	Cmd_Driver cmd_driver=new Cmd_Driver();
	System.setOut(new PrintStream(outContent));
	cmd_driver.Cmd_S(file_buffer,-1);
	assertTrue(outContent.toString().equals("NUMBER LINES VALUE MUST BE POSITIVE & NONZERO.  No action taken.\r\n"));
	outContent.reset();

	cmd_driver.Cmd_S(file_buffer,3);
	assertTrue(outContent.toString().equals("ù ���Դϴ�.\r\n"+
											"�ι�° ���Դϴ�.\r\n"+
											"����° ���Դϴ�.\r\n"));
	outContent.reset();
	file_buffer.SetCLN(5);
	cmd_driver.Cmd_S(file_buffer,3);
	assertTrue(outContent.toString().equals("�ټ���° ���Դϴ�.\r\n"+
											"������° ���Դϴ�.\r\n"+
											"�ϰ���° ���Դϴ�.\r\n"));
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
		assertTrue(outContent.toString().equals("������° ���Դϴ�.\r\n"+
												"��ȩ��° ���Դϴ�.\r\n"+
												"����° ���Դϴ�.\r\n"));	
	}

	public void CommandATest() throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream("hi".getBytes());
	 	System.setIn(in);
		Cmd_Driver cmd_driver=new Cmd_Driver();
		cmd_driver.Cmd_A(file_buffer);
		System.setOut(new PrintStream(outContent));
		cmd_driver.Cmd_S(file_buffer,3);
		assertTrue(outContent.toString().equals("hi\r\n"+
												"ù ���Դϴ�.\r\n"+
												"�ι�° ���Դϴ�.\r\n"));	
	}
	@Test
	public void CommandFTest() throws IOException {
		Cmd_Driver cmd_driver=new Cmd_Driver();
		System.setOut(new PrintStream(outContent));
		cmd_driver.Cmd_F(file_buffer,3,"");
		assertTrue(outContent.toString().equals("A NULL (0 LENGTH) STRING HAS NO MEANING HERE:  No action taken.\r\n"));
		outContent.reset();
		cmd_driver.Cmd_F(file_buffer,10,"�ټ�");
		assertTrue(outContent.toString().equals("5: �ټ���° ���Դϴ�.\r\n"));
		
		outContent.reset();
		file_buffer.SetCLN(10);
		cmd_driver.Cmd_F(file_buffer,2,"��");
		assertTrue(outContent.toString().equals("10: ����° ���Դϴ�.\r\n"+
												"11: ���ѹ�° ���Դϴ�.\r\n"+
												"12: ���ι�° ���Դϴ�.\r\n"));	
	}
	@Test
	public void CommandRTest() throws IOException {
		Cmd_Driver cmd_driver=new Cmd_Driver();
		System.setOut(new PrintStream(outContent));
		cmd_driver.Cmd_R(file_buffer,3,"","");
		assertTrue(outContent.toString().equals("A NULL (0 LENGTH) STRING HAS NO MEANING HERE:  No action taken.\r\n"));
		outContent.reset();
		file_buffer.SetCLN(10);
		cmd_driver.Cmd_R(file_buffer,1,"���Դϴ�.","��");
		assertTrue(outContent.toString().equals("����° ��\r\n"));
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
		assertEquals(file_buffer.GetLine(15),"����° ���Դϴ�.");
	
		file_buffer.SetCLN(10);
		cmd_driver.Cmd_Z(file_buffer,2);
		assertEquals(file_buffer.GetLine(10),"���ι�° ���Դϴ�.");
		assertEquals(file_buffer.GetLine(15),"���ι�° ���Դϴ�.");
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
		cmd_driver.Cmd_K("Ű����");
		assertTrue(outContent.toString().equals("1  2  \r\n"));
		
		outContent.reset();
		cmd_driver.Cmd_K("Ű����2");
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
		assertEquals(file_buffer.GetLine(1),"�׹�° ���Դϴ�.");
		assertEquals(file_buffer.GetLine(2),"�ټ���° ���Դϴ�.");
		assertEquals(file_buffer.GetLine(11),"���ѹ�° ���Դϴ�.");
		outContent.reset();
		cmd_driver.Cmd_O(file_buffer,15);
		assertEquals(file_buffer.GetLine(14),"ù ���Դϴ�.");
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
}
