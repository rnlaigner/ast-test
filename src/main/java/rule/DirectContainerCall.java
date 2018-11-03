package rule;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;

import model.ContainerClassType;
import model.Element;
import model.ElementResult;
import model.VariableDeclarationElement;

public class DirectContainerCall extends AbstractMethodCallVisitor {
	
	Integer containerCallCount = 0;
	
	private boolean isContainerCall(String methodCall) {
		return ContainerClassType.SPRING.getContainerCall().equals(methodCall) || 
				ContainerClassType.CDI.getContainerCall().equals(methodCall);
	}

	@Override
	protected void visitMethodCallImpl(MethodCallExpr methodCall, Element arg) {
		
		VariableDeclarationElement arg_ = (VariableDeclarationElement) arg;
		
		NameExpr nameNode = (NameExpr) methodCall.getScope().get();
		
		if(!nameNode.getName().getIdentifier().equals(arg_.getVariableName())) return;
		
		String methodCallStr = getMethodCall(methodCall);
    	
		Boolean isContainerCall = isContainerCall( methodCallStr );
		
		if ( isContainerCall ) containerCallCount++;		
		
	}

	@Override
	public ElementResult processRule(CompilationUnit cu, Element element) {
		
		visit(cu,element);
		
		ElementResult result = new ElementResult();
		
		result.addElement(element);
		
		result.setResult(false);
		
		if(containerCallCount > 0) result.setResult(true);
		
        return result;
		
	}

}
