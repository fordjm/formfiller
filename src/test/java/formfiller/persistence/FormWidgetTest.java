package formfiller.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.Constraint;
import formfiller.entities.NoQuestion;
import formfiller.entities.Prompt;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.enums.Cardinality;
import formfiller.enums.ContentConstraint;
import formfiller.utilities.AnswerMocker;
import formfiller.utilities.QuestionMocker;

@RunWith(HierarchicalContextRunner.class)
public class FormWidgetTest {
	static Prompt oldPrompt;
	static Prompt addedPrompt;
	static Prompt newPrompt;
	static Answer oldAnswer;
	static Answer addedAnswer;
	static Answer newAnswer;
	
	static void assertPromptIsANoQuestion() {
		assertTrue(FormWidget.getPrompt() instanceof NoQuestion);
		assertEquals("", FormWidget.getPrompt().getId());
		assertEquals("", FormWidget.getPrompt().getContent());
	}
	
	static void assertAnswerIsANoAnswer() {
		assertTrue(FormWidget.getAnswer().equals(AnswerImpl.NONE));
		assertEquals(-1, FormWidget.getAnswer().getId());
		assertEquals("", FormWidget.getAnswer().getContent());
	}
	
	static void assertWidgetHasNoConstraints(){
		Collection<Constraint> constraintValues = getConstraintValues();
		assertTrue(constraintValues.size() == 0);
	}
	
	static Collection<Constraint> getConstraintValues(){
		Map<ContentConstraint, Constraint> constraintsMap = getConstraintsMap();
		return constraintsMap.values();
	}
	
	static Map<ContentConstraint, Constraint> getConstraintsMap(){
		return FormWidget.getConstraints();
	}
	
	static Prompt makeMockNameQuestion() {
		return QuestionMocker.makeMockNameQuestion();
	}
	
	static Answer makeMockNameAnswer() {
		return AnswerMocker.makeMockAnswer(0, "Joe", true);
	}
	
	static Prompt makeMockPrompt(String id, String content){
		Prompt result = mock(Prompt.class);
		when (result.getId()).thenReturn(id);
		when (result.getContent()).thenReturn(content);
		return result;
	}
	
	static void assertPromptDidNotChange() {
		assertNotSame(oldPrompt, newPrompt);
		assertSame(oldPrompt, newPrompt);
	}
	
	static void assertPromptChanged() {
		assertNotSame(oldPrompt, addedPrompt);
		assertNotSame(oldPrompt, newPrompt);
		assertSame(addedPrompt, newPrompt);
	}
	
	static void setNewPromptValue() {
		newPrompt = FormWidget.getPrompt();
	}
	
	static void updateAnswerFieldValues(Answer... responses) {
		oldAnswer = FormWidget.getAnswer();
		addAnswers(responses);
		newAnswer = FormWidget.getAnswer();
	}
	
	static void addAnswers(Answer... responses) {
		for (Answer response : responses){
			addedAnswer = response;
			FormWidget.addAnswer(response);
		}
	}
	
	void setNewAnswer() {
		newAnswer = FormWidget.getAnswer();
	}
	
	void assertAnswerDidNotChange() {
		assertNotSame(addedAnswer, newAnswer);
		assertSame(oldAnswer, newAnswer);
	}
	
	void assertAnswerChanged() {
		assertNotSame(oldAnswer, addedAnswer);
		assertNotSame(oldAnswer, newAnswer);
		// assertSame(addedAnswer, newAnswer);		TODO:  Put this test elsewhere (doesn't belong here.)	
	}
	
	public class GivenAClearedWidget{
		
		@Before
		public void givenWidgetInStartState(){
			FormWidget.clear();
		}
		
		public class GivenFieldsHaveDefaultValues {
			
			public class GivenAPrompt{
				
				@Before
				public void givenAPrompt(){
					oldPrompt = FormWidget.getPrompt();					
				}
				
				public class GivenAnInvalidPrompt {
					
					@Before
					public void givenAnInvalidPrompt(){
						addedPrompt = null;
					}
					
					@Test(expected = IllegalArgumentException.class)
					public void whenAddPromptRuns_ThenPromptThrowsException(){
						FormWidget.addPrompt(addedPrompt);
						setNewPromptValue();
						assertPromptDidNotChange();
					}
				}
				
				public class GivenAValidPrompt{
					
					@Before
					public void givenAValidPrompt(){
						addedPrompt = makeMockNameQuestion();
					}
					
					@Test
					public void whenAddPromptRuns_ThenWidgetAddsNewPrompt(){
						FormWidget.addPrompt(addedPrompt);
						setNewPromptValue();
						assertPromptChanged();
					}
				}
			}			
			
			void assertAnswerCardinality(Cardinality cardinality) {
				assertSame(cardinality, FormWidget.getCardinality());
			}			
			
			public class GivenSingleAnswerCardinality{
				
				@Before
				public void givenSingleAnswerCardinality(){
					FormWidget.setCardinality(Cardinality.SINGLE);
				}
				
				@Test
				public void whenGetCardinalityRuns_ThenItReturnsSingle(){
					assertAnswerCardinality(Cardinality.SINGLE);
				}
				
				@Test
				public void whenWidgetIsCleared_ThenCardinalityIsSingle(){
					FormWidget.clear();
					assertAnswerCardinality(Cardinality.SINGLE);
				}
			}			
			
			public class GivenMultipleAnswerCardinality{
				
				@Before
				public void givenMultipleAnswerCardinality(){
					FormWidget.setCardinality(Cardinality.MULTI);
				}
				
				@Test
				public void whenGetCardinalityRuns_ThenItReturnsMulti(){
					assertAnswerCardinality(Cardinality.MULTI);
				}
				
				@Test
				public void whenWidgetIsCleared_ThenCardinalityIsSingle(){
					FormWidget.clear();
					assertAnswerCardinality(Cardinality.SINGLE);
				}
			}
			
			public class GivenAnInvalidAnswer{
				
				@Before
				public void givenAnInvalidAnswer(){
					addedAnswer = AnswerImpl.NONE;
				}

				@Test(expected = IllegalStateException.class)
				public void whenSetAnswerRuns_ThenAnswerDoesNotChange(){
					updateAnswerFieldValues(addedAnswer);
					assertAnswerDidNotChange();			
				}
			}
			
			public class GivenAValidAnswer{

				@Before
				public <T> void givenAValidAnswer(){
					addedAnswer = makeMockNameAnswer();
				}

				@Test(expected = IllegalStateException.class)
				public void whenAddAnswerRuns_ThenAnswerDoesNotChange(){
					updateAnswerFieldValues(addedAnswer);
					assertAnswerDidNotChange();
				}	
			}
		}
	}
	
	public class GivenValidQuestionWasAdded{
		
		public class GivenAnInvalidAnswer{
			
			@Before
			public void givenAnInvalidAnswer(){
				addedAnswer = AnswerImpl.NONE;
			}

			@Test(expected = IllegalArgumentException.class)
			public void whenSetAnswerRuns_ThenAnswerDoesNotChange(){
				updateAnswerFieldValues(addedAnswer);
				assertAnswerDidNotChange();			
			}
		}
		public class GivenAValidAnswer{

			@Before
			public <T> void givenAValidAnswer(){
				addedAnswer = makeMockNameAnswer();
			}

			@Test
			public void whenSetAnswerRuns_ThenWidgetSetsNewAnswer(){
				updateAnswerFieldValues(addedAnswer);
				assertAnswerChanged();
			}	
		}
		
		Prompt makeMockAgeQuestion() {
			return QuestionMocker.makeMockAgeQuestion();
		}
		
		Answer makeMockAgeAnswer(int age) {
			return AnswerMocker.makeMockAgeAnswer(age);
		}
		
		@Before
		public void givenValidPromptAdded(){
			FormWidget.clear();
			addedPrompt = makeMockNameQuestion();
			FormWidget.addPrompt(addedPrompt);
		}
		
		public class GivenAnswerIsNotRequired {
			
			@Before
			public void givenAnswerIsNotRequired(){
				FormWidget.setRequired(false);		
				addedPrompt = makeMockAgeQuestion();	
			}
			
			@Test
			public void whenAddPromptRuns_ThenItAddsANewPrompt(){
				FormWidget.addPrompt(addedPrompt);
				setNewPromptValue();
				assertPromptChanged();
			}			
			
			public class GivenPromptTakesASingleAnswer {
				
				@Before
				public void givenPromptTakesASingleAnswer(){
					FormWidget.setCardinality(Cardinality.SINGLE);
				}
				
				@Test
				public void whenAddAnswerRuns_ThenItAddsANewAnswer(){
					addedAnswer = makeMockAgeAnswer(47);
					updateAnswerFieldValues(addedAnswer);
					assertAnswerChanged();
				}
				
				@Test(expected = IllegalStateException.class)
				public void whenAddAnswerRunsTwice_ThenItThrowsAnException(){
					addedAnswer = makeMockAgeAnswer(47);
					Answer secondAnswer = AnswerMocker.makeMockAnswer(1, 52, true);
					updateAnswerFieldValues(addedAnswer, secondAnswer);
				}				
			}			
			
			public class GivenPromptTakesMultipleAnswers {
				Answer firstAnswer;
				Answer secondAnswer;
				
				private void assertAnswerContainsNAnswers(int n) {
					assertTrue(newAnswer.getContent() instanceof List);
					List<?> content = (List<?>) newAnswer.getContent();
					assertSame(n, content.size());
				}				
				
				@Before
				public void givenPromptTakesMultipleAnswers(){
					FormWidget.setCardinality(Cardinality.MULTI);
				}
				
				@Test
				public void whenAddAnswerRuns_ThenItAddsANewAnswer(){
					firstAnswer = makeMockAgeAnswer(47);
					updateAnswerFieldValues(firstAnswer);
					assertAnswerChanged();
					assertAnswerContainsNAnswers(1);
				}
				
				@Test
				public void whenAddAnswerRunsTwice_ThenItAddsTwoAnswers(){
					firstAnswer = makeMockAgeAnswer(47);
					secondAnswer = makeMockAgeAnswer(52);
					updateAnswerFieldValues(firstAnswer, secondAnswer);
					assertAnswerChanged();
					assertAnswerContainsNAnswers(2);
				}	
			}
		}
		public class GivenAnswerIsRequired{
			
			@Before
			public void givenAnswerIsRequired(){
				oldPrompt = makeMockNameQuestion();
				FormWidget.addPrompt(oldPrompt);
				FormWidget.setRequired(true);			
			}
			
			@Test
			public void whenAnswerIsRequired_ThenRequiredReturnsTrue(){
				assertTrue(FormWidget.isAnswerRequired());
			}
			
			public class GivenAnswerIsAbsent{
				
				@Test(expected = IllegalStateException.class)
				public void whenAnswerIsAbsent_ThenAddPromptThrowsException(){
					addedPrompt = makeMockAgeQuestion();
					FormWidget.addPrompt(addedPrompt);
				}
			}
			
			public class GivenAnswerIsPresent{
				
				@Before
				public void givenAnswerIsPresent(){
					addedAnswer = makeMockAgeAnswer(47);
					FormWidget.addAnswer(addedAnswer);					
				}
				
				@Test
				public void whenAddPromptRuns_ThenItAddsANewPrompt(){
					addedPrompt = makeMockAgeQuestion();
					oldPrompt = FormWidget.getPrompt();
					FormWidget.addPrompt(addedPrompt);
					setNewPromptValue();
					assertPromptChanged();
				}
			}
		}
	}
}
