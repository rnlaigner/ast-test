package practices;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import identification.ConstructorInjectionIdentificator;
import identification.FieldDeclarationInjectionIdentificator;
import model.Element;
import model.ElementResult;
import rule.IsNonUsedInjection;

public class BadPracticeOne extends AbstractPractice {
	
	private IsNonUsedInjection rule;

	public BadPracticeOne(CompilationUnit cu) {
		super(cu);
		
		rule = new IsNonUsedInjection();
	}

	@Override
	public void process() {
		
		/* identifica elementos que bad practice pode se aplicar */
        FieldDeclarationInjectionIdentificator fieldId = new FieldDeclarationInjectionIdentificator();
        
        ConstructorInjectionIdentificator constructorId = new ConstructorInjectionIdentificator();
        
        List<Element> elements = fieldId.identify(cu);
        
        elements.addAll(constructorId.identify(cu));
        //Faltam outros elementos
        
        for (Element elem : elements) {
        	ElementResult result = rule.processRule(cu, elem);
        	
        	
        	
        }
        
        
		
	}
	
	

}
