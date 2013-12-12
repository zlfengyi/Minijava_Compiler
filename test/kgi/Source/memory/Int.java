package memory;

import util.*;

public class Int extends Memory {
    public boolean isInt() {return true;}
    private int val;
    public Int(int v) {val=v;}
    public Int(Integer i) {val=i.intValue();}
    public Int(String i) {val=(new Integer(i)).intValue();}
    public int getInt() {return val;}
    public String toString() {
        if (Environment.verbose)
            return "(int:"+val+")";
        else
            return ""+val;
    }
}
