package minijava.symboltable;

import java.util.ArrayList;
import java.util.HashMap;


public class MMethod extends MIdentifier implements VarContainer {
	protected String returnType;
	protected String pigletName;
	protected HashMap<String, MVar> varTable = new HashMap<String, MVar>();
	protected ArrayList<MVar> paramList = new ArrayList<MVar>();
	
	
	public MMethod(String v_name, String v_returnType, MIdentifier v_parent, int v_line, int v_col) {
		super(v_name, "method", v_line, v_col);
		this.setParent(v_parent);
		this.setReturnType(v_returnType);
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
	
	public String insertParam(MVar newParam) {
		paramList.add(newParam);
		return insertVar(newParam);
	}
	
	public int alloc(int currentTemp) {
		int num = 0;
		for (MVar mvar : paramList) {
			mvar.setTemp(++num);
		}
		
		for (MVar mvar : varTable.values()) {
			boolean flag = true;
			for (MVar mvar2 : paramList) {
				if (mvar2.getName().equals(mvar.getName())) 
					flag = false;
			}	
			if (flag)
				mvar.setTemp(currentTemp++);
		}
		return currentTemp;
	}

//--------------------Getters and Setters---------------------------//
	@Override
	public MVar getVar(String name) {
		if (varTable.containsKey(name)) {
			return varTable.get(name);
		}
		return ((MClass)this.parent).getVar(name);
	}
	
	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getPigletName() {
		return pigletName;
	}
	
	public String getPigletDefineName() {
		return this.pigletName + " [ " + (paramList.size()+1) + " ]";
	}
	
	public void setPigletName(String pigletName) {
		this.pigletName = pigletName;
	}
	
	
}
