package spiglet.spiglet2kanga;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import spiglet.ParseException;
import spiglet.SpigletParser;
import spiglet.TokenMgrError;
import spiglet.syntaxtree.Node;
import spiglet.syntaxtree.NodeToken;
import spiglet.visitor.LivenessVisitor;
import spiglet.visitor.Spiglet2KangaVisitor;

public class Spiglet2Kanga {
	public static String translate(String str) {
		
		InputStream is = new ByteArrayInputStream(str.getBytes());
		
		Node root = new NodeToken("rr");
		try {
			root = new SpigletParser(is).Goal();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		return env.KangaCode.toString();
	
	}
}
