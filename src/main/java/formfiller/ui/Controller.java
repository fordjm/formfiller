package formfiller.ui;

import formfiller.ui.consoleUi.ParsedUserRequest;

// Adapted from:
// https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/src/cleancoderscom/http/Controller.java
// Retrieved 2015-08-14
public interface Controller {
	public void handle(ParsedUserRequest parsedInput);
}
