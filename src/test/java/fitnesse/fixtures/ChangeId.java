package fitnesse.fixtures;

import formfiller.utilities.StringUtilities;

public class ChangeId {
	private StringEventManager stringEventManager;
	
	public ChangeId() {
		stringEventManager = new StringEventManager();
	}
	
	public void whenTheUserChangesTheIdFromOldToNew(String oldId, String newId){
		String spacedIds = StringUtilities.makeSpacedString(oldId, newId);
		stringEventManager.updateHandler("ChangeId " + spacedIds);
	}
	
}