package minijava.minijava2piglet;

import java.util.Scanner;


public class Main { 
 
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	String code = "";
    	while (sc.hasNext()) {
    		code += sc.nextLine() + "\n";
    	}
    	code = Minijava2Piglet.translate(code);
    	
    	System.out.println(code);
    }
}