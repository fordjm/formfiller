package fitnesse.fixtures;

public class DeleteFormComponent {
	private StringEventManager stringEventManager;

	public DeleteFormComponent() {
		stringEventManager = new StringEventManager();
	}
	
	public void whenTheUserDeletesTheFormComponent(String componentId){
		stringEventManager.updateHandler("DelFC " + componentId);
	}
	
}
