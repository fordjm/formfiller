package formfiller.usecases.factory;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.appBoundaries.UseCase;
import formfiller.usecases.addFormComponent.AddFormComponentUseCase;
import formfiller.usecases.askQuestion.AskQuestionUseCase;
import formfiller.usecases.factory.UseCaseFactoryImpl;
import formfiller.usecases.handleUnfoundUseCase.HandleUnfoundUseCaseUseCase;

public class UseCaseFactoryImplTest {
	private UseCaseFactoryImpl factory;
	private UseCase useCase;
	
	@Before
	public void setUp() {
		factory = new UseCaseFactoryImpl();
	}
	
	@Test
	public void canHandleNull() {
		useCase = factory.make(null);
		
		assertThat(useCase, is(instanceOf(HandleUnfoundUseCaseUseCase.class)));
	}
	
	@Test
	public void canHandleEmptyString() {
		useCase = factory.make("");
		
		assertThat(useCase, is(instanceOf(HandleUnfoundUseCaseUseCase.class)));
	}

	@Test
	public void canHandleUnknownUseCase(){
		useCase = factory.make("unknown");

		assertThat(useCase, is(instanceOf(HandleUnfoundUseCaseUseCase.class)));
	}
	
	@Test
	public void canCreateHandleUnfoundUseCaseUseCase() {
		useCase = factory.make("handleUnfoundUseCase");
		
		assertThat(useCase, is(instanceOf(HandleUnfoundUseCaseUseCase.class)));
	}
	
	@Test
	public void canCreateAddUnstructuredFormComponentUseCase() {
		useCase = factory.make("addFormComponent");
		
		assertThat(useCase, is(instanceOf(AddFormComponentUseCase.class)));
	}
	
	@Test
	public void canCreateAskQuestionUseCase() {
		useCase = factory.make("askQuestion");
		
		assertThat(useCase, is(instanceOf(AskQuestionUseCase.class)));
	}	
}
