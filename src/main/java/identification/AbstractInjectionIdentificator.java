package identification;

import model.InjectionAnnotation;

public abstract class AbstractInjectionIdentificator extends AbstractIdentificator {
	
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

}
