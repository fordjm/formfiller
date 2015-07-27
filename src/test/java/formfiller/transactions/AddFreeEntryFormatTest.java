package formfiller.transactions;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.Test;

import formfiller.entities.FreeEntryFormat;
import formfiller.entities.ResponseImpl;
import formfiller.entities.Constraint;
import formfiller.persistence.FormWidget;
import formfiller.utilities.ConstraintName;

public class AddFreeEntryFormatTest<T> {

	@Test
	public void canAddFreeEntryFormat() {
		ResponseImpl<T> mockResponse = mock(ResponseImpl.class);
		Transaction t = new AddFreeEntryFormat<T>(mockResponse);
		t.execute();
		
		Map<ConstraintName, Constraint<?>> p = FormWidget.constraints();
		Constraint<?> r = p.get(ConstraintName.FORMAT);
		assertTrue(r instanceof FreeEntryFormat);
	}

}
