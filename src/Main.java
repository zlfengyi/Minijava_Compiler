import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import kanga.kanga2mips.Kanga2Mips;

import piglet.piglet2spiglet.Piglet2Spiglet;
import spiglet.spiglet2kanga.Spiglet2Kanga;

import minijava.minijava2piglet.Minijava2Piglet;

public class Main {

	public static void main(String[] args) {
		FileInputStream fs;
		try {
			fs = new FileInputStream(
					"/Users/zlfengyi/Desktop/compiler_fy/task1/test/code.java");
			PrintStream ps = new PrintStream(new FileOutputStream(
					"/Users/zlfengyi/Desktop/compiler_fy/task1/test/code.kg"));
			System.setIn(fs);
		//	System.setOut(ps);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);
		String code = "";
		while (sc.hasNext()) {
			code += sc.nextLine() + "\n";
		}
		code = Minijava2Piglet.translate(code);
		code = Piglet2Spiglet.translate(code);
	//	code = Spiglet2Kanga.translate(code);
		//code = Kanga2Mips.translate(code);
		
		System.out.println(code);
	
		
	}
}
