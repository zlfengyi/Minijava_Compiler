package minijava.symboltable;

import java.util.ArrayList;
import java.util.HashMap;


public class MMethod extends MIdentifier {
	private String returnType;
	private HashMap<String, MVar> varTable = new HashMap<String, MVar>();
	private ArrayList<MVar> paramList = new ArrayList<MVar>();
	
	
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
	
	@Override
	public MVar getVar(String name) {
		if (varTable.containsKey(name)) {
			return varTable.get(name);
		}
		return this.parent.getVar(name);
	}
	
	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	
}
