package practices;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import identification.ConstructorInjectionIdentificator;
import identification.ContainerCallIdentificator;
import identification.FieldDeclarationInjectionIdentificator;
import identification.MethodInjectionIdentificator;
import model.Element;
import model.ElementResult;
import rule.AppearsInEveryMethod;
import rule.DirectContainerCallRule;
import rule.ReferenceOnConcreteClassRule;

public class BadPracticeSeven extends AbstractPractice {
	
	private DirectContainerCallRule rule;

	public BadPracticeSeven(CompilationUnit cu) {
		super(cu);
		rule = new DirectContainerCallRule();
	}

	@Override
	public void process() {
		
        ContainerCallIdentificator contId = new ContainerCallIdentificator();

        List<Element> elements = contId.identify(cu);
        
        for (Element elem : elements) {
        	ElementResult result = rule.processRule(cu, elem);
        	
        	
        	
        }
		
	}

}
