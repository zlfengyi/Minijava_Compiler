/**
 * 表示标识符的类，可用于表示变量
 */
package minijava.symboltable;

public class MIdentifier extends MType {
	protected MIdentifier parent = null;
	protected String name;

	public MIdentifier(String v_name, int v_line, int v_column) {
		super(null, v_line, v_column);
		name = v_name;
	}
	
	public MIdentifier(String v_name, String v_type, int v_line, int v_column) {
		super(v_type, v_line, v_column);
		this.setName(v_name);
	}
	
	public String insertVar(MVar newVar) {
		return null;
	}
	
	public MVar getVar(String name) {
		return null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MIdentifier getParent() {
		return parent;
	}

	public void setParent(MIdentifier parent) {
		this.parent = parent;
	}
	
}

