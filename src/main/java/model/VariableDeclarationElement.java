package model;

public class VariableDeclarationElement extends Element {
	
	private String methodCall;
	
	private String variableName;

	public String getMethodCall() {
		return methodCall;
	}

	public void setMethodCall(String methodCall) {
		this.methodCall = methodCall;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
}
