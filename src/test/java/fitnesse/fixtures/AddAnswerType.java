package fitnesse.fixtures;

import java.lang.reflect.Type;
import java.util.Collection;

import formfiller.Context;
import formfiller.entities.constrainable.AnswerType;
import formfiller.entities.constrainable.Constrainable;
import formfiller.entities.formComponent.FormComponent;
import formfiller.response.models.PresentableResponse;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.StringToTypeConverter;

//	TODO:	Determine whether drapostolos' type-parser helps here.
//			Fix AnswerValidator and its unit tests.
public class AddAnswerType {
	private StringToTypeConverter converter;
	private String componentId;
	private String typeString;
	
	public AddAnswerType() {
		converter = new StringToTypeConverter();
	}

	public void whenTheUserAddsTheAnswerTypeToComponent(String type, String componentId){
		typeString = type;
		this.componentId = componentId;
		executeTemporaryBehavior();
	}
	
	private void executeTemporaryBehavior() {
		Type toAdd = converter.convert(typeString);
		AnswerType constraint = new AnswerType(toAdd);
		FormComponent found = FormComponentUtilities.find(componentId);
		found.validator.constraints.add(constraint);
		presentMessage("You successfully added the answer type " + typeString);
	}

	private void presentMessage(String message) {
		PresentableResponse response = new PresentableResponse();
		response.message = message;
		Context.outcomePresenter.present(response);
	}

	public boolean componentRequiresType(String componentId, String type){
		FormComponent found = FormComponentUtilities.find(componentId);
		AnswerType typeConstraint = getTypeConstraint(found.validator.constraints);
		Type toCheck = converter.convert(type);
		//	TODO:	Must have a null object since we're dereferencing.
		return typeConstraint.requiresType(toCheck);
	}
	
	//	TODO:	Improve and fix duplication in AddAnswerTypeTest (extract class?)
	//			Limit constraints to exactly one AnswerType.
	private AnswerType getTypeConstraint(Collection<Constrainable> constraints) {
		for (Constrainable constraint : constraints)
			if (constraint instanceof AnswerType)
				return (AnswerType) constraint;
		return null;
	}
	
}
