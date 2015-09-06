package formfiller.utilities;

import formfiller.ApplicationContext;
import formfiller.entities.FormComponent;
import formfiller.gateways.InMemoryFormComponentGateway;

//	TODO:	Get rid of this hack.
public class InMemoryFormComponentLiason {
	
	public static FormComponent findFormComponentByIndex(int index){
		return getInMemoryFormComponentGateway().findByIndex(index);
	}
	
	private static InMemoryFormComponentGateway getInMemoryFormComponentGateway(){
		InMemoryFormComponentGateway result = (InMemoryFormComponentGateway)
				ApplicationContext.formComponentGateway;		
		return result;
	}
}
