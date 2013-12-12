package visitor;

import memory.*;
import util.*;
import syntaxtree.*;
import java.util.*;

public class Interp extends MyBase<Memory> {
    KangaRuntime runtime;

    public Interp(KangaRuntime r) {
        runtime=r;
    }

    private int outputSize=0;
    public void out(String s) {
        System.out.println(s);
        outputSize+=s.length();
        if (outputSize>Environment.maxOutputChar) {
            err("Exceeded the maximum allowable print size ("+
                Environment.maxOutputChar+
                "), exiting in the middle of the program...");
            runtime.exit=true;
        }
            
    }
    public void err(String s) {System.err.println("ERROR: "+s);}
    public void err(NodeToken t, String s) {System.err.println("ERROR: line " + t.beginLine + ": " + s);}

    public Memory dereference(NodeToken t, Memory register) {
        if (!register.isReg()) {
            err(t, "Attempt to dereference "+register+", which is not a register.");
            runtime.exception=true;
        }
        Memory val=((memory.Reg)register).getVal();
        return val;
    }

    /**
    * f0 -> NoOpStmt()
    *       | ErrorStmt()
    *       | CJumpStmt()
    *       | JumpStmt()
    *       | HStoreStmt()
    *       | HLoadStmt()
    *       | MoveStmt()
    *       | PrintStmt()
    *       | ALoadStmt()
    *       | AStoreStmt()
    *       | PassArgStmt()
    *       | CallStmt()
    */
    public Memory visit(Stmt n) {
        return n.f0.accept(this);
    }

    /**
    * f0 -> "NOOP"
    */
    public Memory visit(NoOpStmt n) {
        runtime.pc++;
        return n.f0.accept(this);
    }

    /**
    * f0 -> "ERROR"
    */
    public Memory visit(ErrorStmt n) {
        out("ERROR");
        runtime.exit=true;
        return null;
    }

    /**
    * f0 -> "CJUMP"
    * f1 -> Reg()
    * f2 -> Label()
    */
    public Memory visit(CJumpStmt n) {
        memory.Reg reg = (memory.Reg)n.f1.accept(this);
        Memory possibleInt = dereference(n.f0, reg);
        if (!possibleInt.isInt()) {
            err(n.f0, "CJUMP only recognizes IntegerLiteral, but "+reg+" contains "+possibleInt);
            runtime.exception=true;
        }
        memory.Int i = ((memory.Int)possibleInt);
        if (i.getInt()==1) {
            runtime.pc++;
        } else {
            memory.Label label = (memory.Label)n.f2.accept(this);
            runtime.pc=label.getPC();
        }
        return null;
    }

    /**
    * f0 -> "JUMP"
    * f1 -> Label()
    */
    public Memory visit(JumpStmt n) {
        memory.Label label = (memory.Label)n.f1.accept(this);
        if (label==null) {
            runtime.exception=true;
            return null;
        }
        runtime.pc=label.getPC();
        return null;
    }

    /**
    * f0 -> "HSTORE"
    * f1 -> Reg()
    * f2 -> IntegerLiteral()
    * f3 -> Reg()
    */
    public Memory visit(HStoreStmt n) {
        runtime.hstore++;
        memory.Reg memLoc = (memory.Reg)n.f1.accept(this);
        Memory possibleHeap = dereference(n.f0, memLoc); // get the heap
        if (!possibleHeap.isHeap()) {
            err(n.f0, "The first operand of HSTORE must point to the heap.");
            runtime.exception=true; return null;
        }
        Heap heap = (Heap)possibleHeap;

        int offset=Integer.valueOf(n.f2.f0.toString());

        memory.Reg val = (memory.Reg)n.f3.accept(this);
        try {
            heap.setContent(offset, dereference(n.f0, val));
        } catch (KangaHeapException e) {
            runtime.exception=true;
        }

        runtime.pc++;
        return null;
    }

    /**
    * f0 -> "HLOAD"
    * f1 -> Reg()
    * f2 -> Reg()
    * f3 -> IntegerLiteral()
    */
    public Memory visit(HLoadStmt n) {
        runtime.hload++;
        memory.Reg destReg = (memory.Reg)n.f1.accept(this);

        memory.Reg memLoc = (memory.Reg)n.f2.accept(this);
        Memory possibleHeap = dereference(n.f0, memLoc);
        if (!possibleHeap.isHeap()) {
            err(n.f0, "The second operand of HLOAD must point to the heap.");
            runtime.exception=true; return null;
        }
        Heap heap = (Heap)possibleHeap;

        int offset=Integer.valueOf(n.f3.f0.toString());

        Memory content;
        try {
            content = heap.getContent(offset);
        } catch (KangaHeapException e) {
            runtime.exception=true; return null;
        }            
        
        destReg.setVal(content);
            
        runtime.pc++;
        return null;
    }

    /**
    * f0 -> "MOVE"
    * f1 -> Reg()
    * f2 -> Exp()
    */
    public Memory visit(MoveStmt n) {
        runtime.move++;
        memory.Reg reg = (memory.Reg) n.f1.accept(this);
        Memory exp     = n.f2.accept(this);
        if (exp==null) {
            err(n.f0, "Second operand in the MOVE expression is null");
            runtime.exception=true; return null;
        } else if (exp.isReg()) {
            // dereference the register content
            reg.setVal(dereference(n.f0, exp));
        } else {
            reg.setVal(exp);
        }
        runtime.pc++;
        return null;
    }

    /**
    * f0 -> "PRINT"
    * f1 -> SimpleExp()
    */
    public Memory visit(PrintStmt n) {
        Memory exp=n.f1.accept(this);
        //if (exp.isReg()) exp=dereference(exp);

        Memory.resetPrintHistory();
        if (exp==null ||
            (exp.isReg() && dereference(n.f0, exp)==null)) {
            err(n.f0, "Printing null, exiting...");
            runtime.exception=true; return null;
        } else if (exp.isInternal()) {
            String cmd = ((memory.Internal)exp).cmd;
            if (cmd.equals(KangaRuntime.dumpCurrentStack)) {
                out("");
                out(runtime.stackToString());
            } else if (cmd.equals(KangaRuntime.dumpStack)) {
                out("");
                out("Dumping the entire stack not implemented yet.");
            } else if (cmd.equals(KangaRuntime.dumpRegisters)) {
                out("");
                out(runtime.registerToString());
            } else {
                err(n.f0, "Command "+cmd+" unknown to the interpreter.");
                runtime.exception=true; return null;
            }
        } else {
            out(exp.toString());
        }
        runtime.pc++;
        return null;
    }

    /**
    * f0 -> "ALOAD"
    * f1 -> Reg()
    * f2 -> SpilledArg()
    */
    public Memory visit(ALoadStmt n) {
        runtime.aload++;
        memory.Reg reg = (memory.Reg)n.f1.accept(this);
        int offset = getOffset(n.f2);

        Memory val=null;
        try {
            val=runtime.getCurrentStack().getStack(offset);
        } catch (KangaException e) {
            runtime.exception=true; return null;
        }
        reg.setVal(val);

        runtime.pc++;
        return null;
    }

    /**
    * f0 -> "ASTORE"
    * f1 -> SpilledArg()
    * f2 -> Reg()
    */
    public Memory visit(AStoreStmt n) {
        runtime.astore++;
        int offset = getOffset(n.f1);
        memory.Reg reg = (memory.Reg)n.f2.accept(this);
        Memory val = dereference(n.f0, reg);

        /*
        if (val==null) {
            err(n.f0, "Last operand in the ASTORE expression is null.");
            runtime.exception=true; return null;
        }
        */
        
        try {
            runtime.getCurrentStack().setStack(offset, val);
        } catch (KangaException e) {
            runtime.exception=true; return null;
        }

        runtime.pc++;
        return null;
    }

    /**
    * f0 -> "PASSARG"
    * f1 -> IntegerLiteral()
    * f2 -> Reg()
    */
    public Memory visit(PassArgStmt n) {
        int offset = (Integer.valueOf(n.f1.f0.toString())-1);
        memory.Reg reg = (memory.Reg)n.f2.accept(this);
        Memory value = dereference(n.f0, reg);

        try {
            runtime.getCurrentStack().setCallArg(offset, value);
        } catch (KangaException e) {
            runtime.exception=true; return null;
        }

        runtime.pc++;
        return null;
    }

    /**
    * f0 -> "CALL"
    * f1 -> SimpleExp()
    */
    public Memory visit(CallStmt n) {
        runtime.call++;
        Memory exp = n.f1.accept(this);
        if (exp.isReg()) exp=dereference(n.f0, exp);
        if (exp==null) {
            err(n.f0, "CALL with uninitialized register");
            runtime.exception=true; return null;
        }
        if (!exp.isLabel()) {
            err(n.f0, "The operand to CALL is "+exp+", but it must be a label.");
            runtime.exception=true; return null;
        }
        memory.Label label = (memory.Label)exp;

        int procedureAttrib[] = runtime.procedureHash.get(label.getVal());
        if (procedureAttrib==null) {
            err(n.f0, "Can't find procedure "+label);
            runtime.exception=true; return null;
        }
        
        try {
            runtime.pushStack(procedureAttrib[0],
                              procedureAttrib[1],
                              procedureAttrib[2]);
        } catch (KangaException e) {
            runtime.exception=true; return null;
        }
                          
        runtime.pc=label.getPC();
        return null;
    }

    // -----------------------------------------------------------

    /**
    * f0 -> HAllocate()
    *       | BinOp()
    *       | SimpleExp()
    */
    public Memory visit(Exp n) {
        return n.f0.accept(this);
    }

    /**
    * f0 -> "HALLOCATE"
    * f1 -> SimpleExp()  // Reg, Int, Label
    */
    public Memory visit(HAllocate n) {
        runtime.hallocate++;
        Memory arg = n.f1.accept(this);
        if (arg.isReg()) arg=dereference(n.f0, arg);
        if (!arg.isInt()) {
            err(n.f0, "Halloc only accepts integers. It is given "+arg);
            runtime.exception=true;
        }
        int val = ((memory.Int)arg).getInt();
        Heap heap;
        try {
            heap = new Heap(val);
        } catch (KangaException e) {
            runtime.exception=true; return null;
        }
        return heap;
    }

    int binop(int op, int v1, int v2) {
        switch (op) {
        case 0: return (v1<v2?1:0);
        case 1: return v1+v2;
        case 2: return v1-v2;
        case 3: return v1*v2;
        default: return -666; // should never reach this point
        }
    }
    /**
    * f0 -> Operator()
    * f1 -> Reg()
    * f2 -> SimpleExp()  // Reg, Int, Label
    */
    public Memory visit(BinOp n) {
        runtime.binop++;
        int op=n.f0.f0.which;
        memory.Reg reg = (memory.Reg)n.f1.accept(this);
		  NodeToken opToken = (NodeToken)n.f0.f0.choice;
        Memory exp1 = dereference(opToken, reg);

        Memory exp2 = n.f2.accept(this);
        if (exp2.isReg()) exp2=dereference(opToken, exp2);

        if (exp1.isInt() && exp2.isInt()) {
            //err("Illegal arithmetic operation on register "+reg+" which "+
            //    "contains expression "+exp1);
            //runtime.exception=true;
            int v1 = ((Int)exp1).getInt();
            int v2 = ((Int)exp2).getInt();
            int res=binop(op, v1, v2);
            //System.out.println("Adding "+v1+" "+v2+"="+res);
            return runtime.getInt(new String(""+res));
        } else if ((exp1.isHeap() && exp2.isInt()) ||
                   (exp2.isHeap() && exp1.isInt())) {
            memory.Heap heap;
            int offset1, offset2, offset;
            if (exp1.isHeap()) {
                heap=(Heap)exp1;
                offset1=heap.getOffset();
                offset2=((memory.Int)exp2).getInt();
                offset=binop(op, offset1, offset2);
            } else {
                heap=(Heap)exp2;
                offset1=((memory.Int)exp1).getInt();
                offset2=heap.getOffset();
                offset=binop(op, offset2, offset1);
            }
            if (offset==0) {return heap;}

            Heap newHeap;
            try {
                newHeap = new Heap(heap, offset);
            } catch (KangaException e) {
                runtime.exception=true; return null;
            }
            return newHeap;
        } else {
            err(opToken, "Case "+exp1+" OP "+exp2+" not taken care of ");
            runtime.exception=true;
        }
        return null;
    }

    /**
    * f0 -> "LT"
    *       | "PLUS"
    *       | "MINUS"
    *       | "TIMES"
    */
    public Memory visit(Operator n) {
        return n.f0.accept(this);
    }

    /**
    * f0 -> "SPILLEDARG"
    * f1 -> IntegerLiteral()
    */
    public int getOffset(SpilledArg n) {
        return Integer.valueOf(n.f1.f0.toString());
    }
    public Memory visit(SpilledArg n) {
        // should be an error here
        err(n.f0, "We should never visit SpilledArg");
        return null;
    }

    /**
    * f0 -> Reg()
    *       | IntegerLiteral()
    *       | Label()
    */
    public Memory visit(SimpleExp n) {
        return n.f0.accept(this);
    }

    /**
    * f0 -> "a0"
    *       | "a1"
    *       | "a2"
    *       | "a3"
    *       | "t0"
    *       | "t1"
    *       | "t2"
    *       | "t3"
    *       | "t4"
    *       | "t5"
    *       | "t6"
    *       | "t7"
    *       | "s0"
    *       | "s1"
    *       | "s2"
    *       | "s3"
    *       | "s4"
    *       | "s5"
    *       | "s6"
    *       | "s7"
    *       | "t8"
    *       | "t9"
    *       | "v0"
    *       | "v1"
    */
    public Memory visit(syntaxtree.Reg n) {
        return runtime.getReg(n.f0.which);
    }

    /**
    * f0 -> <INTEGER_LITERAL>
    */
    public Memory visit(IntegerLiteral n) {
        return runtime.getInt(n.f0.toString());
    }

    /**
    * f0 -> <IDENTIFIER>
    */
    public Memory visit(syntaxtree.Label n) {
        String id = n.f0.toString();
        if (Environment.allowDumps && 
            (id.equals(KangaRuntime.dumpCurrentStack) || 
             id.equals(KangaRuntime.dumpStack) ||
             id.equals(KangaRuntime.dumpRegisters)) )
            return new memory.Internal(id);

        try {
            return runtime.getLabel(n.f0.toString());
        } catch (KangaException e) {
            runtime.exception=true;
            return null;
        }
    }
}
