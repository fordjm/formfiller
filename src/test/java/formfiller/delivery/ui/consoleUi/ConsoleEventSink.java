package formfiller.delivery.ui.consoleUi;

import formfiller.EventSink;
import formfiller.usecases.askQuestion.AskQuestionViewModel;

public class ConsoleEventSink implements EventSink {
	public void receive(AskQuestionViewModel viewModel) {
		ConsoleAskQuestionView view = new ConsoleAskQuestionView();
		view.generateView(viewModel);
	}

}
