package formfiller.utilities;

import com.aliasi.spell.JaccardDistance;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;

public class LingPipeJaccardProximityCalculator {
	private JaccardDistance jaccardDistance;
	
	public LingPipeJaccardProximityCalculator(){
		jaccardDistance = new JaccardDistance(IndoEuropeanTokenizerFactory.INSTANCE);
	}
	
	public double calculate(String string1, String string2){
		return jaccardDistance.proximity(string1, string2);
	}

}
