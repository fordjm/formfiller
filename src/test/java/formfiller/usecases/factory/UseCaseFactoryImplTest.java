package formfiller.usecases.factory;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.boundaries.UseCase;
import formfiller.usecases.factory.UseCaseFactoryImpl;
import formfiller.usecases.factory.UseCaseFactoryImpl.UnknownUseCase;
import formfiller.usecases.handleUnfoundController.HandleUnfoundControllerUseCase;
import formfiller.usecases.navigation.NavigationUseCase;

public class UseCaseFactoryImplTest {
	private UseCaseFactoryImpl factory;
	private UseCase useCase;
	
	@Before
	public void setUp() {
		factory = new UseCaseFactoryImpl();
	}
	
	@Test
	public void canCreateHandleUnfoundControllerUseCase() {
		useCase = factory.make("handleUnfoundController");
		assertThat(useCase, is(instanceOf(HandleUnfoundControllerUseCase.class)));
	}
	
	@Test
	public void canCreateNavigationUseCase() {
		useCase = factory.make("navigation");
		assertThat(useCase, is(instanceOf(NavigationUseCase.class)));
	}

	@Test(expected = UnknownUseCase.class)
	public void unknownUseCaseThrowsException(){
		useCase = factory.make("unknown");
	}	
}
