package formfiller.usecases.presentQuestion;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.Question;
import formfiller.utilities.MockCreation;


public class PresentableQuestionTest {
	
	private PresentableQuestionFactory factory;
	private PresentableQuestion presentableQuestion;
	
	@Before
	public void givenAPresentableQuestion(){
		factory = new PresentableQuestionFactoryImpl();
		presentableQuestion = factory.makePresentableQuestion();
	}
	@Test
	public void test() {
		Question mockQuestion = MockCreation.makeMockNameQuestion();
		
		presentableQuestion.setId(mockQuestion.getId());
		presentableQuestion.setContent(mockQuestion.getContent());
		
		assertThat(presentableQuestion.getId(), is(mockQuestion.getId()));
		assertThat(presentableQuestion.getContent(), is(mockQuestion.getContent()));
		
	}

}
