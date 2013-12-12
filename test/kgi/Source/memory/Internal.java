package memory;

// internal representation
public class Internal extends Memory {
    public boolean isInternal() {return true;}
    public String cmd; // a0, v1, t5, etc etc
    public Internal(String cmd) {this.cmd=cmd;}

    public String toString() {
        if (cmd==null) {
            System.err.println("ERROR: Internal "+cmd+" not set");
            return null;
        }
        return "(_internal_:"+cmd+")";
    }
}

