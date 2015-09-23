package formfiller.entities.answerFormat;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import formfiller.entities.Answer;

public class UnstructuredAnswerFormatTest {

	@Test
	public void testUnstructuredSingleAnswerFormat() {
		Unstructured format = new Unstructured(0, 1);
		assertThat(format.matchesFormat(null), is(false));
		assertThat(format.matchesFormat(""), is(true));
		assertThat(format.matchesCardinality(makeLegalSingleAnswer()), is(true));
		assertThat(format.matchesCardinality(makeLegalMultipleAnswer()), is(false));
	}

	@Test
	public void testUnstructuredMultipleAnswerFormat() {
		Unstructured format = new Unstructured(2, 5);
		assertThat(format.matchesFormat(null), is(false));
		assertThat(format.matchesFormat(""), is(true));
		assertThat(format.matchesCardinality(makeLegalSingleAnswer()), is(false));
		assertThat(format.matchesCardinality(makeLegalMultipleAnswer()), is(true));
	}

	private Answer makeLegalSingleAnswer() {
		Answer result = new Answer();
		result.questionId = "questionId";
		result.content = "answerContent";
		return result;
	}
	
	private Answer makeLegalMultipleAnswer() {
		Answer result = new Answer();
		result.questionId = "questionId";
		result.content = Arrays.asList("answerContent0", "answerContent1", "answerContent2");
		return result;
	}
}
