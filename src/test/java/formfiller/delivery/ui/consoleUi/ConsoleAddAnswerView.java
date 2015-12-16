package formfiller.delivery.ui.consoleUi;

import formfiller.usecases.addAnswer.AddAnswerViewModel;

public class ConsoleAddAnswerView {

	public void generateView(AddAnswerViewModel viewModel) {
		printMessage("You added the answer " + viewModel.answerContent.toString());
	}

	private void printMessage(String message) {
		System.out.println(message);
	}

}
