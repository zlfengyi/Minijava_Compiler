package kanga.kanga2mips;

public class Environment {
	protected StringBuilder mipsCode;
	public boolean isInStmt = true;
	
	public Environment() {
		mipsCode = new StringBuilder();
	}
	
	public void append(String str) {
		mipsCode.append(str);
		mipsCode.append("\n");
		
	}
}
