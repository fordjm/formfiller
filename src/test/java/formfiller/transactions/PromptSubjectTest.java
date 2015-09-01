package formfiller.transactions;

import static org.junit.Assert.*;

import org.junit.Before;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import formfiller.entities.Prompt;
import formfiller.persistence.FormWidget;
import formfiller.utilities.*;

@RunWith(HierarchicalContextRunner.class)
public class PromptSubjectTest {
	private Transaction promptSubject;

	PromptSubject makeTransaction(String promptId, String promptContent) {
		return new PromptSubject(promptId, promptContent, false);
	}
	
	PromptSubject makeTransaction(String promptId, String promptContent, boolean required) {
		return new PromptSubject(promptId, promptContent, required);
	}
	
	void makeAgePromptTransaction(){
		promptSubject = makeTransaction("age", "What is your age?");
	}			

	public class WidgetHasNoPromptContext{
		
		@Before
		public void givenWidgetHasNoPrompt(){
			FormWidget.clear();
		}
		
		public class GivenAPrompt{
			
			public class GivenAnInvalidPrompt {
				
				@Before
				public void givenAnInvalidPrompt(){
					promptSubject = makeTransaction(null, null);
				}
				
				@Test(expected = IllegalArgumentException.class)
				public void whenAddPromptRuns_ThenPromptThrowsException(){
					promptSubject.execute();
				}
			}
			
			public class GivenAValidPrompt{
				
				@Before
				public void givenAValidPrompt(){
					promptSubject = makeTransaction("name", "What is your name?");
				}
				
				@Test
				public void whenAddPromptRuns_ThenWidgetAddsNewPrompt(){
					promptSubject.execute();
					assertEquals("name", FormWidget.getPrompt().getId());
					assertEquals("What is your name?", FormWidget.getPrompt().getContent());
				}
			}
		}
		
		public class WidgetHasAPromptContext{
			Prompt prompt = QuestionMocker.makeMockNameQuestion();
			Prompt newPrompt;
			
			@Before
			public void givenWidgetHasAPrompt(){
				FormWidget.clear();
				FormWidget.addPrompt(prompt);
			}
			
			public class ResponseNotRequiredContext{
				
				@Before
				public void givenResponseIsNotRequired(){
					FormWidget.setRequired(false);
				}
				
				@Test
				public void whenPromptSubjectRuns_ThenWidgetAddsNewPrompt(){
					makeAgePromptTransaction();
					promptSubject.execute();
					newPrompt = FormWidget.getPrompt();
					assertEquals("age", newPrompt.getId());
					assertEquals("What is your age?", newPrompt.getContent());
				}		
			}
			
			public class ResponseRequiredContext{
				
				@Before
				public void givenResponseIsRequired(){
					FormWidget.setRequired(true);
				}
				
				@Test(expected = IllegalStateException.class)
				public void whenPromptSubjectRuns_ThenWidgetThrowsException(){
					makeAgePromptTransaction();
					promptSubject.execute();
				}		
			} 
		}
		
		public class AddedARequiredPromptContext{
			
			@Before
			public void givenARequiredPromptWasAdded(){
				promptSubject = makeTransaction("name", "What is your name?", true);
				promptSubject.execute();
			}
			
			@Test(expected = IllegalStateException.class)
			public void whenPromptSubjectRuns_ThenWidgetThrowsException(){
				makeAgePromptTransaction();
				promptSubject.execute();
			}	
		}
	}
}