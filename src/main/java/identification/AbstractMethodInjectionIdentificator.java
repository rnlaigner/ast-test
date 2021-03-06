package identification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.stmt.Statement;

import model.AssignmentBusiness;
import model.AttributeElement;
import model.Element;
import model.InjectionType;
/*
 * It serves Constructor and Method injection identifier
 */
public abstract class AbstractMethodInjectionIdentificator extends AbstractInjectionIdentificator {
	
	public AbstractMethodInjectionIdentificator(InjectionType injectionType) {
		super(injectionType);
	}

	protected List<Element> identifyFromParameters( CallableDeclaration<?> f ) throws Exception {
		
		List<Element> elements = new ArrayList<Element>();
		
		NodeList<Parameter> parameters = f.getParameters();
		
		NodeList<Statement> statements = getStatementsFromBody(f);
		
		//search body for assigment of element
		List<AssignExpr> assignments = AssignmentBusiness.getAssignmentsFromStatements(statements);
		
		for (Parameter parameter : parameters) {
			
			//get assignments from current parameter
			List<AssignExpr> assignmentsFromCurrentParameter = assignments
										.stream()
										.filter(a -> a
												.getValue()
												.toString()
												.equals(parameter.getName().toString())
												)
										.collect(Collectors.toList());
			
			for ( AssignExpr assignment : assignmentsFromCurrentParameter ) {
				
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
				
				try {
					elem.setAnnotation(getInjectionAnnotationFromString(annotation));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				elem.setInjectionType(getInjectionType());
				elem.setName(assignment.getTarget().toString());
				
				elements.add( elem );
			}
		}
		
		return elements;
	}
	
	private NodeList<Statement> getStatementsFromBody( CallableDeclaration<?> f ) throws Exception {

		if (f instanceof MethodDeclaration) {
			return ( (MethodDeclaration) f).getBody().get().getStatements();
		}
		
		if (f instanceof ConstructorDeclaration) {
			return ( (ConstructorDeclaration) f).getBody().getStatements();
		}
		
		throw new Exception("Tipo nao reconhecido");
		
	}
	
	protected boolean isSetMethod(MethodDeclaration f){
		return f.getName().getIdentifier().startsWith("set");
	}

}
