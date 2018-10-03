package rule;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import model.Element;

public abstract class AbstractRule {

	private String name;
	
	private String description;
	
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
	
	protected abstract boolean processRule(CompilationUnit cu, List<Element> elements);
	
}
