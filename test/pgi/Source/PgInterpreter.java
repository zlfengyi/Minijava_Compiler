import syntaxtree.*;
import visitor.*;
import value.*;

public class PgInterpreter{
   public static void main(String [] args) {
      try {
         Node root = new PigletParser(System.in).Goal();
         //System.out.println("Program parsed successfully");
	 root.accept(new GJPigletInterpreter("MAIN",null,root),root);
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
}

