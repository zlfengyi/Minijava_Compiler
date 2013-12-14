package spiglet.spiglet2kanga;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

import spiglet.ParseException;
import spiglet.SpigletParser;
import spiglet.TokenMgrError;
import spiglet.syntaxtree.Node;
import spiglet.visitor.GJDepthFirst;
import spiglet.visitor.LivenessVisitor;
import spiglet.visitor.Spiglet2KangaVisitor;




public class Main { 
 
    public static void main(String[] args) {
    	try {
    		FileInputStream fs = new FileInputStream(
    				"/Users/zlfengyi/Desktop/compiler_fy/task1/test/code.spg");
			PrintStream ps = new PrintStream(new FileOutputStream(
					"/Users/zlfengyi/Desktop/compiler_fy/task1/test/code.kg"));
    		System.setIn(fs);
			System.setOut(ps);
		
    		Node root = new SpigletParser(System.in).Goal();
    		/*
    		 * TODO: Implement your own Visitors and other classes.
    		 * 
    		 */
    		
    		LivenessVisitor v1 = new LivenessVisitor();
    		Spiglet2KangaVisitor v2 = new Spiglet2KangaVisitor();
    		Environment env = new Environment();
    		
    		//Traverse the Abstract Grammar Tree
    		root.accept(v1, env);
    		env.alloc();
    		root.accept(v2, env);
    		
    		System.out.println(env.KangaCode);
    		
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