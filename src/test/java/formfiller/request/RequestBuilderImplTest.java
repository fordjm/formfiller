package formfiller.request;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import formfiller.delivery.controller.Arguments;
import formfiller.entities.answerFormat.Unstructured;
import formfiller.enums.QuestionAsked;
import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.HandleUnfoundUseCaseRequest;
import formfiller.request.models.AddFormComponentRequest;
import formfiller.request.models.AddUnstructuredFormComponentRequest;
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
				buildRequest("addFormComponent", 
						makeArguments(argumentsMap));
		String name = request.name;
		AddFormComponentRequest castRequest = 
				(AddUnstructuredFormComponentRequest) request;
		
		assertThat(request, 
				is(instanceOf(AddUnstructuredFormComponentRequest.class)));
		assertThat(name, is("AddUnstructuredFormComponent"));
		assertThat(castRequest.questionId, is("questionId"));
		assertThat(castRequest.questionContent, is("questionContent"));
		assertThat(castRequest.format, instanceOf(Unstructured.class));
	}

	//	TODO:	Fix duplication in AddFormComponentController by
	//			using mock Arguments object.
	//			Test other formats, other min/maxAnswers values.
	private HashMap<String, Object> makeArgumentsMap() {
		HashMap<String, Object> result = new HashMap<String,Object>();
		result.put("questionId", "questionId");
		result.put("questionContent", "questionContent");
		result.put("answerFormat", "U");
		return result;
	}
	
	@Test
	public void canBuildHandleUnfoundUseCaseRequest() {
		Request handleUnfoundUseCaseRequest = 
				builder.build("handleUnfoundUseCase", new Arguments());
		
		assertThat(handleUnfoundUseCaseRequest, 
				is(instanceOf(HandleUnfoundUseCaseRequest.class)));
		assertThat(handleUnfoundUseCaseRequest.name, 
				is("HandleUnfoundUseCase"));
	}
	
	@Test
	public void canBuildAskQuestionRequest() {
		Request askQuestionRequest = 
				buildRequest("askQuestion", makeArguments("which", QuestionAsked.NEXT));
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
