package identification;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import model.Element;

public class MethodInjectionIdentificator extends AbstractMethodInjectionIdentificator {
	
	@Override
	public List<Element> identify(CompilationUnit cu){
		
		List<Element> elements = new ArrayList<Element>();
		
		cu.findAll(MethodDeclaration.class).stream()
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
				
				try {
					elements.addAll(identifyFromParameters(f));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		);
		
		
		return elements;
		
	}

}
