
public class GrammerAnalyze {
	GrammerAnalyze()
	{}
	public boolean declareAnalyze(SymbolTable token)
	{
		SymbolNode forward=token.getThisNode(1);
		Stark sk=new Stark();
		sk.push(new SymbolNode("#"));
		sk.push(new SymbolNode("X"));
		while(!forward.property.equals("#")||(!sk.first.property.equals("#")))
		{
			if(sk.first.property.equals(forward.property))
			{
				sk.pop();
				forward=forward.next;
				
			}
			if(sk.first.property.equals("X"))
			if(forward.property.equals("var")||forward.property.equals("#")||forward.property.equals(";"))
			{
				sk.pop();
				sk.push(new SymbolNode("S1"));
				sk.push(new SymbolNode("S"));
				
			}
			
			if(sk.first.property.equals("S1"))
				if(forward.property.equals(";"))
				{
					sk.pop();
					sk.push(new SymbolNode("X"));
					sk.push(new SymbolNode(";"));
					
					
				}
			if(sk.first.property.equals("S1"))
				if(forward.property.equals("#"))
				
					sk.pop();
					
			if(sk.first.property.equals("S"))
				if(forward.property.equals("var"))
				{
					sk.pop();
					sk.push(new SymbolNode("D"));
					sk.push(new SymbolNode("var"));
					
				}
			if(sk.first.property.equals("S"))
				if(forward.property.equals(";")||forward.property.equals("#"))
				{
					sk.pop();
					
				}
			
			if(sk.first.property.equals("D"))
				if(forward.type==39)
				{
					sk.pop();
						sk.push(new SymbolNode("D1"));
						sk.push(new SymbolNode(";"));
						sk.push(new SymbolNode("K"));
						sk.push(new SymbolNode(":"));
						sk.push(new SymbolNode("L"));
				
					
				}
			
			if(sk.first.property.equals("D1"))
				if(forward.property.equals(";")||forward.property.equals("#"))
				{
					sk.pop();
					
				}
			
			
			if(sk.first.property.equals("D1"))
				if(forward.type==39)
				{
					sk.pop();
					sk.push(new SymbolNode("D"));
				}
			
			if(sk.first.property.equals("L"))
				if(forward.type==39)
				{
					sk.pop();
					sk.push(new SymbolNode("L1"));
					sk.push(new SymbolNode(forward.property));
				}
			
			if(sk.first.property.equals("L1"))
				if(forward.property.equals(","))
				{
					sk.pop();
					sk.push(new SymbolNode("L"));
					sk.push(new SymbolNode(","));
					
				}
			
			if(sk.first.property.equals("L1"))
				if(forward.property.equals(":"))
				{
					sk.pop();
				
				}
			
			if(sk.first.property.equals("K"))
				if(forward.property.equals("integer"))
				{
					sk.pop();
					sk.push(new SymbolNode("integer"));
				}
			
			if(sk.first.property.equals("K"))
				if(forward.property.equals("bool"))
				{
					sk.pop();
					sk.push(new SymbolNode("bool"));
				}
			if(sk.first.property.equals("K"))
				if(forward.property.equals("real"))
				{
					sk.pop();
					sk.push(new SymbolNode("real"));
				}
			
		}
		
		if(sk.first.property.equals(forward.property))
			return true;
		else return false;
	}	
	
	public boolean CountAnalyze(SymbolTable token) {
		//String[] Symbol= {"+","*","(",")","-","id","#"};
		int[] Symbol= {25,27,44,45,26,39,0};
		String[][] primary= new String[7][7];
		String[] primary1={">","<","<",">","<","<",">"};
		String[] primary2={">",">","<",">","<","<",">"};
		String[] primary3={"<","<","<","=","<","<","null"};
		String[] primary4={">",">","null",">","null","null",">"};
		String[] primary5={">",">","null",">","<","<",">"};
		String[] primary6={">",">","null",">","null","null",">"};
		String[] primary7={"<","<","<","null","<","<","="};
		primary[0]=primary1;
		primary[1]=primary2;
		primary[2]=primary3;
		primary[3]=primary4;
		primary[4]=primary5;
		primary[5]=primary6;
		primary[6]=primary7;
		
		//int i=0;
		//int j=0;
		//String[] CountStack=new String[token.size+1];
		Stark CountStack=new Stark();
		//CountStack[0]="#";
		CountStack.push(new SymbolNode(0,"#"));
		//String p1;
		SymbolNode p1=new SymbolNode();
		//String p2;
		SymbolNode p2=new SymbolNode();
		SymbolNode p3=token.getThisNode(1);
		do {
			if(isVT(CountStack.first))
				p1=CountStack.first;
			else p1=CountStack.first.next;//p1指向栈顶第一个终结符
			p2=p1;
			if(getSequence(primary,Symbol,p1,p3).equals(">"))
			{
				if(p2.type==p1.type&&p1.type==0)
						p2=p1;
				else
				do {
					if(isVT(p2.next))
						p2=p2.next;
					else p2=p2.next.next;
					}while(!getSequence(primary,Symbol,p2,p1).equals("<"));
				//q2取小于q1的第一个终结符
				if(p1.type==25||p1.type==27)
				{
					while(p1!=p2)
					{
						p1=p1.next;
						CountStack.pop();
					}
					CountStack.push(new SymbolNode("N"));
				}//加法或乘法的规约
				
				if(p1.type==45)
				{
					while(p1.type!=44)
					{
						p1=p1.next;
						CountStack.pop();
						
					}
					p1=p1.next;
					CountStack.pop();
					CountStack.push(new SymbolNode("N"));
				}//右括号的情况
				
				if(p1.type==26)
				{
					p1=p1.next.next;
					CountStack.pop();
					CountStack.pop();
					CountStack.push(new SymbolNode("N"));
				}//负号的情况
			
				if(p1.type==39||p1.type==40||p1.type==41)
				{
					p1=p1.next;
				CountStack.pop();
				CountStack.push(new SymbolNode("N"));
				}//用户自定义情况
				
			}
			else {
				CountStack.push(new SymbolNode(p3.type,p3.property,p3.add));
				p3=p3.next;
			}//移进的情况
			
		}while(!(p1.type==0&&(p2.type==0||p2.type==1)));
		
		//System.out.println("success!");
		return true;
	}
		
	
	
	//int tem=0;//记住p2位置
	/*do {
		
		if(isVT(CountStack[i]))
		{p1=CountStack[i];
		//tem=i;}
		else {p1=CountStack[i-1];
		//tem=i-1;}
		//int tem1=tem;//记住p1位置
		p2=p1;
		if(getSequence(primary,Symbol,p1,token.getThisNode(j).property).equals("<")||getSequence(primary,Symbol,p1,token.getThisNode(j).property).equals("="))
		//push
		{
			i++;
			CountStack[i]=token.getThisNode(j).property;
			j++;
		}
		else {
			do {
				tem1=tem;
				p1=p2;
				if(tem==0)
					p2=p1;
				else
			if(isVT(CountStack[tem-1]))
				{p2=CountStack[tem-1];
				tem--;}
			else {
				p2=CountStack[tem-2];
				tem=tem-2;
			}}while(!getSequence(primary,Symbol,p2,p1).equals("<"));
			tem++;
			if(p1.equals("id"))
				CountStack[tem1]="N";
			else if(p1.equals(")"))
					{
				CountStack[tem]="N";
				for(int i1=tem;i1<tem1+1;i1++)
					CountStack[i1]=null;
				
					}
			else if(!p1.equals("#"))
			{
				CountStack[tem]="N";
				for(int i1=tem;i1<tem1+2;i1++)
					CountStack[i1]=null;
			}
		}
		
		
	}while(p1!="#");	*/
	public boolean start(SymbolTable token)
	{
		if(token.getThisNode(1).type==9&&token.getThisNode(2).type==39&&GrAnalyze(token.toSmall(2, token.findNode(0))))
		{
			return true;
			
		}
		return false;
	}
	public boolean GrAnalyze(SymbolTable token)
	{
		if(declareAnalyze(token.toSmall(0,token.findNode(10)))&&LAnalyze(token.toSmall(token.findNode(10), token.findNode(0))))
				{
			token.getThisNode(0).next=new SymbolNode("D");
			token.getThisNode(1).next=new SymbolNode(10,"$");
			token.getThisNode(2).next=new SymbolNode("L");
			token.getThisNode(3).next=new SymbolNode("#");
			return true;
				}
				
		return true;
	}
		
	public String getSequence(String[][] a,int[] b,SymbolNode x,SymbolNode y)
	{
		int c=0;
		int d=0;
		switch(x.type)
		{case 39:
		case 40:
		case 41:c=5;break;
		default:
		while(b[c]!=x.type)	c++;break;}
		
		switch(y.type)
		{case 39:
		case 40:
		case 41:d=5;break;
		default:
		while(b[d]!=y.type)	d++;break;}
		return a[c][d];
		
	}
	public boolean isVT(SymbolNode x)
	{
		if(x.type>-1&&x.type<54)
			return true;
		else return false;
	}
	
	public boolean boolAnalyze(SymbolTable token) {
		//String Symbol[]= {"or","and","not","(",")","relop","id","#"};
		int [] Symbol= {33,32,34,44,45,30,39,0};
		String primary[][]= {{">","<","<","<",">","<","<",">"},{">",">","<","<",">","<","<",">"},{">",">","null","<",">","<","<",">"},{"<","<","<","<","=","<","<","null"},{">",">","null","null",">","null","null",">"},{">",">","null","null",">","null","<",">"},{">",">","null","null",">",">","null",">"},{"<","<","<","<","null","<","<","="}};
		Stark BoolStack=new Stark();
		BoolStack.push(new SymbolNode(0,"#"));
		SymbolNode p1=new SymbolNode();
		SymbolNode p2=new SymbolNode();
		SymbolNode p3=token.getThisNode(1);
		do {
			if(isVT(BoolStack.first))
					p1=BoolStack.first;
			else p1=BoolStack.first.next;
			p2=p1;
			if(getSequence1(primary,Symbol,p1,p3).equals(">"))
			{
				if(p1.type==p2.type&&p1.type==0)
					p2=p1;
				else do {
					if(isVT(p2.next))
						p2=p2.next;
					else p2=p2.next.next;
				}while(!getSequence1(primary,Symbol,p2,p1).equals("<"));//p1，p2位置确定，开始规约
				
				switch(p1.type)
				{	
				case 29://relop
				case 30:
				case 31:
				case 35:
				case 36:
				case 37:
					p1=p1.next.next;
					BoolStack.pop();
					BoolStack.pop();
					BoolStack.pop();
					BoolStack.push(new SymbolNode("B"));
					break;
				case 39://id
				case 40:
				case 41:
				case 42:
				case 43:	
					p1=p1.next;
					BoolStack.pop();
					BoolStack.push(new SymbolNode("B"));
					break;
				case 32://and
				case 33://or
					p1=p1.next.next;
					BoolStack.pop();
					BoolStack.pop();
					BoolStack.pop();
					BoolStack.push(new SymbolNode("B"));
					break;
				case 34://not
					p1=p1.next.next;
					BoolStack.pop();
					BoolStack.pop();
					BoolStack.push(new SymbolNode("B"));
					break;
				case 45://")"
					p1=p1.next.next.next;
					BoolStack.pop();
					BoolStack.pop();
					BoolStack.pop();
					BoolStack.push(new SymbolNode("B"));
					break;
				default:break;//错误
				}
			}
			else 
			{
				BoolStack.push(new SymbolNode(p3.type,p3.property,p3.add));
				p3=p3.next;
				
			}//移进
			
			if(isVT(BoolStack.first))
				p1=BoolStack.first;
		else p1=BoolStack.first.next;
		}while(!(p1.type==0&&p3.type==0));
		//System.out.println("Success!");
		return true;
	}
	
	public String getSequence1(String[][] a,int[] b,SymbolNode x,SymbolNode y)
	{
		int c=0;
		int d=0;
		switch(x.type)
		{
		case 29:
		case 30:
		case 31:
		case 35:
		case 36:
		case 37:c=5;break;//relop	
		
		case 39:
		case 40:
		case 41:
		case 42:
		case 43:	c=6;break;//id
		default:
		while(b[c]!=x.type)	c++;break;}
		
		switch(y.type)
		{
		case 29:
		case 30:
		case 31:
		case 35:
		case 36:
		case 37:d=5;break;//relop	
		
		case 39:
		case 40:
		case 41:
		case 42:
		case 43:	d=6;break;
		default:
		while(b[d]!=y.type)	d++;break;}
		return a[c][d];
		
	}
	
		//{">","<","<","<",">","<","<",">"};第一行
		//{">",">","<","<",">","<","<",">"};第二行
		//{">",">","null","<",">","<","<",">"};第三行
		//{"<","<","<","<","=","<","<","null"};第四行
		//{">",">","null","null",">","null","null",">"};第五行
		//{">",">","null","null",">","null","<",">"};第六行
		//{">",">","null","null",">",">","null",">"};第七行
		//{"<","<","<","<","null","<","<","="};第八行
		/*int i=0;
		int j=1;
		String[] CountStack=new String[token.size+1];
		CountStack[0]="#";
		String p1;
		String p2;
		int tem=0;//记住p2位置,末尾
		do {
			if(isVT(CountStack[i]))
			{p1=CountStack[i];
			tem=i;}
			else {p1=CountStack[i-1];
			tem=i-1;}
			int tem1=tem;//记住p1位置，即待归约终结符位置
			p2=p1;
			if(getSequence(primary,Symbol,p1,token.getThisNode(j).property).equals("<")||getSequence(primary,Symbol,p1,token.getThisNode(j).property).equals("="))
				//push
				{
					i++;
					CountStack[i]=token.getThisNode(j).property;
					j++;
				}
			else {
				do {
					tem1=tem;
					p1=p2;
					if(tem==0)
						p2=CountStack[0];
					else
				if(isVT(CountStack[tem-1]))
					{p2=CountStack[tem-1];
					tem--;}
				else {
					p2=CountStack[tem-2];
					tem=tem-2;
				}}while(!getSequence(primary,Symbol,p2,p1).equals("<"));
				tem++;//找到小于它优先级的终结符
				if(p1.equals("id"))
				{
					CountStack[tem1]="N";//id
					
				}
				else if(p1.equals("relop"))
				{
					CountStack[tem]="N";
					for(int i1=tem;i1<tem1+1;i1++)
						CountStack[i1]=null;//relop
					i=i-2;
				}
				else if(p1.equals("not"))
				{
					CountStack[tem1]="N";
					CountStack[tem1+1]=null;//not
					i--;
					
				}
				else if(p1.equals(")"))
				{
					CountStack[tem]="N";
					for(int i1=tem;i1<tem1+1;i1++)
						CountStack[i1]=null;
					i=i-2;
				}
				else if(!p1.equals("#"))
				{
					
					for(int i1=tem;i1<tem1+2;i1++)
						CountStack[i1]=null;//and or
					CountStack[tem]="N";
					i=i-2;
					
				}
			}
			if(isVT(CountStack[i]))
			{p1=CountStack[i];
			tem=i;}
			else {p1=CountStack[i-1];
			tem=i-1;}
			tem1=tem;//记住p1位置
			p2=p1;
			
		}while(!(p1.equals("#")&&(j>=token.size)));
		System.out.println("Success!");
		
		*/

	public boolean cutTosmall(SymbolTable token)
	{
		if(token.size<=1)
			return true;{
		SymbolNode FL=new SymbolNode();
		FL=token.getThisNode(1);
		SymbolNode tem=new SymbolNode();
		switch(FL.type)
		{case 18://if
			if(token.findNode(20)==-1) {
			if(boolAnalyze(token.toSmall(1, token.findNode(19)))==true)
			{	
				tem=token.getThisNode(token.findNode(19));
				token.getThisNode(1).next=new SymbolNode("B");
				token.getThisNode(2).next=tem;
			}//规约Bool
			if(cutTosmall(token.toSmall(3, token.findNode(0)))==true)
			{
				tem=token.getThisNode(token.findNode(0));
				token.getThisNode(3).next=new SymbolNode("S");
				token.getThisNode(4).next=tem;
			}
			}
			else
			{
				if(boolAnalyze(token.toSmall(1, token.findNode(19)))==true)
				{	
					tem=token.getThisNode(token.findNode(19));
					token.getThisNode(1).next=new SymbolNode("B");
					token.getThisNode(2).next=tem;
				}//规约Bool
				if(cutTosmall(token.toSmall(3, token.findNode(20)))==true)
				{
					tem=token.getThisNode(token.findNode(20));
					token.getThisNode(3).next=new SymbolNode("S");
					token.getThisNode(4).next=tem;
				}
				if(cutTosmall(token.toSmall(5, token.findNode(0)))==true)
				{
					tem=token.getThisNode(token.findNode(0));
					token.getThisNode(3).next=new SymbolNode("S");
					token.getThisNode(4).next=tem;
				}
				
			}
				break;
			
		case 14://while
			if(boolAnalyze(token.toSmall(1, token.findNode(15)))==true)
			{
				tem=token.getThisNode(token.findNode(15));
				token.getThisNode(1).next=new SymbolNode("B");
				token.getThisNode(2).next=tem;
			}
			if(cutTosmall(token.toSmall(3, token.findNode(0)))==true)
			{
				tem=token.getThisNode(token.findNode(0));
				token.getThisNode(3).next=new SymbolNode("S");
				token.getThisNode(4).next=tem;	
			}
			break;
		case 39://id
			if(token.getThisNode(1).type==39&&token.getThisNode(2).type==38&&CountAnalyze(token.toSmall(3, token.findNode(48))))
			{
				tem=token.getThisNode(token.findNode(48));
				token.getThisNode(2).next=new SymbolNode("E");
				token.getThisNode(3).next=tem;
				
			}
			break;
		case 1:	//begin
			if(LAnalyze(token.toSmall(1, token.findNode(2)))==true)
			{
				tem=token.getThisNode(token.findNode(2));
				token.getThisNode(1).next=new SymbolNode("L");
				token.getThisNode(2).next=tem;
			}
			
			break; 
			default:return true;
		}
	return true;}
	}
	
	
	
	
	/*public void structureAnalyze(SymbolTable token)
	{
		SymbolNode FL=token.getThisNode(1);
		while(FL.type!=0)
		{
			if(FL.type==18&&token.findNode(20)==-1)
			{
				
				
			}
			
			
		}
	}
		
		*/
	public boolean LAnalyze(SymbolTable token)
	{SymbolNode tem=new SymbolNode();
		if(token.size<=2)
		return true;
	if(cutTosmall(token.toSmall(0,token.findNode(48)))&&LAnalyze(token.toSmall(token.findNode(48), token.findNode(0))))
	{
		tem=token.getThisNode(token.findNode(48));
		token.getThisNode(0).next=new SymbolNode("S");
		token.getThisNode(1).next=tem;
		tem=token.getThisNode(token.findNode(0));
		token.getThisNode(2).next=new SymbolNode("L");
		token.getThisNode(3).next=tem;
		return true;
	}
	else if(cutTosmall(token.toSmall(0,token.findNode(48))))
	{
		tem=token.getThisNode(token.findNode(48));
		token.getThisNode(0).next=new SymbolNode("S");
		token.getThisNode(1).next=tem;
		return true;
	}
	else return false;
	
	
	}
	
		/*SymbolNode tem=new SymbolNode();
		tem=token.getThisNode(1);
		Stark st=new Stark();
		st.push(new SymbolNode(0,"#"));
		st.push(new SymbolNode("L"));
		do {
			if(st.first.property==tem.property)
				{
				st.pop();
				tem=tem.next;
				}
			if(tem.property.equals("S")&&st.first.property.equals("L"))
			{
				st.pop();
				st.push(new SymbolNode("L1"));
				st.push(new SymbolNode(";"));
				st.push(new SymbolNode("S"));
			}
			if(st.first.property.equals("L1"))
			{
				if(tem.property.equals("S"))
				{
					st.pop();
					st.push(new SymbolNode("L"));
					
				}
				if(tem.property.equals("#"))
				{
					st.pop();
					
				}
				
			}
				
		}while(st.first.property!=tem.property);
		return true;
	}*/
	public boolean whileAnalyze(SymbolTable token)
	{
		if(token.getThisNode(1).type==14&&token.getThisNode(2).property.equals("B")&&token.getThisNode(3).type==15&&token.getThisNode(4).property.equals("S"))
		return true;
		else return false;
	}		
	public boolean beginAnalyze(SymbolTable token)
	{
		if(token.getThisNode(1).type==1&&token.getThisNode(2).property.equals("L")&&token.getThisNode(3).type==2)
		return true;
		else return false;
	}
	public boolean ifAnalyze(SymbolTable token)
	{
		if(token.getThisNode(1).type==18&&token.getThisNode(2).property.equals("B")&&token.getThisNode(3).type==19&&token.getThisNode(4).property.equals("S")&&token.getThisNode(5).type==20&&token.getThisNode(6).property.equals("S"))
			return true;
		else if(token.getThisNode(1).type==18&&token.getThisNode(2).property.equals("B")&&token.getThisNode(3).type==19&&token.getThisNode(4).property.equals("S"))
			return true;
		else return false;
	}
	
	public boolean getAnalyze(SymbolTable token)
	{
		if(token.getThisNode(1).type==39&&token.getThisNode(2).type==38&&token.getThisNode(3).property.equals("E"))
			return true;
		else return false;
		
	}
	
		/*
		 先栈
		 if（L）
		 L→S； | S ; L
		 if（id）
		 S→id := E
		 if（while）
		 S→while B do S
		 if（while）
		 S→begin L end
		 if（遍历 ；前有else）
		if token then token else token
		else
		 if token then token
		 if（begin）
		 S→begin L end
		 
		 */
		
		
	}
	
	/*public void CountAnalyze(SymbolTable token) {
		String[] Symbol= {"+","-","*","/","(",")","id","#"};
		String[][] primary= new String[8][8];
		String[] primary1={">",">","<","<","<",">","<",">"};
		String[] primary2={">",">","<","<","<",">","<",">"};
		String[] primary3={">",">",">",">","<",">","<",">"};
		String[] primary4={">",">",">",">","<",">","<",">"};
		String[] primary5={"<","<","<","<","<","=","<",">"};
		String[] primary6={">",">",">",">","null",">","null",">"};
		String[] primary7={">",">",">",">","null",">","null",">"};
		String[] primary8={"<","<","<","<","<","null","<","="};
		primary[0]=primary1;
		primary[1]=primary2;
		primary[2]=primary3;
		primary[3]=primary4;
		primary[4]=primary5;
		primary[5]=primary6;
		primary[6]=primary7;
		primary[7]=primary8;
		
	}
	*/
	
	/*public void CountAnalyze(SymbolTable token)
	{
		SymbolNode forward=token.getThisNode(1);
		Stark sk=new Stark();
		sk.push(new SymbolNode("#"));
		sk.push(new SymbolNode("P"));
		while(!sk.first.property.equals("#")||(!forward.property.equals("#")))
		{
			if(sk.first.property.equals(forward.property))
			{
				sk.pop();
				forward=forward.next;
				
			}
			
			if(sk.first.property.equals("P"))
				if(forward.property.equals("(")||forward.type==39||forward.property.equals("r")||forward.property.equals("id"))
				{
					sk.pop();
					sk.push(new SymbolNode("S"));
				}
			
			if(sk.first.property.equals("S"))
			if(forward.property.equals("(")||forward.type==39||forward.property.equals("r")||forward.property.equals("id"))
			{
				sk.pop();
				sk.push(new SymbolNode("S2"));
				sk.push(new SymbolNode("T"));
			}
			
			if(sk.first.property.equals("S2"))
				if(forward.property.equals("+")||forward.property.equals("-"))
				{
					sk.pop();
					sk.push(new SymbolNode("S"));
					sk.push(new SymbolNode("S1"));
				}
			if(sk.first.property.equals("S2"))
				if(forward.property.equals(")")||forward.property.equals("#"))
				{
					sk.pop();
				}
			
			
			if(sk.first.property.equals("S1"))
				if(forward.property.equals("+"))
				{
					sk.pop();
					sk.push(new SymbolNode("T"));
					sk.push(new SymbolNode("+"));
				}
			
			if(sk.first.property.equals("S1"))
				if(forward.property.equals("-"))
				{
					sk.pop();
					sk.push(new SymbolNode("T"));
					sk.push(new SymbolNode("-"));
				}
			
			
			if(sk.first.property.equals("T"))
				if(forward.property.equals("(")||forward.type==39||forward.property.equals("r")||forward.property.equals("id"))
				{
					sk.pop();
					sk.push(new SymbolNode("T2"));
					sk.push(new SymbolNode("A"));
				}
			
			if(sk.first.property.equals("T2"))
				if(forward.property.equals("+")||forward.property.equals("-")||forward.property.equals("#")||forward.property.equals(")"))
				{
					sk.pop();
				}
			
			if(sk.first.property.equals("T2"))
				if(forward.property.equals("*")||forward.property.equals("/"))
				{
					sk.pop();
					sk.push(new SymbolNode("T2"));
					sk.push(new SymbolNode("T1"));
				}
			
			
			if(sk.first.property.equals("T1"))
				if(forward.property.equals("*"))
				{
					sk.pop();
					sk.push(new SymbolNode("A"));
					sk.push(new SymbolNode("*"));
				}
			
			if(sk.first.property.equals("T1"))
				if(forward.property.equals("/"))
				{
					sk.pop();
					sk.push(new SymbolNode("A"));
					sk.push(new SymbolNode("/"));
				}
			
			if(sk.first.property.equals("A"))
				if(forward.property.equals("("))
				{
					sk.pop();
					sk.push(new SymbolNode(")"));
					sk.push(new SymbolNode("S"));
					sk.push(new SymbolNode("("));
				}
			if(sk.first.property.equals("A"))
				if(forward.type==39||forward.property.equals("r")||forward.property.equals("id"))
				{
					sk.pop();
					sk.push(new SymbolNode("B"));
				}
			
			if(sk.first.property.equals("B"))
				if(forward.type==39)
				{
					sk.pop();
					sk.push(new SymbolNode("i"));
				}
			
			if(sk.first.property.equals("B"))
				if(forward.property.equals("r"))
				{
					sk.pop();
					sk.push(new SymbolNode("r"));
				}
			
			if(sk.first.property.equals("B"))
				if(forward.property.equals("id"))
				{
					sk.pop();
					sk.push(new SymbolNode("id"));
				}
			
		}
		
		System.out.println("Success!");
		
	}
	*/

