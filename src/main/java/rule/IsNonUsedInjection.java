package rule;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;

import model.Element;

public class IsNonUsedInjection extends AbstractMethodVisitor {
	
	private Map<String,Integer> map = new HashMap<String,Integer>();

	@Override
	public boolean processRule(CompilationUnit cu, List<Element> elements) {
		visit(cu,elements);
		
        //Iterate through map
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            //it.remove(); // avoids a ConcurrentModificationException
        }
		
		return false;
	}

	@Override
	protected void visitImpl(MethodCallExpr methodCall, List<Element> arg) {
		//System.out.print("Method call: " + methodCall.getName() + "\n");
        List<Expression> args = methodCall.getArguments();
        if (args != null)
        {
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

	private boolean doesItAppear(String nodeName, List<Element> variables)
    {
    	Long count = variables
    					.stream()
    					.filter(f -> f.getName().equals(nodeName))
    					.count();
    	if (count >= 1) return true; return false;
    }

}
