package practices;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import identification.ConstructorInjectionIdentificator;
import identification.FieldDeclarationInjectionIdentificator;
import identification.MethodInjectionIdentificator;
import identification.SetMethodInjectionIdentificator;
import model.Element;
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
		
		/* identifica elementos que bad practice pode se aplicar */
        FieldDeclarationInjectionIdentificator fieldId = new FieldDeclarationInjectionIdentificator();
        ConstructorInjectionIdentificator constructorId = new ConstructorInjectionIdentificator();
        SetMethodInjectionIdentificator setMethodId = new SetMethodInjectionIdentificator();
        
        List<Element> elements = fieldId.identify(cu);
        elements.addAll(constructorId.identify(cu));
        elements.addAll(setMethodId.identify(cu));
		
		ElementResult result = rule.processRule(cu, elements);
	}

}
