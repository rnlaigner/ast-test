package model;

import java.util.List;

public abstract class Element {
	
	private List<String> modifiers;
	private ObjectType classType;
	private String type;
	private String name;
	
	public List<String> getModifiers() {
		return modifiers;
	}
	
	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public ObjectType getClassType() {
		return classType;
	}
	
	public void setClassType(ObjectType classType) {
		this.classType = classType;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
