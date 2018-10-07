package rule;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import model.Element;
import model.ElementResult;
import model.MethodElement;

public class ProducerMethodWithBusinessRule extends AbstractRule {
	
	private List<String> businessClasses;

	public ProducerMethodWithBusinessRule(List<String> businessClasses) {
		super();
		this.businessClasses = businessClasses;
	}



	@Override
	public ElementResult processRule(CompilationUnit cu, Element element) {
		
		MethodElement methodElement = (MethodElement) element;
		
		
		
		return null;
	}
	
	

}
