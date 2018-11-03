package model;

import java.util.List;

public class ElementResult {
	
	private boolean result;
	
	private List<Element> elements;

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public Element getElement() {
		if (elements.size() == 1){
			return elements.get(0);
		}
		return null;
	}

	public void addElement(Element element) {
		this.elements.add( element );
	}

}
