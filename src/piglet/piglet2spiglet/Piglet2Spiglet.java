package piglet.piglet2spiglet;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import piglet.ParseException;
import piglet.PigletParser;
import piglet.syntaxtree.Node;
import piglet.syntaxtree.NodeToken;
import piglet.visitor.GetMaxTempVisitor;
import piglet.visitor.Piglet2SpigletVisitor;

public class Piglet2Spiglet {
	public static String translate(String str) {
 		InputStream is = new ByteArrayInputStream(str.getBytes());
 		
 		
 		Node root = new NodeToken("rr");
		try {
			root = new PigletParser(is).Goal();
	    } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * TODO: Implement your own Visitors and other classes.
		 * 
		 */
		
		Piglet2SpigletVisitor v1 = new Piglet2SpigletVisitor();
		GetMaxTempVisitor v2 = new GetMaxTempVisitor();
		root.accept(v2);
		v1.setTempNum(v2.maxTempNum + 1);
		
		MSpigletExp ans = root.accept(v1);
		
		return ans.toString();
	}
}
