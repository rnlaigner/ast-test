package identification;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import model.ContainerCall;
import model.Element;

public class ContainerCallIdentificator extends AbstractIdentificator {
	
	@Override
	public List<Element> identify(CompilationUnit cu){
		
		List<Element> elements = new ArrayList<Element>();
		
		
		
		
		return elements;
		
	}
	
	protected String getContainerCallRegex(){
		return ContainerCall.SPRING.getClassName().toString() + "|" + ContainerCall.CDI.getClassName().toString();
	}
	
}
