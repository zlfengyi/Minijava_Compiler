/**
 * 用于类型检查的主函数入口
 */
package minijava.typecheck;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import minijava.MiniJavaParser;
import minijava.ParseException;
import minijava.TokenMgrError;
import minijava.symboltable.MClassList;
import minijava.symboltable.MType;
import minijava.syntaxtree.Node;
import minijava.visitor.BuildSymbolTableVisitor;
import minijava.visitor.TypeCheckVisitor;

public class Main {

	public static void main(String[] args) {

		try {
		
			InputStream is = new FileInputStream("/Users/zlfengyi/Desktop/compiler_fy/task1/test/code.java");
			Node root = new MiniJavaParser(is).Goal();
			//Node root = new MiniJavaParser(System.in).Goal();
    		
			// 初始化符号表中最大的类
			MType allClassesList = new MClassList();

			// 遍历抽象语法树，建立符号表，检查是否重复定义
			root.accept(new BuildSymbolTableVisitor(), allClassesList);
			
			// 再次遍历语法树，检查类型错误
			root.accept(new TypeCheckVisitor(), allClassesList);
			
			// 打印错误信息
			ErrorPrinter.printAll();
			
			System.out.println("type check done!");
		} catch (TokenMgrError e) {

			// Handle Lexical Errors
			e.printStackTrace();
		} catch (ParseException e) {

			// Handle Grammar Errors
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}