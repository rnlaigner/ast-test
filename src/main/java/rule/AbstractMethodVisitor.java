package rule;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import model.Element;

public abstract class AbstractMethodVisitor extends AbstractRule {
	
	
	protected MethodVisitor methodVisitor;
	
	public AbstractMethodVisitor(){
		methodVisitor = new MethodVisitor();
	}
	
	protected void visit(CompilationUnit cu, Element element) {
		methodVisitor.visit(cu, element);
	}
	
	protected abstract void visitImpl(MethodCallExpr methodCall, Element arg);
	
	private class MethodVisitor extends VoidVisitorAdapter<Element> {
		
		
		@Override
	    public void visit(MethodCallExpr methodCall, Element arg)
	    {
	        visitImpl(methodCall, arg);
	    }
		
	}

	protected boolean doesItAppear(String nodeName, Element element)
    {
    	return element.getName().equals(nodeName);
    }
	
	protected String getNodeName(MethodCallExpr methodCall)
	{
		return methodCall.getChildNodes().get(0).toString();
	}
	
}
