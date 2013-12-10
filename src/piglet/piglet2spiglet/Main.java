package piglet.piglet2spiglet;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

import minijava.symboltable.MClassList;
import minijava.symboltable.MType;
import minijava.visitor.BuildSymbolTableVisitor;
import piglet.ParseException;
import piglet.PigletParser;
import piglet.TokenMgrError;
import piglet.syntaxtree.Node;
import piglet.visitor.GJDepthFirst;
import piglet.visitor.GetMaxTempVisitor;
import piglet.visitor.Piglet2SpigletVisitor;


public class Main { 
 
    public static void main(String[] args) {
    	try {
    		FileInputStream fs = new FileInputStream(
    				"/Users/zlfengyi/Desktop/test/code.pg");
			PrintStream ps = new PrintStream(new FileOutputStream(
					"/Users/zlfengyi/Desktop/test/code.spg"));
    		System.setIn(fs);
			System.setOut(ps);
			
			
    		Node root = new PigletParser(System.in).Goal();
    		/*
    		 * TODO: Implement your own Visitors and other classes.
    		 * 
    		 */
    		Piglet2SpigletVisitor v1 = new Piglet2SpigletVisitor();
    		GetMaxTempVisitor v2 = new GetMaxTempVisitor();
    		root.accept(v2);
    		v1.setTempNum(v2.maxTempNum + 1);
    		
    		MSpigletExp ans = root.accept(v1);
    		
    		System.out.println(ans);
    		
    	}
    	catch(TokenMgrError e){
    		//Handle Lexical Errors
    		e.printStackTrace();
    	}
    	catch (ParseException e){
    		//Handle Grammar Errors
    		e.printStackTrace();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
}