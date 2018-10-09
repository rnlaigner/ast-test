package identification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.Parameter;

import model.AttributeElement;
import model.Element;

public abstract class AbstractMethodInjectionIdentificator extends AbstractInjectionIdentificator {
	
	protected List<Element> identifyFromParameters( CallableDeclaration<?> f ) {
		
		List<Element> elements = new ArrayList<Element>();
		
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
				e1.printStackTrace();
			}
			
			elements.add( elem );
		}
		
		return elements;
	}

}
