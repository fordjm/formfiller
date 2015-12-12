package fitnesse.fixtures;

import formfiller.utilities.StringUtilities;

public class ChangeId {
	private ConsoleEventManager stringEventManager;
	
	public ChangeId() {
		stringEventManager = new ConsoleEventManager();
	}
	
	public void whenTheUserChangesTheIdFromOldToNew(String oldId, String newId){
		String spacedIds = StringUtilities.makeSpacedString(oldId, newId);
		stringEventManager.updateHandler("ChangeId " + spacedIds);
	}
	
}