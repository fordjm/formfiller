package formfiller.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.Constrainable;
import formfiller.entities.NullPrompt;
import formfiller.entities.NullResponse;
import formfiller.entities.Prompt;
import formfiller.entities.Response;
import formfiller.enums.ContentConstraint;
import formfiller.utilities.TestUtil;

@RunWith(HierarchicalContextRunner.class)
public class FormWidgetTest {
	static Prompt oldPrompt;
	static Prompt addedPrompt;
	static Prompt newPrompt;
	static Response<?> oldResponse;
	static Response<?> addedResponse;
	static Response<?> newResponse;

	static void assertPromptIsNullPrompt() {
		assertTrue(FormWidget.getPrompt() instanceof NullPrompt);
		assertEquals("", FormWidget.getPrompt().getId());
		assertEquals("", FormWidget.getPrompt().getContent());
	}

	static void assertResponseIsNullResponse() {
		assertTrue(FormWidget.getResponse() instanceof NullResponse);
		assertEquals(-1, FormWidget.getResponse().getId());
		assertEquals("", FormWidget.getResponse().getContent());
	}

	static void assertWidgetHasNoConstraints(){
		Collection<Constrainable<?>> constraintValues = getConstraintValues();
		assertTrue(constraintValues.size() == 0);
	}

	static Collection<Constrainable<?>> getConstraintValues(){
		Map<ContentConstraint, Constrainable<?>> constraintsMap = getConstraintsMap();
		return constraintsMap.values();
	}

	static Map<ContentConstraint, Constrainable<?>> getConstraintsMap(){
		return FormWidget.getConstraints();
	}

	static Prompt makeMockNamePrompt() {
		return makeMockPrompt("name", "What is your name?");
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

	static void updateResponseFieldValues() {
		oldResponse = FormWidget.getResponse();
		FormWidget.addResponse(addedResponse);
		newResponse = FormWidget.getResponse();
	}

	void setNewResponse() {
		newResponse = FormWidget.getResponse();
	}

	void assertResponseDidNotChange() {
		assertNotSame(addedResponse, newResponse);
		assertSame(oldResponse, newResponse);
	}

	void assertResponseChanged() {
		assertNotSame(oldResponse, addedResponse);
		assertNotSame(oldResponse, newResponse);
		assertSame(addedResponse, newResponse);		
	}

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
				addedPrompt = makeMockNamePrompt();
			}

			@Test
			public void whenAddPromptRuns_ThenWidgetAddsNewPrompt(){
				FormWidget.addPrompt(addedPrompt);
				setNewPromptValue();
				assertPromptChanged();
			}
		}
	}

	public class GivenAClearedWidget{

		@Before
		public void givenWidgetInStartState(){
			FormWidget.clear();
		}

		public class GivenFieldsHaveDefaultValues {

			@Test
			public void whenIsRequiredRuns_ThenItReturnsFalse(){
				assertFalse(FormWidget.isResponseRequired());
			}

			@Test
			public void whenGetPromptRuns_ThenItReturnsNullPrompt(){
				assertPromptIsNullPrompt();
			}

			@Test
			public void whenGetResponseRuns_ThenItReturnsNullResponse(){
				assertResponseIsNullResponse();
			}	

			@Test
			public void whenConstraintValuesSizeChecked_ThenItReturnsZero(){
				assertWidgetHasNoConstraints();
			}

			public class GivenAnInvalidResponse{
				@Before
				public void givenAnInvalidResponse(){
					addedResponse = new NullResponse();
				}

				@Test
				public void whenSetResponseRuns_ThenResponseDoesNotChange(){
					updateResponseFieldValues();
					assertResponseDidNotChange();			
				}
			}

			public class GivenAValidResponse{

				@Before
				public <T> void givenAValidResponse(){
					addedResponse = TestUtil.makeMockResponse(0, (T) "Joe", true);
				}

				@Test
				public void whenSetResponseRuns_ThenWidgetSetsNewResponse(){
					updateResponseFieldValues();
					assertResponseDidNotChange();
				}	
			}
		}
	}

	public class GivenValidPromptAdded{

		public class GivenAnInvalidResponse{
			@Before
			public void givenAnInvalidResponse(){
				addedResponse = new NullResponse();
			}

			@Test
			public void whenSetResponseRuns_ThenResponseDoesNotChange(){
				updateResponseFieldValues();
				assertResponseDidNotChange();			
			}
		}

		public class GivenAValidResponse{

			@Before
			public <T> void givenAValidResponse(){
				addedResponse = TestUtil.makeMockResponse(0, (T) "Joe", true);
			}

			@Test
			public void whenSetResponseRuns_ThenWidgetSetsNewResponse(){
				updateResponseFieldValues();
				assertResponseChanged();
			}	
		}

		Prompt makeMockAgePrompt() {
			return makeMockPrompt("age", "What is your age?");
		}

		Response<Integer> makeMockAgeResponse() {
			return TestUtil.makeMockResponse(0, 47, true);
		}

		@Before
		public void givenValidPromptAdded(){
			FormWidget.clear();
			addedPrompt = makeMockNamePrompt();
			FormWidget.addPrompt(addedPrompt);
		}

		public class GivenResponseIsNotRequired {

			@Before
			public void givenResponseIsNotRequired(){
				FormWidget.setRequired(false);		
				addedPrompt = makeMockAgePrompt();	
			}

			@Test
			public void whenAddPromptRuns_ThenItAddsANewPrompt(){
				FormWidget.addPrompt(addedPrompt);
				setNewPromptValue();
				assertPromptChanged();
			}

			@Test
			public void whenAddResponseRuns_ThenItAddsANewResponse(){
				addedResponse = makeMockAgeResponse();
				updateResponseFieldValues();
				assertResponseChanged();
			}

			// TODO:  Cannot add two responses (must add Cardinality)
		}

		public class GivenResponseIsRequired{
			@Before
			public void givenResponseIsRequired(){
				oldPrompt = makeMockNamePrompt();
				FormWidget.addPrompt(oldPrompt);
				FormWidget.setRequired(true);			
			}

			@Test
			public void whenResponseIsRequired_ThenRequiredReturnsTrue(){
				assertTrue(FormWidget.isResponseRequired());
			}

			public class GivenResponseIsAbsent{
				@Test(expected = IllegalStateException.class)
				public void whenResponseIsAbsent_ThenAddPromptThrowsException(){
					addedPrompt = makeMockAgePrompt();
					FormWidget.addPrompt(addedPrompt);
				}
			}

			public class GivenResponseIsPresent{
				@Before
				public void givenResponseIsPresent(){
					addedResponse = makeMockAgeResponse();
					FormWidget.addResponse(addedResponse);					
				}

				@Test
				public void whenAddPromptRuns_ThenItAddsANewPrompt(){
					addedPrompt = makeMockAgePrompt();
					oldPrompt = FormWidget.getPrompt();
					FormWidget.addPrompt(addedPrompt);
					setNewPromptValue();
					assertPromptChanged();
				}
			}
		}
	}
}
