package formfiller.delivery.presenter;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.delivery.ViewModel;
import formfiller.delivery.viewModel.FormComponentViewModel;
import formfiller.response.models.PresentableAnswer;
import formfiller.response.models.PresentableFormComponent;
import formfiller.response.models.PresentableQuestion;
import formfiller.response.models.PresentableResponse;

@RunWith(HierarchicalContextRunner.class)
public class FormComponentPresenterTest {
	private FormComponentPresenter presenter;
	private PresentableResponse presentableFormComponent;
	
	private PresentableResponse makeMockPresentableFormComponent() {
		PresentableFormComponent result = Mockito.mock(PresentableFormComponent.class);
		result.question = Mockito.mock(PresentableQuestion.class);
		result.answer = Mockito.mock(PresentableAnswer.class);
		return result;
	}

	@Before
	public void setUp(){
		presenter = new FormComponentPresenter();
	}
	
	//	TODO:	What should presenting null do?
	@Test
	public void canHandleNull(){
		presenter.present(null);
		
		ViewModel currentViewModel = presenter.getViewModel();
		FormComponentViewModel castViewModel = 
				(FormComponentViewModel) currentViewModel;
		
		assertThat(currentViewModel, is(instanceOf(FormComponentViewModel.class)));
		assertThat(castViewModel.questionMessage, is(""));
		assertThat(castViewModel.answerMessage, is(""));
	}
	
	public class GivenAPresentableResponse {		
		@Before
		public void givenAPresentableFormComponent(){
			presentableFormComponent = makeMockPresentableFormComponent();
		}
		
		//	TODO:	Test that present(PresentableFormComponent) creates ViewModel with correct field values.
		//			This requires giving the ResponseModel some field values.
		@Test
		public void gettingPresentableResponse_ReturnsGivenPresentableFormComponent(){
			presenter.present(presentableFormComponent);
			
			ViewModel presentableResponse = 
					presenter.getViewModel();
			
			assertThat(presentableResponse, 
					is(instanceOf(FormComponentViewModel.class)));
		}
		
	}
}
