package formfiller.usecases.presentQuestion;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import formfiller.boundaryCrossers.PresentableQuestion;
import formfiller.boundaryCrossers.PresentableQuestion;
import formfiller.entities.Question;
import formfiller.utilities.MockCreation;


public class PresentableQuestionTest {
	
	private PresentableQuestion presentableQuestion;
	
	@Before
	public void givenAPresentableQuestion(){
		presentableQuestion = new PresentableQuestion();
	}
	@Test
	public void test() {
		Question mockQuestion = MockCreation.makeMockNameQuestion();
		
		presentableQuestion.setId(mockQuestion.getId());
		presentableQuestion.setMessage(mockQuestion.getContent());
		
		assertThat(presentableQuestion.getId(), is(mockQuestion.getId()));
		assertThat(presentableQuestion.getMessage(), is(mockQuestion.getContent()));
		
	}

}
