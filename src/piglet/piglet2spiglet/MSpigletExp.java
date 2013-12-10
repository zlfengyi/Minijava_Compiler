package piglet.piglet2spiglet;

import java.util.ArrayList;

public class MSpigletExp {
	protected StringBuilder genCode;
	protected String temp, simpleExp, op;
	protected int iden = -1;
	protected ArrayList<String> tempList = new ArrayList<String>();
	
	public MSpigletExp() {
	}
	
	public MSpigletExp(String str, int identation) {
		this.iden = identation;
		String tab = "	";
		for (int i = 0; i < identation; i++) str = tab + str;
		this.genCode = new StringBuilder(str);
	}
	
	public void append(String str) {
		if (this.genCode == null) 
			this.genCode = new StringBuilder(str);
		else
			this.genCode.append(str);
	}
	public void append(MSpigletExp sp) {
		if (sp == null || sp.genCode == null) return;
		
		if (this.genCode == null) this.genCode = new StringBuilder("");	
		if (sp.iden == -1)
			this.genCode.append(" ");		
		else
			this.genCode.append("\n");
		
		this.genCode.append(sp.genCode);
	}
	
	public void addTemp(String _temp) {
		tempList.add(_temp);
	}
	public ArrayList<String> getTempList() {
		return tempList;
	}
	
	public boolean isSimpleExp() {
		return( simpleExp != null);
	}

	@Override
	public String toString() {
		return this.genCode.toString();
	}
//-----------------------Getters and Setters-----------------------//
	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getSimpleExp() {
		return simpleExp;
	}

	public void setSimpleExp(String simpleExp) {
		this.simpleExp = simpleExp;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}	
	

}
