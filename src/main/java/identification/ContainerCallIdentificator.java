package identification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import model.ContainerClassType;
import model.Element;
import model.MethodElement;

public class ContainerCallIdentificator extends AbstractIdentificator {
	
	@Override
	public List<Element> identify(CompilationUnit cu){
		
		List<Element> elements = new ArrayList<Element>();
		
		cu.findAll(ClassOrInterfaceType.class).stream()
			.filter(f -> { 
				return 
						f.getName()
							.stream()
							.anyMatch(
									a -> a.toString()
									.matches(getContainerClassTypeRegex()));
			} )
			.forEach(f -> { 
				
				//TODO criar o tipo variableDeclaration e nao usar o methodelement
				MethodElement methodElement = new MethodElement();
				
				Optional<Node> declaration = f.getParentNode();
				
				//methodElement.
				
				List<Node> childNodes = f.getChildNodes();
				
				elements.add(methodElement);
				
			} );
		
		return elements;
		
	}
	
	protected String getContainerClassTypeRegex(){
		return ContainerClassType.SPRING.getClassName().toString() + "|" + ContainerClassType.CDI.getClassName().toString();
	}
	
}
