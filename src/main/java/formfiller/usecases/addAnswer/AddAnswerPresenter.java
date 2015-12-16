package formfiller.usecases.addAnswer;

public class AddAnswerPresenter {
	private AddAnswerViewModel viewModel;
	
	public AddAnswerViewModel getViewModel() {
		return viewModel;
	}

	public void present(AddAnswerResponseModel responseModel) {
		makeViewable(responseModel);
	}

	private void makeViewable(AddAnswerResponseModel responseModel) {
		viewModel = new AddAnswerViewModel();
		viewModel.id = responseModel.id;
		viewModel.format = responseModel.format;
		viewModel.answerContent = responseModel.content;
	}

}
