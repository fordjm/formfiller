package formfiller.delivery.ui.consoleUi;

import formfiller.usecases.askQuestion.AskQuestionViewModel;

public class ConsoleAskQuestionView {

	public void generateView(AskQuestionViewModel viewModel) {
		String format = viewModel.format;
		if (format.equalsIgnoreCase("Unstructured"))
			generateUnstructuredView(viewModel);
		else if (format.equalsIgnoreCase("SingleOptionVariable"))
			generateSingleOptionVariableView(viewModel);
		else if (format.equalsIgnoreCase("MultiOptionVariable"))
			generateMultiOptionVariableView(viewModel);
		else
			throw new IllegalStateException("Cannot generate view for format " + format);
	}

	private void generateUnstructuredView(AskQuestionViewModel viewModel) {
		printMessage(viewModel.message);
	}

	private void printMessage(String message) {
		System.out.println(message);
	}

	// TODO: Need options.
	private void generateSingleOptionVariableView(AskQuestionViewModel viewModel) {
		printMessage(viewModel.message);
		// TODO: Print options and star any selected option.
	}

	private void generateMultiOptionVariableView(AskQuestionViewModel viewModel) {
		printMessage(viewModel.message);
		// TODO: Print options and star any selected options.
		
	}

}
