package formfiller.usecases.navigation;

public class NavigationRequestFactoryImpl implements NavigationRequestFactory {

	public NavigationRequest makeNavigationRequest() {
		return new NavigationRequestImpl();
	}
	
	public class NavigationRequestImpl implements NavigationRequest {
		private int offset = -1;
		
		private NavigationRequestImpl(){ }

		public int getOffset() {
			return offset;
		}
		public void setOffset(int offset) {
			this.offset = offset;
		}
	}
}
