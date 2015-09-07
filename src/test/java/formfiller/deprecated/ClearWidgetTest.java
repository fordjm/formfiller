package formfiller.deprecated;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.deprecated.ClearWidget;
import formfiller.deprecated.FormWidget;
import formfiller.deprecated.Transaction;
import formfiller.entities.Answer;
import formfiller.entities.Constraint;
import formfiller.entities.NoQuestion;
import formfiller.enums.Cardinality;
import formfiller.enums.ContentConstraint;

@RunWith(HierarchicalContextRunner.class)
public class ClearWidgetTest {
	Transaction clearWidget;
	
	static void assertGetPromptGetsANullPrompt() {
		assertTrue(FormWidget.getPrompt() instanceof NoQuestion);
		assertEquals("", FormWidget.getPrompt().getId());
		assertEquals("", FormWidget.getPrompt().getContent());
	}
	
	static void assertGetAnswerGetsANullAnswer() {
		assertTrue(FormWidget.getAnswer().equals(Answer.NONE));
		assertThat(FormWidget.getAnswer().getId(), is(-1));
		assertEquals("", FormWidget.getAnswer().getContent());
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
			assertThat(FormWidget.isAnswerRequired(), is(false));
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
		public void whenGetAnswerRuns_ThenItReturnsNullAnswer(){
			assertGetAnswerGetsANullAnswer();
		}	
		
		@Test
		public void whenConstraintValuesSizeChecked_ThenItReturnsZero(){
			assertWidgetHasNoConstraints();
		}
	}
}
