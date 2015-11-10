package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

@RunWith(HierarchicalContextRunner.class)
public class CompositeAnswerTest {
	private CompositeAnswer compositeAnswer;
	private List<Object> contentList;

	@Before
	public void setUp() {
		compositeAnswer = new CompositeAnswer("id");
	}

	private Answer makeMockAnswer(Object content) {
		Answer result = Mockito.mock(Answer.class);
		Mockito.when(result.getId()).thenReturn("id");
		Mockito.when(result.getContent()).thenReturn(content);
		Mockito.when(result.isValid()).thenReturn(true);
		return result;
	}

	private List<Object> castContentAsList(Object content) {
		return (List<Object>) content;
	}
	
	public class EmptyCompositeAnswerContext {
		@Test
		public void hasCorrectDefaultValues() {
			contentList = castContentAsList(compositeAnswer.getContent());
			
			assertThat(compositeAnswer.getId(), is("id"));
			assertThat(compositeAnswer.isValid(), is(false));
			assertThat(compositeAnswer.size(), is(0));
			assertThat(contentList.size(), is(0));
		}
		
		@Test
		public void canSetId() {
			compositeAnswer.setId("newId");
			
			assertThat(compositeAnswer.getId(), is("newId"));
			assertThat(compositeAnswer.isValid(), is(false));
		}
		
		@Test(expected = IllegalStateException.class)
		public void cannotSetContent() {
			compositeAnswer.setContent("new content");			
		}
		
		@Test
		public void canAddAnswer() {
			Answer mockAnswer = makeMockAnswer("content");
			
			compositeAnswer.add(mockAnswer);
			contentList = castContentAsList(compositeAnswer.getContent());

			assertThat(compositeAnswer.isValid(), is(true));
			assertThat(compositeAnswer.size(), is(1));
			assertThat(compositeAnswer.contains(mockAnswer), is(true));
			assertThat(contentList.contains(mockAnswer.getContent()), is(true));
		}
		
		@Test(expected = IllegalStateException.class)
		public void removingAnswerThrowsException() {
			Answer mockAnswer = makeMockAnswer("content");
			
			compositeAnswer.remove(mockAnswer);
		}
	}
	
	//	TODO:	Test invalid answer.
	
	public class ContainsOneAnswerContext {
		private Answer mockAddedAnswer;

		@Before
		public void setUp() {
			compositeAnswer.add(makeRealAnswerHack("content 0"));
			mockAddedAnswer = makeMockAnswer("content 1");
		}

		private Answer makeRealAnswerHack(String content) {
			Answer result = new AnswerImpl();
			result.setId("id");
			result.setContent(content);
			return result;
		}
		
		@Test
		public void hasCorrectDefaultValues() {
			contentList = castContentAsList(compositeAnswer.getContent());
			
			assertThat(compositeAnswer.getId(), is("id"));
			assertThat(compositeAnswer.isValid(), is(true));
			assertThat(compositeAnswer.size(), is(1));
			assertThat(contentList.size(), is(1));
			assertThat(contentList.contains("content 0"), is(true));
		}
		
		//	TODO:	How to update ID on mock answer?
		@Test
		public void canSetId() {
			compositeAnswer.setId("newId");
			
			assertThat(compositeAnswer.getId(), is("newId"));
			assertThat(compositeAnswer.isValid(), is(true));
		}
		
		@Test
		public void canSetContent() {
			compositeAnswer.setContent("new content");
			contentList = castContentAsList(compositeAnswer.getContent());
			
			assertThat(contentList.size(), is(1));
			assertThat(contentList.contains("new content"), is(true));
		}
		
		@Test
		public void canAddAnswer() {			
			compositeAnswer.add(mockAddedAnswer);
			contentList = castContentAsList(compositeAnswer.getContent());

			assertThat(compositeAnswer.isValid(), is(true));
			assertThat(compositeAnswer.size(), is(2));
			assertThat(compositeAnswer.contains(mockAddedAnswer), is(true));
			assertThat(contentList.size(), is(2));
			assertThat(contentList.contains("content 0"), is(true));
			assertThat(contentList.contains("content 1"), is(true));
		}
		
		@Test
		public void canRemoveAnswer() {
			compositeAnswer.add(mockAddedAnswer);
			
			compositeAnswer.remove(mockAddedAnswer);
			contentList = castContentAsList(compositeAnswer.getContent());

			assertThat(compositeAnswer.isValid(), is(true));
			assertThat(compositeAnswer.size(), is(1));
			assertThat(compositeAnswer.contains(mockAddedAnswer), is(false));
			assertThat(contentList.size(), is(1));
			assertThat(contentList.contains("content 0"), is(true));
			assertThat(contentList.contains("content 1"), is(false));
		}
		
	}
	
	//	TODO:	ContainsTwoAnswersContext

}
