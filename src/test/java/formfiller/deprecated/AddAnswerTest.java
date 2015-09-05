package formfiller.deprecated;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.Constraint;
import formfiller.entities.NoConstraint;
import formfiller.entities.Prompt;
import formfiller.deprecated.AddAnswer;
import formfiller.deprecated.FormWidget;
import formfiller.deprecated.Transaction;
import formfiller.entities.AnswerType;
import formfiller.utilities.*;

@RunWith(HierarchicalContextRunner.class)
public class AddAnswerTest<T> {
	static Transaction addAnswer;
	
	void makeAddAnswer(T content){
		addAnswer = new AddAnswer(content);
	}
	
	public class WidgetHasNoPromptContext{
		
		@Before
		public void givenWidgetHasNoPrompt(){
			FormWidget.clear();
		}
		
		public class GivenAnInvalidAnswer{
			
			@Before
			public void givenAnInvalidAnswer(){
				makeAddAnswer(null);
			}
			
			@Test(expected = IllegalStateException.class)
			public void whenAddAnswerRuns_ThenItThrowsAnException(){
				addAnswer.execute();
			}
		}
		
		public class GivenAValidAnswer{			
			T validContent = (T) "Joe";
			
			@Before
			public void givenAValidAnswer(){
				makeAddAnswer(validContent);
			}
			
			@Test(expected = IllegalStateException.class)
			public void whenAddAnswerRuns_ThenItThrowsAnException(){
				addAnswer.execute();
				assertNotSame(validContent, FormWidget.getAnswer().getContent());
			}	
		}
	}
	
	public class WidgetHasAPromptContext{
		Constraint mockConstraint;
		
		@Before
		public void givenWidgetHasAPrompt(){
			FormWidget.clear();
			Prompt prompt = QuestionMocker.makeMockNameQuestion();
			FormWidget.addPrompt(prompt);
		}
		
		public class GivenAnInvalidAnswer{
			
			@Before
			public void givenAnInvalidAnswer(){
				makeAddAnswer(null);
			}
			
			@Test(expected = IllegalArgumentException.class)
			public void whenAddAnswerRuns_ThenItThrowsAnException(){
				addAnswer.execute();
			}
		}
		
		public class GivenAValidAnswer{
			T validContent = (T) "Joe";
			
			@Before
			public void givenAValidAnswer(){
				makeAddAnswer(validContent);
			}
			
			public class GivenNoConstraints{
				@Test
				public void whenAddAnswerRuns_ThenItAddsANewAnswer(){
					addAnswer.execute();
					assertSame(validContent, FormWidget.getAnswer().getContent());
				}	
			}
			
			public class GivenAnUnsatisfiedConstraint{
				
				@Before
				public void givenAnUnsatisfiedConstraint(){
					mockConstraint = ConstraintMocker.makeMockConstraint(0, false);
					addConstraints(mockConstraint);
				}
				
				@Test(expected = IllegalArgumentException.class)
				public void whenAddAnswerRuns_ThenItThrowsAnException(){
					addAnswer.execute();
				}
			}
			
			// TODO:  Figure out proper constraint mocking.
			public class GivenASatisfiedConstraint{
				Constraint realConstraint = new NoConstraint();
				
				@Before
				public void givenASatisfiedConstraint(){
					mockConstraint = ConstraintMocker.makeMockConstraint(0, true);
					addConstraints(realConstraint);
				}
				
				@Test
				public void whenAddAnswerRunsOnce_ThenItAddsANewAnswer(){
					addAnswer.execute();
					assertSame(validContent, FormWidget.getAnswer().getContent());
				}
				
				@Test(expected = IllegalStateException.class)
				public void whenAddAnswerRunsTwice_ThenItThrowsAnException(){
					addAnswer.execute();
					makeAddAnswer((T) "Bob");
					addAnswer.execute();
				}
			}
			
			void addConstraints(Constraint... constraints){
				for (Constraint constraint : constraints)
					FormWidget.addConstraint(constraint);
			}
			
			public class GivenTwoConstraintsWhereOneIsUnsatisfied{
				Constraint noConstraint = new NoConstraint();
				Constraint answerType = new AnswerType(Double.class);
				
				@Before
				public void givenTwoConstraintsWhereOneIsUnsatisfied(){
					addConstraints(noConstraint, answerType);
				}
				
				@Test(expected = IllegalArgumentException.class)
				public void whenAddAnswerRuns_ThenItThrowsAnException(){
					addAnswer.execute();
				}
			}
		}
	}
}