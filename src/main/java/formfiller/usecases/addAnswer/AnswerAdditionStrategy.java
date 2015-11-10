package formfiller.usecases.addAnswer;

import formfiller.entities.Answer;

//	TODO:	Probably belongs in Format or own package.
public interface AnswerAdditionStrategy {
	void addAnswerToComponent(String componentId, Answer answer);

}
