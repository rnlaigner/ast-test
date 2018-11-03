package rule;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import model.Element;
import model.ElementResult;

public abstract class AbstractRuleWithMultipleElements extends AbstractMethodCallVisitor {
	
	protected abstract ElementResult processRule(CompilationUnit cu, List<Element> elements) throws Exception;
	
}
