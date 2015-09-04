package formfiller.delivery.presenter;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.delivery.viewModel.PresentableResponseViewModel;
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
		presenter = new FormComponentPresenter(new PresentableResponseViewModel());
	}
	
	@Test(expected = ResponsePresenter.NullPresentableResponse.class)
	public void presentingNullThrowsException(){
		presenter.present(null);
	}
	
	public class GivenAPresentableResponse {
		
		@Before
		public void givenAPresentableFormComponent(){
			presentableFormComponent = makeMockPresentableFormComponent();
		}
		
		@Test
		public void gettingPresentableResponse_ReturnsGivenPresentableNavigation(){
			presenter.present(presentableFormComponent);
			
			PresentableResponse presentableResponse = presenter.getPresentableResponse();
			
			assertThat(presentableResponse, is(presentableFormComponent));
			assertThat(presentableResponse, is(instanceOf(PresentableFormComponent.class)));
		}
	}
}
