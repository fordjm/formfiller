package formfiller.delivery.event.eventSink;

import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.usecases.addAnswer.AddAnswerViewModel;
import formfiller.usecases.askQuestion.AskQuestionViewModel;

public interface EventSink {
	public void receive(AskQuestionViewModel viewModel);

	public void receive(AddAnswerViewModel viewModel);

	public void receive(NotificationViewModel viewModel);
}
