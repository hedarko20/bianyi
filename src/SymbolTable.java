
public class SymbolTable {
private SymbolNode firNode;
private SymbolNode lasNode;
int size;
	public void insert(SymbolNode x)
	{
	this.lasNode.next=	x;
	this.lasNode=this.lasNode.next;
	this.size++;
		
	}
	public SymbolNode getThisNode(int x){
		if(x<=0)
			return this.firNode;
		else {
		SymbolNode tem=this.firNode.next;
		while(--x>0)
		{
		tem=tem.next;	
			
		}
		
		return tem;}
		
	}
	
	public SymbolTable()
	{
		this.firNode=new SymbolNode();
		this.lasNode=this.firNode;
		this.size=0;
		
		
		
	}
	
	public boolean equal(String x)
	{
	SymbolNode tem=this.firNode.next;
	while(tem!=null)
	{
		if(tem.property.equals(x))
			return true;
		tem=tem.next;
	}
		return false;
		
	}
	
	public int findNode(String x)
	{
		SymbolNode tem=this.firNode.next;
		int temN=1;
		while(tem!=null)
		{
			if(tem.property.equals(x))
				return temN;
			tem=tem.next;
				temN++;
		}
		return -1;
	}
	public int findNode(int x)
	{
		SymbolNode tem=this.firNode.next;
		int temN=1;
		while(tem!=null)
		{
			if(tem.type==x)
				return temN;
			tem=tem.next;
				temN++;
		}
		return -1;
	}
	public void print()
	{SymbolNode temSN=this.firNode.next;
		while(temSN!=null)
		{
			
		temSN.print();
		temSN=temSN.next;
		}
	}
	
	public SymbolTable toSmall(int a,int b)
	{
		SymbolTable tem=new SymbolTable();
		SymbolNode temNode=new SymbolNode();
		if(a<b)
			{
			temNode=this.getThisNode(a).next;
			for(int i=a+1;i<b;i++)
				{
				tem.insert(new SymbolNode(temNode.type,temNode.property,temNode.add));
				temNode=temNode.next;
				}
			
			}
			tem.insert(new SymbolNode(0,"#"));
			return tem;
		
		
	}
}
