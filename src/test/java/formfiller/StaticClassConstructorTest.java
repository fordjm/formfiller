package formfiller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import formfiller.delivery.router.PlaceholderRouterFactory;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.usecases.askQuestion.AskQuestionValidator;
import formfiller.usecases.askQuestion.PresenterSelector;
import formfiller.utilities.FormatArgumentParser;
import formfiller.utilities.WhichQuestionParser;

public class StaticClassConstructorTest {
	private Object objectUnderTest;

	@Test
	public void testContextConstructor() {
		objectUnderTest = new Context();
		
		assertThat(objectUnderTest, instanceOf(Context.class));
	}

	@Test
	public void testPlaceholderRouterFactoryConstructor() {
		objectUnderTest = new PlaceholderRouterFactory();
		
		assertThat(objectUnderTest, instanceOf(PlaceholderRouterFactory.class));
	}

	@Test
	public void testNullFormComponentsConstructor() {
		objectUnderTest = new NullFormComponents();
		
		assertThat(objectUnderTest, instanceOf(NullFormComponents.class));
	}

	@Test
	public void testAskQuestionValidatorConstructor() {
		objectUnderTest = new AskQuestionValidator();
		
		assertThat(objectUnderTest, instanceOf(AskQuestionValidator.class));
	}

	@Test
	public void testPresenterSelectorConstructor() {
		objectUnderTest = new PresenterSelector();
		
		assertThat(objectUnderTest, instanceOf(PresenterSelector.class));
	}

	@Test
	public void testWhichQuestionParserConstructor() {
		objectUnderTest = new WhichQuestionParser();
		
		assertThat(objectUnderTest, instanceOf(WhichQuestionParser.class));
	}

	@Test
	public void testFormatArgumentParserConstructor() {
		objectUnderTest = new FormatArgumentParser();
		
		assertThat(objectUnderTest, instanceOf(FormatArgumentParser.class));
	}

}
