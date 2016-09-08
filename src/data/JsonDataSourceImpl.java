package data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.google.gson.JsonArray;

public class JsonDataSourceImpl implements DataSource {

	private Map<Category, JsonArray> data;
	private Map<Category, HashSet<String>> fields;

	private DataLoader dataLoader;
	
	
	public JsonDataSourceImpl(DataLoader dataLoader) {
		this.dataLoader = dataLoader;		
		
		data = new HashMap<Category, JsonArray>();
		fields = new HashMap<Category, HashSet<String>>();
				
		this.dataLoader.load(data, fields);
	}

	/**
	 * Returns a string array of the available categories within the data
	 * 
	 * @return a string array of the available categories
	 */
	public String[] getCategories() {
		String[] categories = new String[data.keySet().size()];
		
		Integer i = 0;
		for(Category key : data.keySet()) {
			categories[i] = key.getValue();
			i++;
		}
		 
		
		return categories;
	}

	/**
	 * Returns the collection of data held by the dataSource
	 * 
	 * @return a Map<Category, JsonArray> of all the data in each category held by the dataSource
	 */
	public Map<Category, JsonArray> getData() {
		return data;
	}

	/**
	 * Returns a HashMap with keys of category enums and values of the collection of fields available 
	 * within that category
	 * 
	 * @return a Map<Category, HashSet<String>> with keys of category enums and values of HashSet<String> containing the available fields within that category
	 */
	public Map<Category, HashSet<String>> getFields() {
		return fields;
	}
	
	/**
	 * Gets the collection of data as a JsonArray contained within the given category
	 * 
	 * @param category a category for which to get the available data
	 * @return a JsonArray of the stored data for within the given category
	 */
	public JsonArray getDataInCategory(Category category) {
		if(!data.containsKey(category)) {
			return new JsonArray();
		}
		
		return data.get(category);
	}
	
	/**
	 * Gets the fields available in the given category as a HashSet<String>
	 * 
	 * @param category a category for which the available fields are needed
	 * @return a HashSet<String> of the available fields as strings in the given category
	 */
	public HashSet<String> getFieldsInCategory(Category category) {
		if(!fields.containsKey(category)) {
			return new HashSet<String>();
		}
		
		return fields.get(category);
	}
	
}
