package model;

public enum InjectionAnnotation {
	
	AUTOWIRED("Autowired"),
	INJECT("Inject");
	
	private String value;
	
	InjectionAnnotation(String value) {
		this.value = value;
    }

	public String getValue() {
		return value;
	}
	
}
