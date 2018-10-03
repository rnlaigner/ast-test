package rule;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import model.Element;

public abstract class AbstractMethodVisitor extends AbstractRule {
	
	
	protected MethodVisitor methodVisitor;
	
	public AbstractMethodVisitor(){
		methodVisitor = new MethodVisitor();
	}
	
	protected void visit(CompilationUnit cu, List<Element> elements) {
		methodVisitor.visit(cu, elements);
	}
	
	protected abstract void visitImpl(MethodCallExpr methodCall, List<Element> arg);
	
	private class MethodVisitor extends VoidVisitorAdapter<List<Element>> {
		
		
		@Override
	    public void visit(MethodCallExpr methodCall, List<Element> arg)
	    {
	        visitImpl(methodCall, arg);
	    }
		
	}
	

	
	
}
