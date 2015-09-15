package formfiller.utilities;

import formfiller.enums.WhichQuestion;

public class WhichQuestionParser {
	
	public static WhichQuestion parseWhich(String input){
		for (WhichQuestion which : WhichQuestion.values())
			if (inputMatches(input, which)) 
				return which;
		throw new IllegalArgumentException();
	}

	private static boolean inputMatches(String input, WhichQuestion whichQuestion) {
		return input.equalsIgnoreCase(whichQuestion.toString());
	}
}
