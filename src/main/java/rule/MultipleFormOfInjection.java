package rule;

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
				
		//busco por elementos repetidos
		
		Map<String,Integer> elementCountMap = new HashMap<String,Integer>();
		
		for(Element element : elements){
			
			String key = element.getName();
			
			//verifica se existe
			if (elementCountMap.containsKey(key)) {
			    // Okay, there's a key but the value is null
				int value = elementCountMap.get(key);
				
				//TODO adiciona a lista para retorno ja aqui para evitar ter que iterar pelo map depois
				
				value++;
				elementCountMap.put(key, value);
				
		    } else {
		       // Definitely no such key
		    	elementCountMap.put(key, 1);
		    }
			
		}
		
		//itero pelo map buscando elementos com mais de uma forma de injecao
		/*
		for (Map.Entry<String, Integer> entry : elementCountMap.entrySet())
		{
		    int count = entry.getValue();
		    
	    	ElementResult result = new ElementResult();
	    	
	    	Element element = new Element();
	    	element.setName(entry.getKey());
			
			result.setElement(element);
			
			result.setResult(false);
	    
			if(count > 1) result.setResult(true);
		}
		*/
		
        return null;
	}

}
