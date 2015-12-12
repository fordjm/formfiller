package formfiller.delivery.event.eventSink;

import formfiller.usecases.askQuestion.AskQuestionViewModel;

public interface EventSink {
	public void receive(AskQuestionViewModel viewModel);
}
