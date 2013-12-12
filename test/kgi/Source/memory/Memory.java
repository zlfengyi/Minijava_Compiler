package memory;

import java.util.*;

abstract public class Memory {
    public boolean isHeap() {return false;}
    public boolean isLabel() {return false;}

    public boolean isInt() {return false;}
    public boolean isReg() {return false;}
    public boolean isInternal() {return false;}
    public boolean equals(Memory m) {return false;}

    protected static Set<Memory> printHistory = new HashSet();
    protected boolean addedToHistory() {
        boolean added = printHistory.contains(this);
        printHistory.add(this);
        return added;
    }
    public static void resetPrintHistory() {printHistory.clear();}
}
