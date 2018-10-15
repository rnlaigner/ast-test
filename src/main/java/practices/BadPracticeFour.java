package practices;

import com.github.javaparser.ast.CompilationUnit;

import model.ElementResult;
import rule.GodDependencyInjectionClass;

public class BadPracticeFour extends AbstractPractice {
	
	private GodDependencyInjectionClass rule;

	public BadPracticeFour(CompilationUnit cu) {
		super(cu);
		rule = new GodDependencyInjectionClass();
	}

	@Override
	public void process() {       
		ElementResult result = rule.processRule(cu, null);
	}

}
