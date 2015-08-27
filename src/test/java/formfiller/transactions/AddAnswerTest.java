package formfiller.transactions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.Constraint;
import formfiller.entities.NoConstraint;
import formfiller.entities.Prompt;
import formfiller.entities.AnswerType;
import formfiller.persistence.FormWidget;
import formfiller.utilities.MockCreation;

@RunWith(HierarchicalContextRunner.class)
public class AddAnswerTest<T> {
	static Transaction addAnswer;
	void makeAddResponse(T content){
		addAnswer = new AddResponse(content);
	}
	public class WidgetHasNoPromptContext{
		@Before
		public void givenWidgetHasNoPrompt(){
			FormWidget.clear();
		}
		public class GivenAnInvalidResponse{
			@Before
			public void givenAnInvalidResponse(){
				makeAddResponse(null);
			}
			@Test(expected = IllegalStateException.class)
			public void whenAddResponseRuns_ThenItThrowsAnException(){
				addAnswer.execute();
			}
		}
		public class GivenAValidResponse{
			T validContent = (T) "Joe";
			@Before
			public void givenAValidResponse(){
				makeAddResponse(validContent);
			}
			@Test(expected = IllegalStateException.class)
			public void whenAddResponseRuns_ThenItThrowsAnException(){
				addAnswer.execute();
				assertNotSame(validContent, FormWidget.getResponse().getContent());
			}	
		}
	}
	public class WidgetHasAPromptContext{
		@Before
		public void givenWidgetHasAPrompt(){
			FormWidget.clear();
			Prompt prompt = MockCreation.makeMockNameQuestion();
			FormWidget.addPrompt(prompt);
		}
		public class GivenAnInvalidResponse{
			@Before
			public void givenAnInvalidResponse(){
				makeAddResponse(null);
			}
			@Test(expected = IllegalArgumentException.class)
			public void whenAddResponseRuns_ThenItThrowsAnException(){
				addAnswer.execute();
			}
		}
		public class GivenAValidResponse{
			T validContent = (T) "Joe";
			@Before
			public void givenAValidResponse(){
				makeAddResponse(validContent);
			}
			public class GivenNoConstraints{
				@Test
				public void whenAddResponseRuns_ThenItAddsANewResponse(){
					addAnswer.execute();
					assertSame(validContent, FormWidget.getResponse().getContent());
				}	
			}
			public class GivenAnUnsatisfiedConstraint{
				Constraint mockConstraint;
				@Before
				public void givenAnUnsatisfiedConstraint(){
					mockConstraint = MockCreation.makeMockConstraint(0, false);
					addConstraints(mockConstraint);
				}
				@Test(expected = IllegalArgumentException.class)
				public void whenAddResponseRuns_ThenItThrowsAnException(){
					addAnswer.execute();
				}
			}
			// TODO:  Figure out proper constraint mocking.
			public class GivenASatisfiedConstraint{
				Constraint mockConstraint;
				Constraint realConstraint = new NoConstraint();
				@Before
				public void givenASatisfiedConstraint(){
					mockConstraint = MockCreation.makeMockConstraint(0, true);
					addConstraints(realConstraint);
				}
				@Test
				public void whenAddResponseRuns_ThenItAddsANewResponse(){
					addAnswer.execute();
					assertSame(validContent, FormWidget.getResponse().getContent());
				}
			}
			void addConstraints(Constraint... constraints){
				for (Constraint constraint : constraints)
					FormWidget.addConstraint(constraint);
			}
			public class GivenTwoConstraintsWhereOneIsUnsatisfied{
				Constraint format = new NoConstraint();
				Constraint responseType = new AnswerType(Double.class);
				@Before
				public void givenTwoConstraintsWhereOneIsUnsatisfied(){
					addConstraints(format, responseType);
				}
				@Test(expected = IllegalArgumentException.class)
				public void whenAddResponseRuns_ThenItThrowsAnException(){
					addAnswer.execute();
				}
			}
		}
	}
}