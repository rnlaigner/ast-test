package rule;

import com.github.javaparser.ast.CompilationUnit;

import model.Element;
import model.ElementResult;
import model.ObjectType;

public class ReferenceOnConcreteClass extends AbstractRule {

	public ReferenceOnConcreteClass() {
		super();
	}

	@Override
	public ElementResult processRule(CompilationUnit cu, Element element) {
		
		ElementResult elementResult = new ElementResult();
		
		elementResult.addElement(element);
		elementResult.setResult(false);
		
		if (! element.getClassType().equals(ObjectType.INTERFACE)){
			elementResult.setResult(true);
		}
		
		return elementResult;
	}
	
	

}
