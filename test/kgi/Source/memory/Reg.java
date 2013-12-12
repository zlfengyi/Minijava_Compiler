package memory;

import util.*;

public class Reg extends Memory {
    public boolean isReg() {return true;}
    private String name; // a0, v1, t5, etc etc
    private Memory val; // could contain Int, Label, Heap

    public Reg(String name, Memory val) {this.name=name; this.val=val;}

    public boolean isNull() {return (val==null);}
    public Memory getVal() /*throws KangaRegisterException*/ {
        /*
        if (val==null) {
            System.err.println("ERROR: Reading "+name+" but it is not set");
            throw new KangaRegisterException();
        }
        */
        return val;
    }
    public void setVal(Memory val) {this.val=val;}
    public String toString() {
        if (val==null) {
            System.err.println("ERROR: Register "+name+" not set");
            return null;
        }
        if (Environment.verbose)
            return "("+name+":"+val.toString()+")";
        else
            return val.toString();
    }
}

