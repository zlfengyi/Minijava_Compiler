/**
 * 表示类型的类，symboltable包中所有类的父类
 */
package minijava.symboltable;

public class MType {
	protected String type; // 类型
	protected int line = 0; // 所在行
	protected int column = 0; // 所在列

	public MType() {
	}
	
	public MType(String m_type) {
		type = m_type;
	}

	public MType(String m_type, int m_line, int m_column) {
		type = m_type;
		line = m_line;
		column = m_column;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public String getType() {
		return type;
	}


	public void setLine(int m_line) {
		line = m_line;
	}

	public void setColumn(int m_column) {
		column = m_column;
	}
	
	public void setType(String m_type) {
		type = m_type;
	}
}

