/**
 * 表示标识符的类，可用于表示变量
 */
package minijava.symboltable;

public class MIdentifier extends MType {
	protected MIdentifier parent = null;
	protected String name;
	protected int temp = 0, offset;
	
	
	public MIdentifier() {
		
	}
	
	public MIdentifier(String v_name, int v_line, int v_column) {
		super(null, v_line, v_column);
		name = v_name;
	}
	
	public MIdentifier(String v_name, String v_type, int v_line, int v_column) {
		super(v_type, v_line, v_column);
		this.setName(v_name);
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
	
	public int getTemp() {
		return temp;
	}
	
	public void setTemp(int temp) {
		this.temp = temp;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public boolean isTemp() {
		return temp > 0;
	}
}

