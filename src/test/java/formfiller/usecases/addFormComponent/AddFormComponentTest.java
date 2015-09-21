package formfiller.usecases.addFormComponent;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.FormFillerContext;
import formfiller.request.models.AddFormComponentRequest;
import formfiller.request.models.Request;
import formfiller.usecases.undoable.UndoableUseCase;

public class AddFormComponentTest {
	private AddFormComponentUseCase afc;

	@Before
	public void setUp() {
		afc = new AddFormComponentUseCase();
	}

	@Test
	public void implementsUndoableUseCase() {		
		assertThat(afc, is(instanceOf(UndoableUseCase.class)));
	}
	
	//	TODO:	Move to AddFormComponentRequestTest
	@Test
	public void addFormComponentRequestTest(){
		AddFormComponentRequest request = new AddFormComponentRequest();
		assertThat(request, is(instanceOf(Request.class)));
		assertThat(request.name, is("AddFormComponentRequest"));
		assertThat(request.questionId, is(""));
		assertThat(request.questionContent, is(""));
		assertThat(request.minAnswerCount, is(0));
		assertThat(request.maxAnswerCount, is(1));
		assertEquals(Object.class, request.answerType);
	}

	@Test
	public void executingNull_DoesNotAddUseCaseToExecutedUseCases() {
		AddFormComponentRequest request = Mockito.mock(AddFormComponentRequest.class);
		
		afc.execute(request);
		UndoableUseCase mostRecent = 
				FormFillerContext.executedUseCases.getMostRecent();
		
		assertNotEquals(mostRecent, afc);
	}
	
	//	TODO next:	Test for malformed request.
}
