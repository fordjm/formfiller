package fitnesse.fixtures;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;
import formfiller.response.models.PresentableResponse;
import formfiller.utilities.FormComponentUtilities;

public class FixtureUtilities {
	public static String messagePresented() {
		PresentableResponse response = 
				Context.outcomePresenter.getPresentableResponse();
		return response.message;
	}
	
	public static boolean foundComponent(String id){
		FormComponent foundComponent = 
				Context.formComponentGateway.find(id);
		return !FormComponentUtilities.isComponentNull(foundComponent);
	}
}
