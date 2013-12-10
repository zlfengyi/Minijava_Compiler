import syntaxtree.*;
import visitor.*;

public class Main {
   public static void main(String [] args) {
      try {
         Node root = new SpigletParser(System.in).Goal();
         System.out.println("Program parsed successfully");
         root.accept(new DepthFirstVisitor());
      }
      catch (ParseException e) {
         System.err.println(e.toString());
			System.exit(1);
      }
   }
}
