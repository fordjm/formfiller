package fitnesse.fixtures;

import formfiller.FormFillerContext;
import formfiller.response.models.PresentableResponse;

public class ChangeId {
	String oldId = ""; 
	String newId = "";
	
	public void whenTheUserChangesTheIdFromOldToNew(String oldId, String newId){
		this.oldId = oldId;
		this.newId = newId;
		makeBogusResultForFitNesseTest();
	}

	private void makeBogusResultForFitNesseTest() {
		PresentableResponse response = new PresentableResponse();
		response.message = "You successfully changed the id from " + 
				makeQuotedString(oldId) + " to " + makeQuotedString(newId);
		FormFillerContext.outcomePresenter.present(response);
	}

	private String makeQuotedString(String input) {
		String result = "\""+ input + "\"";
		return result;
	}
}
