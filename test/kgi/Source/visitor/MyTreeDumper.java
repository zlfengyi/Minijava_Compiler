package visitor;

import syntaxtree.*;
import java.util.*;
import java.io.*;

public class MyTreeDumper extends DepthFirstVisitor {
    //GJVoidDepthFirst<Hashtable<Stmt, String>>  {
    //protected PrintWriter out;
    StringBuffer sb=new StringBuffer();

    protected int curLine = 1;
    protected int curColumn = 1;
    private boolean startAtNextToken = false;
    private boolean printSpecials = true;

    Hashtable<Stmt, String> hash;
    public MyTreeDumper(Hashtable<Stmt, String> hash) {
        this.hash = hash;
    }

    public void visit(Stmt n/*, Hashtable<Stmt, String> hash*/) {
        sb = new StringBuffer();
        n.f0.accept(this/*, hash*/);
        if (hash!=null) 
            hash.put(n, sb.toString()+" (line:"+curLine+" col:"+curColumn+")");
        //System.out.println(n+" "+hash.get(n));
    }

    //public MyTreeDumper()  { out = new PrintWriter(System.out, true); }

    public void visit(NodeToken n) {
        if ( n.beginLine == -1 || n.beginColumn == -1 ) {
            printToken(n.tokenImage);
            return;
        }

        //
        // Handle special tokens
        //
        if ( printSpecials && n.numSpecials() > 0 )
            for ( Enumeration<NodeToken> e = n.specialTokens.elements(); e.hasMoreElements(); )
                visit(e.nextElement());

        //
        // Handle startAtNextToken option
        //
        if ( startAtNextToken ) {
            curLine = n.beginLine;
            curColumn = 1;
            startAtNextToken = false;

            //if ( n.beginColumn < curColumn ) out.println();
        }

        //
        // Check for invalid token position relative to current position.
        //
        if ( n.beginLine < curLine )
            throw new IllegalStateException("at token \"" + n.tokenImage +
                                            "\", n.beginLine = " + Integer.toString(n.beginLine) +
                                            ", curLine = " + Integer.toString(curLine));
        else if ( n.beginLine == curLine && n.beginColumn < curColumn )
            throw new IllegalStateException("at token \"" + n.tokenImage +
                                            "\", n.beginColumn = " +
                                            Integer.toString(n.beginColumn) + ", curColumn = " +
                                            Integer.toString(curColumn));

        //
        // Move output "cursor" to proper location, then print the token
        //
        if ( curLine < n.beginLine ) {
            curColumn = 1;
            for ( ; curLine < n.beginLine; ++curLine ) {
                sb.append(" ");
                //out.println();
            }
        }

        for ( ; curColumn < n.beginColumn; ++curColumn ) {
            sb.append(" ");
            //out.print(" ");
        }

        printToken(n.tokenImage);
    }

    private void printToken(String s) {
        for ( int i = 0; i < s.length(); ++i ) { 
            if ( s.charAt(i) == '\n' ) {
                ++curLine;
                curColumn = 1;
            } else {
                curColumn++;
                sb.append(s.charAt(i));
            }
            //out.print(s.charAt(i));
        }
        //out.flush();
   }
}
