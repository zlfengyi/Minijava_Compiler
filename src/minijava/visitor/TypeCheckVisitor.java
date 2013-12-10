package minijava.visitor;

import java.util.Enumeration;
import java.util.HashSet;

import minijava.symboltable.*;
import minijava.syntaxtree.*;
import minijava.typecheck.ErrorPrinter;

public class TypeCheckVisitor extends GJDepthFirst<MType, MType> {
	MClassList allClassesList;
	
   //
   // Auto class visitors--probably don't need to be overridden.
   //
   public MType visit(NodeList n, MType argu) {
      MType _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
      return _ret;
   }

   public MType visit(NodeListOptional n, MType argu) {
      if ( n.present() ) {
         MType _ret=null;
         int _count=0;
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this,argu);
            _count++;
         }
         return _ret;
      }
      else
         return null;
   }

   public MType visit(NodeOptional n, MType argu) {
      if ( n.present() )
         return n.node.accept(this,argu);
      else
         return null;
   }

   public MType visit(NodeSequence n, MType argu) {
      MType _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
      return _ret;
   }

   public MType visit(NodeToken n, MType argu) { return null; }
  
   //
   // User-generated visitor methods below
   //

   //检查类型type是否已定义，未定义输出错误信息
   public void checkTypeDeclared(MType type) {
	   String typeName;
	   
	   if (type instanceof MIdentifier) {
		   typeName = ((MIdentifier)type).getName();
		   if (allClassesList.containClass(typeName)) return;
	   } else {
		   typeName = type.getType();
		   if (typeName.equals("int") || typeName.equals("int[]") 
				   || typeName.equals("boolean") ) return;
	   }
	   
	   ErrorPrinter.print(type.getLine(), type.getColumn(),
			   			"Undefined type \'" + typeName + "\'");
   }
   
   //判断表达式exp是否为指定的类型，
   //如果exp没有类型，表明变量没有定义
   //如果exp不等于目标类型，输出相应的错误信息
   public void checkExpEqual(MIdentifier exp, String target, String errMsg) {
	   if (exp == null) {
		   System.err.println("exp is null in checkTypeEqual!!!");
		   return;
	   }
	   
	   if (exp.getType() == null) {
		   ErrorPrinter.print(exp.getLine(), exp.getColumn(), 
				   "Undefined variable \'" + exp.getName() + "\'");
		   return;
	   } else if (target != null) {
		   if (!allClassesList.classEqualsOrDerives(exp.getType(), target)
				   && !exp.getType().equals(target)) {
			   ErrorPrinter.print(exp.getLine(), exp.getColumn(), errMsg);
		   }
	   }
   }
   
   
   //判断变量newVar是否等于指定的类型, 可能出现的情况：
   //1. 变量在符号表中未定义：newVar == null
   //2. newVar不等于指定的类型
   public boolean checkVarEqual(MIdentifier id, MVar newVar, String target, String errMsg) {
	   if (newVar == null) {
		   ErrorPrinter.print(id.getLine(), id.getColumn(), 
				   "Undefined variable \'" + id.getName() + "\'");
		   return false;
	   } else if (target != null) {
		   if (!newVar.getType().equals(target) 
				   && !allClassesList.classEqualsOrDerives(newVar.getName(), target)) {
			   ErrorPrinter.print(id.getLine(), id.getColumn(), errMsg);
			   return false;
		   }
	   }
	   return true;
   }
   
   //----------------------- start visit here -------------------------//
   /**
    * f0 -> MainClass()
    * f1 -> ( TypeDeclaration() )*
    * f2 -> <EOF>
    */
   public MType visit(Goal n, MType argu) {
      MType _ret=null;
      
      allClassesList = (MClassList)argu;
      n.f0.accept(this, allClassesList);
      n.f1.accept(this, allClassesList);
      n.f2.accept(this, allClassesList);
      
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
   
   public MType visit(MainClass n, MType argu) {
      MType _ret=null;
      
      n.f0.accept(this, argu);
      MIdentifier id = (MIdentifier)n.f1.accept(this, argu);
      MClass newClass = allClassesList.getClass(id.getName());
      
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      
      MMethod newMethod = newClass.getMethod("main");
      
      n.f7.accept(this, newMethod);
      n.f8.accept(this, newMethod);
      n.f9.accept(this, newMethod);
      n.f10.accept(this, newMethod);
      n.f11.accept(this, newMethod);
      n.f12.accept(this, newMethod);
      n.f13.accept(this, newMethod);
      n.f14.accept(this, newMethod);
      n.f15.accept(this, newMethod);
      n.f16.accept(this, newMethod);
      
      return _ret;
   }

   /**
	* f0 -> ClassDeclaration()
	*       | ClassExtendsDeclaration()
	*/
   public MType visit(TypeDeclaration n, MType argu) {
      return n.f0.accept(this, argu);
   }

   /**
	* f0 -> "class"
	* f1 -> Identifier()
	* f2 -> "{"
	* f3 -> ( VarDeclaration() )*
	* f4 -> ( MethodDeclaration() )*
	* f5 -> "}"
	*/
   public MType visit(ClassDeclaration n, MType argu) {
      MType _ret=null;
      
      n.f0.accept(this, argu);
      
      MIdentifier id = (MIdentifier)n.f1.accept(this, argu);
      MClass newClass = allClassesList.getClass(id.getName());
 
      n.f2.accept(this, newClass);
      n.f3.accept(this, newClass);
      n.f4.accept(this, newClass);
      n.f5.accept(this, newClass);
      
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
   public MType visit(ClassExtendsDeclaration n, MType argu) {
      MType _ret=null;
      n.f0.accept(this, argu);
      
      MIdentifier id = (MIdentifier)n.f1.accept(this, argu);
      MClass newClass = allClassesList.getClass(id.getName());
      
      n.f2.accept(this, newClass);
      n.f3.accept(this, newClass);
      
      String baseClassName = newClass.getBaseClassName();
      if (!allClassesList.containClass(baseClassName)) {
    	  ErrorPrinter.print(id.getLine(), id.getColumn(),
    			  "Base class doesn't declarated \'" + baseClassName + "\'");
      } else {
    	  HashSet<String> baseNameSet = new HashSet<String>();
    	  baseNameSet.add(baseClassName);
    	  while (baseClassName != null) {
    		  if (baseClassName.equals(newClass.getName())) {
    			  ErrorPrinter.print(id.getLine(), id.getColumn(),
    					  "Base class circular extends \'" + newClass.getName() + "\'");
    			  break;
    		  } else if (baseNameSet.contains(baseClassName)) break;
    		  
    		  baseNameSet.add(baseClassName);
    		  MClass baseClass = allClassesList.getClass(baseClassName);
    		  if (baseClass != null) baseClassName = baseClass.getBaseClassName();
    		  else break;
    	  }
      }
      
      n.f4.accept(this, newClass);
      n.f5.accept(this, newClass);
      n.f6.accept(this, newClass);
      n.f7.accept(this, newClass);
      
      return _ret;
   }

   /**
	* f0 -> Type()
	* f1 -> Identifier()
	* f2 -> ";"
	*/
   public MType visit(VarDeclaration n, MType argu) {
      MType _ret=null;
      
      MType type = n.f0.accept(this, argu);
      this.checkTypeDeclared(type);
      
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
   public MType visit(MethodDeclaration n, MType argu) {
      MType _ret=null;
      
      n.f0.accept(this, argu);
      MType type = n.f1.accept(this, argu);
      checkTypeDeclared(type);
      
      MIdentifier id = (MIdentifier)n.f2.accept(this, argu);
      MMethod newMethod = ((MClass)argu).getMethod(id.getName());
      
      n.f3.accept(this, newMethod);
      n.f4.accept(this, newMethod);
      n.f5.accept(this, newMethod);
      n.f6.accept(this, newMethod);
      n.f7.accept(this, newMethod);
      n.f8.accept(this, newMethod);
      n.f9.accept(this, newMethod);
      
      n.f10.accept(this, argu);
      n.f11.accept(this, argu);
      n.f12.accept(this, argu);
      return _ret;
   }

   /**
	* f0 -> FormalParameter()
	* f1 -> ( FormalParameterRest() )*
	*/
   public MType visit(FormalParameterList n, MType argu) {
      MType _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    */
   public MType visit(FormalParameter n, MType argu) {
      MType _ret=null;
      
      MType type = n.f0.accept(this, argu);
      this.checkTypeDeclared(type);
      
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
	* f0 -> ","
	* f1 -> FormalParameter()
	*/
   public MType visit(FormalParameterRest n, MType argu) {
      MType _ret=null;
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
   public MType visit(Type n, MType argu) {
      MType ret = n.f0.accept(this, argu);
      if (ret instanceof MIdentifier) {
    	  ret.setType(((MIdentifier) ret).getName());
      }   
      return ret;
   }

   /**
	* f0 -> "int"
	* f1 -> "["
	* f2 -> "]"
	*/
   public MType visit(ArrayType n, MType argu) {
	   return new MType("int[]", n.f0.beginLine, n.f0.beginColumn);
   }

   /**
	* f0 -> "boolean"
	*/
   public MType visit(BooleanType n, MType argu) {
	   return new MType("boolean", n.f0.beginLine, n.f0.beginColumn);
   }

   /**
	* f0 -> "int"
	*/
   public MType visit(IntegerType n, MType argu) {
      return new MType("int", n.f0.beginLine, n.f0.beginColumn);
   }

   /**
	* f0 -> Block()
	*       | AssignmentStatement()
	*       | ArrayAssignmentStatement()
	*       | IfStatement()
	*       | WhileStatement()
	*       | PrintStatement()
	*/
   public MType visit(Statement n, MType argu) {
      MType _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
	* f0 -> "{"
	* f1 -> ( Statement() )*
	* f2 -> "}"
	*/
   public MType visit(Block n, MType argu) {
      MType _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
	* f0 -> Identifier()
	* f1 -> "="
	* f2 -> Expression()
	* f3 -> ";"
	*/
   public MType visit(AssignmentStatement n, MType argu) {
	   
      MIdentifier id = (MIdentifier)n.f0.accept(this, argu);
      MVar newVar = ((VarContainer)argu).getVar(id.getName());
   
      if (newVar == null) {
    	  ErrorPrinter.print(id.getLine(), id.getColumn(), 
    			  "Undefined variable \'" + id.getName() + "\'");
      } else {
    	  MIdentifier exp = (MIdentifier)n.f2.accept(this, argu);
    	  checkExpEqual(exp, newVar.getType(), 
    			  "Type mismatch \'" + id.getName() + " = " + exp.getType() + "\'");
      }
      return null;
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
   public MType visit(ArrayAssignmentStatement n, MType argu) {
      
	  MIdentifier id = (MIdentifier)n.f0.accept(this, argu);
	  MVar newVar = ((VarContainer) argu).getVar(id.getName());
	  checkVarEqual(id, newVar, "int[]", "Not an array \'" + id.getName() + "\'");
	  
	  n.f1.accept(this, argu);
      MIdentifier exp1 = (MIdentifier)n.f2.accept(this, argu);
      checkExpEqual(exp1, "int", "Index is not an Integer");
      
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      
      MIdentifier exp2 = (MIdentifier)n.f5.accept(this, argu);
      checkExpEqual(exp2, "int", "Type mismatch, assignment value is not an Integer" );
      
      n.f6.accept(this, argu);
      return null;
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
   public MType visit(IfStatement n, MType argu) {
      MType _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      
      MIdentifier exp = (MIdentifier)n.f2.accept(this, argu);
      checkExpEqual(exp, "boolean", "Condition expression is not a boolean");
      
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      return _ret;
   }

   /**
	* f0 -> "while"
	* f1 -> "("
	* f2 -> Expression()
	* f3 -> ")"
	* f4 -> Statement()
	*/
   public MType visit(WhileStatement n, MType argu) {
      MType _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      
      MIdentifier exp = (MIdentifier)n.f2.accept(this, argu);
      checkExpEqual(exp, "boolean", "Condition expression is not a boolean");
      
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      return _ret;
   }

   /**
	* f0 -> "System.out.println"
	* f1 -> "("
	* f2 -> Expression()
	* f3 -> ")"
	* f4 -> ";"
	*/
   public MType visit(PrintStatement n, MType argu) {
      MType _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      
      MIdentifier exp = (MIdentifier)n.f2.accept(this, argu);
      checkExpEqual(exp, "int", "Unable to print a non-digital expression");
      
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
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
   public MType visit(Expression n, MType argu) {
      return n.f0.accept(this, argu);
   }

   /**
	* f0 -> PrimaryExpression()
	* f1 -> "&&"
	* f2 -> PrimaryExpression()
	*/
   public MType visit(AndExpression n, MType argu) {
      MIdentifier exp1 = (MIdentifier)n.f0.accept(this, argu);
      MIdentifier exp2 = (MIdentifier)n.f2.accept(this, argu);
      checkExpEqual(exp1, "boolean", "Left expression of '&&' is not a boolean");
      checkExpEqual(exp2, "boolean", "Right expression of '&&' is not a boolean");
      
      return new MIdentifier(null, "boolean", exp1.getLine(), exp1.getLine());
   }

   /**
	* f0 -> PrimaryExpression()
	* f1 -> "<"
	* f2 -> PrimaryExpression()
	*/
   public MType visit(CompareExpression n, MType argu) {
	  MIdentifier exp1 = (MIdentifier)n.f0.accept(this, argu);
      MIdentifier exp2 = (MIdentifier)n.f2.accept(this, argu);
      checkExpEqual(exp1, "int", "Left expression of '<' is not a int");
      checkExpEqual(exp2, "int", "Right expression of '<' is not a int");
      
      return new MIdentifier(null, "boolean", exp1.getLine(), exp1.getLine());
   }

   /**
	* f0 -> PrimaryExpression()
	* f1 -> "+"
	* f2 -> PrimaryExpression()
	*/
   public MType visit(PlusExpression n, MType argu) {
	  MIdentifier exp1 = (MIdentifier)n.f0.accept(this, argu);
      MIdentifier exp2 = (MIdentifier)n.f2.accept(this, argu);
      checkExpEqual(exp1, "int", "Left expression of '+' is not a int");
      checkExpEqual(exp2, "int", "Right expression of '+' is not a int");
      
      return new MIdentifier(null, "int", exp1.getLine(), exp1.getLine());
   }

   /**
	* f0 -> PrimaryExpression()
	* f1 -> "-"
	* f2 -> PrimaryExpression()
	*/
   public MType visit(MinusExpression n, MType argu) {
	  MIdentifier exp1 = (MIdentifier)n.f0.accept(this, argu);
      MIdentifier exp2 = (MIdentifier)n.f2.accept(this, argu);
      checkExpEqual(exp1, "int", "Left expression of '-' is not a int");
      checkExpEqual(exp2, "int", "Right expression of '-' is not a int");
      
      return new MIdentifier(null, "int", exp1.getLine(), exp1.getLine());
   }

   /**
	* f0 -> PrimaryExpression()
	* f1 -> "*"
	* f2 -> PrimaryExpression()
	*/
   public MType visit(TimesExpression n, MType argu) {
	  MIdentifier exp1 = (MIdentifier)n.f0.accept(this, argu);
      MIdentifier exp2 = (MIdentifier)n.f2.accept(this, argu);
      checkExpEqual(exp1, "int", "Left expression of '*' is not a int");
      checkExpEqual(exp2, "int", "Right expression of '*' is not a int");
      
      return new MIdentifier(null, "int", exp1.getLine(), exp1.getLine());
   }

   /**
	* f0 -> PrimaryExpression()
	* f1 -> "["
	* f2 -> PrimaryExpression()
	* f3 -> "]"
	*/
   public MType visit(ArrayLookup n, MType argu) {
      MIdentifier exp1 = (MIdentifier)n.f0.accept(this, argu);
      MIdentifier exp2 = (MIdentifier)n.f2.accept(this, argu);
      checkExpEqual(exp1, "int[]", "Left expression of '[' is not an array");
      checkExpEqual(exp2, "int", "INdex is not an integer");
      
      return new MIdentifier(null, "int", exp1.getLine(), exp1.getLine());
   }

   /**
	* f0 -> PrimaryExpression()
	* f1 -> "."
	* f2 -> "length"
	*/
   public MType visit(ArrayLength n, MType argu) {
      MIdentifier exp1 = (MIdentifier)n.f0.accept(this, argu);
      checkExpEqual(exp1, "int[]", "Left expression of '[' is not an array");

      return new MIdentifier(null, "int", exp1.getLine(), exp1.getLine());
   }

   /**
* f0 -> PrimaryExpression()
* f1 -> "."
* f2 -> Identifier()
* f3 -> "("
* f4 -> ( ExpressionList() )?
* f5 -> ")"
*/
   public MType visit(MessageSend n, MType argu) {
      MType _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      return _ret;
   }

   /**
* f0 -> Expression()
* f1 -> ( ExpressionRest() )*
*/
   public MType visit(ExpressionList n, MType argu) {
      MType _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
	* f0 -> ","
	* f1 -> Expression()
	*/
   public MType visit(ExpressionRest n, MType argu) {
      MType _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
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
   public MType visit(PrimaryExpression n, MType argu) {
      MIdentifier id = (MIdentifier)n.f0.accept(this, argu);
      
      //如果没有type，表明是一个已经声明的变量(indentifier)，在所在的方法中找到相应的变量
      //返回该变量
      if (id != null && id.getType() == null && id.getName() != null) {
    	  MVar newVar = ((VarContainer)argu).getVar(id.getName());
    	  if (newVar != null) id = newVar;
      }
      return id;
   }

   /**
	* f0 -> <INTEGER_LITERAL>
	*/
   public MType visit(IntegerLiteral n, MType argu) {
	   return new MIdentifier(n.f0.tokenImage, "int", n.f0.beginLine, n.f0.beginColumn);
   }

   /**
	* f0 -> "true"
	*/
   public MType visit(TrueLiteral n, MType argu) {
	   return new MIdentifier(n.f0.tokenImage, "boolean", n.f0.beginLine, n.f0.beginColumn);
   }

   /**
	* f0 -> "false"
	*/
   public MType visit(FalseLiteral n, MType argu) {
	   return new MIdentifier(n.f0.tokenImage, "boolean", n.f0.beginLine, n.f0.beginColumn);
   }

   /**
	* f0 -> <IDENTIFIER>
	*/
   public MIdentifier visit(Identifier n, MType argu) {
     String identifier_name = n.f0.toString();
     MIdentifier _ret = null;
     _ret = new MIdentifier(identifier_name, null, n.f0.beginLine, n.f0.beginColumn);
     return _ret;
   }

   /**
    * f0 -> "this"
    */
   public MType visit(ThisExpression n, MType argu) {
      MIdentifier parent = ((MIdentifier) argu).getParent();
      
      while (parent != null && !(parent instanceof MClass)) {
    	  parent = parent.getParent();
      }
	  
      return new MIdentifier("this", parent.getName(), n.f0.beginLine, n.f0.beginColumn);
   }

   /**
	* f0 -> "new"
	* f1 -> "int"
	* f2 -> "["
	* f3 -> Expression()
	* f4 -> "]"
	*/
   public MType visit(ArrayAllocationExpression n, MType argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      
      MIdentifier exp = (MIdentifier)n.f3.accept(this, argu);
      checkExpEqual(exp, "int", "Array length is not an integer");
      
      n.f4.accept(this, argu);
   
      return new MIdentifier(null, "int[]", n.f0.beginLine, n.f0.beginColumn);
   }

   /**
	* f0 -> "new"
	* f1 -> Identifier()
	* f2 -> "("
	* f3 -> ")"
	*/
   public MType visit(AllocationExpression n, MType argu) {
      MType _ret=null;
      n.f0.accept(this, argu);
      
      MIdentifier id = (MIdentifier)n.f1.accept(this, argu);
      checkTypeDeclared(id);
      _ret = new MIdentifier(null, id.getName(), id.getLine(), id.getColumn());
      
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      return _ret;
   }

   /**
	* f0 -> "!"
	* f1 -> Expression()
	*/
   public MType visit(NotExpression n, MType argu) {
      MIdentifier exp = (MIdentifier)n.f1.accept(this, argu);
      checkExpEqual(exp, "boolean", "Condition expression is not a boolean");
      
      return new MIdentifier(null, "boolean", exp.getLine(), exp.getColumn());
   }

   /**
	* f0 -> "("
	* f1 -> Expression()
	* f2 -> ")"
	*/
   public MType visit(BracketExpression n, MType argu) {
      MIdentifier exp = (MIdentifier)n.f1.accept(this, argu);
      checkExpEqual(exp, null, null);
      
      return exp;
   }

}
