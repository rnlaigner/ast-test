package rule;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;

import model.Element;

public class AppearsInEveryMethod extends AbstractRule {



	@Override
	protected boolean processRule(CompilationUnit cu, List<Element> elements) {
		
		
		
		return false;
	}
	
	
	
	
}
