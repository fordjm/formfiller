package formfiller.request;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import formfiller.delivery.controller.Arguments;
import formfiller.entities.format.Unstructured;
import formfiller.enums.QuestionAsked;
import formfiller.request.models.HandleUnfoundUseCaseRequest;
import formfiller.request.builder.RequestBuilderImpl;
import formfiller.request.models.AddAnswerRequest;
import formfiller.request.models.AddFormComponentRequest;
import formfiller.request.models.AskQuestionRequest;
import formfiller.request.models.Request;

public class RequestBuilderImplTest {
	private RequestBuilderImpl builder;
	
	private Request buildRequest(String requestName, Arguments args) {
		return builder.build(requestName, args);
	}
	
	private Arguments makeArguments(Map<String,Object> argumentsMap) {
		Arguments result = new Arguments();
		for (String key : argumentsMap.keySet()){
			Object value = argumentsMap.get(key);
			result.add(key, value);			
		}			
		return result;
	}
	
	private Arguments makeArguments(String key, Object value) {
		Arguments result = new Arguments();
		result.add(key, value);
		return result;
	}
	
	@Before
	public void setUp() {
		builder = new RequestBuilderImpl();
	}
	
	@Test
	public void canBuildAddUnstructuredFormComponentRequest() {
		Map<String, Object> argumentsMap = makeArgumentsMap();
		Request request = 
				buildRequest("AddFormComponent", 
						makeArguments(argumentsMap));
		String name = request.name;
		AddFormComponentRequest castRequest = 
				(AddFormComponentRequest) request;
		
		assertThat(request, 
				is(instanceOf(AddFormComponentRequest.class)));
		assertThat(name, is("AddFormComponent"));
		assertThat(castRequest.componentId, is("componentId"));
		assertThat(castRequest.questionContent, is("questionContent"));
		assertThat(castRequest.format, instanceOf(Unstructured.class));
	}

	//	TODO:	Fix duplication in AddFormComponentController by
	//			using mock Arguments object.
	//			Test other formats, other min/maxAnswers values.
	private HashMap<String, Object> makeArgumentsMap() {
		HashMap<String, Object> result = new HashMap<String,Object>();
		result.put("componentId", "componentId");
		result.put("questionContent", "questionContent");
		result.put("format", new Unstructured());
		return result;
	}
	
	@Test
	public void canBuildHandleUnfoundUseCaseRequest() {
		Request handleUnfoundUseCaseRequest = 
				builder.build("HandleUnfoundUseCase", new Arguments());
		
		assertThat(handleUnfoundUseCaseRequest, 
				is(instanceOf(HandleUnfoundUseCaseRequest.class)));
		assertThat(handleUnfoundUseCaseRequest.name, 
				is("HandleUnfoundUseCase"));
	}
	
	@Test
	public void canBuildAddAnswerRequest() {
		Request addAnswerRequest = 
				buildRequest("AddAnswer", makeArguments("content", "content"));
		String name = addAnswerRequest.name;
		AddAnswerRequest castRequest = 
				(AddAnswerRequest) addAnswerRequest;
		
		assertThat(addAnswerRequest, 
				is(instanceOf(AddAnswerRequest.class)));
		assertThat(name, is("AddAnswer"));
		assertEquals("content", castRequest.content);
	}
	
	@Test
	public void canBuildAskQuestionRequest() {
		Request askQuestionRequest = 
				buildRequest("AskQuestion", makeArguments("which", QuestionAsked.NEXT));
		String name = askQuestionRequest.name;
		AskQuestionRequest castRequest = 
				(AskQuestionRequest) askQuestionRequest;
		
		assertThat(askQuestionRequest, 
				is(instanceOf(AskQuestionRequest.class)));
		assertThat(name, is("AskQuestion"));
		assertThat(castRequest.which, is(QuestionAsked.NEXT));
	}
	
	@Test
	public void canBuildUnknownRequest() {
		Request request = builder.build("unknown", new Arguments());
		
		assertThat(request, is(Request.NULL));
	}
}
