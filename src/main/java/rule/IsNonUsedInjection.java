package rule;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;

import model.Element;
import model.ElementResult;

public class IsNonUsedInjection extends AbstractMethodVisitor {

	@Override
	public ElementResult processRule(CompilationUnit cu, Element element) {
		visit(cu,element);
		
        return new ElementResult();
	}

	@Override
	protected void visitImpl(MethodCallExpr methodCall, Element arg) {
        
		//This rule is just an subset of appearsineverymethod ...
    		
	}



}
