package practices;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import identification.FieldDeclarationInjectionIdentificator;
import model.Element;
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
        FieldDeclarationInjectionIdentificator fdii = new FieldDeclarationInjectionIdentificator();
        List<Element> elements = fdii.identify(cu);
        //Faltam outros elementos
        
        rule.processRule(cu, elements);
        	
		
	}
	
	

}
