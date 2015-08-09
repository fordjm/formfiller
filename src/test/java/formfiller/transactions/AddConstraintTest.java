package formfiller.transactions;

import static org.junit.Assert.*;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.Constrainable;
import formfiller.entities.Constraint;
import formfiller.entities.FreeEntryFormat;
import formfiller.entities.ResponseType;
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
		List<T> selections;
		@Before
		public void givenASelectionFormat(){
			selections = Arrays.asList((T) "a", (T) "b", (T) "c");
			addConstraint = new AddSelectionFormat<T>(selections);
		}
		@Test
		public void whenAddConstraintExecutes_ThenFormWidgetHasConstraint(){
			addConstraint.execute();		
			setConstraintsMap();		
			setConstraint(ContentConstraint.FORMAT);
			SelectionFormat<T> selectionFormat = (SelectionFormat<T>) constraint;
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
			ResponseType responseType = (ResponseType) constraint;
			assertTrue(constraint instanceof ResponseType);
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
