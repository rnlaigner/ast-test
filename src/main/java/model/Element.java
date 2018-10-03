package model;

import java.util.List;

public class Element {
	
	private List<String> modifiers;
	private ClassType classType;
	private String type;
	private String name;
	private InjectionAnnotation annotation;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ClassType getClassType() {
		return classType;
	}
	public void setClassType(ClassType classType) {
		this.classType = classType;
	}
	public InjectionAnnotation getAnnotation() {
		return annotation;
	}
	public void setAnnotation(InjectionAnnotation annotation) {
		this.annotation = annotation;
	}
	public List<String> getModifiers() {
		return modifiers;
	}
	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}
	
	
	
}
