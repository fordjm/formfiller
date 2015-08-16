package formfiller.usecases;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.usecases.UseCaseFactoryImpl.UnknownUseCase;
import formfiller.usecases.navigation.NavigationUseCase;
import formfiller.usecases.presentQuestion.PresentQuestionUseCase;

public class UseCaseFactoryImplTest {

	private UseCaseFactoryImpl factory;
	private UseCase useCase;
	
	@Before
	public void setUp() {
		factory = new UseCaseFactoryImpl();
	}
	@Test
	public void canCreateNavigationUseCase() {
		useCase = factory.make("navigation");
		assertThat(useCase, is(instanceOf(NavigationUseCase.class)));
	}
	@Test
	public void canCreatePresentQuestionUseCase() {
		useCase = factory.make("presentQuestion");
		assertThat(useCase, is(instanceOf(PresentQuestionUseCase.class)));
	}
	@Test(expected = UnknownUseCase.class)
	public void unknownUseCaseThrowsException(){
		useCase = factory.make("unknown");
	}
	
}
