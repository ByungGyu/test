
import java.io.*;

public class Help
/****************************************************************************
*  AUTH:  Truly, Yours                    DATE:  Oct. 1999                  *
*  DEPT:  Computer Science, CS-200        ORG.:  Colorado State University  *
*****************************************************************************
*                                                                           *
*  FILE:  Help.java                                                         *
*                                                                           *
*  DESC:  Contains the member functions for the Help Class.        .        *
*                                                                           *
****************************************************************************/
{

  //***************************************************************************
  public static void General()
  { 
	  Msg.wLMsg("Q :Update and Quit");
	  Msg.wLMsg("X :Don't Update and Quit");
	  Msg.wLMsg("T :Position CLN to line 1");
	  Msg.wLMsg("E :Position CLN to last line");
	  Msg.wLMsg("N :Move forward nl lines");
	  Msg.wLMsg("B :Move backwards nl lines");
	  Msg.wLMsg("W :Print/Show CLN value");
	  Msg.wLMsg("C :Print/Show # \fle lines");
	  Msg.wLMsg("L :CLN at last listed");
	  Msg.wLMsg("S :CLN remain unchanged");
	  Msg.wLMsg("D :Delete line");
	  Msg.wLMsg("A :Quit w/return on new line");
	  Msg.wLMsg("F :str delimited");
	  Msg.wLMsg("R :oldstr & newstr delimited");
	  Msg.wLMsg("Y :Copy lines to buffer");
	  Msg.wLMsg("Z :Delete lines to buffer");
	  Msg.wLMsg("P :Puts buffer lines after CLN");
	  Msg.wLMsg("I :Regist Keyword");
	  Msg.wLMsg("K :keyword delimited");
	  Msg.wLMsg("O :Sorts L-H lines");
	  Msg.wLMsg("M :Sets column positions, c1 <= c2");
  }
	 

  //***************************************************************************
  public static void Command(char cmd)
  {
	  switch(cmd) {
	  case 'Q':
		  Msg.wLMsg("Update and Quit");
		  break;
	  case 'X':
		  Msg.wLMsg("Don't Update and Quit");
		  break;
	  case 'T':
		  Msg.wLMsg("Position CLN to line 1");
		  break;
	  case 'E':
		  Msg.wLMsg("Position CLN to last line");
		  break;
	  case 'N':
		  Msg.wLMsg("Move forward nl lines");
		  break;
	  case 'B':
		  Msg.wLMsg("Move backwards nl lines");
		  break;
	  case 'W':
		  Msg.wLMsg("Print/Show CLN value");
		  break;
	  case 'C':
		  Msg.wLMsg("Print/Show # \fle lines");
		  break;
	  case 'L':
		  Msg.wLMsg("CLN at last listed");
		  break;
	  case 'S':
		  Msg.wLMsg("CLN remain unchanged");
		  break;
	  case 'D':
		  Msg.wLMsg("Delete line");
		  break;
	  case 'A':
		  Msg.wLMsg("Quit w/return on new line");
		  break;
	  case 'F':
		  Msg.wLMsg("str delimited");
		  break;
	  case 'R':
		  Msg.wLMsg("oldstr & newstr delimited");
		  break;
	  case 'Y':
		  Msg.wLMsg("Copy lines to buffer");
		  break;
	  case 'Z':
		  Msg.wLMsg("Delete lines to buffer");
		  break;
	  case 'P':
		  Msg.wLMsg("Puts buffer lines after CLN");
		  break;
	  case 'I':
		  Msg.wLMsg("Regist Keyword");
		  break;
	  case 'K':
		  Msg.wLMsg("keyword delimited");
		  break;
	  case 'O':
		  Msg.wLMsg("Sorts L-H lines");
		  break;
	  case 'M':
		  Msg.wLMsg("Sets column positions, c1 <= c2");
		  break;
	  default :
		  Msg.wLMsg("This is Command that don't exist");
	  }
  }

} // EndClass Help
