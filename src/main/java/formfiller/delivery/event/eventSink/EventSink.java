package formfiller.delivery.event.eventSink;

import formfiller.usecases.addAnswer.AddAnswerViewModel;
import formfiller.usecases.askQuestion.AskQuestionViewModel;

public interface EventSink {
	public void receive(AskQuestionViewModel viewModel);

	public void receive(AddAnswerViewModel viewModel);
}
