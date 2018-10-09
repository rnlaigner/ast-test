package model;

public enum ProducerAnnotation {

	PRODUCES("Produces"),
	BEAN("Bean");
	
	private String value;
	
	ProducerAnnotation(String value) {
		this.value = value;
    }

	public String getValue() {
		return value;
	}
	
}