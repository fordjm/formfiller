package formfiller.entities.answerFormat;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import formfiller.entities.AnswerImpl;
import formfiller.entities.format.Unstructured;

public class UnstructuredAnswerFormatTest {

	@Test
	public void testUnstructuredSingleAnswerFormat() {
		Unstructured format = new Unstructured();
		assertThat(format.matchesFormat(null), is(false));
		assertThat(format.matchesFormat(""), is(true));
		assertThat(format.matchesCardinality(makeLegalSingleAnswer()), is(true));
		assertThat(format.matchesCardinality(makeLegalMultipleAnswer()), is(false));
	}

	@Test
	public void testUnstructuredMultipleAnswerFormat() {
		Unstructured format = new Unstructured();
		format.minAnswers = 2;
		format.maxAnswers = 5;
		
		assertThat(format.matchesFormat(null), is(false));
		assertThat(format.matchesFormat(""), is(true));
		assertThat(format.matchesCardinality(makeLegalSingleAnswer()), is(false));
		assertThat(format.matchesCardinality(makeLegalMultipleAnswer()), is(true));
	}

	private AnswerImpl makeLegalSingleAnswer() {
		AnswerImpl result = new AnswerImpl();
		result.setId("questionId");
		result.setContent("answerContent");
		return result;
	}
	
	private AnswerImpl makeLegalMultipleAnswer() {
		AnswerImpl result = new AnswerImpl();
		result.setId("questionId");
		result.setContent(Arrays.asList(
				"answerContent0", "answerContent1", "answerContent2"));
		return result;
	}
}
