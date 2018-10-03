package identification;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import model.Element;
import model.InjectionAnnotation;

public abstract class AbstractInjectionIdentificator {
	
	protected String getInjectAnnotationsRegex(){
		return InjectionAnnotation.AUTOWIRED.getValue().toString() + "|" + InjectionAnnotation.INJECT.getValue().toString();
	}
	
	protected InjectionAnnotation getInjectionAnnotationFromString(String annotation) throws Exception {
		
		if(annotation.equals( InjectionAnnotation.AUTOWIRED.getValue().toString() ) )
				return InjectionAnnotation.AUTOWIRED;
		if(annotation.equals( InjectionAnnotation.INJECT.getValue().toString() ) )
				return InjectionAnnotation.INJECT;
		throw new Exception("Errado!");
	
	}
	
	public abstract List<Element> identify(CompilationUnit cu);

}
