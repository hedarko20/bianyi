
public class tool {
public char getFirstL(char[] x,int [] a)
{
	char temC=x[a[0]++];
	return temC;
}
	



public String addCharacter(String x,char y)
{
String temS=String.valueOf(y);
x=x.concat(temS);
return x;
}
	
public char getNBlank(char[] x,int [] a)
{
	while(x[a[0]]==' '||x[a[0]]=='\t')
a[0]++;
	char tem=getFirstL(x,a);	
	return tem;
}

public void insNode(String x,int z,SymbolTable y)
{
	SymbolNode tem=new SymbolNode(z,x);
	y.insert(tem);
	
}

}
