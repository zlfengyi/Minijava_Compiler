package minijava.symboltable;

public class MVar extends MIdentifier {
	public MVar(String v_name, String v_type, MIdentifier v_parent, int v_line, int v_col) {
		super(v_name, v_type, v_line, v_col);
		this.setParent(v_parent);
	}
	
	public String toString() {
		return this.getType() + " " + this.getName();
	}
}
