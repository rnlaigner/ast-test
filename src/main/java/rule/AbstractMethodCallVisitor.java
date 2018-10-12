package rule;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import model.Element;

public abstract class AbstractMethodCallVisitor extends AbstractRule {
	
	protected MethodCallVisitor methodCallVisitor;
	
	public AbstractMethodCallVisitor(){
		methodCallVisitor = new MethodCallVisitor();
	}
	
	protected void visit(CompilationUnit cu, Element element) {
		methodCallVisitor.visit(cu, element);
	}
	
	protected abstract void visitImpl(MethodCallExpr methodCall, Element arg);
	
	private class MethodCallVisitor extends VoidVisitorAdapter<Element> {
		
		
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
	
	protected String getMethodCall(MethodCallExpr methodCall) {
		return methodCall.getName().getIdentifier();
	}
	
}
