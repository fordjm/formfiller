package formfiller;

import java.util.ArrayList;
import java.util.List;

import formfiller.delivery.event.eventSink.EventSink;
import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.usecases.addAnswer.AddAnswerViewModel;
import formfiller.usecases.askQuestion.AskQuestionViewModel;

/**
 * The EventSinks class stores a collection of EventSink objects.
 * EventSinks receives the presented ViewModel output from each
 * use case and passes it to each EventSink in the collection.
 */
public class EventSinks {
	private static List<EventSink> itsSinks = new ArrayList<EventSink>();
	/**
	 * This method adds an EventSink object to the collection.
	 * 
	 * @param sink EventSink variable to be added
	 * @since version 0.0.1
	 */
	public static void add(EventSink sink) {
		itsSinks.add(sink);
	}
	
	/**
	 * This method returns whether the collection contains 
	 * the given EventSink object.
	 * 
	 * @param sink EventSink variable to be check
	 * @return whether itsSinks contains sink
	 * @since version 0.0.1
	 */
	public static boolean contains(EventSink sink) {
		return itsSinks.contains(sink);
	}
	
	/**
	 * This method receives view models resulting from 
	 * the Ask Question use case and distributes them to 
	 * each EventSink in itsSinks.
	 * 
	 * @param viewModel AskQuestionViewModel to distribute
	 * @since version 0.0.1
	 */
	public static void receive(AskQuestionViewModel viewModel) {
		for (EventSink sink : itsSinks) 
			sink.receive(viewModel);
	}
	
	/**
	 * This method receives view models resulting from 
	 * the Add Answer use case and distributes them to 
	 * each EventSink in itsSinks.
	 * 
	 * @param viewModel AddAnswerViewModel to distribute
	 * @since version 0.0.1
	 */
	public static void receive(AddAnswerViewModel viewModel) {
		for (EventSink sink : itsSinks) 
			sink.receive(viewModel);
	}
	
	/**
	 * This method receives view models resulting from 
	 * error conditions and distributes them to 
	 * each EventSink in itsSinks.
	 * 
	 * @param viewModel NotificationViewModel to distribute
	 * @since version 0.0.1
	 */
	public static void receive(NotificationViewModel viewModel) {
		for (EventSink sink : itsSinks) 
			sink.receive(viewModel);
	}

}
