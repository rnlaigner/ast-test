package model;

public class AttributeElement extends Element {
	

	private InjectionAnnotation annotation;
	
	public InjectionAnnotation getAnnotation() {
		return annotation;
	}
	
	public void setAnnotation(InjectionAnnotation annotation) {
		this.annotation = annotation;
	}

}
