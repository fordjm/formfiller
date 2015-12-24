package fitnesse.fixtures;

import java.lang.reflect.Type;

import formfiller.Context;
import formfiller.entities.constrainable.AnswerType;
import formfiller.entities.formComponent.FormComponent;
import formfiller.response.models.NotificationResponseModel;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.StringToTypeConverter;
import formfiller.utilities.PreDefinedStringToTypeConverter;
import formfiller.utilities.TypeRequirementTester;

//	TODO:	Fix AnswerValidator and its unit tests.
public class AddAnswerType {
	private StringToTypeConverter converter;
	
	public AddAnswerType() {
		converter = new PreDefinedStringToTypeConverter();
	}

	public void whenTheUserAddsTheAnswerTypeToComponent(String type, 
			String componentId){
		executeUseCaseBehavior(type, componentId);
	}
	
	private void executeUseCaseBehavior(String type, 
			String componentId) {
		Class<?> toAdd = converter.convert(type);
		AnswerType constraint = new AnswerType(toAdd);
		FormComponent found = FormComponentUtilities.find(componentId);
		found.validator.addConstraint(constraint);
		presentMessage("You successfully added the answer type " + type);
	}

	private void presentMessage(String message) {
		NotificationResponseModel response = new NotificationResponseModel();
		response.message = message;
		Context.outcomePresenter.present(response);
	}

	public boolean componentRequiresType(String componentId, String type){
		FormComponent found = FormComponentUtilities.find(componentId);
		Type toCheck = new PreDefinedStringToTypeConverter().convert(type);
		return new TypeRequirementTester().isTypeRequired(found.validator, toCheck);
	}
	
}
