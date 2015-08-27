package formfiller.utilities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import formfiller.entities.Constraint;
import formfiller.enums.ContentConstraint;

public class ConstraintMocker {

	public static Constraint makeMockConstraint(int id, boolean satisfiesConstraint) {
		Constraint result = mock(Constraint.class);
		when(result.getName()).thenReturn(ContentConstraint.MOCK);
		when(result.getId()).thenReturn(id);
		when(result.satisfiesConstraint()).thenReturn(satisfiesConstraint);
		return result;
	}
}
