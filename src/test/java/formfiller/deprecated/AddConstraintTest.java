package formfiller.deprecated;

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
import formfiller.deprecated.AddAnswerType;
import formfiller.deprecated.AddNoConstraint;
import formfiller.deprecated.AddSelectionConstraint;
import formfiller.deprecated.AddValueMaximum;
import formfiller.deprecated.AddValueMinimum;
import formfiller.deprecated.FormWidget;
import formfiller.deprecated.Transaction;
import formfiller.entities.AnswerType;
import formfiller.entities.Constraint;
import formfiller.entities.NoConstraint;
import formfiller.entities.SelectionConstraint;
import formfiller.entities.ValueMaximum;
import formfiller.entities.ValueMinimum;
import formfiller.enums.ContentConstraint;

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
	
	public class NoConstraintContext<T>{
		
		@Before
		public void givenANoConstraint(){
			addConstraint = new AddNoConstraint();
		}
		
		@Test
		public void whenAddConstraintExecutes_ThenFormWidgetHasConstraint(){
			addConstraint.execute();		
			setConstraintsMap();		
			setConstraint(ContentConstraint.NONE);
			assertTrue(constraint instanceof NoConstraint);
		}		
	}
	
	public class SelectionFormatContext<T>{
		List<Object> selections;
		
		@Before
		public void givenASelectionFormat(){
			selections = Arrays.asList((Object) "a", (Object) "b", (Object) "c");
			addConstraint = new AddSelectionConstraint<Object>(selections);
		}
		
		@Test
		public void whenAddConstraintExecutes_ThenFormWidgetHasAConstraint(){
			addConstraint.execute();		
			setConstraintsMap();		
			setConstraint(ContentConstraint.SELECTION);
			SelectionConstraint selectionFormat = (SelectionConstraint) constraint;
			assertTrue(constraint instanceof SelectionConstraint);
			assertEquals(selections, selectionFormat.getSelections());
		}
	}
	
	public class AnswerTypeContext<T>{
		Type type;
		
		@Before
		public void givenAnAnswerType(){
			type = String.class;
			addConstraint = new AddAnswerType(type);
		}
		
		@Test
		public void whenAddConstraintExecutes_ThenFormWidgetHasAConstraint(){
			addConstraint.execute();		
			setConstraintsMap();
			setConstraint(ContentConstraint.TYPE);
			AnswerType answerType = (AnswerType) constraint;
			assertTrue(constraint instanceof AnswerType);
			assertEquals(type, answerType.getType());
		}
	}
}
