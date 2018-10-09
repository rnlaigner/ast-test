package practices;

import com.github.javaparser.ast.CompilationUnit;

import model.ElementResult;
import rule.GodDependencyInjectionClassRule;

public class BadPracticeFour extends AbstractPractice {
	
	private GodDependencyInjectionClassRule rule;

	public BadPracticeFour(CompilationUnit cu) {
		super(cu);
		rule = new GodDependencyInjectionClassRule();
	}

	@Override
	public void process() {       
		ElementResult result = rule.processRule(cu, null);
	}

}
