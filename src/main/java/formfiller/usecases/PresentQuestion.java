package formfiller.usecases;

import formfiller.gateways.Context;
import formfiller.transactions.PresentableQuestion;
import formfiller.transactions.Transaction;

// As presented in the Clean Coders Java Case Study codecast
// https://cleancoders.com/episode/case-study-episode-1/show
// Retrieved 2015-08-06
public class PresentQuestion implements Transaction {
	public PresentableQuestion presentQuestion(){
		return null;
	}
	public void execute() {
		Context.questionGateway.findQuestionByIndexOffset(1);
	}
}
