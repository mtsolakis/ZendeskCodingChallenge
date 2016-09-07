package data;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;

public interface DataSearcher {
	/**
	 * Returns a string array of the available categories
	 * 
	 * @return a string array of the available categories as strings
	 */
	public String[] getCategories();
	
	/**
	 * Determines if the given category is valid to be searched upon
	 * 
	 * @param category a category as a string which needs to be validated as an available category for searching
	 * @return a boolean which represents whether or not the given category is valid for searching
	 */
	public boolean isValidCategory(String category);
	
	/**
	 * Determines if the given field is valid to be searched upon with the given category
	 * 
	 * @param category a category as a string in which the given field must exist in order to be considered valid for searching
	 * @param field a field as a string which needs to be validated as an available field for searching within the given category
	 * @return a boolean which represents whether or not the given field is valid for searching within the given category
	 */
	public boolean isValidField(String category, String field);
	
	/**
	 * Creates a Map<Category, List<String>> where the key is the category and the value
	 * is a list of the fields within that category as strings
	 * 
	 * @return a Map<Category, List<String>> where the key is the category and the value is a list of 
	 * the fields within that category as strings
	 */
	public Map<Category, List<String>> getFieldsByCategory();
	
	/**
	 * Finds and returns an ArrayList of JsonObjects which match the given category, field and value.
	 * 
	 * @param category a category within which to search for matching data
	 * @param field a field within the given category whose value should be matched against
	 * @param value a value which should be used to compare against the value of the data entities to determine a match
	 * @return a List<JsonObject> containing the found JsonObjects which match the given parameters
	 */
	public List<JsonObject> search(Category category, String field, String value);
}
