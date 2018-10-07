package identification;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import model.Element;
import model.ObjectType;

public abstract class AbstractIdentificator {
	
	public abstract List<Element> identify(CompilationUnit cu);
	
	protected ObjectType getObjectTypeFromString(String objectType) throws Exception {
		
		if(objectType.equals( ObjectType.CLASS.getValue().toString() )) {
			return ObjectType.CLASS;
		}
		
		if(objectType.equals( ObjectType.INTERFACE.getValue().toString() )) {
			return ObjectType.INTERFACE;
		}
		
		throw new Exception("Errado");
		
	}

}
