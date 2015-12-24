package formfiller.delivery.ui.consoleUi;

import formfiller.delivery.viewModel.NotificationViewModel;

public class ConsoleNotificationView {
	public void generateView(NotificationViewModel viewModel) {
		System.out.println(viewModel.message);
	}

}
