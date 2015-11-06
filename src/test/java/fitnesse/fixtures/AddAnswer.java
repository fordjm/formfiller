package fitnesse.fixtures;

import java.util.ArrayList;
import java.util.List;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.Format;
import formfiller.entities.format.*;

public class AddAnswer {
	private boolean amongOptions;
	private List<String> answerContentList;
	private int maxAnswers;	//	TODO:	Set and update format object field value.
	private FormComponent component;
	
	//	TODO:	Should actually add to component.
	public void whenTheUserAddsTheAnswer(String answerContent){
		if (shouldAddAnswer(answerContent))
			answerContentList.add(answerContent);
	}

	//	TODO:	answerContent is not used.
	private boolean shouldAddAnswer(String answerContent) {
		Format format = component.format;
		boolean hasRoom = answerContentList.size() < maxAnswers;
		//	TODO:	Find better test than instanceof
		//			Or don't, since this is temporary.
		if (format instanceof Unstructured)
			return hasRoom;
		else
			return hasRoom && amongOptions;
	}
	
	public boolean doesComponentHaveAnswer(String answerContent){
		for (String content : answerContentList)
			if (Context.stringMatcher.matches(content, answerContent))
				return true;
		return false;
	}
	
	public void givenComponentHasAnswers(String id, int numAnswers){
		setupComponent(id, numAnswers);
		addExistingAnswers(numAnswers);
	}

	private void setupComponent(String id, int numAnswers) {
		component = Context.formComponentGateway.find(id);
		answerContentList = new ArrayList<String>();
	}
	
	private void addExistingAnswers(int numAnswers) {
		for (int i=0; i<numAnswers; ++i)
			answerContentList.add("existing answer " + i);
	}

	//	TODO:	Take actual options.
	public void givenComponentHasOptions(boolean flag){
		amongOptions = flag;
	}
	
	public void givenComponentHasMaxAnswers(int maxAnswers){
		this.maxAnswers = maxAnswers;
	}
	
}
