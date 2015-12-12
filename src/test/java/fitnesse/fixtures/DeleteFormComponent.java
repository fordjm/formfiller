package fitnesse.fixtures;

public class DeleteFormComponent {
	private ConsoleEventManager stringEventManager;

	public DeleteFormComponent() {
		stringEventManager = new ConsoleEventManager();
	}
	
	public void whenTheUserDeletesTheFormComponent(String componentId){
		stringEventManager.updateHandler("DelFC " + componentId);
	}
	
}
