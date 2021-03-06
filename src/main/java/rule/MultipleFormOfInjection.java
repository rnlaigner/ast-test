package rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;

import model.Element;
import model.ElementResult;

public class MultipleFormOfInjection extends AbstractRule {

	@Override
	public ElementResult processRule(CompilationUnit cu, Element element) throws Exception {
		throw new Exception("Excecao!");
	}
	
	public List<ElementResult> processRule(CompilationUnit cu, List<Element> elements) {
				
		List<ElementResult> results = new ArrayList<ElementResult>();
		
		//busco por elementos repetidos
		
		Map<String,Integer> elementCountMap = new HashMap<String,Integer>();
		
		for(Element element : elements){
			
			String key = element.getName();
			
			//verifica se existe
			if (elementCountMap.containsKey(key)) {
			    // Okay, there's a key but the value is null
				int value = elementCountMap.get(key);
				
				//valor um apenas uma vez
				if(value == 1){
					ElementResult result = new ElementResult();
					result.addElement(element);
					results.add(result);
				}
				
				value++;
				elementCountMap.put(key, value);
				
		    } else {
		       // Definitely no such key
		    	elementCountMap.put(key, 1);
		    }
			
		}
		
        return results;
	}

}
