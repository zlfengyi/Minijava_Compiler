package minijava.visitor;

import java.util.Enumeration;

import minijava.symboltable.MClass;
import minijava.symboltable.MClassList;
import minijava.symboltable.MIdentifier;
import minijava.symboltable.MMethod;
import minijava.symboltable.MPiglet;
import minijava.symboltable.MVar;
import minijava.syntaxtree.AllocationExpression;
import minijava.syntaxtree.AndExpression;
import minijava.syntaxtree.ArrayAllocationExpression;
import minijava.syntaxtree.ArrayAssignmentStatement;
import minijava.syntaxtree.ArrayLength;
import minijava.syntaxtree.ArrayLookup;
import minijava.syntaxtree.ArrayType;
import minijava.syntaxtree.AssignmentStatement;
import minijava.syntaxtree.Block;
import minijava.syntaxtree.BooleanType;
import minijava.syntaxtree.BracketExpression;
import minijava.syntaxtree.ClassDeclaration;
import minijava.syntaxtree.ClassExtendsDeclaration;
import minijava.syntaxtree.CompareExpression;
import minijava.syntaxtree.Expression;
import minijava.syntaxtree.ExpressionList;
import minijava.syntaxtree.ExpressionRest;
import minijava.syntaxtree.FalseLiteral;
import minijava.syntaxtree.FormalParameter;
import minijava.syntaxtree.FormalParameterList;
import minijava.syntaxtree.FormalParameterRest;
import minijava.syntaxtree.Goal;
import minijava.syntaxtree.Identifier;
import minijava.syntaxtree.IfStatement;
import minijava.syntaxtree.IntegerLiteral;
import minijava.syntaxtree.IntegerType;
import minijava.syntaxtree.MainClass;
import minijava.syntaxtree.MessageSend;
import minijava.syntaxtree.MethodDeclaration;
import minijava.syntaxtree.MinusExpression;
import minijava.syntaxtree.Node;
import minijava.syntaxtree.NodeList;
import minijava.syntaxtree.NodeListOptional;
import minijava.syntaxtree.NodeOptional;
import minijava.syntaxtree.NodeSequence;
import minijava.syntaxtree.NodeToken;
import minijava.syntaxtree.NotExpression;
import minijava.syntaxtree.PlusExpression;
import minijava.syntaxtree.PrimaryExpression;
import minijava.syntaxtree.PrintStatement;
import minijava.syntaxtree.Statement;
import minijava.syntaxtree.ThisExpression;
import minijava.syntaxtree.TimesExpression;
import minijava.syntaxtree.TrueLiteral;
import minijava.syntaxtree.Type;
import minijava.syntaxtree.TypeDeclaration;
import minijava.syntaxtree.VarDeclaration;
import minijava.syntaxtree.WhileStatement;

public class Java2PigletVisitor extends GJDepthFirst<MPiglet, MIdentifier> {
	//
   // Auto class visitors--probably don't need to be overridden.
   //
   public MPiglet visit(NodeList n, MIdentifier argu) {
      MPiglet _ret = null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         if (_ret == null) _ret = e.nextElement().accept(this,argu);
         else _ret.append(e.nextElement().accept(this,argu));
         _count++;
      }
      
      return _ret;
   }

   public MPiglet visit(NodeListOptional n, MIdentifier argu) {
      if ( n.present() ) {
         MPiglet _ret=null;
         int _count=0;
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
        	 if (_ret == null) _ret = e.nextElement().accept(this,argu);
             else _ret.append(e.nextElement().accept(this,argu));
        	 _count++;
         }
         return _ret;
      }
      else
         return null;
   }

   public MPiglet visit(NodeOptional n, MIdentifier argu) {
      if ( n.present() )
         return n.node.accept(this,argu);
      else
         return null;
   }

   public MPiglet visit(NodeSequence n, MIdentifier argu) {
      MPiglet _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
    	  if (_ret == null) _ret = e.nextElement().accept(this,argu);
          else _ret.append(e.nextElement().accept(this,argu));
    	  _count++;
      }
      return _ret;
   }

   public MPiglet visit(NodeToken n, MIdentifier argu) { return null; }

   
   //
   // User-generated visitor methods below
   //

   // 当前寄存器编号，和当前代码缩进的tab数
   protected int nCurrentTemp, nCurrentTab = 0, nCurrentLabel = 0;
   protected MClassList allClasses;
   
   public String getNextLabel() {
	   return "label_" + (++nCurrentLabel);
   }

   public String getNextTemp() {
	   return "TEMP " + (nCurrentTemp++);
   }
   
   public int getCurrentTemp() {
	   return nCurrentTemp;
   }

   public void setCurrentTemp(int currentTemp) {
	   this.nCurrentTemp = currentTemp;
   }
   
   public void setAllClasses(MClassList _allClasses) {
	   allClasses = _allClasses;
   }
   
   public void addBegin(MPiglet _ret) {
	   MPiglet _begin = new MPiglet("BEGIN", ++nCurrentTab);
	   _ret.append(_begin);
   }
   
   public void addEnd(MPiglet _ret) {
	   MPiglet _end = new MPiglet("END", nCurrentTab--);
	   _ret.append(_end);
   }
   
   
   /**
    * f0 -> MainClass()
    * f1 -> ( TypeDeclaration() )*
    * f2 -> <EOF>
    */
   public MPiglet visit(Goal n, MIdentifier argu) {
	  MPiglet _ret = n.f0.accept(this, argu);
	  _ret.append("\nEND");
	  _ret.append(n.f1.accept(this, argu));
      
      return _ret;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "{"
    * f3 -> "public"
    * f4 -> "static"
    * f5 -> "void"
    * f6 -> "main"
    * f7 -> "("
    * f8 -> "String"
    * f9 -> "["
    * f10 -> "]"
    * f11 -> Identifier()
    * f12 -> ")"
    * f13 -> "{"
    * f14 -> PrintStatement()
    * f15 -> "}"
    * f16 -> "}"
    */
   public MPiglet visit(MainClass n, MIdentifier argu) {
      MPiglet _ret = new MPiglet("MAIN", nCurrentTab);
      
      MClass mclass = allClasses.getClass(n.f1.f0.toString());
      MMethod mmethod = mclass.getMethod("main");
      
      _ret.append(n.f14.accept(this, mmethod));
      
      return _ret;
   }

   /**
    * f0 -> ClassDeclaration()
    *       | ClassExtendsDeclaration()
    */
   public MPiglet visit(TypeDeclaration n, MIdentifier argu) {
      MPiglet _ret = n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "{"
    * f3 -> ( VarDeclaration() )*
    * f4 -> ( MethodDeclaration() )*
    * f5 -> "}"
    */
   public MPiglet visit(ClassDeclaration n, MIdentifier argu) {
      MClass mclass = allClasses.getClass(n.f1.f0.toString());
      
	  MPiglet _ret = n.f4.accept(this, mclass);

	  return _ret;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "extends"
    * f3 -> Identifier()
    * f4 -> "{"
    * f5 -> ( VarDeclaration() )*
    * f6 -> ( MethodDeclaration() )*
    * f7 -> "}"
    */
   public MPiglet visit(ClassExtendsDeclaration n, MIdentifier argu) {
	   MClass mclass = allClasses.getClass(n.f1.f0.toString());
      
	   MPiglet _ret = n.f6.accept(this, mclass);

	   return _ret;
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    * f2 -> ";"
    */
   public MPiglet visit(VarDeclaration n, MIdentifier argu) {
      MPiglet _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "public"
    * f1 -> Type()
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( FormalParameterList() )?
    * f5 -> ")"
    * f6 -> "{"
    * f7 -> ( VarDeclaration() )*
    * f8 -> ( Statement() )*
    * f9 -> "return"
    * f10 -> Expression()
    * f11 -> ";"
    * f12 -> "}"
    */
   public MPiglet visit(MethodDeclaration n, MIdentifier argu) {
      MMethod mmethod = ((MClass)argu).getMethod(n.f2.f0.toString());
	  MPiglet _ret = new MPiglet("\n"+mmethod.getPigletDefineName(), nCurrentTab);
	  
	  addBegin(_ret);
	  _ret.append(n.f8.accept(this, mmethod));
	  MPiglet returnExp = new MPiglet("RETURN", nCurrentTab);
	  returnExp.append(n.f10.accept(this, mmethod));
	  _ret.append(returnExp);
	  addEnd(_ret);
	  
	  return _ret;
   }

   /**
    * f0 -> FormalParameter()
    * f1 -> ( FormalParameterMPigletest() )*
    */
   public MPiglet visit(FormalParameterList n, MIdentifier argu) {
      MPiglet _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    */
   public MPiglet visit(FormalParameter n, MIdentifier argu) {
      MPiglet _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> ","
    * f1 -> FormalParameter()
    */
   public MPiglet visit(FormalParameterRest n, MIdentifier argu) {
      MPiglet _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> ArrayType()
    *       | BooleanType()
    *       | IntegerType()
    *       | Identifier()
    */
   public MPiglet visit(Type n, MIdentifier argu) {
      MPiglet _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "int"
    * f1 -> "["
    * f2 -> "]"
    */
   public MPiglet visit(ArrayType n, MIdentifier argu) {
      MPiglet _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "boolean"
    */
   public MPiglet visit(BooleanType n, MIdentifier argu) {
      MPiglet _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "int"
    */
   public MPiglet visit(IntegerType n, MIdentifier argu) {
      MPiglet _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Block()
    *       | AssignmentStatement()
    *       | ArrayAssignmentStatement()
    *       | IfStatement()
    *       | WhileStatement()
    *       | PrintStatement()
    */
   public MPiglet visit(Statement n, MIdentifier argu) {
      MPiglet _ret = n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "{"
    * f1 -> ( Statement() )*
    * f2 -> "}"
    */
   // pass unit test
   public MPiglet visit(Block n, MIdentifier argu) {
      MPiglet _ret = n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "="
    * f2 -> Expression()
    * f3 -> ";"
    */
   // pass unit test
   public MPiglet visit(AssignmentStatement n, MIdentifier argu) {
      MPiglet _ret = new MPiglet();
      MPiglet r1 = n.f0.accept(this, argu);
      MPiglet r2 = n.f2.accept(this, argu);
      MVar mvar = r1.getVar();
      
      if (mvar.isTemp()) {
    	  _ret.append(new MPiglet("MOVE " + r1, nCurrentTab));
      } else {
    	  _ret.append(new MPiglet("HSTORE TEMP 0 " + mvar.getOffset(), nCurrentTab));
      }
      _ret.append(r2);
      
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "["
    * f2 -> Expression()
    * f3 -> "]"
    * f4 -> "="
    * f5 -> Expression()
    * f6 -> ";"
    */
   public MPiglet visit(ArrayAssignmentStatement n, MIdentifier argu) {
	  MPiglet _ret = new MPiglet();
	  String temp1 = getNextTemp(), temp2 = getNextTemp();
	  
	  _ret.append(new MPiglet("MOVE " + temp1, nCurrentTab));
	  _ret.append(n.f0.accept(this, argu));
	  _ret.append(new MPiglet("MOVE " + temp2 + " PLUS 4 TIMES 4", nCurrentTab));
	  _ret.append(n.f2.accept(this, argu));
	  _ret.append(new MPiglet("MOVE " + temp1 + " PLUS " + temp1 + " " + temp2, nCurrentTab)); 
	  _ret.append(new MPiglet("HSTORE " + temp1 + " 0", nCurrentTab));
	  _ret.append(n.f5.accept(this, argu));
	  
      return _ret;
   }

   /**
    * f0 -> "if"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    * f5 -> "else"
    * f6 -> Statement()
    */
   // pass unit test
   public MPiglet visit(IfStatement n, MIdentifier argu) {
      //String temp1 = this.getNextTemp();
	  //MPiglet _ret = new MPiglet("MOVE " + temp1, nCurrentTab);
	  
	  MPiglet _ret = new MPiglet("CJUMP", nCurrentTab);
	  _ret.append(n.f2.accept(this, argu));
	  
      String label1 = getNextLabel();
      String label2 = getNextLabel();
      
      _ret.append(" " + label1);
      
      _ret.append(n.f4.accept(this, argu));
      _ret.append(new MPiglet("JUMP " + label2, nCurrentTab));
      _ret.append("\n"+label1);
      _ret.append(n.f6.accept(this, argu));
      _ret.append("\n"+label2);
      _ret.append(new MPiglet("NOOP", nCurrentTab));
      
      return _ret;
   }

   /**
    * f0 -> "while"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    */
   // pass unit test
   public MPiglet visit(WhileStatement n, MIdentifier argu) {
      String label1 = this.getNextLabel(), label2 = this.getNextLabel();
	  MPiglet _ret = new MPiglet(label1, 0);
	  _ret.append(new MPiglet("CJUMP", nCurrentTab));
	  _ret.append(" " + n.f2.accept(this, argu).toString() + " " + label2);
      _ret.append(n.f4.accept(this, argu));
      _ret.append(new MPiglet("JUMP " + label1, nCurrentTab));
      _ret.append("\n"+label2);
      _ret.append(new MPiglet("NOOP", nCurrentTab));
      
      return _ret;
   }

   /**
    * f0 -> "System.out.println"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> ";"
    */
   // pass unit test
   public MPiglet visit(PrintStatement n, MIdentifier argu) {
      MPiglet _ret = new MPiglet("PRINT", nCurrentTab);
      
      _ret.append(n.f2.accept(this, argu));
      
      return _ret;
   }

   /**
    * f0 -> AndExpression()
    *       | CompareExpression()
    *       | PlusExpression()
    *       | MinusExpression()
    *       | TimesExpression()
    *       | ArrayLookup()
    *       | ArrayLength()
    *       | MessageSend()
    *       | PrimaryExpression()
    */
   public MPiglet visit(Expression n, MIdentifier argu) {
      MPiglet _ret = n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "&&"
    * f2 -> PrimaryExpression()
    */
   public MPiglet visit(AndExpression n, MIdentifier argu) {
      MPiglet _ret = new MPiglet("TIMES");
      _ret.append(n.f0.accept(this, argu));
      _ret.append(n.f2.accept(this, argu));
      
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "<"
    * f2 -> PrimaryExpression()
    */
   public MPiglet visit(CompareExpression n, MIdentifier argu) {
	   MPiglet _ret = new MPiglet("LT");
	   _ret.append(n.f0.accept(this, argu));
	   _ret.append(n.f2.accept(this, argu));
	   
	   return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "+"
    * f2 -> PrimaryExpression()
    */
   public MPiglet visit(PlusExpression n, MIdentifier argu) {
	   MPiglet _ret = new MPiglet("PLUS");
	   _ret.append(n.f0.accept(this, argu));
	   _ret.append(n.f2.accept(this, argu));
	   
	   return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "-"
    * f2 -> PrimaryExpression()
    */
   public MPiglet visit(MinusExpression n, MIdentifier argu) {
	   MPiglet _ret = new MPiglet("MINUS");
	   _ret.append(n.f0.accept(this, argu));
	   _ret.append(n.f2.accept(this, argu));
	   
	   return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "*"
    * f2 -> PrimaryExpression()
    */
   public MPiglet visit(TimesExpression n, MIdentifier argu) {
	   MPiglet _ret = new MPiglet("TIMES");
	   _ret.append(n.f0.accept(this, argu));
	   _ret.append(n.f2.accept(this, argu));
	   
	   return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "["
    * f2 -> PrimaryExpression()
    * f3 -> "]"
    */
   public MPiglet visit(ArrayLookup n, MIdentifier argu) {
      MPiglet _ret = new MPiglet();
      String temp1 = getNextTemp(), temp2 = getNextTemp(), temp3 = getNextTemp();
      
      addBegin(_ret);
      _ret.append(new MPiglet("MOVE " + temp1, nCurrentTab));
      _ret.append(n.f0.accept(this, argu));
      _ret.append(new MPiglet("MOVE " + temp2, nCurrentTab));
      _ret.append(n.f2.accept(this, argu));
      _ret.append(new MPiglet("MOVE " + temp2 + " PLUS 4 TIMES 4 " + temp2, nCurrentTab));
      _ret.append(new MPiglet("MOVE " + temp1 + " PLUS " + temp1 + " " + temp2));
      _ret.append(new MPiglet("HLOAD " + temp3 + " " + temp1 + " 0", nCurrentTab));
      _ret.append(new MPiglet("RETURN " + temp3, nCurrentTab));
      addEnd(_ret);
      
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "."
    * f2 -> "length"
    */
   public MPiglet visit(ArrayLength n, MIdentifier argu) {
      MPiglet _ret = new MPiglet();
      String temp1 = getNextTemp(), temp2 = getNextTemp();
      
      addBegin(_ret);
      _ret.append(new MPiglet("MOVE " + temp1, nCurrentTab));
      _ret.append(n.f0.accept(this, argu));
      _ret.append(new MPiglet("HLOAD " + temp2 + " " + temp1 + " 0", nCurrentTab));
      _ret.append(new MPiglet("RETURN " + temp2, nCurrentTab));
      addEnd(_ret);
      
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "."
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( ExpressionList() )?
    * f5 -> ")"
    */
   // pass "Allocation . NoParameter " Call 
   public MPiglet visit(MessageSend n, MIdentifier argu) {
	  MPiglet _ret = new MPiglet();
	  
      _ret.append("CALL");
      addBegin(_ret);
      MPiglet r1 = n.f0.accept(this, argu);
//r1.debug();
//System.out.println(n.f2.f0.toString());
      MMethod mmethod = r1.getMclass().getMethod(n.f2.f0.toString());
      _ret.setMclass(allClasses.getClass(mmethod.getReturnType()));
      String temp1 = this.getNextTemp(), temp2 = this.getNextTemp(), temp3 = this.getNextTemp();
      
      _ret.append(new MPiglet("MOVE " + temp1 + " " + r1.getPigCode().toString(), nCurrentTab));
      _ret.append(new MPiglet("HLOAD " + temp2 + " " + temp1 + " 0", nCurrentTab));
      _ret.append(new MPiglet("HLOAD " + temp3 + " " + temp2 + " " + mmethod.getOffset(), nCurrentTab));
      _ret.append(new MPiglet("RETURN " + temp3, nCurrentTab));
      addEnd(_ret);
      
      _ret.append(" (" + temp1);
      _ret.append(n.f4.accept(this, argu));
      _ret.append(")");
      
      return _ret;
   }

   /**
    * f0 -> Expression()
    * f1 -> ( ExpressionRest() )*
    */
   public MPiglet visit(ExpressionList n, MIdentifier argu) {
      MPiglet _ret = n.f0.accept(this, argu);
      _ret.append(n.f1.accept(this, argu));
      return _ret;
   }

   /**
    * f0 -> ","
    * f1 -> Expression()
    */
   public MPiglet visit(ExpressionRest n, MIdentifier argu) {
      MPiglet _ret = n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> IntegerLiteral()
    *       | TrueLiteral()
    *       | FalseLiteral()
    *       | Identifier()
    *       | ThisExpression()
    *       | ArrayAllocationExpression()
    *       | AllocationExpression()
    *       | NotExpression()
    *       | BracketExpression()
    */
   public MPiglet visit(PrimaryExpression n, MIdentifier argu) {
      MPiglet _ret = n.f0.accept(this, argu);
      
      return _ret;
   }

   /**
    * f0 -> <INTEGER_LITERAL>
    */
   public MPiglet visit(IntegerLiteral n, MIdentifier argu) {
      MPiglet _ret = new MPiglet(n.f0.toString());
      return _ret;
   }

   /**
    * f0 -> "true"
    */
   public MPiglet visit(TrueLiteral n, MIdentifier argu) {
	   MPiglet _ret = new MPiglet("1");
	   return _ret;
   }

   /**
    * f0 -> "false"
    */
   public MPiglet visit(FalseLiteral n, MIdentifier argu) {
	   MPiglet _ret = new MPiglet("0");
	   return _ret;
   }

   /**
    * f0 -> <IDENTIFIER>
    */
   public MPiglet visit(Identifier n, MIdentifier argu) {
      MVar _var = ((MMethod)argu).getVar(n.f0.toString());
      MPiglet _ret = new MPiglet();
      _ret.setVar(_var);
      _ret.setMclass(allClasses.getClass(_var.getType())); 
      _ret.setLine(n.f0.beginLine);
      _ret.setColumn(n.f0.beginColumn);
      
      // Piglet code to get the _var 
      if (_var.isTemp()) {
    	  _ret.append("TEMP " + _var.getTemp());
      } else {
    	  addBegin(_ret);
    	  String temp1 = getNextTemp();
    	  MPiglet st1 = new MPiglet("HLOAD " + temp1, nCurrentTab);
    	  st1.append(" TEMP 0 " + _var.getOffset());
    	  MPiglet st2 = new MPiglet("RETURN " + temp1, nCurrentTab);
    	  _ret.append(st1);
    	  _ret.append(st2);
    	  addEnd(_ret);
      }
      
      return _ret;
   }

   /**
    * f0 -> "this"
    */
   // "this" point store in TEMP 0, as the first parameter 
   public MPiglet visit(ThisExpression n, MIdentifier argu) {
      MPiglet _ret = new MPiglet("TEMP 0");
      _ret.setMclass((MClass)argu.getParent());
      _ret.setLine(n.f0.beginLine);
      _ret.setColumn(n.f0.beginColumn);
      
      return _ret;
   }

   /**
    * f0 -> "new"
    * f1 -> "int"
    * f2 -> "["
    * f3 -> Expression()
    * f4 -> "]"
    */
   public MPiglet visit(ArrayAllocationExpression n, MIdentifier argu) {
      MPiglet _ret = new MPiglet();
      String temp1 = getNextTemp(), temp2 = getNextTemp(), temp3 = getNextTemp();
      
      addBegin(_ret);
      _ret.append(new MPiglet("MOVE " + temp1, nCurrentTab));
      _ret.append(n.f3.accept(this, argu));
      _ret.append(new MPiglet("MOVE " + temp2 + " TIMES 4 " + temp1, nCurrentTab));
      _ret.append(new MPiglet("MOVE " + temp2 + " PLUS 4 " + temp2, nCurrentTab));
      _ret.append(new MPiglet("MOVE " + temp3 + " HALLOCATE " + temp2, nCurrentTab));
      _ret.append(new MPiglet("HSTORE " + temp3 + " 0 " + temp1, nCurrentTab));
      _ret.append(new MPiglet("RETURN " + temp3, nCurrentTab));
      addEnd(_ret);
  	
      return _ret;
   }

   /**
    * f0 -> "new"
    * f1 -> Identifier()
    * f2 -> "("
    * f3 -> ")"
    */
   public MPiglet visit(AllocationExpression n, MIdentifier argu) {
      MClass mclass = allClasses.getClass(n.f1.f0.toString());
      MPiglet _ret = new MPiglet();
      _ret.setMclass(allClasses.getClass(n.f1.f0.toString()));
      _ret.setLine(n.f1.f0.beginLine);
      _ret.setColumn(n.f1.f0.beginColumn);
      String temp1 = getNextTemp(), temp2 = getNextTemp();
      
      addBegin(_ret);
      MPiglet st1 = new MPiglet("MOVE " + temp1 + " HALLOCATE " + mclass.getMethodSize(), nCurrentTab);
      for (MMethod mmethod : mclass.getMethodSet()) {
    	  MPiglet st1_1 = new MPiglet("HSTORE " + temp1 + " " + mmethod.getOffset() , nCurrentTab);
    	  st1_1.append(" " + mmethod.getPigletName());
    	  st1.append(st1_1);
      }
      MPiglet st2 = new MPiglet("MOVE " + temp2 + " HALLOCATE " + (4+mclass.getVarSize()), nCurrentTab);
      for (MVar mvar : mclass.getVarSet()) {
    	  MPiglet st2_1 = new MPiglet("HSTORE " + temp2 + " " + mvar.getOffset(), nCurrentTab);
    	  st2_1.append(" 0");
    	  st2.append(st2_1);
      }
      MPiglet st3 = new MPiglet("HSTORE " + temp2 + " 0 " + temp1, nCurrentTab);
      MPiglet st4 = new MPiglet("RETURN " + temp2, nCurrentTab);
      _ret.append(st1);
      _ret.append(st2);
      _ret.append(st3);
      _ret.append(st4);
      addEnd(_ret);
      
	  return _ret;
   }

   /**
    * f0 -> "!"
    * f1 -> Expression()
    */
   public MPiglet visit(NotExpression n, MIdentifier argu) {
      MPiglet _ret = new MPiglet("MINUS 1");
      _ret.append(n.f1.accept(this, argu));
      
      return _ret;
   }

   /**
    * f0 -> "("
    * f1 -> Expression()
    * f2 -> ")"
    */
   public MPiglet visit(BracketExpression n, MIdentifier argu) {
      MPiglet _ret = n.f1.accept(this, argu);
      return _ret;
   }

}
