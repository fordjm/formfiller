package formfiller.deprecated;

public class ClearWidget implements Transaction {
	
	public void execute() {
		FormWidget.clear();
	}
}
