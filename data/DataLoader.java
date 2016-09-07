package data;

import java.util.HashSet;
import java.util.Map;

import com.google.gson.JsonArray;

public interface DataLoader {
	
	/**
	 * Loads the data from the resources into the given data and fields objects.
	 * 
	 * @param data a Map<Category, JsonArray> to be populated with key value pairs of category enum and JsonArray of objects found in that category 
	 * @param fields a Map<Category, Set<String>> to be populated with key value pairs of category enum and corresponding HashSet of fields found in that category
	 */
	public void load(Map<Category, JsonArray> data, Map<Category, HashSet<String>> fields);
}
