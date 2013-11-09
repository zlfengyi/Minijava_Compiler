package spiglet.spiglet2kanga;

import spiglet.ParseException;
import spiglet.SpigletParser;
import spiglet.TokenMgrError;
import spiglet.syntaxtree.Node;
import spiglet.visitor.GJDepthFirst;




public class Main { 
 
    public static void main(String[] args) {
    	try {
    		Node root = new SpigletParser(System.in).Goal();
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