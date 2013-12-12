package memory;
import syntaxtree.*;

public class Label extends Memory {
    private String label;
    private int pc;
    public boolean isLabel() {return true;}
    public Label(String l, int p) {label=l; pc=p;}
    public int getPC() {return pc;}
    public String getVal() {return label;}
    public String toString() {return "(Label "+label+"@"+pc+")";}
}
