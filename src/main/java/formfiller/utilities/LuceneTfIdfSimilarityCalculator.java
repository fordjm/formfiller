package formfiller.utilities;

import java.io.IOException;

public class LuceneTfIdfSimilarityCalculator {
	public double calculate(String string1, String string2) {
		try {
			return new CosineDocumentSimilarity(string1, string2).getCosineSimilarity();
		} catch (IOException e) {
			e.printStackTrace();
			return Double.MIN_VALUE;	//	TODO:	Handle differently
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return Double.MIN_VALUE;	//	TODO:	Handle differently
		}
	}
}
