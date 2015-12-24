package formfiller.delivery.ui.consoleUi;

import formfiller.delivery.event.eventSink.EventSink;
import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.usecases.addAnswer.AddAnswerViewModel;
import formfiller.usecases.askQuestion.AskQuestionViewModel;

public class ConsoleEventSink implements EventSink {
	public void receive(AskQuestionViewModel viewModel) {
		ConsoleAskQuestionView view = new ConsoleAskQuestionView();
		view.generateView(viewModel);
	}
	
	public void receive(AddAnswerViewModel viewModel) {
		ConsoleAddAnswerView view = new ConsoleAddAnswerView();
		view.generateView(viewModel);
	}

	public void receive(NotificationViewModel viewModel) {
		ConsoleNotificationView view = new ConsoleNotificationView();
		view.generateView(viewModel);
	}

}
