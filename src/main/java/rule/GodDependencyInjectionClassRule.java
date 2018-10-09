package rule;

import com.github.javaparser.ast.CompilationUnit;

import model.Element;
import model.ElementResult;

public class GodDependencyInjectionClassRule extends AbstractRule {

	public GodDependencyInjectionClassRule() {
		super();
	}

	@Override
	public ElementResult processRule(CompilationUnit cu, Element element) {
		// TODO definir metrica para god class com injecao de dependencia
		return null;
	}

}
