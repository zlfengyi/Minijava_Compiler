package minijava.symboltable;

public class MPiglet {
	protected StringBuilder pigCode;
	protected boolean isHead = false;
	protected MVar var;
	protected MClass mclass;
	protected int line = -1, column = -1; // for debug

	
	public MPiglet() {
	}
	
	public MPiglet(String str) {
		this.pigCode = new StringBuilder(str);
	}
	
	public MPiglet(String str, int nCurrentTab) {
		this.isHead = true;
		this.pigCode = new StringBuilder("");
		for (int i = 0; i < nCurrentTab; i++) this.pigCode.append("	");
		this.pigCode.append(str);
	}
	
	public void append(MPiglet mp) {
		if (mp == null || mp.pigCode == null) return;
		
		if (this.pigCode == null) 
			this.pigCode = new StringBuilder("");
		if (mp.isHead) 
			this.pigCode.append("\n");
		else
			this.pigCode.append(" ");
		
		this.pigCode.append(mp.pigCode.toString());
	}
	
	public void append(String str) {
		if (this.pigCode == null) 
			this.pigCode = new StringBuilder("");
		this.pigCode.append(str);
	}
	
	@Override
	public String toString() {
		return pigCode.toString();
	}
	
	public void debug() {
		System.out.println(" in line: "+ this.line + ", column: " + this.column);
	}
//------------------------getters and setters-----------------------//
	
	public StringBuilder getPigCode() {
		return pigCode;
	}

	public void setPigCode(StringBuilder pigCode) {
		this.pigCode = pigCode;
	}

	public MVar getVar() {
		return var;
	}

	public void setVar(MVar var) {
		this.var = var;
	}

	public MClass getMclass() {
		return mclass;
	}

	public void setMclass(MClass mclass) {
		this.mclass = mclass;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
}
