package formfiller.entities.answerFormat;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.AnswerImpl;
import formfiller.entities.format.Format.MaximumLessThanMinimum;
import formfiller.entities.format.Unstructured;

//	TODO:	Unstructured format takes at most one answer.
public class UnstructuredAnswerFormatTest {

	private Unstructured format;
	
	@Before
	public void setUp() {
		format = new Unstructured();
	}

	@Test
	public void testUnstructuredSingleAnswerFormat() {
		assertThat(format.matchesFormat(null), is(false));
		assertThat(format.matchesFormat(""), is(true));
		assertThat(format.matchesCardinality(makeLegalSingleAnswer()), is(true));
		assertThat(format.matchesCardinality(makeLegalMultipleAnswer()), is(false));
	}
	
	@Test(expected = MaximumLessThanMinimum.class)
	public void settingMinGreaterThanOneThrowsException() {
		format.setMinAnswers(2);
	}
	
	@Test(expected = IllegalStateException.class)
	public void settingMaxToAnyValueThrowsException() {
		format.setMaxAnswers(2);
	}

	private AnswerImpl makeLegalSingleAnswer() {
		AnswerImpl result = new AnswerImpl();
		result.setId("questionId");
		result.setContent("answerContent");
		return result;
	}
	
	//	TODO:	Should have no legal multiple answers?
	private AnswerImpl makeLegalMultipleAnswer() {
		AnswerImpl result = new AnswerImpl();
		result.setId("questionId");
		result.setContent(Arrays.asList(
				"answerContent0", "answerContent1", "answerContent2"));
		return result;
	}
}
