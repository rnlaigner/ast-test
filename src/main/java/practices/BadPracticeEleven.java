package practices;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import identification.ConstructorInjectionIdentificator;
import identification.FieldDeclarationInjectionIdentificator;
import identification.MethodInjectionIdentificator;
import model.Element;
import model.ElementResult;
import rule.InjectionAssignedToMoreThanOneAttribute;

public class BadPracticeEleven extends AbstractPractice {
	
	private InjectionAssignedToMoreThanOneAttribute rule;

	public BadPracticeEleven(CompilationUnit cu) {
		super(cu);
		rule = new InjectionAssignedToMoreThanOneAttribute();
	}

	@Override
	public void process() {
		
        /*
         * TODO should I consider?
         * ContainerCallIdentificator contId = new ContainerCallIdentificator();
         */
        FieldDeclarationInjectionIdentificator fieldId = new FieldDeclarationInjectionIdentificator();
        ConstructorInjectionIdentificator constructorId = new ConstructorInjectionIdentificator();
        MethodInjectionIdentificator methodId = new MethodInjectionIdentificator();
        
        List<Element> elements = fieldId.identify(cu);
        elements.addAll(constructorId.identify(cu));
        elements.addAll(methodId.identify(cu));
        
        for (Element elem : elements) {
        	ElementResult result = rule.processRule(cu, elem);
        	
        	
        	
        }
		
	}

}
