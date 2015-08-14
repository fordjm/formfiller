package formfiller.ui;

import formfiller.ui.consoleUi.ParsedUserRequest;

public interface UserRequestParser {

	ParsedUserRequest parse(String string);

}
