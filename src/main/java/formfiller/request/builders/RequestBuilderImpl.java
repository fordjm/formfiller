package formfiller.request.builders;

import formfiller.Context;
import formfiller.delivery.controller.Arguments;
import formfiller.entities.format.Format;
import formfiller.entities.format.OptionVariable;
import formfiller.entities.format.Unstructured;
import formfiller.enums.QuestionAsked;
import formfiller.request.models.*;

//  Adapted from:
//  https://github.com/unclebob/CC_SMC/blob/master/src/smc/parser/SyntaxBuilder.java
//  Retrieved 2015/10/06

//	TODO:	Refactor duplicate requests.
public class RequestBuilderImpl implements RequestBuilder {
	private String requestName;
	private Arguments args;
	private Request product;

	public Request build(String requestName, Arguments args) {
		this.requestName = requestName;
		this.args = args;
		
		if(requestName.equalsIgnoreCase("HandleUnfoundUseCase"))
			buildHandleUnfoundUseCaseRequest();
		else if(requestName.equalsIgnoreCase("AddAnswerCountBoundary"))
			buildAddAnswerCountBoundaryRequest();
		else if(requestName.equalsIgnoreCase("AddOption"))
			buildAddOptionRequest();
		else if(requestName.equalsIgnoreCase("AddOptionVariableFormComponent"))
			buildAddFormComponentRequest();
		else if(requestName.equalsIgnoreCase("AddUnstructuredFormComponent"))
			buildAddFormComponentRequest();
		else if(requestName.equalsIgnoreCase("AskQuestion"))
			buildAskQuestionRequest();
		else if(requestName.equalsIgnoreCase("ChangeId"))
			buildChangeIdRequest();
		else if(requestName.equalsIgnoreCase("ChangeUnstructured"))
			buildChangeFormatRequest();
		else if(requestName.equalsIgnoreCase("ChangeOptionVariable"))
			buildChangeFormatRequest();
		else if(requestName.equalsIgnoreCase("DeleteFormComponent"))
			buildDeleteFormComponentRequest();
		else
			return Request.NULL;
		return product;
	}

	public void buildAnswerCount() {
		AddAnswerCountBoundaryRequest castRequest = getRequestAsAddAnswerCountBoundaryRequest();
		castRequest.count = (Integer) args.getById("count");
	}

	private AddAnswerCountBoundaryRequest getRequestAsAddAnswerCountBoundaryRequest() {
		return (AddAnswerCountBoundaryRequest) product;
	}

	public void buildAnswerCountBoundary() {
		AddAnswerCountBoundaryRequest castRequest = getRequestAsAddAnswerCountBoundaryRequest();
		castRequest.boundary = getArgumentAsString("boundary");
	}

	public void buildComponentId() {
		RequestWithComponentId castRequest = (RequestWithComponentId) product;
		castRequest.componentId = getArgumentAsString("componentId");
	}

	public void buildFormat() {
		AddFormComponentRequest castRequest = (AddFormComponentRequest) product;
		castRequest.format = parseFormat();		
	}

	private Format parseFormat() {
		String formatString = getArgumentAsString("format");
		if (Context.stringMatcher.matches(formatString, "V"))
			return new OptionVariable();
		else if (Context.stringMatcher.matches(formatString, "U"))
			return new Unstructured();
		else
			throw new IllegalArgumentException(
					"Could not match format string " + formatString);
	}

	public void buildMessage() {
		HandleUnfoundUseCaseRequest castRequest = (HandleUnfoundUseCaseRequest) product;
		castRequest.message = getArgumentAsString("message");		
	}

	public void buildName() {
		product.name = requestName;
	}

	public void buildNewId() {
		ChangeIdRequest castRequest = (ChangeIdRequest) product;
		castRequest.newId = getArgumentAsString("newId");		
	}

	public void buildOldId() {
		ChangeIdRequest castRequest = (ChangeIdRequest) product;
		castRequest.oldId = getArgumentAsString("oldId");		
	}
	
	public void buildOption() {
		AddOptionRequest castRequest = (AddOptionRequest) product;
		castRequest.option = getArgumentAsString("option");	
	}

	public void buildQuestionContent() {
		AddFormComponentRequest castRequest = (AddFormComponentRequest) product;
		castRequest.questionContent = getArgumentAsString("questionContent");
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

	private void buildAddOptionRequest() {
		product = new AddOptionRequest();
		buildName();
		buildComponentId();
		buildOption();
	}

	private void buildAddFormComponentRequest() {
		product = new AddFormComponentRequest();
		buildName();
		buildComponentId();
		buildQuestionContent();
		buildFormat();
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

	private void buildChangeFormatRequest() {
		product = new ChangeFormatRequest();
		buildName();
		buildComponentId();
	}

	private String getArgumentAsString(String key) {
		return (String) args.getById(key);
	}

	private void buildDeleteFormComponentRequest() {
		product = new RequestWithComponentId();
		buildComponentId();
	}

	private void buildHandleUnfoundUseCaseRequest() {
		product = new HandleUnfoundUseCaseRequest();
		buildName();
		buildMessage();
	}
	
}
