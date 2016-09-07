package data;

import java.util.HashSet;
import java.util.Map;

import com.google.gson.JsonArray;

public interface DataSource {

	/**
	 * Returns a string array of the available categories within the data
	 * 
	 * @return a string array of the available categories
	 */
	public String[] getCategories();

	/**
	 * Returns the collection of data held by the dataSource
	 * 
	 * @return a Map<Category, JsonArray> of all the data in each category held by the dataSource
	 */
	public Map<Category, JsonArray> getData();

	/**
	 * Returns a HashMap with keys of category enums and values of the collection of fields available 
	 * within that category
	 * 
	 * @return a Map<Category, HashSet<String>> with keys of category enums and values of HashSet<String> containing the available fields within that category
	 */
	public Map<Category, HashSet<String>> getFields();
	
	/**
	 * Gets the collection of data as a JsonArray contained within the given category
	 * 
	 * @param category a category for which to get the available data
	 * @return a JsonArray of the stored data for within the given category
	 */
	public JsonArray getDataInCategory(Category category);
	
	/**
	 * Gets the fields available in the given category as a HashSet<String>
	 * 
	 * @param category a category for which the available fields are needed
	 * @return a HashSet<String> of the available fields as strings in the given category
	 */
	public HashSet<String> getFieldsInCategory(Category category);
	
}
