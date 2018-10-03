package practices;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import model.Element;

public abstract class AbstractPractice {

	private String name;
	
	private String description;
	
	private List<Element> elements;
	
	protected CompilationUnit cu;
	
	public AbstractPractice(CompilationUnit cu){
		this.cu = cu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	protected abstract void process();
	
	
}
