
public class SymbolNode {
	SymbolNode next;
	int type;
	String property;
	int add=-1;
	
	public SymbolNode(int type,String property)
	{
		this.type=type;
		this.property=property;
		this.next=null;
		
		
	}
	public SymbolNode(String property)
	{
		this.type=-1;
		this.property=property;
		this.next=null;
		
		
	}
	
	public SymbolNode()
	{
		this.type=0;
		this.property=null;
		this.next=null;
		
		
	}
	
	public SymbolNode(int type,String property,int add)
	{
		this.type=type;
		this.property=property;
		this.next=null;
		this.add=add;
		
	}
	
	public void print()
	{
		if(this.add!=-1)
		System.out.println(this.type+"\""+this.property+"\""+"\""+this.add+"\"");
		else System.out.println(this.type+"\""+this.property+"\"");
	}
	
}
