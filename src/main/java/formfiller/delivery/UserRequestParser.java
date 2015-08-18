package formfiller.delivery;

import formfiller.delivery.userRequestParser.ParsedUserRequest;

public interface UserRequestParser {

	ParsedUserRequest parse(String string);

}
