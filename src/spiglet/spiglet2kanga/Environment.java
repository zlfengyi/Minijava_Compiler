package spiglet.spiglet2kanga;

import java.util.ArrayList;
import java.util.HashMap;

public class Environment {
	protected HashMap<String, CMethod> methodTable;
	
	public CMethod currentMethod;
	public CStmt currentStmt;
	public boolean isInStmt = false;
	public StringBuilder KangaCode;
	public int vReg = 0;
	public int isPassingPara = -1;
	public int moveToReg = -1;
	public boolean isInExp = false;
	
	public Environment() {
		methodTable = new HashMap<String, CMethod>();
		KangaCode = new StringBuilder();
	}
	
	public void addMethod() {
		methodTable.put(currentMethod.name, currentMethod);
	}

	public void setMethod(String name) {
		currentMethod = methodTable.get(name);
	}
	
	public void append(String str) {
		KangaCode.append(str + "\n");
	}
	
	public void alloc() {
		for (CMethod method : methodTable.values()) {
			method.allocReg();
		}
	}
}
