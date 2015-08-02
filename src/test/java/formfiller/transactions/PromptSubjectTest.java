package formfiller.transactions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import formfiller.entities.Prompt;
import formfiller.persistence.FormWidget;

@RunWith(HierarchicalContextRunner.class)
public class PromptSubjectTest {

	private Transaction transaction;
	private Prompt initialPrompt;
	private Prompt currentPrompt;

	PromptSubject makeTransaction(String promptId, String promptContent, boolean required) {
		return new PromptSubject(promptId, promptContent, required);
	}
	
	@BeforeClass
	public static void givenAClearedFormWidget(){
		FormWidget.clear();
	}
	
	@Test
	public void whenPromptSubjectRuns_ThenWidgetAddsNewPrompt(){
		initialPrompt = FormWidget.getPrompt();
		transaction = makeTransaction("name", "What is your name?", false);
		transaction.execute();
		currentPrompt = FormWidget.getPrompt();
		assertNotSame(initialPrompt, currentPrompt);
		assertSame("name", currentPrompt.getId());
		assertSame("What is your name?", currentPrompt.getContent());
		assertFalse(FormWidget.isResponseRequired());
	}
	
	public class ResponseNotRequiredContext{
		@Before
		public void givenResponseIsNotRequired(){
			FormWidget.setRequired(false);
		}
		
		@Test
		public void whenPromptSubjectRuns_ThenWidgetAddsNewPrompt(){
			initialPrompt = FormWidget.getPrompt();	
			transaction = makeTransaction("name", "What is your name?", false);		
			transaction.execute();
			currentPrompt = FormWidget.getPrompt();
			assertNotSame(initialPrompt, currentPrompt);
		}		
	}
	
	public class ResponseRequiredContext{
		@Before
		public void givenResponseIsRequired(){
			FormWidget.setRequired(true);
		}
		
		@Test(expected = IllegalStateException.class)
		public void whenPromptSubjectRuns_ThenWidgetThrowsException(){
			initialPrompt = FormWidget.getPrompt();
			transaction = makeTransaction("name", "What is your name?", true);
			transaction.execute();
			currentPrompt = FormWidget.getPrompt();
			assertSame(initialPrompt, currentPrompt);
		}		
	}
}