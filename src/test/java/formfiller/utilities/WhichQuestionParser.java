package formfiller.utilities;

import formfiller.enums.WhichQuestion;

public class WhichQuestionParser {
	
	public static WhichQuestion parseWhich(String input){
		if (input.equalsIgnoreCase("next")) return WhichQuestion.NEXT;
		else if (input.equalsIgnoreCase("current")) return WhichQuestion.CURRENT;
		else if (input.equalsIgnoreCase("prev")) return WhichQuestion.PREV;
		else throw new IllegalArgumentException();
	}
}
