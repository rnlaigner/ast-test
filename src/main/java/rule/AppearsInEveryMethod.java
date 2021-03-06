package rule;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;

import model.Element;
import model.ElementResult;

public class AppearsInEveryMethod extends AbstractMethodCallVisitor {

	private Integer numberOfAppearances = 0;
	private Integer numberOfMethods = 0;

	@Override
	public ElementResult processRule(CompilationUnit cu, Element element) {
		
		visit(cu,element);
		
		ElementResult result = new ElementResult();
		
		result.addElement(element);
		
		result.setResult(false);
		
		if(numberOfAppearances == numberOfMethods) result.setResult(true);
		
        return result;
	}
	
	
	@Override
	protected void visitMethodCallImpl(MethodCallExpr methodCall, Element arg) {
		
    	String nodeName = getNodeName(methodCall);

    	numberOfMethods++;
    	
		Boolean itDoesAppear = doesItAppear( nodeName, arg );
		
		if (itDoesAppear) numberOfAppearances++;
		
	}
	
	
}
