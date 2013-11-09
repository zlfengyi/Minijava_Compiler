/**
 * 该类用于表示声明的类
 */
package minijava.symboltable;

import java.util.HashMap;
import java.util.Vector;

import minijava.typecheck.ErrorPrinter;

public class MClass extends MIdentifier {
	public boolean isDeclared = false; // 是否已声明，用于检查符号表
	private String baseClassName;
	private HashMap<String, MMethod> methodTable = new HashMap<String, MMethod>();
	private HashMap<String, MVar> varTable = new HashMap<String, MVar>();
	
	public MClass(String v_name, int m_line, int m_column) {
		super(v_name, "class", m_line, m_column);
	}
	
	@Override
	public String insertVar(MVar newVar) {
		if (!varTable.containsKey(newVar.getName())) {
			varTable.put(newVar.getName(), newVar);
			return null;
		} else {
			return "Multiple variables declarations \'" + newVar.getName() + "\'";
		}
	}

	public String insertMethod(MMethod newMethod) {
		if (methodTable.containsKey(newMethod.getName())) {
			return "Method declarate repeated with same name \'" + newMethod.getName() + "\'";
		} else {
			methodTable.put(newMethod.getName(), newMethod);
			return null;
		}
	}
	
	public MMethod getMethod(String methodName) {
		return methodTable.get(methodName);
	}
	
	@Override
	public MVar getVar(String name) {
		return varTable.get(name);
	}
	
	public boolean isDeclared() {
		return isDeclared;
	}

	public void setDeclared(boolean isDeclared) {
		this.isDeclared = isDeclared;
	}

	public String getBaseClassName() {
		return baseClassName;
	}

	public void setBaseClassName(String baseClassName) {
		this.baseClassName = baseClassName;
	}
	
	
}

