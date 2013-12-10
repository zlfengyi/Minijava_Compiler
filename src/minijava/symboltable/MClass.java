/**
 * 该类用于表示声明的类
 */
package minijava.symboltable;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import minijava.typecheck.ErrorPrinter;

public class MClass extends MIdentifier implements VarContainer {
	protected boolean declared = false; // 是否已声明，用于检查符号表

	protected String baseClassName;
	protected MClass baseClass;
	protected HashMap<String, MMethod> methodTable = new HashMap<String, MMethod>();
	protected HashMap<String, MVar> varTable = new HashMap<String, MVar>();

	
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
	
	void updateVarAndMethodTable() {
		if (this.baseClass == null) return;
		
		this.baseClass.updateVarAndMethodTable();
		for (String vName : this.baseClass.varTable.keySet()) {
			if (this.varTable.containsKey(vName)) continue;
			this.varTable.put(vName, this.baseClass.varTable.get(vName));
		}
		for (String mName : this.baseClass.methodTable.keySet()) {
			if (this.methodTable.containsKey(mName)) continue;
			this.methodTable.put(mName, this.baseClass.methodTable.get(mName));
		}
	}
	
	public int alloc(int currentTemp, HashSet<String> pigletNameSet) {
		int currentOffset = 4;
		for (MVar mvar : varTable.values()) {
			mvar.setOffset(currentOffset);
			currentOffset += 4;
		}
		
		currentOffset = 0;
		for (MMethod mmethod : methodTable.values()) {
			mmethod.setOffset(currentOffset);
			for (int i = 0; ;) {
				String name = this.getName() + "_" + mmethod.getName();
				if (i >= 2) name = name + "_" + i;
				if (!pigletNameSet.contains(name)) {
					pigletNameSet.add(name);
					mmethod.setPigletName(name);
					break;
				}
			}
			
			currentOffset += 4;
			currentTemp = mmethod.alloc(currentTemp);
		}
		
		return currentTemp;
	}
	
//---------------------------Getters and Setters----------------------//
	public int getVarSize() {
		return varTable.size()*4;
	}
	
	public int getMethodSize() {
		return methodTable.size()*4;
	}
	
	public MMethod getMethod(String methodName) {
		return methodTable.get(methodName);
	}
	
	@Override
	public MVar getVar(String name) {
		return varTable.get(name);
	}
	
	public boolean isDeclared() {
		return declared;
	}

	public void setDeclared(boolean _declared) {
		this.declared = _declared;
	}

	public String getBaseClassName() {
		return baseClassName;
	}

	public void setBaseClassName(String baseClassName) {
		this.baseClassName = baseClassName;
	}
	
	public Collection<MMethod> getMethodSet() {
		return methodTable.values();
	}
	
	public Collection<MVar> getVarSet() {
		return varTable.values();
	}

	public MClass getBaseClass() {
		return baseClass;
	}

	public void setBaseClass(MClass baseClass) {
		this.baseClass = baseClass;
	}
}

