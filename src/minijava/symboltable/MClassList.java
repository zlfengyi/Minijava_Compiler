/**
 * 所有声明的类的列表
 */
package minijava.symboltable;

import java.util.ArrayList;
import java.util.HashSet;

public class MClassList extends MType {
	public ArrayList<MClass> classesList = new ArrayList<MClass>(); // 用于存放类

	// 在表中插入类
	public String InsertClass(MClass v_class) { 
		String class_name = v_class.getName();
		if (Repeated(class_name)) // 如已经定义过该类，返回错误信息
			return "Class double declaration " + "\"" + class_name + "\"";
		classesList.add(v_class);
		return null;
	}

	// 判定是否定义同名的类
	public boolean Repeated(String class_name) {
		int sz = classesList.size();
		for (int i = 0; i < sz; i++) {
			String c_name = ((MClass) classesList.get(i)).getName();
			if (c_name.equals(class_name))
				return true;
		}
		return false;
	}
	
	// 根据类名，返回类，不存在返回null
	public MClass getClass(String name) {
		for (MClass mclass : classesList) {
			if (mclass.getName().equals(name)) {
				return mclass;
			}
		}
		return null;
	}
	
	// 判断是否含有名字为name的类
	public boolean containClass(String name) {
		for (MClass mclass : classesList) {
			if (mclass.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	// name类是否相等 或者继承自 target
	public boolean classEqualsOrDerives(String name, String target) {
		if (name == null && target == null) {
			return true;
		}
		if (name == null || target == null) {
			return false;
		}
		while (1>0) {
			if (name.equals(target)) return true;
			MClass curClass = this.getClass(name);
			if (curClass != null) 
				name = curClass.getBaseClassName();
			else 
				break;
			if (name == null) break;
		}
		return false;
	}
	
	// 为所有类的变量和方法分配地址
	// 采用1次间接地址, 设新建的实体引用地址为addr, addr[0]保存函数表的首地址，addr[4, 8, 12..n]依次保存类的成员变量
	public int alloc(int currentTemp) {
		HashSet<String> pigletNameTable = new HashSet<String>();
		for (MClass mclass : classesList) {
			currentTemp = mclass.alloc(currentTemp, pigletNameTable);
		}
		return currentTemp;
	}
	
	public void updateVarAndMethodTable() {
		for (MClass mclass : classesList) {
			mclass.updateVarAndMethodTable();
		}
	}
	
	public void updateBaseClass() {
		for (MClass mclass : classesList) {
			MClass baseClass = this.getClass(mclass.baseClassName);
			mclass.setBaseClass(baseClass);
		}
	}
}

