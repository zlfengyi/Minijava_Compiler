package minijava.minijava2piglet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

import minijava.MiniJavaParser;
import minijava.ParseException;
import minijava.TokenMgrError;
import minijava.symboltable.MClassList;
import minijava.symboltable.MIdentifier;
import minijava.symboltable.MPiglet;
import minijava.symboltable.MType;
import minijava.syntaxtree.Node;
import minijava.visitor.BuildSymbolTableVisitor;
import minijava.visitor.GJDepthFirst;
import minijava.visitor.Java2PigletVisitor;


public class Main { 
 
    public static void main(String[] args) {
    	try {
			FileInputStream fs = new FileInputStream("tests/test-2.java");
			PrintStream ps = new PrintStream(new FileOutputStream(
					"/Users/zlfengyi/Desktop/pgi/test1"));
    		System.setIn(fs);
			System.setOut(ps);
			
			
			//Node root = new MiniJavaParser(System.in).Goal();
    		Node root = new MiniJavaParser(fs).Goal();
    		
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
    		
    		System.out.println(finalCode.toString());
    		
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