package formfiller.usecases.askQuestion;

//	TODO:	Implements AskQuestionOutputBoundary?
public class AskQuestionPresenter {
	private AskQuestionViewModel viewModel;
	
	public AskQuestionViewModel getViewModel() {
		return viewModel;
	}
	
	public void present(AskQuestionResponseModel responseModel){
		makeViewable(responseModel);
	}

	private void makeViewable(AskQuestionResponseModel responseModel) {
		viewModel = new AskQuestionViewModel();
		viewModel.id = responseModel.id;
		viewModel.format = responseModel.format;
		viewModel.message = responseModel.message;
		viewModel.answerContent = responseModel.answerContent;
	}
	
}
