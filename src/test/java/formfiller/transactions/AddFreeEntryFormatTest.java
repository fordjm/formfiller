package formfiller.transactions;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import formfiller.entities.AbstractResponse;
import formfiller.entities.Constrainable;
import formfiller.entities.ConstraintDecorator;
import formfiller.entities.FreeEntryFormat;
import formfiller.entities.ResponseImpl;
import formfiller.persistence.FormWidget;
import formfiller.utilities.ConstraintName;

public class AddFreeEntryFormatTest<T> {
	
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
	
	public static class GivenAnInvalidResponse<T> extends GivenAFreeEntryFormat<T>{

		@Override
		protected int makeResponseId() {
			return -1;
		}

		@Override
		protected T makeResponseContent() {
			return null;
		}		
		
		@Ignore
		@Test
		// Ugly comment:  Format does change.
		// Should check response validity in Transaction.
		// Must update transaction to handle Response objects.
		public void whenAddFreeEntryFormatRuns_ThenFormatDoesNotChange(){
			ConstraintDecorator<?> defaultFormat = getFormatConstraint();
			Transaction t = new AddFreeEntryFormat<T>(response);
			t.execute();
			
			Map<ConstraintName, Constrainable<?>> c = FormWidget.getConstraints();
			Constrainable<?> f = c.get(ConstraintName.FORMAT_FREE_ENTRY);
			assertTrue(f instanceof FreeEntryFormat);
			assertSame(defaultFormat, getFormatConstraint());
		}
		
	}

	@Test
	public void canAddFreeEntryFormat() {
		ResponseImpl<T> mockResponse = mock(ResponseImpl.class);
		Transaction t = new AddFreeEntryFormat<T>(mockResponse);
		t.execute();
		
		Map<ConstraintName, Constrainable<?>> c = FormWidget.getConstraints();
		Constrainable<?> f = c.get(ConstraintName.FORMAT_FREE_ENTRY);
		assertTrue(f instanceof FreeEntryFormat);
	}
}
