package formfiller.entities.format;

public abstract class AnswerFormat {
	public static final AnswerFormat UNSTRUCTURED = makeUnstructuredFormat();

	private static AnswerFormat makeUnstructuredFormat() {
		return new AnswerFormat() {
			public boolean matchesContent(Object objectUnderTest) {
				return true;
			}			
		};
	}
	
	//	TODO:	Rename.
	public abstract boolean matchesContent(Object objectUnderTest);
}