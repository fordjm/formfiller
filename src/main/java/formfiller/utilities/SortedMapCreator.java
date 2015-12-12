package formfiller.utilities;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

// Adapted From:
// http://stackoverflow.com/questions/109383/how-to-sort-a-mapkey-value-on-the-values-in-java
// Retrieved:	05/25/2015
public class SortedMapCreator {
	public static Map<String,Double> createSortedMap(Map<String,Double> unsortedMap) {
        ValueComparator bvc =  new ValueComparator(unsortedMap);
        TreeMap<String,Double> sorted_map = new TreeMap<String,Double>(bvc);
        sorted_map.putAll(unsortedMap);
        return sorted_map;
    }
}

class ValueComparator implements Comparator<String> {
    Map<String, Double> base;
    
    public ValueComparator(Map<String, Double> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}