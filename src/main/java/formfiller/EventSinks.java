package formfiller;

import java.util.ArrayList;
import java.util.List;

import formfiller.delivery.event.eventSink.EventSink;
import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.usecases.addAnswer.AddAnswerViewModel;
import formfiller.usecases.askQuestion.AskQuestionViewModel;

public class EventSinks {
	private static List<EventSink> itsSinks = new ArrayList<EventSink>();
	
	public static void add(EventSink sink) {
		itsSinks.add(sink);
	}
	
	public static boolean contains(EventSink sink) {
		return itsSinks.contains(sink);
	}

	public static void receive(AskQuestionViewModel viewModel) {
		for (EventSink sink : itsSinks) 
			sink.receive(viewModel);
	}

	public static void receive(AddAnswerViewModel viewModel) {
		for (EventSink sink : itsSinks) 
			sink.receive(viewModel);
	}

	public static void receive(NotificationViewModel viewModel) {
		for (EventSink sink : itsSinks) 
			sink.receive(viewModel);
	}

}
