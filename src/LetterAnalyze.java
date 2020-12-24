import java.util.Scanner;
public class LetterAnalyze {
	LetterAnalyze ()
	{}
public  SymbolTable analyzeL(SymbolTable keyTable,SymbolTable SYTable) {
	int status=0;
	SymbolTable tokenTable=new SymbolTable();
	
	Scanner scn=new Scanner(System.in);
	tool tl=new tool();
	String temSTR="";
	String temSTRtem="";
	do {temSTRtem=scn.nextLine();
	
	temSTR=temSTR.concat(temSTRtem);
	temSTR=temSTR.concat(" ");
	}while(!temSTRtem.endsWith("#"));
	char buffer[]=temSTR.toCharArray();
	int [] forward=new int[1];
		forward[0]=0;//÷∏’Î	
		String token="";
		char C=0;
	do {//while
	switch (status) {
	case 0:{
		token="";
		C=tl.getNBlank(buffer, forward);
		switch(C)
		{//switch
		case 'a':	case 'b':	case 'c':	case 'd':	case 'e':	case 'f':	case 'g':	case 'h':	case 'i':	case 'j':	case 'k':
		case 'l':	case 'm':	case 'n':	case 'o':	case 'p':	case 'q':	case 'r':	case 's':	case 't':	case 'u':	case 'v':
		case 'w':	case 'x':	case 'y':	case 'z':	case 'A':	case 'B':	case 'C':	case 'D':	case 'E':	case 'F':	case 'G':	
		case 'H':	case 'I':	case 'J':	case 'K':	case 'L':	case 'M':	case 'N':	case 'O':	case 'P':	case 'Q':	case 'R':
		case 'S':	case 'T':	case 'U':	case 'V':	case 'W':	case 'X':	case 'Y':	case 'Z':
			status=1;
			token=tl.addCharacter(token, C);
			//C=tl.getFirstL(buffer, forward);
			break;
		case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
			status=2;
			token=tl.addCharacter(token, C);
			//C=tl.getFirstL(buffer, forward);
			break;
		case '=':
		
			//System.out.println(29+"\""+"="+"\"");
			tokenTable.insert(new SymbolNode(29,"="));
			break;
		case '$':
			
			//System.out.println(29+"\""+"="+"\"");
			tokenTable.insert(new SymbolNode(10,"$"));
			break;
		case '+':
		
			//System.out.println(25+"\""+"+"+"\"");
			tokenTable.insert(new SymbolNode(25,"+"));
			break;
		case '-':
	
			//System.out.println(26+"\""+"-"+"\"");
			tokenTable.insert(new SymbolNode(26,"-"));
			break;
		case '*':
		
			//System.out.println(27+"\""+"*"+"\"");
			tokenTable.insert(new SymbolNode(27,"*"));
			break;
		case '/':
		
			//System.out.println(28+"\""+"/"+"\"");
			tokenTable.insert(new SymbolNode(28,"/"));
			break;
		case ';':
			
			//System.out.println(48+"\""+";"+"\"");
			tokenTable.insert(new SymbolNode(48,";"));
			break;
		case '(':
			
			//System.out.println(44+"\""+"("+"\"");
			tokenTable.insert(new SymbolNode(44,"("));
			break;
		case ',':
			
			//System.out.println(44+"\""+"("+"\"");
			tokenTable.insert(new SymbolNode(49,","));
			break;
			
		case ')':
			
			//System.out.println(45+"\""+")"+"\"");
			tokenTable.insert(new SymbolNode(45,")"));
			break;
			
		case '.':
			
			//System.out.println(47+"\""+"."+"\"");
			tokenTable.insert(new SymbolNode(47,"."));
			break;
		case '\'':
			
			//System.out.println(51+"\""+"'"+"\"");
			tokenTable.insert(new SymbolNode(51,"\\"));
			break;
		case '<':
			status=8;
			token=tl.addCharacter(token, C);
			
			break;
		case '>':
			status=9;
			token=tl.addCharacter(token, C);
		
			break;
		case ':':
			status=10;
			token=tl.addCharacter(token, C);
		
			break;
			
		default:if(C=='#')
		{
			tokenTable.insert(new SymbolNode(0,"#"));
		}
			break;
		}
		
			
		break;}
		
		
		
	case 1:{
		C=tl.getFirstL(buffer, forward);
		switch(C){
	case 'a':	case 'b':	case 'c':	case 'd':	case 'e':	case 'f':	case 'g':	case 'h':	case 'i':	case 'j':	case 'k':
	case 'l':	case 'm':	case 'n':	case 'o':	case 'p':	case 'q':	case 'r':	case 's':	case 't':	case 'u':	case 'v':
	case 'w':	case 'x':	case 'y':	case 'z':	case 'A':	case 'B':	case 'C':	case 'D':	case 'E':	case 'F':	case 'G':	
	case 'H':	case 'I':	case 'J':	case 'K':	case 'L':	case 'M':	case 'N':	case 'O':	case 'P':	case 'Q':	case 'R':
	case 'S':	case 'T':	case 'U':	case 'V':	case 'W':	case 'X':	case 'Y':	case 'Z':	case '0':	case '1':	case '2':
	case '3':	case '4':	case '5':	case '6':	case '7':	case '8':	case '9':
		status=1;
		token=tl.addCharacter(token, C);
		
		if(C=='#')
		{
			if(keyTable.findNode(token)!=-1)
			{
			SymbolNode temSN=new SymbolNode();
			temSN=keyTable.getThisNode(keyTable.findNode(token));
			//temSN.print();
			tokenTable.insert(new SymbolNode(temSN.type,token,temSN.add));
			tokenTable.insert(new SymbolNode(0,"#"));}
		else {
			if(!SYTable.equal(token))
			tl.insNode(token,39,SYTable);
		tokenTable.insert(new SymbolNode(39,token));
		tokenTable.insert(new SymbolNode(0,"#"));}
			
			
		}
		break;
	default:
		
			forward[0]--;
			status=0;
	if(keyTable.findNode(token)!=-1)
		{
		SymbolNode temSN=new SymbolNode();
		temSN=keyTable.getThisNode(keyTable.findNode(token));
		//temSN.print();
		tokenTable.insert(new SymbolNode(temSN.type,token,temSN.add));}
	else {
		if(!SYTable.equal(token))
		tl.insNode(token,39,SYTable);
	tokenTable.insert(new SymbolNode(39,token,SYTable.size-1));}
	if(C=='#')
		tokenTable.insert(new SymbolNode(0,"#"));
	break;
	}	
	break;}
	
	case 2:C=tl.getFirstL(buffer, forward);
		switch(C)
		{
		case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
			status=2;
			token=tl.addCharacter(token, C);
			
			if(C=='#')
			{
				if(keyTable.findNode(token)!=-1)
				{
				SymbolNode temSN=new SymbolNode();
				temSN=keyTable.getThisNode(keyTable.findNode(token));
				//temSN.print();
				tokenTable.insert(temSN);
				tokenTable.insert(new SymbolNode(0,"#"));}
			else {tl.insNode(token,40,SYTable);
			tokenTable.insert(new SymbolNode(40,token,SYTable.size-1));
			tokenTable.insert(new SymbolNode(0,"#"));}
			}
			break;
		case 'E':
			status=5;
			token=tl.addCharacter(token, C);
			//C=tl.getFirstL(buffer, forward);
			
			break;
		case '.':
			status=3;
			token=tl.addCharacter(token, C);
			//C=tl.getFirstL(buffer, forward);
		
			break;

		default:
			forward[0]--;
			status=0;
	if(keyTable.findNode(token)!=-1)
		{
		SymbolNode temSN=new SymbolNode();
		temSN=keyTable.getThisNode(keyTable.findNode(token));
		//temSN.print();
		tokenTable.insert(temSN);}
	else {tl.insNode(token,40,SYTable);
	tokenTable.insert(new SymbolNode(40,token,SYTable.size-1));}
	if(C=='#')
		tokenTable.insert(new SymbolNode(0,"#"));
	break;
			
		}
	break;
	
	case 3:C=tl.getFirstL(buffer, forward);
		switch(C)
		{
		case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
			status=4;
			token=tl.addCharacter(token, C);
			
			break;
		}
		break;
		
	case 4:C=tl.getFirstL(buffer, forward);
		switch(C)
		{
		case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
			status=4;
			token=tl.addCharacter(token, C);
			
			if(C=='#')
			{
				if(keyTable.findNode(token)!=-1)
				{
				SymbolNode temSN=new SymbolNode();
				temSN=keyTable.getThisNode(keyTable.findNode(token));
				//temSN.print();
				tokenTable.insert(temSN);
				tokenTable.insert(new SymbolNode(0,"#"));}
			else {tl.insNode(token,41,SYTable);
			tokenTable.insert(new SymbolNode(41,token,SYTable.size-1));
			tokenTable.insert(new SymbolNode(0,"#"));}
				}
			break;
		case 'E':
			status=5;
			token=tl.addCharacter(token, C);
			//C=tl.getFirstL(buffer, forward);
			
		default:
			forward[0]--;
			status=0;
	if(keyTable.findNode(token)!=-1)
		{
		SymbolNode temSN=new SymbolNode();
		temSN=keyTable.getThisNode(keyTable.findNode(token));
		//temSN.print();
		tokenTable.insert(temSN);}
	else {tl.insNode(token,41,SYTable);
	tokenTable.insert(new SymbolNode(41,token,SYTable.size-1));}
	break;
		}
		break;
		
	case 5:C=tl.getFirstL(buffer, forward);
		switch(C)
		{
		case'+':
		case'-':
			status=6;
			token=tl.addCharacter(token, C);
			
			break;
		case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
			status=7;
			token=tl.addCharacter(token, C);
			//C=tl.getFirstL(buffer, forward);
			break;
		
		}
		break;
	case 6:C=tl.getFirstL(buffer, forward);
		switch(C)
		{
		case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
			status=7;
			token=tl.addCharacter(token, C);
			
			break;
		
		
		}
		break;
		
	case 7:C=tl.getFirstL(buffer, forward);
		switch(C)
		{
		case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
			status=7;
			token=tl.addCharacter(token, C);
			
			if(C=='#')
			{
				if(keyTable.findNode(token)!=-1)
				{
				SymbolNode temSN=new SymbolNode(keyTable.getThisNode(keyTable.findNode(token)).type,keyTable.getThisNode(keyTable.findNode(token)).property,keyTable.getThisNode(keyTable.findNode(token)).add);
				//temSN=keyTable.getThisNode(keyTable.findNode(token));
				//temSN.print();
				tokenTable.insert(temSN);
				tokenTable.insert(new SymbolNode(0,"#"));}
			else {tl.insNode(token,41,SYTable);
			tokenTable.insert(new SymbolNode(41,token,SYTable.size-1));
			tokenTable.insert(new SymbolNode(0,"#"));}
				}
			break;
		default:
			forward[0]--;
			status=0;
	if(keyTable.findNode(token)!=-1)
		{
		SymbolNode temSN=new SymbolNode(keyTable.getThisNode(keyTable.findNode(token)).type,keyTable.getThisNode(keyTable.findNode(token)).property,keyTable.getThisNode(keyTable.findNode(token)).add);
		//temSN=keyTable.getThisNode(keyTable.findNode(token));
		//temSN.print();
		tokenTable.insert(temSN);}
	else {tl.insNode(token,41,SYTable);
	tokenTable.insert(new SymbolNode(41,token,SYTable.size-1));}
	break;
		
		}
		break;
		
		
	case 8:C=tl.getFirstL(buffer, forward);
		
		switch(C)
		{
		case '=':
			//System.out.println(35+"\""+"<="+"\"");
			status=0;
			tokenTable.insert(new SymbolNode(35,"<="));
			break;
		case '>':
			//System.out.println(37+"\""+"<>"+"\"");
			tokenTable.insert(new SymbolNode(37,"<>"));
			status=0;
			break;
		default:
			forward[0]--;
			status=0;
			//System.out.println(30+"\""+"<"+"\"");
			tokenTable.insert(new SymbolNode(30,"<"));
	break;
		}break;
	case 9:C=tl.getFirstL(buffer, forward);
	
		switch(C)
		{
		case '=':
			//System.out.println(36+"\""+">="+"\"");
			tokenTable.insert(new SymbolNode(36,">="));
			status=0;
			break;
	
		default:
			forward[0]--;
			status=0;
			//System.out.println(31+"\""+">"+"\"");
			tokenTable.insert(new SymbolNode(31,">"));
	break;
		}break;
	case 10:C=tl.getFirstL(buffer, forward);
		
		switch(C)
		{
		case '=':
			//System.out.println(38+"\""+":="+"\"");
			tokenTable.insert(new SymbolNode(38,":="));
			status=0;
			break;
		
		default:
			forward[0]--;
			status=0;
			//System.out.println(46+"\""+":"+"\"");
			tokenTable.insert(new SymbolNode(46,":"));
	break;
		}break;	
		
	}
		
	
	
	
		
}while(C!='#');//while
	System.out.println("SYTable:");
	SYTable.print();
	System.out.println("token:");
	tokenTable.print();
	return tokenTable;
	
}
}