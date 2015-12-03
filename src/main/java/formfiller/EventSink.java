package formfiller;

import formfiller.usecases.askQuestion.AskQuestionViewModel;

public interface EventSink {
	public void receive(AskQuestionViewModel viewModel);
}
