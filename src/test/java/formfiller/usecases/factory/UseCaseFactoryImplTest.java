package formfiller.usecases.factory;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.appBoundaries.UseCase;
import formfiller.usecases.factory.UseCaseFactoryImpl;
import formfiller.usecases.handleUnfoundController.HandleUnfoundUseCaseUseCase;
import formfiller.usecases.navigation.NavigationUseCase;

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
	public void canCreateNavigationUseCase() {
		useCase = factory.make("navigation");
		
		assertThat(useCase, is(instanceOf(NavigationUseCase.class)));
	}	
}
