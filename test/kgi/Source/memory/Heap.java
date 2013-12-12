package memory;

import java.util.*;

public class Heap extends Memory {
    public boolean isHeap() {return true;}
    private int offset=0;
    private Vector<Memory> mem;

    public Heap(Heap h) throws KangaHeapException {
        mem=h.mem;
    }
    public Heap(Heap h, int _offset) throws KangaHeapException {
        mem=h.mem;
        if (offset%4!=0) {
            System.err.println("ERROR: offset "+offset+" is not divisible by 4");
            throw new KangaHeapException();
        }            
        offset=(_offset/4);
        if (offset<0 || offset>mem.size()) {
            System.err.println("ERROR: offset "+(offset*4)+" is out of bound with heap size "+(mem.size()*4));
            throw new KangaHeapException();
        }
    }
    public Heap(int size) throws KangaHeapException {
        if (size<0 || size%4!=0) {
            System.err.println("ERROR: HALLOC must be 0-positive, divisible by 4. You tried "+size+".");
            throw new KangaHeapException();
        }
        mem = new Vector();
        for (int i=0; i<size/4; i++) mem.add(null);
    }

    public int getSize() {return mem.size()*4;}
    public int getOffset() {return offset*4;}
    public Memory getContent(int i) throws KangaHeapException {
        if (i+(4*offset)<0 || i+(4*offset)>=(mem.size()*4) || i%4!=0) {
            System.err.println("Illegal heap read value ("+i+"+4*"+offset+"), must be 0-"+(4*mem.size()-4)+" and divisible by 4.");
            throw new KangaHeapException();
        }
        return mem.get((i/4)+offset);
    }
    public void setContent(int i, Memory m) throws KangaHeapException {
        if (i+(4*offset)<0 || i+(4*offset)>=(mem.size()*4) || i%4!=0) {
            System.err.println("Illegal heap write value ("+i+"+4*"+offset+"), must be 0-"+(4*mem.size()-4)+" and divisible by 4.");
            throw new KangaHeapException();
        }
        mem.set((i/4)+offset, m);
    }
    public String toString() {
        if (addedToHistory()) {return "(heap <recursive>)";}
        StringBuffer buf = new StringBuffer();
        for (int i=0; i<mem.size(); i++) {
            if (offset==i) buf.append("*");
            buf.append((4*i)+":"+mem.get(i));
            if (i+1!=mem.size()) buf.append(" ");
        }
        return "(heap "+buf+")";
    }
}


