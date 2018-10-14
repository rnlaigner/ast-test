package rule;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import model.Element;
import model.ElementResult;
import model.ProducerAnnotation;

public class InjectionAssignedToMoreThanOneAttributeRule extends AbstractRule {
	
	/*
	 class ExampleBusiness extends GenericBusinessImpl {
	 
	    @Inject
	    Business anotherInjection;
	    
	    Business variable;
	 
		IDAO exampleDAO;
		
		@Inject
		public void setExampleDAO(ExampleDAO exampleDAO) {
			this.genericDAO = exampleDAO; //genericDAO is an attribute of GenericBusinessImpl
			this.exampleDAO = exampleDAO;
		}
		
		public void anyMethod() {
			variable = anotherInjection;
		}
		
	 }
	*/
	
	private Integer attributeAssignment = 0;
	
	private MethodDeclarationVisitor methodDeclarationVisitor;
	
	public InjectionAssignedToMoreThanOneAttributeRule() {
		super();
		methodDeclarationVisitor = new MethodDeclarationVisitor();
	}

	private class MethodDeclarationVisitor extends VoidVisitorAdapter<Element> {
		
		@Override
	    public void visit(MethodDeclaration methodDeclaration, Element element)
	    {
			Boolean containsProducerAnnotation = 
					methodDeclaration
					.getAnnotations()
					.stream()
					.anyMatch(
							 an -> an
							.getName()
							.getIdentifier()
							.matches(ProducerAnnotation.getProducerAnnotationsRegex())
							);
			
			//Pode ser metodo que retorna container call
			
			if (containsProducerAnnotation) return;
			
			NodeList<Statement> statements = methodDeclaration.getBody().get().getStatements();
			
			//search body for reassigment of element
			for(Statement stmt : statements){
				
				if (stmt.isExpressionStmt() && stmt.asExpressionStmt().getExpression().isAssignExpr()){
					AssignExpr expr = stmt.asExpressionStmt().getExpression().asAssignExpr();
					//String target = expr.getTarget().toString();
					String value = expr.getValue().toString();
					/*
					if (target.contains("this.")){
						target = target.substring(target.indexOf("this."),target.length());
					}
					*/
					if (value.contains("this.")){
						value = value.substring(value.indexOf("this."),value.length());
					}
					if(value.equals(element.getName())){
						attributeAssignment++;
					}
				}
				
			}
			
	    }
		
	}

	@Override
	public ElementResult processRule(CompilationUnit cu, Element element) {
		
		//iterate through method declarations
		methodDeclarationVisitor.visit(cu, element);
		
		ElementResult result = new ElementResult();
		
		result.setElement(element);
		
		result.setResult(false);
		
		if (attributeAssignment > 1) result.setResult(true);
		
        return result;
		
	}

}