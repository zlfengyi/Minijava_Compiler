package kanga.kanga2mips;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

import kanga.KangaParser;
import kanga.ParseException;
import kanga.TokenMgrError;
import kanga.syntaxtree.Node;
import kanga.visitor.GJDepthFirst;
import kanga.visitor.Kanga2MipsVisitor;

public class Main {

	public static void main(String[] args) {
		try {
			FileInputStream fs = new FileInputStream(
    				"/Users/zlfengyi/Desktop/compiler_fy/task1/test/code.kg");
			PrintStream ps = new PrintStream(new FileOutputStream(
					"/Users/zlfengyi/Desktop/compiler_fy/task1/test/code.asm"));
    		System.setIn(fs);
			System.setOut(ps);
		
			
			Node root = new KangaParser(System.in).Goal();
			/*
			 * TODO: Implement your own Visitors and other classes.
			 */
			
			Kanga2MipsVisitor v = new Kanga2MipsVisitor(); 
			Environment env = new Environment();
			root.accept(v, env);
			
			System.out.println(env.mipsCode);
			
		} catch (TokenMgrError e) {
			// Handle Lexical Errors
			e.printStackTrace();
		} catch (ParseException e) {
			// Handle Grammar Errors
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}