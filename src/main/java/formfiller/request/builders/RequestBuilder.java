package formfiller.request.builders;

import formfiller.delivery.controller.Arguments;
import formfiller.request.models.Request;

// Adapted from:
// https://github.com/unclebob/CC_SMC/blob/master/src/smc/parser/Builder.java
// Retrieved 2015/10/06

public interface RequestBuilder {	
	public Request build(String requestName, Arguments args);

	public void buildAnswerCountBoundary();
	public void buildAnswerCount();
	public void buildComponentId();
	public void buildFormat();
	public void buildMessage();
	public void buildName();
	public void buildNewId();
	public void buildOldId();
	public void buildOption();
	public void buildQuestionContent();
	public void buildWhichQuestion();
}
