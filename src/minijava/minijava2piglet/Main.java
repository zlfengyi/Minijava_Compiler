package minijava.minijava2piglet;

import minijava.MiniJavaParser;
import minijava.ParseException;
import minijava.TokenMgrError;
import minijava.syntaxtree.Node;
import minijava.visitor.GJDepthFirst;


public class Main { 
 
    public static void main(String[] args) {
    	try {
    		Node root = new MiniJavaParser(System.in).Goal();
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