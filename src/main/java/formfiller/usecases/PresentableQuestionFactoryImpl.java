package formfiller.usecases;

public class PresentableQuestionFactoryImpl implements PresentableQuestionFactory {
	public PresentableQuestionImpl makePresentableQuestion(){
		return new PresentableQuestionImpl();
	}
	
	public class PresentableQuestionImpl implements PresentableQuestion {
		public String id;
		public String content;
		private PresentableQuestionImpl() { }

		public String getId() {
			return id;
		}
		public String getContent() {
			return content;
		}
		public void setId(String id) {
			this.id = id;
		}
		public void setContent(String content) {
			this.content = content;
		}
	}
}
