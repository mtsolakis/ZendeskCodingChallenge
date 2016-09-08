package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.JsonElement;

public abstract class Utils {

	/**
	 * A utility method which converts a set of objects to an alphabetised list 
	 * of the contents of the set
	 * 
	 * @param set the set to be converted to an alphabetised list
	 * @return an alphabetised list of string of the contents of the given set
	 */
	public static List<String> setToAlphabetisedList(Set<?> set) {
		List<String> sortedList = new ArrayList<String>(set.size());
		
		//Convert the contents of the set into a list of strings
		for(Object key : set) {
			sortedList.add(key.toString());
		}
		
		//Sort the list
		java.util.Collections.sort(sortedList);
		
		return sortedList;
	}
	
	public static String getJsonElementAsString(JsonElement element) {
		//If value is string
		if(element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
			return element.getAsString();
		}
	    else {
	    	return element.toString();
	    }
	}
}
