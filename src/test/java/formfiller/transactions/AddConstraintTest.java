package formfiller.transactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.AnswerType;
import formfiller.entities.Constraint;
import formfiller.entities.FreeEntryFormat;
import formfiller.entities.SelectionFormat;
import formfiller.entities.ValueMaximum;
import formfiller.entities.ValueMinimum;
import formfiller.enums.ContentConstraint;
import formfiller.persistence.FormWidget;

@RunWith(HierarchicalContextRunner.class)
public class AddConstraintTest {
	static Transaction addConstraint;
	static Map<ContentConstraint, Constraint> constraintsMap;
	static Constraint constraint;
	static void setConstraintsMap() {
		constraintsMap = FormWidget.getConstraints();
	}
	static void setConstraint(ContentConstraint contentConstraint) {
		constraint = constraintsMap.get(contentConstraint);
	}
	public class FreeEntryFormatContext<T>{
		@Before
		public void givenAFreeEntryFormat(){
			addConstraint = new AddFreeEntryFormat();
		}
		@Test
		public void whenAddConstraintExecutes_ThenFormWidgetHasConstraint(){
			addConstraint.execute();		
			setConstraintsMap();		
			setConstraint(ContentConstraint.FORMAT);
			assertTrue(constraint instanceof FreeEntryFormat);
		}		
	}
	public class SelectionFormatContext<T>{
		List<Object> selections;
		@Before
		public void givenASelectionFormat(){
			selections = Arrays.asList((Object) "a", (Object) "b", (Object) "c");
			addConstraint = new AddSelectionFormat<Object>(selections);
		}
		@Test
		public void whenAddConstraintExecutes_ThenFormWidgetHasConstraint(){
			addConstraint.execute();		
			setConstraintsMap();		
			setConstraint(ContentConstraint.FORMAT);
			SelectionFormat selectionFormat = (SelectionFormat) constraint;
			assertTrue(constraint instanceof SelectionFormat);
			assertEquals(selections, selectionFormat.getSelections());
		}
	}
	public class ResponseTypeContext<T>{
		Type type;
		@Before
		public void givenAResponseType(){
			type = String.class;
			addConstraint = new AddResponseType(type);
		}
		@Test
		public void whenAddConstraintExecutes_ThenFormWidgetHasConstraint(){
			addConstraint.execute();		
			setConstraintsMap();
			setConstraint(ContentConstraint.TYPE);
			AnswerType responseType = (AnswerType) constraint;
			assertTrue(constraint instanceof AnswerType);
			assertEquals(type, responseType.getType());
		}
	}
	public class ValueMaximumContext<T>{
		T maximum;
		@Before
		public void givenAValueMaximum(){
			maximum = (T) "m";
			addConstraint = new AddValueMaximum<T>(maximum);
		}
		@Test
		public void whenAddConstraintExecutes_ThenFormWidgetHasConstraint(){
			addConstraint.execute();		
			setConstraintsMap();
			setConstraint(ContentConstraint.VALUE_MAXIMUM);
			assertTrue(constraint instanceof ValueMaximum);
		}
	}
	public class ValueMinimumContext<T>{
		T minimum;
		@Before
		public void givenAValueMinimum(){
			minimum = (T) "m";
			addConstraint = new AddValueMinimum<T>(minimum);
		}
		@Test
		public void whenAddConstraintExecutes_ThenFormWidgetHasConstraint(){
			addConstraint.execute();		
			setConstraintsMap();
			setConstraint(ContentConstraint.VALUE_MINIMUM);
			assertTrue(constraint instanceof ValueMinimum);
		}
	}
}
