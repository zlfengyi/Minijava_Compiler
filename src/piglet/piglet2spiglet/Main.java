package piglet.piglet2spiglet;


import minijava.symboltable.MClassList;
import minijava.symboltable.MType;
import minijava.visitor.BuildSymbolTableVisitor;
import piglet.ParseException;
import piglet.PigletParser;
import piglet.TokenMgrError;
import piglet.syntaxtree.Node;
import piglet.visitor.GJDepthFirst;


public class Main { 
 
    public static void main(String[] args) {
    	try {
    		Node root = new PigletParser(System.in).Goal();
    		/*
    		 * TODO: Implement your own Visitors and other classes.
    		 * 
    		 */
    		
    		
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