package formfiller.utilities;

import formfiller.enums.QuestionAsked;

public class WhichQuestionParser {
	
	public static QuestionAsked parseWhich(String input){
		for (QuestionAsked which : QuestionAsked.values())
			if (inputMatches(input, which)) 
				return which;
		throw new IllegalArgumentException();
	}

	private static boolean inputMatches(String input, QuestionAsked whichQuestion) {
		return input.equalsIgnoreCase(whichQuestion.toString());
	}
}
