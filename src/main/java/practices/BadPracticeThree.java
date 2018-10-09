package practices;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import identification.ProducerMethodIdentificator;
import model.Element;
import model.ElementResult;
import rule.ProducerMethodWithBusinessRule;

public class BadPracticeThree extends AbstractPractice {
	
	private ProducerMethodWithBusinessRule rule;

	public BadPracticeThree(CompilationUnit cu) {
		super(cu);
		
		List<String> list = identifyBusinessClasses();
		
		rule = new ProducerMethodWithBusinessRule(list);
	}

	@Override
	public void process() {
		
		/* identifica elementos que bad practice pode se aplicar */
		ProducerMethodIdentificator methods = new ProducerMethodIdentificator();
        
        List<Element> elements =  methods.identify(cu);
        
        for (Element elem : elements) {
        	ElementResult result = rule.processRule(cu, elem);
        	
        	
        	
        }
        
        
		
	}
	
	//TODO: encontrar todas as classes de negocio do projeto
	private List<String> identifyBusinessClasses(){
		return new ArrayList<String>();
	}

}
