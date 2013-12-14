package spiglet.spiglet2kanga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class CMethod {
	protected String name;
	protected ArrayList<CStmt> stmtList;
	public int nParaSize, nSpilledPara, nSpilledSize;
	
	public CMethod(String _name) {
		this.name = _name;
		stmtList = new ArrayList<CStmt>();
	}
	
	public void addStmt(CStmt _stmt) {
		stmtList.add(_stmt);
	}
	

//-------------------for register allocation--------------//
	
	class Pair implements Comparable<Pair> {
		public int t1, t2;
		
		public Pair(int _t1, int _t2) {
			this.t1 = _t1;
			this.t2 = _t2;
		}
		
		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			if (this.t1 < o.t1) return -1;
			if (this.t1 > o.t1) return 1;
			if (this.t2 < o.t2) return -1;
			if (this.t2 > o.t2) return 1;
			return 0;
		}
	}
	
	protected HashSet<Integer> tempSet;
	protected TreeMap<Pair, Boolean> haveEdge;
	protected TreeMap<Integer, Integer> regForTemp;
	
	public boolean update(CStmt st1, CStmt st2) {
		boolean _ret = false;
		if (st2 == null) return _ret;
		
		for (int temp : st2.usedTempList) {
			if (!st1.outSet.contains(temp))  {
				_ret = true;
				st1.outSet.add(temp);
			}
		}
		
		for (int temp : st2.outSet) {
			if (temp != st1.genTemp && !st1.outSet.contains(temp))  {
				_ret = true;
				st1.outSet.add(temp);
			}
		}
		
		return _ret;
	}
	
	private void buildGraph() {
		HashMap<String, CStmt> labelStmt = new HashMap<String, CStmt>();
		for (CStmt stmt : stmtList) {
			String label = stmt.entryLabel;
			if (label != null) labelStmt.put(label, stmt);
		}
		
		for (int i = 0; i < stmtList.size(); i++) {
			CStmt stmt = stmtList.get(i);
			if (!stmt.isUnconditionJump && i+1 < stmtList.size()) {
				stmt.nextStmt1 = stmtList.get(i+1);
			}
			if (stmt.jumpLabel != null) {
				stmt.nextStmt2 = labelStmt.get(stmt.jumpLabel);
			}
		}
	/*
		System.out.println(name);
		for (int i = 0; i < stmtList.size(); i++) {
			CStmt stmt = stmtList.get(i);
			System.out.print(i+":    " + stmt.entryLabel + ",    " + stmt.jumpLabel + ",    " + stmtList.get(i).genTemp +",  ");
			
			for (int temp : stmtList.get(i).usedTempList) {
				System.out.print(temp+"  ");
			}
			System.out.println();
		}
		*/
			
		while (1>0) {
			boolean flag = false;
			for (CStmt stmt : stmtList) {
				flag |= update(stmt, stmt.nextStmt1);
				flag |= update(stmt, stmt.nextStmt2);
			}
			if (!flag) break; 
		}

/*
		System.out.println(name);
		for (int i = 0; i < stmtList.size(); i++) {
			System.out.print(i+":    ");
			for (int temp : stmtList.get(i).outSet) {
				System.out.print(temp+"  ");
			}
			System.out.println();
		}
*/		
		tempSet = new HashSet<Integer>();
		haveEdge = new TreeMap<Pair, Boolean>();
		for (CStmt stmt : stmtList) {
			tempSet.addAll(stmt.outSet);
		}
		for (int temp1 : tempSet) {
			for (int temp2 : tempSet) if (temp1 < temp2) {
				boolean flag = false;
				for (CStmt stmt : stmtList) {
					if (stmt.outSet.contains(temp1) && stmt.outSet.contains(temp2)) {
						flag = true;
						break;
					}
				}
				haveEdge.put(new Pair(temp1, temp2), flag);
				haveEdge.put(new Pair(temp2, temp1), flag);
			}
		}	
	}
	
	public void allocReg() {
		buildGraph();	
		nSpilledPara = Math.max(0, nParaSize-4);
		regForTemp = new TreeMap<Integer, Integer>();
		nSpilledSize = 0;
		for (int temp1 : tempSet) {
			regForTemp.put(temp1, nSpilledSize++);
	/*
			boolean[] colour = new boolean[18];
			Arrays.fill(colour, false);
			
			for (int temp2 : tempSet) {
				Integer c = regForTemp.get(temp2);
				if (c == null || haveEdge.get(new Pair(temp1, temp2)) == false) continue;
				if (c < 18) colour[c] = true;
			} 
			boolean flag = false;
			for (int i = 0 ; i < 18; i++) {
				if (colour[i] == false) {
					regForTemp.put(temp1, i);
					nSpilledSize = Math.max(nSpilledSize, i+1);
					flag = true;
					break;
				} else {
					nSpilledSize = Math.max(nSpilledSize, i+1);
				}
				
			}
			if (flag == false) {
				regForTemp.put(temp1, nSpilledSize++);
			}
			*/
	//	System.out.println("temp " + temp1 + ":  " + regForTemp.get(temp1));
		}
	//	System.out.println();
	}
	
	public int getReg(int t) {
		if (tempSet.contains(t) == false) return -1;
		return regForTemp.get(t);
	}
}
