import syntaxtree.*;
import visitor.*;
import util.*;

import java.util.*;

public class kgi {
    // if you want to test passing String []args to the interpreter,
    // make it "true".
    private static boolean STDIN_ONLY = false;
    private static boolean kanga = false;

    public static void main(String args[]) {
        KangaParser parser;

        //System.err.println("Reading from file " + args[0] + " . . .");
        for (int i=0; i<args.length; i++) {
            if (args[i].equals("-instructionOutput"))
                Environment.instructionOutput=true;
            else if (args[i].equals("-instructionCount"))
                Environment.instructionCount=true;
            else if (args[i].equals("-verbose"))
                Environment.verbose=true;
            else if (args[i].equals("-allowDumps"))
                Environment.allowDumps=true;
            else {
                System.err.println("Argument "+args[i]+" not recognized");
                System.exit(1);
            }
        }
        parser = new KangaParser(System.in);

            /*
            try { 
                parser = new SpigletParser(new java.io.FileInputStream(args[0])); 
            } catch (java.io.FileNotFoundException e) {
                System.err.println("File " + args[0] + " not found.");
                return;
            }

            System.err.println("Usage is one of:");
            System.err.println("         java Typecheck < inputfile");
            System.err.println("OR");
            System.err.println("         java Typecheck inputfile");
            return;
            */

        try {
            // Here's where the AST actions and visiting take place.
            Node root = parser.Goal();

            //TreeFormatter tf = new TreeFormatter();
            //root.accept(tf);

            // get line numbers
            Hashtable<Stmt, String> stmtInfo = new Hashtable();
            MyTreeDumper treedumper = new MyTreeDumper(stmtInfo);
            root.accept(treedumper);

            KangaRuntime runtime = new KangaRuntime(stmtInfo);
            root.accept(new SetLabel(runtime));
            //System.out.println(runtime);

            runtime.run();

        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.err.println("Encountered errors during parse.");
        }
    }
}
