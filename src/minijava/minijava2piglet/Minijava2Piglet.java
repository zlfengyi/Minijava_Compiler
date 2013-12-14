package minijava.minijava2piglet;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import minijava.MiniJavaParser;
import minijava.ParseException;
import minijava.symboltable.MClassList;
import minijava.symboltable.MIdentifier;
import minijava.symboltable.MPiglet;
import minijava.syntaxtree.Node;
import minijava.syntaxtree.NodeToken;
import minijava.visitor.BuildSymbolTableVisitor;
import minijava.visitor.Java2PigletVisitor;

public class Minijava2Piglet {
	public static String translate(String str) {
 		InputStream is = new ByteArrayInputStream(str.getBytes());
 		
 		Node root = new NodeToken("rr");
		try {
			root = new MiniJavaParser(is).Goal();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * TODO: Implement your own Visitors and other classes.
		 * 
		 */

		MClassList allClassesList = new MClassList();

		//Traverse the Abstract Grammar Tree
		root.accept(new BuildSymbolTableVisitor(), allClassesList);
		
		Java2PigletVisitor v2 = new Java2PigletVisitor();
		v2.setAllClasses(allClassesList);
		v2.setCurrentTemp(allClassesList.alloc(20));
		
		MPiglet finalCode = root.accept(v2, new MIdentifier());
		
		String ret = finalCode.toString();

		return ret;
	}
}
