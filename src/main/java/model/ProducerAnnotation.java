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
	
	public static String getProducerAnnotationsRegex(){
		return ProducerAnnotation.BEAN.getValue().toString() + "|" + ProducerAnnotation.PRODUCES.getValue().toString();
	}
	
}
