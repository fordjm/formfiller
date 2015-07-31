package formfiller.transactions;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.AbstractResponse;
import formfiller.entities.Constrainable;
import formfiller.entities.ConstraintDecorator;
import formfiller.entities.FreeEntryFormat;
import formfiller.enums.ConstraintName;
import formfiller.persistence.FormWidget;

public class AddFreeEntryFormatTest<T> {
	
	public static class GivenAClearedFormWidget{
		@Before
		public void givenAClearedFormWidget(){
			FormWidget.clear();
		}
	}
	
	static ConstraintDecorator<?> getFormatConstraint(){
		ConstraintDecorator<?> result = null;
		Map<ConstraintName, Constrainable<?>> c = FormWidget.getConstraints();
		Constrainable<?> formatConstrainable = c.get(ConstraintName.FORMAT_FREE_ENTRY);
		if (formatConstrainable instanceof ConstraintDecorator<?>)
			result = (ConstraintDecorator<?>) formatConstrainable;
		return result;
	}
	
	public static abstract class GivenAFreeEntryFormat<T>{
		FreeEntryFormat<T> format;
		AbstractResponse<T> response;
		
		protected abstract int makeResponseId();
		protected abstract T makeResponseContent();
		
		protected AbstractResponse<T> makeMockResponse(){
			AbstractResponse<T> result = mock(AbstractResponse.class);
			int id = makeResponseId();
			T content = makeResponseContent();
			when (result.getId()).thenReturn(id);
			when (result.getContent()).thenReturn(content);			
			return result;
		}
		
		@Before
		public void givenAFreeEntryFormat(){
			response = makeMockResponse();
			format = new FreeEntryFormat<T>();
			format.wrap(response);
		}
	}

	@Test
	public void canAddFreeEntryFormat() {
		Transaction t = new AddFreeEntryFormat<T>();
		t.execute();
		
		Map<ConstraintName, Constrainable<?>> c = FormWidget.getConstraints();
		Constrainable<?> f = c.get(ConstraintName.FORMAT_FREE_ENTRY);
		assertTrue(f instanceof FreeEntryFormat);
	}
}
