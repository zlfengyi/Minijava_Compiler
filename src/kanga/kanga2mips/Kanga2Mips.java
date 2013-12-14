package kanga.kanga2mips;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import kanga.KangaParser;
import kanga.ParseException;
import kanga.syntaxtree.Node;
import kanga.syntaxtree.NodeToken;
import kanga.visitor.Kanga2MipsVisitor;


public class Kanga2Mips {
	public static String translate(String str) {
		InputStream is = new ByteArrayInputStream(str.getBytes());
		Node root = new NodeToken("rr");
		try {
			root = new KangaParser(is).Goal();
	    } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Kanga2MipsVisitor v = new Kanga2MipsVisitor(); 
		Environment env = new Environment();
		root.accept(v, env);
		
		return env.mipsCode.toString();
	}
}
