package formfiller.entities;

import java.util.ArrayList;
import java.util.List;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;

public class CompositeAnswer implements Answer {
	private String id;
	private List<Answer> itsAnswers;
	
	public CompositeAnswer(String id) {
		this.id = id;
		itsAnswers = new ArrayList<Answer>();
	}

	public String getId() {
		return id;
	}

	public Object getContent() {
		//	TODO:	Determine how this works.
		//			Return a List<Object> containing the content?
		return null;
	}

	public boolean isValid() {
		//	TODO Continue?
		for (Answer answer : itsAnswers)
			if (!answer.isValid())
				return false;
		return itsAnswers.size() > 0;
	}

	public void setId(String id) {
		//	TODO Throw exception?
	}

	public void setContent(Object content) {
		//	TODO Throw exception?
	}

	public void add(Answer answer) {
		if (componentHasRoom())
			itsAnswers.add(answer);
	}

	private boolean componentHasRoom() {
		FormComponent component = getContainingComponent();
		int maxAnswers = component.format.getMaxAnswers();
		return itsAnswers.size() < maxAnswers;
	}

	private FormComponent getContainingComponent() {
		FormComponent result = Context.formComponentGateway.find(id);
		return result;
	}

	public Boolean contains(Answer answer) {
		return itsAnswers.contains(answer);
	}
}
