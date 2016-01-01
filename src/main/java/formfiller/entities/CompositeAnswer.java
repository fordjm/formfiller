package formfiller.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * CompositeAnswer uses the Composite pattern for uniformly-handling single 
 * or multiple answers. 
 */
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
		List<Object> result = new ArrayList<Object>();
		for (Answer answer : itsAnswers)
			result.add(answer.getContent());
		return result;
	}

	public boolean isValid() {
		for (Answer answer : itsAnswers)
			if (!answer.getId().equals(this.id) || !answer.isValid())
				return false;
		return itsAnswers.size() > 0;
	}

	public void setId(String id) {
		this.id = id;
		for (Answer answer : itsAnswers)
			answer.setId(id);
	}

	public void setContent(Object content) {
		if (!(size() == 1))
			throw new IllegalStateException(
					"You must specify an answer index to set content.");
		
		itsAnswers.get(0).setContent(content);
	}

	//	TODO:	Throw exception if invalid or answer id doesn't match.
	public void add(Answer answer) {
		itsAnswers.add(answer);
	}

	public Boolean contains(Answer answer) {
		return itsAnswers.contains(answer);
	}
	
	public int size() {
		return itsAnswers.size();
	}

	public void remove(Answer answer) {
		if (!contains(answer))
			throw new IllegalStateException("Cannot remove uncontained answer.");
		
		itsAnswers.remove(answer);
	}
	
}
