package model;

public enum ClassType {

	INTERFACE("interface"),
	ENUM("enum"),
	CLASS("class"),
	ABSTRACT_CLASS("abstract class");
	
	private String value;
	
	ClassType(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
