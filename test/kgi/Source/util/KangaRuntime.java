package util;

import memory.*;
import java.util.*;
import syntaxtree.*;
import visitor.*;

public class KangaRuntime {
    final public static String[] registerName = 
    {"a0", "a1", "a2", "a3", 
     "t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7", 
     "s0", "s1", "s2", "s3", "s4", "s5", "s6", "s7", 
     "t8", "t9", 
     "v0", "v1"};

    // the followings
    final public static String dumpCurrentStack = "dumpCurrentStack";
    final public static String dumpStack = "dumpStack";
    final public static String dumpRegisters = "dumpRegisters";

    Hashtable<Stmt, String> stmtInfo = new Hashtable();
    public KangaRuntime(Hashtable<Stmt, String> stmtInfo) {
        this.stmtInfo=stmtInfo;
        for (int i=0; i<registerName.length; i++) {
            registerHash[i]=new memory.Reg(registerName[i], null);
        }
    }

    public String toString() {
        return("stmtList:"+stmtList+
               "\n"+
               "labelHash:"+labelHash+
               "\n"+
               "procedureHash:"+procedureHash);
    }

    public String registerToString() {
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<registerHash.length; i++) {
            if (!registerHash[i].isNull())
                sb.append(">"+registerName[i]+":"+registerHash[i]+"\n");
        }
        return sb.toString();
    }
    public String stackToString() {
        StringBuffer sb = new StringBuffer();
        sb.append(">Current stack ("+stackStack.size()+")\n");
        sb.append(stackStack.peek());
        return sb.toString();
    }

    public memory.Reg getReg(int i) {return registerHash[i];}
    public memory.Int getInt(String s) {
        if (integerHash.get(s)==null) integerHash.put(s, new Int(s));
        return integerHash.get(s);
    }
    public memory.Label getLabel(String s) throws KangaLabelException {
        memory.Label label =  labelHash.get(s);
        if (label==null) {
            System.err.println("Label "+s+" doesn't exist.");
            throw new KangaLabelException();
        }
        return label;
    }

    // these should be set by the SetLabel visitor
    public Vector<Stmt> stmtList = new Vector();
    public HashMap<String, memory.Label> labelHash = new HashMap();
    public HashMap<String, int[]> procedureHash = new HashMap();

    // internal states
    protected java.util.Stack<memory.Stack> stackStack=new java.util.Stack();
    public Interp ivisitor=new Interp(this); // visitor
    public memory.Reg[] registerHash=new memory.Reg[registerName.length];
    public Hashtable<String, Int> integerHash=new Hashtable();
    //protected int stackCount=-1;
    // should change to protected later
    public int pc=0;
    public boolean exit=false;
    public boolean exception=false;

    public void initializeStack(memory.Stack s) {stackStack.push(s);}
    public memory.Stack getCurrentStack() {return stackStack.peek();}
    public memory.Stack pushStack(int paramSize,
                                  int stackSize,
                                  int argSize
                                  ) throws KangaException {
        if (stackStack.size()>Environment.maxStack) {
            System.err.println("Runtime stack size is set to "+
                               Environment.maxStack+", "+
                               "consider increasing it..."); 
            exception=true;
            throw new KangaException();
        }
        memory.Stack currentStack = stackStack.peek();
        memory.Stack newStack;
        try {
            newStack = new memory.Stack(paramSize, stackSize, argSize, 
                                        pc+1, false);
        } catch (KangaException e) {
            exception=true;
            throw e;
        }
        currentStack.copyCallArgToStack(newStack);
        stackStack.push(newStack);
        return newStack;
    }
    public memory.Stack popStack() {
        if (stackStack.size()<=1) {
            System.err.println("Stack size is 1, cannot pop anymore"); 
            exception=true;
            return null;
        }            
        memory.Stack prevStack = stackStack.pop();
        memory.Stack currentStack = stackStack.peek();
        try {
            //System.out.println("--------------");
            //System.out.println(prevStack);
            //System.out.println(currentStack);
            prevStack.copyStackToCallArg(currentStack);
        } catch (KangaException e) {
            exception=true;
        }
        currentStack.resetCallArgs();
        pc = prevStack.getReturnPC();
        return prevStack;
    }

    public int aload, astore, hload, hstore, hallocate, move, call, binop;

    int count=0;
    public void run() {
        Stmt stmt=null;
        while (pc>=0 && !exit && !exception) {
            stmt=stmtList.elementAt(pc); // turn to array for speed
            if (stmt==null) {
                if (stackStack.size()==1) {
                    // last instruction
                    //System.out.println("Interpreter exiting normally...");
                    exit=true;
                } else {
                    // pop the stack, go back to where we came from
                    popStack();
                }
            } else {
                if (Environment.instructionOutput)
                    System.out.println(">"+pc+" "+stmtInfo.get(stmt));
                // execute the instruction
                stmt.accept(ivisitor);
                if (count++>Environment.maxRuntimeInstruction) {
                    System.err.println("Exceeded maximum number of instructions allowed ("+Environment.maxRuntimeInstruction+"), exiting...");
                    exit=true;
                }
            }
        }

        if (exception) {
            System.err.println("Please check near the following source code: ");
            System.err.println(stmtInfo.get(stmt));
        }

        if (Environment.instructionCount) {
            System.out.println("> Instructions:"+count+
                               " aload:"+aload+
                               " astore:"+astore+
                               " hload:"+hstore+
                               " hallocate:"+hallocate+
                               " move:"+move+
                               " call:"+call+
                               " binop:"+binop
                               );
        }
    }
}
