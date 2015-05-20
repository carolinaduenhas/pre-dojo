package br.com.pre.dojo.utils;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;


public class Utils {


	
	
	public static TreeSet sortByValue(Map unsortMap) {	 
		TreeSet set =new TreeSet(Collections.reverseOrder());
		Set<Entry> entrySet = unsortMap.entrySet();
		Iterator<Entry> it = entrySet.iterator();
		while (it.hasNext()) {
			
			Entry entry = it.next();
			set.add(entry.getValue());
		}
		return set;
	}
}
