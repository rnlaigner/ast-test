package identification;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import model.Element;
import model.ObjectType;

public abstract class AbstractIdentificator {
	
	public abstract List<Element> identify(CompilationUnit cu);
	
	protected ObjectType getObjectTypeFromString(String objectType) throws Exception {
		
		// TODO: ver como o ast pode me dar se eh classe ou interface. Por ora, ele diz que eh apenas interface ou classe
		if(objectType.toLowerCase().contains( ObjectType.CLASS.getValue().toString() )) {
			return ObjectType.CLASS;
		}
		
		if(objectType.equals( ObjectType.INTERFACE.getValue().toString() )) {
			return ObjectType.INTERFACE;
		}
		
		throw new Exception("Errado");
		
	}

}
