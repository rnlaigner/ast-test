package identification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import model.Element;
import model.MethodElement;
import model.ProducerAnnotation;

public class ProducerMethodIdentificator extends AbstractIdentificator {

	@Override
	public List<Element> identify(CompilationUnit cu) {

		List<Element> elements = new ArrayList<Element>();
		
		cu.findAll(MethodDeclaration.class).stream()
			.filter(f -> { 
				return 
						f.getAnnotations()
						.stream()
						.anyMatch(a -> a
								.getName()
								.getIdentifier()
								.matches(getProducerAnnotationsRegex()));
			} )
			.forEach(f -> {
				MethodElement elem = new MethodElement();
				
				List<String> modifiers = new ArrayList<String>();
				f.getModifiers().stream().forEach( m -> { 
														modifiers.add( m.asString() ); 
														} 
												 );
				 				
				elem.setModifiers(modifiers);
				
				//VariableDeclarator variable = f.getVariables().get(0);
				
				elem.setType(f.getType().asString());
				
				try {
					elem.setClassType(getObjectTypeFromString
							( f.getType().asString() ));
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
				
				//elem.setName(variable.getName().toString());
				
				try {
					elem.setAnnotation(getProducerAnnotationFromString(annotation));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				elements.add( elem );
			}
		);
		
		
		return elements;
		
	}

	protected ProducerAnnotation getProducerAnnotationFromString(String annotation) throws Exception {
		
		if(annotation.equals( ProducerAnnotation.BEAN.getValue().toString() ) )
				return ProducerAnnotation.BEAN;
		if(annotation.equals( ProducerAnnotation.PRODUCES.getValue().toString() ) )
				return ProducerAnnotation.PRODUCES;
		throw new Exception("Errado!");
	
	}
	
	protected String getProducerAnnotationsRegex(){
		return ProducerAnnotation.BEAN.getValue().toString() + "|" + ProducerAnnotation.PRODUCES.getValue().toString();
	}
	
}
