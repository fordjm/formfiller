package fitnesse.fixtures;

import formfiller.Context;
import formfiller.delivery.ViewModel;
import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.entities.formComponent.FormComponent;
import formfiller.response.models.PresentableResponse;
import formfiller.utilities.FormComponentUtilities;

public class FixtureUtilities {
	public static String messagePresented() {
		ViewModel response = 
				Context.outcomePresenter.getViewModel();
		NotificationViewModel castViewModel = (NotificationViewModel) response;
		return castViewModel.message;
	}
	
	public static boolean foundComponentId(String id){
		FormComponent foundComponent = 
				Context.formComponentGateway.find(id);
		return !FormComponentUtilities.isComponentNull(foundComponent);
	}
	
}
