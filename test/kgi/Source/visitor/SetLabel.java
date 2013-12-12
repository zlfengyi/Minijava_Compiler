package visitor;

import util.*;
import memory.*;

import syntaxtree.*;
import java.util.*;

public class SetLabel extends GJNoArguDepthFirst<Object> {
    KangaRuntime runtime;

    public SetLabel(KangaRuntime runtime) {
        this.runtime=runtime;
    }

    int count=0;

    /**
     * f0 -> "MAIN"
     * f1 -> "["
     * f2 -> IntegerLiteral()
     * f3 -> "]"
     * f4 -> "["
     * f5 -> IntegerLiteral()
     * f6 -> "]"
     * f7 -> "["
     * f8 -> IntegerLiteral()
     * f9 -> "]"
     * f10 -> StmtList()
     * f11 -> "END"
     * f12 -> ( Procedure() )*
     * f13 -> <EOF>
     */
    public Object visit(Goal n) {
        int params     = Integer.valueOf(n.f2.f0.toString());
        int stackSize  = Integer.valueOf(n.f5.f0.toString());
        int maxArgSize = Integer.valueOf(n.f8.f0.toString());

        // initial stack size
        memory.Stack newStack;
        try {
            newStack = new memory.Stack(params, stackSize, maxArgSize, 
                                        -999, false);
        } catch (KangaException e) {
            runtime.exception=true;
            return null;
        }
        runtime.initializeStack(newStack);

        n.f10.accept(this);
        n.f12.accept(this);
        return null;
    }

    /**
     * f0 -> ( ( Label() )? Stmt() )*
     */
    public Object visit(StmtList n) {
        for (Enumeration e=n.f0.elements(); 
             e.hasMoreElements();) {
            NodeSequence ns = (NodeSequence)e.nextElement();
            NodeOptional no = (NodeOptional)ns.elementAt(0);
            if (no.present()) {
                String label=(String)no.accept(this);
                runtime.labelHash.put(label, new memory.Label(label, count));
            }
            runtime.stmtList.add((Stmt)ns.elementAt(1));
            count++;
        }
        runtime.stmtList.add(null);
        count++;
        return null;
    }

    /**
     * f0 -> Label()
     * f1 -> "["
     * f2 -> IntegerLiteral()
     * f3 -> "]"
     * f4 -> "["
     * f5 -> IntegerLiteral()
     * f6 -> "]"
     * f7 -> "["
     * f8 -> IntegerLiteral()
     * f9 -> "]"
     * f10 -> StmtList()
     * f11 -> "END"
     */
    public Object visit(Procedure n) {
        String label=(String)n.f0.accept(this);
        int vals[]=new int[3];
        vals[0] = new Integer((String)n.f2.accept(this)).intValue();
        vals[1] = new Integer((String)n.f5.accept(this)).intValue();
        vals[2] = new Integer((String)n.f8.accept(this)).intValue();
        runtime.procedureHash.put(label, vals);
        runtime.labelHash.put(label, new memory.Label(label, count));
        n.f10.accept(this);
        return null;
    }

    /**
     * f0 -> <INTEGER_LITERAL>
     */
    public Object visit(IntegerLiteral n) {
        return n.f0.toString();
    }

    /**
     * f0 -> <IDENTIFIER>
     */
    public Object visit(syntaxtree.Label n) {
        return n.f0.toString();
    }

}
