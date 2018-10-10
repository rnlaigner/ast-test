package rule;

import com.github.javaparser.ast.CompilationUnit;

import model.Element;
import model.ElementResult;

public class DirectContainerCallRule extends AbstractRule {

	public DirectContainerCallRule() {
		super();
	}

	@Override
	public ElementResult processRule(CompilationUnit cu, Element element) {
		
		ElementResult elementResult = new ElementResult();
		
		elementResult.setElement(element);
		elementResult.setResult(false);
		
		/* TODO rule
		if (! element.getClassType().equals(ObjectType.INTERFACE)){
			elementResult.setResult(true);
		}
		*/
		
		return elementResult;
	}
	
	

}
