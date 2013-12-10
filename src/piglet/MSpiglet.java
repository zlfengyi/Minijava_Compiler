package piglet;

public class MSpiglet {
	protected StringBuilder genCode;
	protected String temp, simpleExp;
	
	public MSpiglet() {
	}
	
	public void append(String str, int identation) {
		String tab = "	";
		for (int i = 0; i < identation; i++) str = tab + str;
		str += "\n";
		
		if (genCode == null) 
			genCode = new StringBuilder(str);
		else
			genCode.append(str);
	}
	
	public void append(MSpiglet sp) {
		if (this.genCode == null) {
			this.genCode = sp.genCode;
		} else { 
			if (sp.genCode != null)
				this.genCode.append(sp.genCode);		
		}
	}
	
	public boolean isSimpleExp() {
		return( simpleExp != null);
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
}
