package piglet.piglet2spiglet;


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
    		GJDepthFirst v = new GJDepthFirst<Object,Object>() {
    		};
    		//Traverse the Abstract Grammar Tree
    		root.accept(v,null);
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