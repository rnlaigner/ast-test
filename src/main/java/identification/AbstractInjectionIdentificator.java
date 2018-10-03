package identification;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import model.Element;
import model.InjectionAnnotation;

public abstract class AbstractInjectionIdentificator {
	
	protected String getInjectAnnotationsRegex(){
		return InjectionAnnotation.AUTOWIRED.getValue().toString() + "|" + InjectionAnnotation.INJECT.getValue().toString();
	}
	
	public abstract List<Element> identify(CompilationUnit cu);

}
