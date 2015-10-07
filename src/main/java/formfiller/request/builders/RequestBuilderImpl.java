package formfiller.request.builders;

import formfiller.Context;
import formfiller.delivery.controller.Arguments;
import formfiller.entities.format.Format;
import formfiller.enums.QuestionAsked;
import formfiller.request.models.*;

//  Adapted from:
//  https://github.com/unclebob/CC_SMC/blob/master/src/smc/parser/SyntaxBuilder.java
//  Retrieved 2015/10/06

public class RequestBuilderImpl implements RequestBuilder {
	private String requestName;
	private Arguments args;
	private Request product;

	public Request build(String requestName, Arguments args) {
		this.requestName = requestName;
		this.args = args;
		
		if(doesRequestNameMatchNamedUseCase("HandleUnfoundUseCase"))
			buildHandleUnfoundUseCaseRequest();
		else if(doesRequestNameMatchNamedUseCase("AddAnswerCountBoundary"))
			buildAddAnswerCountBoundaryRequest();
		else if(doesRequestNameMatchNamedUseCase("AddOption"))
			buildAddOptionRequest();
		else if(doesRequestNameMatchNamedUseCase("AddFormComponent"))
			buildAddFormComponentRequest();
		else if(doesRequestNameMatchNamedUseCase("AskQuestion"))
			buildAskQuestionRequest();
		else if(doesRequestNameMatchNamedUseCase("ChangeId"))
			buildChangeIdRequest();
		else if(doesRequestNameMatchNamedUseCase("ChangeFormat"))
			buildRequestWithComponentIdAndFormat();
		else if(doesRequestNameMatchNamedUseCase("DeleteFormComponent"))
			buildRequestWithComponentId();
		else
			return Request.NULL;
		
		return product;
	}

	private boolean doesRequestNameMatchNamedUseCase(String useCase) {
		return Context.stringMatcher.matches(requestName, useCase);
	}

	public void buildAnswerCount() {
		AddAnswerCountBoundaryRequest castRequest = 
				castProductAsAddAnswerCountBoundaryRequest();
		castRequest.count = (Integer) args.getById("count");
	}

	private AddAnswerCountBoundaryRequest castProductAsAddAnswerCountBoundaryRequest() {
		return (AddAnswerCountBoundaryRequest) product;
	}

	public void buildAnswerCountBoundary() {
		AddAnswerCountBoundaryRequest castRequest = 
				castProductAsAddAnswerCountBoundaryRequest();
		castRequest.boundary = castNamedArgumentAsString("boundary");
	}

	private String castNamedArgumentAsString(String key) {
		return (String) args.getById(key);
	}

	public void buildComponentId() {
		RequestWithComponentId castRequest = (RequestWithComponentId) product;
		castRequest.componentId = castNamedArgumentAsString("componentId");
	}

	public void buildFormat() {
		RequestWithComponentIdAndFormat castRequest = (RequestWithComponentIdAndFormat) product;
		castRequest.format = (Format) args.getById("format");		
	}

	public void buildMessage() {
		HandleUnfoundUseCaseRequest castRequest = (HandleUnfoundUseCaseRequest) product;
		castRequest.message = castNamedArgumentAsString("message");		
	}

	public void buildName() {
		product.name = requestName;
	}

	public void buildNewId() {
		ChangeIdRequest castRequest = (ChangeIdRequest) product;
		castRequest.newId = castNamedArgumentAsString("newId");		
	}

	public void buildOldId() {
		ChangeIdRequest castRequest = (ChangeIdRequest) product;
		castRequest.oldId = castNamedArgumentAsString("oldId");		
	}
	
	public void buildOption() {
		AddOptionRequest castRequest = (AddOptionRequest) product;
		castRequest.option = castNamedArgumentAsString("option");	
	}

	public void buildQuestionContent() {
		AddFormComponentRequest castRequest = (AddFormComponentRequest) product;
		castRequest.questionContent = castNamedArgumentAsString("questionContent");
	}
	
	public void buildWhichQuestion() {
		AskQuestionRequest castRequest = (AskQuestionRequest) product;
		castRequest.which = (QuestionAsked) args.getById("which");	
	}

	private void buildAddAnswerCountBoundaryRequest() {
		product = new AddAnswerCountBoundaryRequest();
		buildName();
		buildComponentId();
		buildAnswerCountBoundary();
		buildAnswerCount();
	}

	private void buildAddFormComponentRequest() {
		product = new AddFormComponentRequest();
		buildName();
		buildComponentId();
		buildQuestionContent();
		buildFormat();
	}

	private void buildAddOptionRequest() {
		product = new AddOptionRequest();
		buildName();
		buildComponentId();
		buildOption();
	}
	
	private void buildAskQuestionRequest() {
		product = new AskQuestionRequest();
		buildName();
		buildWhichQuestion();
	}

	private void buildChangeIdRequest() {
		product = new ChangeIdRequest();
		buildName();
		buildOldId();
		buildNewId();
	}

	private void buildRequestWithComponentId() {
		product = new RequestWithComponentId();
		buildName();
		buildComponentId();
	}

	private void buildRequestWithComponentIdAndFormat() {
		product = new RequestWithComponentIdAndFormat();
		buildName();
		buildComponentId();
		buildFormat();
	}

	private void buildHandleUnfoundUseCaseRequest() {
		product = new HandleUnfoundUseCaseRequest();
		buildName();
		buildMessage();
	}
	
}
