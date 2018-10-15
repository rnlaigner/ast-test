package rule;

import com.github.javaparser.ast.CompilationUnit;

import model.Element;
import model.ElementResult;

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
	
	protected abstract ElementResult processRule(CompilationUnit cu, Element element) throws Exception;
	
}
