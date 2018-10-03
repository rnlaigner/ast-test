package rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;

import model.Element;
import model.ElementResult;

public class AppearsInEveryMethod extends AbstractMethodVisitor {

	private Map<String,Integer> map = new HashMap<String,Integer>();

	@Override
	protected ElementResult processRule(CompilationUnit cu, Element elements) {
		
		 return new ElementResult();
	}
	
	
	@Override
	protected void visitImpl(MethodCallExpr methodCall, Element arg) {
        	String nodeName = methodCall.getChildNodes().get(0).toString();
        	
    		Boolean itDoesAppear = 
    				doesItAppear( nodeName, arg );
    		
    		int plusNumber = 0;
    		
    		if (itDoesAppear) plusNumber++;
    		
    		Integer oldNumber = map.get(nodeName);
    		
    		if (oldNumber == null) {
    			oldNumber = 0;
    		}
    		
    		int newNumber = oldNumber + plusNumber;
    		
    		map.put(nodeName, newNumber);
	}
	
	
}
