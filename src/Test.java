
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SymbolTable keyTable=new SymbolTable();
		keyTable.insert(new SymbolNode(1,"begin"));
		keyTable.insert(new SymbolNode(2,"end"));
		keyTable.insert(new SymbolNode(3,"integer"));
		keyTable.insert(new SymbolNode(4,"char"));
		keyTable.insert(new SymbolNode(5,"bool"));
		keyTable.insert(new SymbolNode(6,"real"));
		keyTable.insert(new SymbolNode(7,"input"));
		keyTable.insert(new SymbolNode(8,"output"));
		keyTable.insert(new SymbolNode(9,"program"));
		keyTable.insert(new SymbolNode(10,"$"));
		keyTable.insert(new SymbolNode(11,"write"));
		keyTable.insert(new SymbolNode(12,"for"));
		keyTable.insert(new SymbolNode(13,"to"));
		keyTable.insert(new SymbolNode(14,"while"));
		keyTable.insert(new SymbolNode(15,"do"));
		keyTable.insert(new SymbolNode(16,"repeat"));
		keyTable.insert(new SymbolNode(17,"until"));
		keyTable.insert(new SymbolNode(18,"if"));
		keyTable.insert(new SymbolNode(19,"then"));
		keyTable.insert(new SymbolNode(20,"else"));
		keyTable.insert(new SymbolNode(21,"true"));
		keyTable.insert(new SymbolNode(22,"false"));
		keyTable.insert(new SymbolNode(23,"var"));
		keyTable.insert(new SymbolNode(24,"const"));
		keyTable.insert(new SymbolNode(32,"and"));
		keyTable.insert(new SymbolNode(33,"or"));
		keyTable.insert(new SymbolNode(34,"not"));
		SymbolTable SYTable=new SymbolTable();
		LetterAnalyze LA=new LetterAnalyze();
		GrammerAnalyze GA=new GrammerAnalyze();
		SymbolTable ttt=LA.analyzeL(keyTable,SYTable);
		System.out.println(GA.declareAnalyze(ttt));
	}


}
