
public class Stark {
SymbolNode first;
int size;
public Stark()
{
this.first=new SymbolNode();
this.size=0;
}


public SymbolNode pop()
{
	if(this.size>0) {
	SymbolNode tem=this.first;
	this.first=this.first.next;
this.size--;
return tem;}
	else return null;
	
}

public void push(SymbolNode x)
{x.next=this.first;
this.first=x;
this.size++;
}


}
