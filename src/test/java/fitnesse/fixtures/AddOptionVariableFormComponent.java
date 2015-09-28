package fitnesse.fixtures;

public class AddOptionVariableFormComponent extends AddFormComponent {
	private String options = "";
	
	protected String makeConsoleRequiredParametersString() {
		String result = super.makeConsoleRequiredParametersString() + 
				formattedOptions();
		return result;
	}
	
	private String formattedOptions() {
		return String.format("%s ", options);
	}

	protected String getCommandString() {
		return "AddFCV";
	}
	
}
