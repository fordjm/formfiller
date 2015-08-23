package formfiller.transactions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.AnswerImpl;
import formfiller.entities.Constraint;
import formfiller.entities.NoQuestion;
import formfiller.enums.Cardinality;
import formfiller.enums.ContentConstraint;
import formfiller.persistence.FormWidget;

@RunWith(HierarchicalContextRunner.class)
public class ClearWidgetTest {
	Transaction clearWidget;
	
	static void assertGetPromptGetsANullPrompt() {
		assertTrue(FormWidget.getPrompt() instanceof NoQuestion);
		assertEquals("", FormWidget.getPrompt().getId());
		assertEquals("", FormWidget.getPrompt().getContent());
	}
	static void assertGetResponseGetsANullResponse() {
		assertTrue(FormWidget.getResponse().equals(AnswerImpl.NONE));
		assertThat(FormWidget.getResponse().getId(), is(-1));
		assertEquals("", FormWidget.getResponse().getContent());
	}
	static void assertWidgetHasNoConstraints(){
		Collection<Constraint> constraintValues = getConstraintValues();
		assertThat(constraintValues.size(), is(0));
	}
	static Collection<Constraint> getConstraintValues(){
		Map<ContentConstraint, Constraint> constraintsMap = getConstraintsMap();
		return constraintsMap.values();
	}
	static Map<ContentConstraint, Constraint> getConstraintsMap(){
		return FormWidget.getConstraints();
	}
	
	public class GivenAClearedWidget{
		@Before
		public void givenAClearedWidget(){
			clearWidget = new ClearWidget();
			clearWidget.execute();
		}
		@Test
		public void whenIsRequiredRuns_ThenItReturnsFalse(){
			assertThat(FormWidget.isResponseRequired(), is(false));
		}
		@Test
		public void whenGetCardinalityRuns_ThenItReturnsSingle(){
			assertThat(FormWidget.getCardinality(), is(Cardinality.SINGLE));
		}
		@Test
		public void whenGetPromptRuns_ThenItReturnsNullPrompt(){
			assertGetPromptGetsANullPrompt();
		}
		@Test
		public void whenGetResponseRuns_ThenItReturnsNullResponse(){
			assertGetResponseGetsANullResponse();
		}	
		@Test
		public void whenConstraintValuesSizeChecked_ThenItReturnsZero(){
			assertWidgetHasNoConstraints();
		}
	}
}
