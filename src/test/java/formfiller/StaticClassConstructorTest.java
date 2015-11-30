package formfiller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import formfiller.delivery.router.PlaceholderTextRouterFactory;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.usecases.askQuestion.AskQuestionValidator;
import formfiller.usecases.askQuestion.PresenterSelector;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.FormatArgumentParser;
import formfiller.utilities.ParseTester;
import formfiller.utilities.StringUtilities;
import formfiller.utilities.WhichQuestionParser;

public class StaticClassConstructorTest {
	private Object objectUnderTest;

	@Test
	public void testAskQuestionValidatorConstructor() {
		objectUnderTest = new AskQuestionValidator();
		
		assertThat(objectUnderTest, instanceOf(AskQuestionValidator.class));
	}

	@Test
	public void testContextConstructor() {
		objectUnderTest = new Context();
		
		assertThat(objectUnderTest, instanceOf(Context.class));
	}

	@Test
	public void testFormatArgumentParserConstructor() {
		objectUnderTest = new FormatArgumentParser();
		
		assertThat(objectUnderTest, instanceOf(FormatArgumentParser.class));
	}

	@Test
	public void testFormComponentUtilitiesConstructor() {
		objectUnderTest = new FormComponentUtilities();
		
		assertThat(objectUnderTest, instanceOf(FormComponentUtilities.class));
	}

	@Test
	public void testNullFormComponentsConstructor() {
		objectUnderTest = new NullFormComponents();
		
		assertThat(objectUnderTest, instanceOf(NullFormComponents.class));
	}

	@Test
	public void testParseTesterConstructor() {
		objectUnderTest = new ParseTester();
		
		assertThat(objectUnderTest, instanceOf(ParseTester.class));
	}

	@Test
	public void testPlaceholderRouterFactoryConstructor() {
		objectUnderTest = new PlaceholderTextRouterFactory();
		
		assertThat(objectUnderTest, instanceOf(PlaceholderTextRouterFactory.class));
	}

	@Test
	public void testPresenterSelectorConstructor() {
		objectUnderTest = new PresenterSelector();
		
		assertThat(objectUnderTest, instanceOf(PresenterSelector.class));
	}

	@Test
	public void testStringUtilitiesConstructor() {
		objectUnderTest = new StringUtilities();
		
		assertThat(objectUnderTest, instanceOf(StringUtilities.class));
	}

	@Test
	public void testWhichQuestionParserConstructor() {
		objectUnderTest = new WhichQuestionParser();
		
		assertThat(objectUnderTest, instanceOf(WhichQuestionParser.class));
	}

}
