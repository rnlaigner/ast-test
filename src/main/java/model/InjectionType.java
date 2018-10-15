package model;

public enum InjectionType {

	CONSTRUCTOR("constructor"),
	METHOD("method"),
	ATTRIBUTE("attribute");
	
	private String value;
	
	InjectionType(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
