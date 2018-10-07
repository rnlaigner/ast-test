package identification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.Parameter;

import model.AttributeElement;
import model.Element;

public class ConstructorInjectionIdentificator extends AbstractInjectionIdentificator {

	//private List<Element> injectedFieldDeclaration = new ArrayList<Element>();
	
	/*
	 * por ora esta so identificando field declaration que sao injecoes
	 * TODO o ideal é ver os construtores e set methods, pois os mesmos podem ter injecao tambem
	 */
	
	@Override
	public List<Element> identify(CompilationUnit cu){
		
		List<Element> elements = new ArrayList<Element>();
		
		cu.findAll(ConstructorDeclaration.class).stream()
			.filter(f -> { 
				return 
						f.getAnnotations()
						.stream()
						.anyMatch(a -> a
								.getName()
								.getIdentifier()
								.matches(getInjectAnnotationsRegex()));
			} )
			.forEach(f -> {
				 
				NodeList<Parameter> parameters = f.getParameters();
				
				for (Parameter parameter : parameters){
					
					AttributeElement elem = new AttributeElement();
					
					elem.setType(parameter.getType().asString());
					
					try {
						elem.setClassType( getObjectTypeFromString
											(parameter.getChildNodes().
													get(0).
													getClass().
													getSimpleName() ) );
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					String annotation = f.getAnnotations()
												.stream()
												.distinct()
												.map(e -> e.getName().asString())
												.collect( Collectors.toList() )
												.get(0);							
					
					elem.setName(parameter.getName().toString());
					
					try {
						elem.setAnnotation(getInjectionAnnotationFromString(annotation));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					elements.add( elem );
				}
				
			}
		);
		
		
		return elements;
		
	}

}
