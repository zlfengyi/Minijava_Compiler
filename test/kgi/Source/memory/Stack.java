package memory;

import util.*;
import java.util.*;

public class Stack {
    int paramSize;
    Memory stack[];
    Memory callArg[];
    boolean warning;
    int returnPC=-999;

    int maxPASSARGset=0;   // maximum PASSARGS (different than callArg.length)
    int maxPASSARGgiven=0; // index in stack space

    public Stack(int paramSize, int stackSize, int maxPASSARGset, 
                 int returnPC, boolean w) throws KangaStackException {
        if (stackSize<0 || stackSize>=Environment.maxStackSize) {
            System.err.println("Stack size must be between 0-"+(Environment.maxStackSize-1)+"("+stackSize+")");
            throw new KangaStackException();
        }
        this.paramSize = paramSize;
        this.stack = new Memory[stackSize];
        this.callArg = new Memory[maxPASSARGset];
        this.warning = w;
        this.returnPC = returnPC;
    }

    public int getReturnPC() {return returnPC;};

    public void setCallArg(int idx, Memory m) throws KangaStackException {
        if (idx<0 || idx>=callArg.length) {
            System.err.println("Set call arg index is out of bound ("+idx+")"+
                               " relative to the max param size");
            throw new KangaStackException();
        }
        callArg[idx]=m;
        if (idx+1>maxPASSARGset) maxPASSARGset=idx+1;
    }
    public Memory getCallArg(int idx) throws KangaStackException {
        if (idx<0 || idx>=callArg.length) {
            System.err.println("Get arg index is out of bound ("+idx+")"+
                               " relative to the max param size");
            throw new KangaStackException();
        }
        Memory m=callArg[idx];
        if (m==null) {
            System.err.println("Reading call arg location "+idx+" which is uninitialized.");
            throw new KangaStackException();
        }
        return m;
    }
    public void resetCallArgs() {
        for (int i=0; i<callArg.length; i++) callArg[i]=null;
        maxPASSARGset=0;
    }
    public void copyCallArgToStack(memory.Stack destStack) throws KangaException {
        if (maxPASSARGset>destStack.paramSize) {
            System.err.println("Cannot copy a bigger call arg size "+maxPASSARGset+" to "+destStack.paramSize);
            throw new KangaStackException();
        }
        if (maxPASSARGset>destStack.stack.length) {
            System.err.println("Arg size "+maxPASSARGset+" is greater than stack size "+destStack.stack.length);
            throw new KangaStackException();
        }
        try {
            for (int i=0; i<maxPASSARGset; i++)
                destStack.stack[i]=callArg[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Fatal interpreter error: "+e);
            throw new KangaStackException();
        }
        destStack.maxPASSARGgiven=maxPASSARGset;
    }
    public void copyStackToCallArg(memory.Stack destStack) throws KangaException {
        if (maxPASSARGgiven>destStack.maxPASSARGset) {
            System.err.println("Cannot copy stack's arg size "+maxPASSARGgiven+" to "+destStack.maxPASSARGset);
            throw new KangaStackException();
        }
        if (destStack.callArg.length<maxPASSARGgiven ||
            stack.length<maxPASSARGgiven) {
            System.err.println("ERROR, copying array size "+
                               maxPASSARGgiven+
                               " to "+stack.length+
                               "&"+destStack.callArg.length);
            throw new KangaStackException();
        }
        try {
            for (int i=0; i<maxPASSARGgiven; i++) 
                destStack.callArg[i]=stack[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Fatal interpreter error: "+e);
            throw new KangaStackException();
        }
    }

    public void setStack(int idx, Memory m) throws KangaStackException {
        if (idx<0 || idx>=Environment.maxStackSize) {
            System.err.println("Stack size must be between 0-"+(Environment.maxStackSize-1)+".");
            throw new KangaStackException();
        }
        if (warning && idx<maxPASSARGgiven) {
            System.err.println("Writing stack at "+idx+" even though it is reserved for argument.");
        }
        try {
            stack[idx]=m;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Stack length="+stack.length+
                               " but accessing "+idx);
            throw new KangaStackException();
        }
    }
    public Memory getStack(int idx) throws KangaStackException {
        if (idx<0 || idx>=Environment.maxStackSize) {
            System.err.println("Stack size must be between 0-"+(Environment.maxStackSize-1)+".");
            throw new KangaStackException();
        }
        Memory m;
        try {
            m=stack[idx];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Stack length="+stack.length+
                               " but accessing "+idx);
            throw new KangaStackException();
        }
        return m;
    }

    public String toString() {
        //if (Memory.addedToHistory()) {return "(stack <recursive>)";}
        StringBuffer sb = new StringBuffer();
        sb.append(">Stack's static "+
                  "param size:"+paramSize+
                  ", max stack size:"+stack.length+
                  ", max call arg size:"+callArg.length+"\n");
        sb.append(">  max PASSARG used:"+maxPASSARGset+
                  ", max PASSARG given:"+maxPASSARGgiven+"\n");
        for (int i=0; i<stack.length; i++) {
            Memory.resetPrintHistory();
            sb.append(">  stack("+i+"): "+stack[i]+"\n");
        }
        for (int i=0; i<callArg.length; i++) {
            Memory.resetPrintHistory();
            if (callArg[i]!=null)
                sb.append(">  PASSARG("+i+"): "+callArg[i]+"\n");
        }
        return sb.toString();
    }
}

