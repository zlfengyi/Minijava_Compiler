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

   //检查类型type是否已定义， type可能的情况：
   // MIdentifer, 自定义的一个class
   // MType, 基本类型 int, int[], boolean
   // 未定义输出错误信息
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
   // %如果exp没有类型，表明变量没有定义
   //如果exp不等于目标类型，输出相应的错误信息
   public void checkExpEqual(MType exp, String target, String errMsg) {
	   if (exp == null) {
		   System.err.println("exp is null in checkTypeEqual!!!");
		   return;
	   }
	/*   
	   if (exp.getType() == null) {
		   ErrorPrinter.print(exp.getLine(), exp.getColumn(), 
				   "Undefined variable \'" + exp.getName() + "\'");
		   return;
	   } else
	*/
	   
	   if (target != null) {
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
   
   private int paraNum = -1;
   private MMethod callingMethod = null;
   
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
    	  // 循环继承检测
    	  HashSet<String> baseNameSet = new HashSet<String>();
    	  
    	  while (baseClassName != null) {
    		  if (baseClassName.equals(newClass.getName())) {
    			  ErrorPrinter.print(id.getLine(), id.getColumn(),
    					  "class " + baseClassName + " circular extends itself");
    			  break;
    		  } else if (baseNameSet.contains(baseClassName)) {
    			  break;
    		  }
    		  
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
      
      MType exp = n.f10.accept(this, newMethod);
      checkExpEqual(exp, type.getType(), "Return expression doesn't match return type");
      
      n.f11.accept(this, newMethod);
      n.f12.accept(this, newMethod);
      
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
      // 基本类型返回 MType
      // 自定义类返回 MIdentifer，需要手动设置这个Midentifer的类型为自身名字
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
    	  MType exp = n.f2.accept(this, argu);
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
	  
	  MType exp1 = n.f2.accept(this, argu);
      checkExpEqual(exp1, "int", "Index is not an Integer");
      
      MType exp2 = n.f5.accept(this, argu);
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
      
      MType exp = n.f2.accept(this, argu);
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
      
      MType exp = n.f2.accept(this, argu);
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
      
      MType exp = n.f2.accept(this, argu);
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
	   MType _ret = n.f0.accept(this, argu);
	   if (this.paraNum >= 0) {
	//	 System.out.println(this.callingMethod.getName() + ":  " +_ret.getType());				
			
		   MVar param = callingMethod.getParam(this.paraNum++);
		   if (param == null) {
			 System.out.println(this.callingMethod.getName() + ": " + this.paraNum);
			   ErrorPrinter.print(_ret.getLine(), _ret.getColumn(), 
					   " parameter number in calling method " + this.callingMethod.getName()
					   + " doesn't match expected number");
		   } else {
			   checkExpEqual(_ret, param.getType(), 
					   _ret.getType() 
					   + " in calling method " + this.callingMethod.getName()
					   + " doesn't match expected parameter type "
					   + param.getType());
		   }/*
			   if (!param.getType().equals(_ret.getType())) {
	//System.out.println(((MIdentifier)_ret).getName());				
//	System.out.println(this.callingMethod.getName());
			   ErrorPrinter.print(_ret.getLine(), _ret.getColumn(), 
					   _ret.getType() + " in MessageSend doesn't match expected type " + param.getType());
		   }*/
		   
	   }
	   return _ret;
   }

   /**
	* f0 -> PrimaryExpression()
	* f1 -> "&&"
	* f2 -> PrimaryExpression()
	*/
   public MType visit(AndExpression n, MType argu) {
      MType exp1 = n.f0.accept(this, argu);
      MType exp2 = n.f2.accept(this, argu);
      checkExpEqual(exp1, "boolean", "Left expression of '&&' is not a boolean");
      checkExpEqual(exp2, "boolean", "Right expression of '&&' is not a boolean");
      
      return new MType("boolean", exp1.getLine(), exp1.getLine());
   }

   /**
	* f0 -> PrimaryExpression()
	* f1 -> "<"
	* f2 -> PrimaryExpression()
	*/
   public MType visit(CompareExpression n, MType argu) {
	  MType exp1 = n.f0.accept(this, argu);
      MType exp2 = n.f2.accept(this, argu);
      checkExpEqual(exp1, "int", "Left expression of '<' is not a int");
      checkExpEqual(exp2, "int", "Right expression of '<' is not a int");
      
      return new MType("boolean", exp1.getLine(), exp1.getLine());
   }

   /**
	* f0 -> PrimaryExpression()
	* f1 -> "+"
	* f2 -> PrimaryExpression()
	*/
   public MType visit(PlusExpression n, MType argu) {
	  MType exp1 = n.f0.accept(this, argu);
      MType exp2 = n.f2.accept(this, argu);
      checkExpEqual(exp1, "int", "Left expression of '+' is not a int");
      checkExpEqual(exp2, "int", "Right expression of '+' is not a int");
      
      return new MType("int", exp1.getLine(), exp1.getLine());
   }

   /**
	* f0 -> PrimaryExpression()
	* f1 -> "-"
	* f2 -> PrimaryExpression()
	*/
   public MType visit(MinusExpression n, MType argu) {
	  MType exp1 = n.f0.accept(this, argu);
      MType exp2 = n.f2.accept(this, argu);
      checkExpEqual(exp1, "int", "Left expression of '-' is not a int");
      checkExpEqual(exp2, "int", "Right expression of '-' is not a int");
      
      return new MType("int", exp1.getLine(), exp1.getLine());
   }

   /**
	* f0 -> PrimaryExpression()
	* f1 -> "*"
	* f2 -> PrimaryExpression()
	*/
   public MType visit(TimesExpression n, MType argu) {
	  MType exp1 = n.f0.accept(this, argu);
      MType exp2 = n.f2.accept(this, argu);
      checkExpEqual(exp1, "int", "Left expression of '*' is not a int");
      checkExpEqual(exp2, "int", "Right expression of '*' is not a int");
      
      return new MType("int", exp1.getLine(), exp1.getLine());
   }

   /**
	* f0 -> PrimaryExpression()
	* f1 -> "["
	* f2 -> PrimaryExpression()
	* f3 -> "]"
	*/
   public MType visit(ArrayLookup n, MType argu) {
      MType id = n.f0.accept(this, argu);
     
      /*
      if (exp1 == null) {
    	  ErrorPrinter.print(id.getLine(), id.getColumn(), 
    			  "Undefined variable \'" + id.getName() + "\'");
      }
      */
      
      MType exp2 = n.f2.accept(this, argu);
      checkExpEqual(id, "int[]", "Left expression of '[' is not an array");
      checkExpEqual(exp2, "int", "INdex is not an integer");
      
      return new MType("int", id.getLine(), id.getLine());
   }

   /**
	* f0 -> PrimaryExpression()
	* f1 -> "."
	* f2 -> "length"
	*/
   public MType visit(ArrayLength n, MType argu) {
      MType exp1 = n.f0.accept(this, argu);
      checkExpEqual(exp1, "int[]", "Left expression of '[' is not an array");

      return new MType("int", exp1.getLine(), exp1.getLine());
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
      
      int tmpParaNum2 = this.paraNum;
	  this.paraNum = -1;
	  MType id = n.f0.accept(this, argu);
	  this.paraNum = tmpParaNum2;
      
      
      String methodName = ((MIdentifier)n.f2.accept(this, argu)).getName();
      String className = "";
          
      if (id instanceof  MIdentifier) {
    	  MVar var = ((VarContainer)argu).getVar(((MIdentifier)id).getName());
    	  if (var == null) {
        	  ErrorPrinter.print(id.getLine(), id.getColumn(), 
        			  "Undefined variable \'" + ((MIdentifier)id).getName() + "\'");
        	  
        	  return new MType("undefined type", id.getLine(), id.getColumn());
    	  } else {
    		  className = var.getType();
    	  }
      } else {
    	  className = id.getType();
      }
      
      MClass mclass = allClassesList.getClass(className);
      if (mclass == null) {
    	  ErrorPrinter.print(id.getLine(), id.getColumn(), 
    			  "Undefined class \'" + className + "\'");
    	  
    	  return new MType("undefined type", id.getLine(), id.getColumn());	  
      }
      
	  MMethod method = (mclass).getMethod(methodName);
	  if (method == null) {
    	  ErrorPrinter.print(id.getLine(), id.getColumn(), 
    			  "Undefined method \'" + methodName + "\' in class " + className);
    	  
    	  return new MType("undefined type", id.getLine(), id.getColumn());	  		  
	  }
	  
	  _ret = new MType(method.getReturnType(), id.getLine(), id.getColumn());
   
	  int tmpParaNum = this.paraNum;
	  MMethod tmpMethod = this.callingMethod;
      this.paraNum = 0;
      this.callingMethod = method;
      
	  n.f4.accept(this, argu);
	  if (this.paraNum < method.getParamSize()) {
		  ErrorPrinter.print(_ret.getLine(), _ret.getColumn(), 
			   " Number of param in MessageSend doesn't match ");
	  }
	  
      this.paraNum = tmpParaNum;
      this.callingMethod = tmpMethod;
      
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
   // 只有identifier返回 MIdentifier，其他均返回MType
   public MType visit(PrimaryExpression n, MType argu) {
      MType _ret = n.f0.accept(this, argu);
      
      //如果返回类型是Identifer，在PrimaryExpression中只可能是变量名，
      //找到该变量，并且返回

      if (_ret instanceof MIdentifier) {
    	  MVar newVar = ((VarContainer)argu).getVar(((MIdentifier)_ret).getName());
    	  if (newVar == null) {
    		  ErrorPrinter.print(_ret.getLine(), _ret.getColumn(), 
   				   "Undefined variable \'" + ((MIdentifier)_ret).getName() + "\'");
    		  ((MIdentifier)_ret).setType("undefined type");
    	  } else {
    		  ((MIdentifier)_ret).setType(newVar.getType());
    	  }
      }
      
      return _ret;
   }

   /**
	* f0 -> <INTEGER_LITERAL>
	*/
   public MType visit(IntegerLiteral n, MType argu) {
	   return new MType("int", n.f0.beginLine, n.f0.beginColumn);
   }

   /**
	* f0 -> "true"
	*/
   public MType visit(TrueLiteral n, MType argu) {
	   return new MType("boolean", n.f0.beginLine, n.f0.beginColumn);
   }

   /**
	* f0 -> "false"
	*/
   public MType visit(FalseLiteral n, MType argu) {
	   return new MType("boolean", n.f0.beginLine, n.f0.beginColumn);
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
      
      if (!(parent instanceof MClass)) {
    	  System.err.println(parent.getName() + " is not an MClass");
      }
      /*
      while (parent != null && !(parent instanceof MClass)) {
    	  parent = parent.getParent();
      }
	  */
      
      return new MType(parent.getName(), n.f0.beginLine, n.f0.beginColumn);
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
      
      MType exp = n.f3.accept(this, argu);
      checkExpEqual(exp, "int", "Array length is not an integer");
      
      n.f4.accept(this, argu);
   
      return new MType("int[]", n.f0.beginLine, n.f0.beginColumn);
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
      _ret = new MType(id.getName(), id.getLine(), id.getColumn());
      
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      return _ret;
   }

   /**
	* f0 -> "!"
	* f1 -> Expression()
	*/
   public MType visit(NotExpression n, MType argu) {
	  MType exp = n.f1.accept(this, argu);
      checkExpEqual(exp, "boolean", "right expression is not a boolean");
      
      return new MType("boolean", exp.getLine(), exp.getColumn());
   }

   /**
	* f0 -> "("
	* f1 -> Expression()
	* f2 -> ")"
	*/
   public MType visit(BracketExpression n, MType argu) {
      MType _ret = n.f1.accept(this, argu);
      
      return _ret;
   }

}
