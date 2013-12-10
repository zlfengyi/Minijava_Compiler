//
// Generated by JTB 1.3.2
//

package piglet.visitor;
import java.util.Enumeration;

import piglet.piglet2spiglet.MSpigletExp;
import piglet.syntaxtree.BinOp;
import piglet.syntaxtree.CJumpStmt;
import piglet.syntaxtree.Call;
import piglet.syntaxtree.ErrorStmt;
import piglet.syntaxtree.Exp;
import piglet.syntaxtree.Goal;
import piglet.syntaxtree.HAllocate;
import piglet.syntaxtree.HLoadStmt;
import piglet.syntaxtree.HStoreStmt;
import piglet.syntaxtree.IntegerLiteral;
import piglet.syntaxtree.JumpStmt;
import piglet.syntaxtree.Label;
import piglet.syntaxtree.MoveStmt;
import piglet.syntaxtree.NoOpStmt;
import piglet.syntaxtree.Node;
import piglet.syntaxtree.NodeList;
import piglet.syntaxtree.NodeListOptional;
import piglet.syntaxtree.NodeOptional;
import piglet.syntaxtree.NodeSequence;
import piglet.syntaxtree.NodeToken;
import piglet.syntaxtree.Operator;
import piglet.syntaxtree.PrintStmt;
import piglet.syntaxtree.Procedure;
import piglet.syntaxtree.Stmt;
import piglet.syntaxtree.StmtExp;
import piglet.syntaxtree.StmtList;
import piglet.syntaxtree.Temp;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class Piglet2SpigletVisitor extends GJNoArguDepthFirst<MSpigletExp> {
	   protected final int TAB_NUM = 1;
	   protected int currentTemp;
	   
	   public void setTempNum(int x) {
		   this.currentTemp = x;
	   }
	   
	   public String getNextTemp() {
		   return "TEMP " + (currentTemp++);
	   }
	   //
	   // Auto class visitors--probably don't need to be overridden.
	   //
	   public MSpigletExp visit(NodeList n) {
	      MSpigletExp _ret = new MSpigletExp();
	      int _count=0;
	      
	      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
	         _ret.append(e.nextElement().accept(this));
	         _count++;
	      }
	      return _ret;
	   }

	   public MSpigletExp visit(NodeListOptional n) {
	      if ( n.present() ) {
	         MSpigletExp _ret = new MSpigletExp();
	         int _count=0;
	         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
	        	Node n1 = e.nextElement();

	        	//System.out.println((++_count) + " " + n1.toString());
	        	
	        	MSpigletExp r1 = n1.accept(this);
	        	_ret.append(r1);
	        	if (r1 != null && r1.getTemp() != null)
	        		_ret.addTemp(r1.getTemp());
	        	
	         }
	         return _ret;
	      }
	      else
	         return null;
	   }

	   public MSpigletExp visit(NodeOptional n) {
		  if ( n.present() ) {
			  if (n.node instanceof Label) {
				  return new MSpigletExp(n.node.toString(), 0);
			  }
			  return n.node.accept(this);
		  }
	      else
	         return null;
	   }

	   public MSpigletExp visit(NodeSequence n) {
	      MSpigletExp _ret = new MSpigletExp();
	      int _count=0;
	      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
	    	  Node n1 = e.nextElement();
	    	  _ret.append(n1.accept(this));
	    	  
	         _count++;
	      }
	      return _ret;
	   }

	   public MSpigletExp visit(NodeToken n) { return null; }

	   //
	   // User-generated visitor methods below
	   //

	   /**
	    * f0 -> "MAIN"
	    * f1 -> StmtList()
	    * f2 -> "END"
	    * f3 -> ( Procedure() )*
	    * f4 -> <EOF>
	    */
	   public MSpigletExp visit(Goal n) {
	      MSpigletExp _ret = new MSpigletExp();
	      _ret.append(new MSpigletExp("MAIN", 0));
	      _ret.append(n.f1.accept(this));
	      _ret.append(new MSpigletExp("END", 0));
	      _ret.append(n.f3.accept(this));
	      
	      return _ret;
	   }

	   /**
	    * f0 -> ( ( Label() )? Stmt() )*
	    */
	   public MSpigletExp visit(StmtList n) {
	      MSpigletExp _ret = n.f0.accept(this);
	      
	      return _ret;
	   }

	   /**
	    * f0 -> Label()
	    * f1 -> "["
	    * f2 -> IntegerLiteral()
	    * f3 -> "]"
	    * f4 -> StmtExp()
	    */
	   public MSpigletExp visit(Procedure n) {
	      MSpigletExp _ret = new MSpigletExp(
	    		  n.f0.f0.tokenImage + "[" + n.f2.f0.tokenImage + "]"
	    		  , 0);
	      
	      MSpigletExp r1 = n.f4.accept(this);
	      _ret.append(new MSpigletExp("BEGIN", 0));
	      _ret.append(r1);
	      _ret.append(new MSpigletExp("RETURN " + r1.getTemp(), TAB_NUM));
	      _ret.append(new MSpigletExp("END", 0));
	      
	      return _ret;
	   }

	   /**
	    * f0 -> NoOpStmt()
	    *       | ErrorStmt()
	    *       | CJumpStmt()
	    *       | JumpStmt()
	    *       | HStoreStmt()
	    *       | HLoadStmt()
	    *       | MoveStmt()
	    *       | PrintStmt()
	    */
	   public MSpigletExp visit(Stmt n) {
	      MSpigletExp _ret = n.f0.accept(this);
	      
	      return _ret;
	   }

	   /**
	    * f0 -> "NOOP"
	    */
	   public MSpigletExp visit(NoOpStmt n) {
	      MSpigletExp _ret = new MSpigletExp("NOOP", TAB_NUM);

	      return _ret;
	   }

	   /**
	    * f0 -> "ERROR"
	    */
	   public MSpigletExp visit(ErrorStmt n) {
		   MSpigletExp _ret = new MSpigletExp("ERROR", TAB_NUM);

		   return _ret;
	   }

	   /**
	    * f0 -> "CJUMP"
	    * f1 -> Exp()
	    * f2 -> Label()
	    */
	   public MSpigletExp visit(CJumpStmt n) {
	      MSpigletExp r1 = n.f1.accept(this);
	      MSpigletExp _ret = new MSpigletExp();
	      
	      _ret.append(r1);
	      _ret.append(new MSpigletExp(
	    		  "CJUMP " + r1.getTemp() + " " + n.f2.f0.tokenImage,
	    		  TAB_NUM));
	      
	      return _ret;
	   }

	   /**
	    * f0 -> "JUMP"
	    * f1 -> Label()
	    */
	   public MSpigletExp visit(JumpStmt n) {
	      MSpigletExp _ret = new MSpigletExp("JUMP " + n.f1.f0.tokenImage, TAB_NUM); 
	      
	      return _ret;
	   }

	   /**
	    * f0 -> "HSTORE"
	    * f1 -> Exp()
	    * f2 -> IntegerLiteral()
	    * f3 -> Exp()
	    */
	   public MSpigletExp visit(HStoreStmt n) {
		  MSpigletExp r1 = n.f1.accept(this);
		  MSpigletExp r2 = n.f3.accept(this);
		  MSpigletExp _ret = new MSpigletExp();
		  
		  _ret.append(r1);
		  _ret.append(r2);
		  _ret.append(new MSpigletExp(
				  "HSTORE " + r1.getTemp() + " " + n.f2.f0.tokenImage + " " + r2.getTemp()
				  , TAB_NUM));
		  
		  return _ret;
	   }

	   /**
	    * f0 -> "HLOAD"
	    * f1 -> Temp()
	    * f2 -> Exp()
	    * f3 -> IntegerLiteral()
	    */
	   public MSpigletExp visit(HLoadStmt n) {
		   MSpigletExp r1 = n.f1.accept(this);
		   MSpigletExp r2 = n.f2.accept(this);
		   MSpigletExp _ret = new MSpigletExp();
		  
		   _ret.append(r1);
		   _ret.append(r2);
		   _ret.append(new MSpigletExp(
				  "HLOAD " + r1.getTemp() + " " + r2.getTemp() + " " + n.f3.f0.tokenImage
				  , TAB_NUM));
			  
		  return _ret;
	   }

	   /**
	    * f0 -> "MOVE"
	    * f1 -> Temp()
	    * f2 -> Exp()
	    */
	   public MSpigletExp visit(MoveStmt n) {
		  MSpigletExp r1 = n.f1.accept(this);
		  MSpigletExp r2 = n.f2.accept(this);
		  MSpigletExp _ret = new MSpigletExp();
		  
		  _ret.append(r1);
		  _ret.append(r2);
		  _ret.append(new MSpigletExp(
				  "MOVE " + r1.getTemp() + " " + r2.getTemp()
				  , TAB_NUM));
		  
	      return _ret;
	   }

	   /**
	    * f0 -> "PRINT"
	    * f1 -> Exp()
	    */
	   public MSpigletExp visit(PrintStmt n) {
		  MSpigletExp r1 = n.f1.accept(this);
		  MSpigletExp _ret = new MSpigletExp();
		  
		  _ret.append(r1);
		  _ret.append(new MSpigletExp(
				  "PRINT " + r1.getTemp()
				  , TAB_NUM));
		  
	      return _ret;
	   }

	   /**
	    * f0 -> StmtExp()
	    *       | Call()
	    *       | HAllocate()
	    *       | BinOp()
	    *       | Temp()
	    *       | IntegerLiteral()
	    *       | Label()
	    */
	   public MSpigletExp visit(Exp n) {
	      MSpigletExp _ret = n.f0.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> "BEGIN"
	    * f1 -> StmtList()
	    * f2 -> "RETURN"
	    * f3 -> Exp()
	    * f4 -> "END"
	    */
	   public MSpigletExp visit(StmtExp n) {
		  MSpigletExp r1 = n.f1.accept(this);
		  MSpigletExp r2 = n.f3.accept(this);
		  MSpigletExp _ret = new MSpigletExp();
		  
		  _ret.append(r1);
		  _ret.append(r2);
		  _ret.setTemp(r2.getTemp());
		  
	      return _ret;
	   }

	   /**
	    * f0 -> "CALL"
	    * f1 -> Exp()
	    * f2 -> "("
	    * f3 -> ( Exp() )*
	    * f4 -> ")"
	    */
	   public MSpigletExp visit(Call n) {
		  MSpigletExp r1 = n.f1.accept(this);
		  MSpigletExp r2 = n.f3.accept(this);
		  MSpigletExp _ret = new MSpigletExp();
		  String temp1 = this.getNextTemp();
		  
		  _ret.append(r1);
		  _ret.append(r2);
		  String code = "CALL " + r1.getTemp() + "(";
		  for (String temp : r2.getTempList()) {
			  code += temp + " ";
		  }
		  code += ")";
		  _ret.append(new MSpigletExp("MOVE " + temp1 + " " + code, TAB_NUM));
		  _ret.setTemp(temp1);
		  
	      return _ret;
	   }

	   /**
	    * f0 -> "HALLOCATE"
	    * f1 -> Exp()
	    */
	   public MSpigletExp visit(HAllocate n) {
	      MSpigletExp r1 = n.f1.accept(this);
	      String temp1 = this.getNextTemp();
	      
	      MSpigletExp _ret = new MSpigletExp();
	      _ret.append(r1);
	      _ret.append(new MSpigletExp(
	    		  "MOVE " + temp1 + " HALLOCATE " + r1.getTemp(), 
	    		  TAB_NUM));
	      _ret.setTemp(temp1);
	      
	      return _ret;
	   }

	   /**
	    * f0 -> Operator()
	    * f1 -> Exp()
	    * f2 -> Exp()
	    */
	   public MSpigletExp visit(BinOp n) {
	      MSpigletExp r0 = n.f0.accept(this);
	      MSpigletExp r1 = n.f1.accept(this);
	      MSpigletExp r2 = n.f2.accept(this);
	      MSpigletExp _ret = new MSpigletExp();
	      String temp1 = this.getNextTemp();
	      
	      _ret.append(r1);
	      _ret.append(r2);
	      _ret.append(new MSpigletExp(
	    		  "MOVE " + temp1 + " " + r0.getOp() + " " + r1.getTemp() + " " + r2.getTemp()
	    		  ,TAB_NUM));
	      _ret.setTemp(temp1);
	      
	      return _ret;
	   }

	   /**
	    * f0 -> "LT"
	    *       | "PLUS"
	    *       | "MINUS"
	    *       | "TIMES"
	    */
	   public MSpigletExp visit(Operator n) {
	      String op;
	      if (n.f0.which == 0) op = "LT";
	      else if (n.f0.which == 1) op = "PLUS";
	      else if (n.f0.which == 2) op = "MINUS";
	      else op = "TIMES";
	     
	      MSpigletExp _ret = new MSpigletExp();
	      _ret.setOp(op);
	      
	      return _ret;
	   }

	   /**
	    * f0 -> "TEMP"
	    * f1 -> IntegerLiteral()
	    */
	   public MSpigletExp visit(Temp n) {
	      MSpigletExp _ret = new MSpigletExp();
	      String temp1 = "TEMP " + n.f1.f0.tokenImage;
	      _ret.setTemp(temp1);
	      
	      return _ret;
	   }

	   /**
	    * f0 -> <INTEGER_LITERAL>
	    */
	   public MSpigletExp visit(IntegerLiteral n) {
		  String temp1 = this.getNextTemp();
			  
		  MSpigletExp _ret = new MSpigletExp("MOVE " + temp1 + " " + n.f0.tokenImage, TAB_NUM);
		  _ret.setTemp(temp1);
	      _ret.setSimpleExp(n.f0.tokenImage);
	      
	      return _ret;
	   }

	   /**
	    * f0 -> <IDENTIFIER>
	    */
	   public MSpigletExp visit(Label n) {
		  String temp1 = this.getNextTemp();
		  
		  MSpigletExp _ret = new MSpigletExp("MOVE " + temp1 + " " + n.f0.tokenImage, TAB_NUM);
		  _ret.setTemp(temp1);
	      _ret.setSimpleExp(n.f0.tokenImage);
	      
	      return _ret;
	   }

}

