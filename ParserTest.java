



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.junit.Rule;
import org.junit.Test;


public class ParserTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

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
}
