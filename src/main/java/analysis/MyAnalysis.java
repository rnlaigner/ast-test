package analysis;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.declarations.ResolvedAnnotationDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

/**
 * Some code that uses JavaSymbolSolver.
 */
public class MyAnalysis 
{
	
	static List<String> injectAnnotations = Arrays.asList( "Inject", "Autowired"  );

    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        // Set up a minimal type solver that only looks at the classes used to run this sample.
        CombinedTypeSolver combinedTypeSolver = new CombinedTypeSolver();
        combinedTypeSolver.add(new ReflectionTypeSolver());

        // Configure JavaParser to use type resolution
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(combinedTypeSolver);
        JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);
        
        String exampleClass = "class X { int x() { return 1 + 1.0 - 5; } }";
        
        String exampleClass1 = "class BusinessExample { "
        							+ "@Autowired "
        							+ "ABusiness business; "
        							+ "@Autowired "
        							+ "AnotherBusiness business1; "
        							+ "public void execute() { "
        							+ "business.execute(); } "
        							+ "}";
        
        //ClassOrInterfaceDeclaration classDeclaration = new ClassOrInterfaceDeclaration();

        String exampleClass2 = "class BusinessExample { private "
				+ "@Autowired "
				+ "AnotherBusiness business; }"; 
        
        // Parse some code
        CompilationUnit cu;
        
        try 
        {
        	cu = JavaParser.parse(exampleClass1);
        }
        catch(Exception e)
        {
        	System.out.println("Excecao");
        	return;
        }

        // Find all the calculations with two sides:
        cu.findAll(AnnotationExpr.class).forEach(be -> {
            // Find out what type it has:
            //ResolvedType resolvedType = be.calculateResolvedType();
        	
        	AnnotationExpr resolvedType = be.asAnnotationExpr();
        	
        	Optional<Node> parent = be.getParentNode();
        	
        	//parent.get().findAll(FieldDeclaration.class).forEach(ac -> { ac.asFieldDeclaration().toString(); });
        	
        	//System.out.println( parent.get().toString() );

            // Show that it's "double" in every case:
            //System.out.println(be.toString() + " is a: " + resolvedType.getNameAsString());
        });
        
        // Find all the calculations with two sides:
        /*
        cu.findAll(FieldDeclaration.class).stream()
                .filter(f -> f.isAnnotationPresent("Autowired"))
                .forEach(f -> System.out.println( f.getElementType().toString() + " " + f.getVariable(0).toString() ) );
        */
        
        List<String> injectedVariables = new ArrayList<String>();
        
        cu.findAll(FieldDeclaration.class).stream()
        		.filter(f -> containsInjectionAnnotation(f))
        		.forEach(f -> {
        		
        		//System.out.println( f.getVariable(0).toString() );
        		//Store fields in a list, in order to find its occurrence later
        		injectedVariables.add( f.getVariable(0).toString() );
        });
        
        
        new MethodVisitor().visit(cu, injectedVariables);
    }
    
    //https://stackoverflow.com/questions/32622879/extract-methods-calls-from-java-code
    @SuppressWarnings("rawtypes")
	private static class MethodVisitor extends VoidVisitorAdapter
    {
        @SuppressWarnings("unchecked")
		@Override
        public void visit(MethodCallExpr methodCall, Object arg)
        {
            //System.out.print("Method call: " + methodCall.getName() + "\n");
            List<Expression> args = methodCall.getArguments();
            if (args != null)
            {
            	if (args instanceof List)
            	{
            		int numberOfAppearances = 
            				handleInjectedVariables( methodCall, (List<String>) arg );
            		System.out.println( methodCall.getName() + " \n" );
            		System.out.println(numberOfAppearances);
            	}
            	else
            	{
            		handleExpressions(args);
            	}
            	
            }
                
        }
        
        private int handleInjectedVariables(MethodCallExpr methodCall, List<String> variables)
        {
        	int i = 0;
        	//Optional<Node> node = methodCall.getParentNode();
        	String nodeName = methodCall.getChildNodes().get(0).toString();
        	
        	//System.out.println(node.get().getParentNode().get().toString());
        	
        	/*
        	System.out.println( methodCall.getName() + " \n" 
        						//+ methodCall.getNameAsString() + " \n"
        						//+ methodCall.findRootNode().toString() + " "
        						+ methodCall.getParentNodeForChildren().toString() + " \n"
        						//+ methodCall.get
        					   );
        	*/
        	
        	//System.out.println(nodeName);
        	
            for (String variable : variables)
            {
            	if(variable.equals(nodeName))
            	{
            		i++;
            	}
                
            }
            return i;
        }

        private void handleExpressions(List<Expression> expressions)
        {
            for (Expression expr : expressions)
            {
                if (expr instanceof MethodCallExpr)
                    visit((MethodCallExpr) expr, null);
                else if (expr instanceof BinaryExpr)
                {
                    BinaryExpr binExpr = (BinaryExpr)expr;
                    handleExpressions(Arrays.asList(binExpr.getLeft(), binExpr.getRight()));
                }
            }
        }
    }

    
    private static boolean containsInjectionAnnotation(@SuppressWarnings("rawtypes") NodeWithAnnotations node)
    {
    	List<String> annotations = new ArrayList<String>();
    	
    	@SuppressWarnings("unchecked")
		NodeList<AnnotationExpr> nodeAnnotations = node.getAnnotations();
    	
    	nodeAnnotations.forEach(action -> { annotations.add( action.getNameAsString() ); });
    	
    	annotations.retainAll(injectAnnotations);
    	
    	if (annotations.size() > 0) {
    		return true;
    	}
    	return false;
    }
}
