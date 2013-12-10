//
// Generated by JTB 1.3.2
//

package piglet.visitor;
import java.util.Enumeration;

import piglet.MSpiglet;
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
public class Piglet2SpigletVisitor extends GJNoArguDepthFirst<MSpiglet> {
	   //
	   // Auto class visitors--probably don't need to be overridden.
	   //
	   public MSpiglet visit(NodeList n) {
	      MSpiglet _ret=null;
	      int _count=0;
	      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
	         e.nextElement().accept(this);
	         _count++;
	      }
	      return _ret;
	   }

	   public MSpiglet visit(NodeListOptional n) {
	      if ( n.present() ) {
	         MSpiglet _ret=null;
	         int _count=0;
	         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
	            e.nextElement().accept(this);
	            _count++;
	         }
	         return _ret;
	      }
	      else
	         return null;
	   }

	   public MSpiglet visit(NodeOptional n) {
	      if ( n.present() )
	         return n.node.accept(this);
	      else
	         return null;
	   }

	   public MSpiglet visit(NodeSequence n) {
	      MSpiglet _ret=null;
	      int _count=0;
	      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
	         e.nextElement().accept(this);
	         _count++;
	      }
	      return _ret;
	   }

	   public MSpiglet visit(NodeToken n) { return null; }

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
	   public MSpiglet visit(Goal n) {
	      MSpiglet _ret = new MSpiglet();
	      _ret.append("MAIN", 0);
	      _ret.append(n.f1.accept(this));
	      _ret.append("END", 0);
	      _ret.append(n.f3.accept(this));
	      
	      return _ret;
	   }

	   /**
	    * f0 -> ( ( Label() )? Stmt() )*
	    */
	   public MSpiglet visit(StmtList n) {
	      MSpiglet _ret = n.f0.accept(this);
	      
	      return _ret;
	   }

	   /**
	    * f0 -> Label()
	    * f1 -> "["
	    * f2 -> IntegerLiteral()
	    * f3 -> "]"
	    * f4 -> StmtExp()
	    */
	   public MSpiglet visit(Procedure n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      n.f3.accept(this);
	      n.f4.accept(this);
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
	   public MSpiglet visit(Stmt n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> "NOOP"
	    */
	   public MSpiglet visit(NoOpStmt n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> "ERROR"
	    */
	   public MSpiglet visit(ErrorStmt n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> "CJUMP"
	    * f1 -> Exp()
	    * f2 -> Label()
	    */
	   public MSpiglet visit(CJumpStmt n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> "JUMP"
	    * f1 -> Label()
	    */
	   public MSpiglet visit(JumpStmt n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      n.f1.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> "HSTORE"
	    * f1 -> Exp()
	    * f2 -> IntegerLiteral()
	    * f3 -> Exp()
	    */
	   public MSpiglet visit(HStoreStmt n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      n.f3.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> "HLOAD"
	    * f1 -> Temp()
	    * f2 -> Exp()
	    * f3 -> IntegerLiteral()
	    */
	   public MSpiglet visit(HLoadStmt n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      n.f3.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> "MOVE"
	    * f1 -> Temp()
	    * f2 -> Exp()
	    */
	   public MSpiglet visit(MoveStmt n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> "PRINT"
	    * f1 -> Exp()
	    */
	   public MSpiglet visit(PrintStmt n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      n.f1.accept(this);
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
	   public MSpiglet visit(Exp n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> "BEGIN"
	    * f1 -> StmtList()
	    * f2 -> "RETURN"
	    * f3 -> Exp()
	    * f4 -> "END"
	    */
	   public MSpiglet visit(StmtExp n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      n.f3.accept(this);
	      n.f4.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> "CALL"
	    * f1 -> Exp()
	    * f2 -> "("
	    * f3 -> ( Exp() )*
	    * f4 -> ")"
	    */
	   public MSpiglet visit(Call n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      n.f3.accept(this);
	      n.f4.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> "HALLOCATE"
	    * f1 -> Exp()
	    */
	   public MSpiglet visit(HAllocate n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      n.f1.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> Operator()
	    * f1 -> Exp()
	    * f2 -> Exp()
	    */
	   public MSpiglet visit(BinOp n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> "LT"
	    *       | "PLUS"
	    *       | "MINUS"
	    *       | "TIMES"
	    */
	   public MSpiglet visit(Operator n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> "TEMP"
	    * f1 -> IntegerLiteral()
	    */
	   public MSpiglet visit(Temp n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      n.f1.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> <INTEGER_LITERAL>
	    */
	   public MSpiglet visit(IntegerLiteral n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> <IDENTIFIER>
	    */
	   public MSpiglet visit(Label n) {
	      MSpiglet _ret=null;
	      n.f0.accept(this);
	      return _ret;
	   }

}

