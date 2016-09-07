package controller;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;

import data.Category;
import data.DataSearcher;
import display.JsonObjectStringGenerator;
import display.JsonObjectStringGeneratorImpl;
import display.LanguageHandler;

public class SearchEngineImpl implements SearchEngine {

	private DataSearcher dataSearcher;
	private LanguageHandler languageHandler;
	
	public SearchEngineImpl(LanguageHandler languageHandler, DataSearcher dataSearcher) {
		this.dataSearcher = dataSearcher;
		this.languageHandler = languageHandler;
	}
	
	/**
	 * Determines if the given category is valid to be searched upon
	 * 
	 * @param category a category as a string which needs to be validated as a category available for searching
	 * @return a boolean which represents whether or not the given category is valid for searching
	 */
	public boolean isValidCategory(String category) {
		if(category == null) {
			return false;
		}
		
		return dataSearcher.isValidCategory(category);
	}

	/**
	 * Determines if the given field is valid to be searched upon with the given category
	 * 
	 * @param category a category as a string in which the given field must exist in order to be considered valid for searching
	 * @param field a field as a string which needs to be validated as an available field for searching within the given category
	 * @return a boolean which represents whether or not the given field is valid for searching within the given category
	 */
	public boolean isValidField(String category, String field) {
		if(category == null || field == null) {
			return false;
		}
		
		return dataSearcher.isValidField(category, field);
	}
	
	
	/**
	 * Creates a comma separated string of the available categories
	 * 
	 * @return a comma separated string of the categories which are available for searching
	 */
	public String getSearchableCategoriesString() {
		return String.join(", ", dataSearcher.getCategories());
	}

	/**
	 * Creates a string of the available categories and fields within those categories which can be searched upon
	 * 
	 * @return a string of the available categories and fields within those categories
	 */
	public String getSearchableFieldsString() {
		Map<Category, List<String>> fieldList = dataSearcher.getFieldsByCategory();
		
		StringBuilder sb = new StringBuilder();
		
		String newLine = System.getProperty("line.separator");
		
		//Iterates through the HashMap of Category, List of fields and generates a string builder with the information
		for(Category key : fieldList.keySet()) {
			sb.append("Search ");
			sb.append(key.getValue());
			sb.append(" by:");
			sb.append(newLine);
			sb.append(String.join(newLine, fieldList.get(key).toArray(new String[fieldList.size()])));
			sb.append(newLine);
			sb.append(languageHandler.getEndDataObjectString());
		}
		
		return sb.toString();	
	}
	
	/**
	 * Performs the search through the data for the given category, field and value
	 * and returns a string of the output to be displayed according to the results of the search
	 * 
	 * @param category a category as a string to be searched upon
	 * @param field a field as a string to be searched upon within the given category
	 * @param value a value to be matched against by the value of the fields
	 * @return a string with the output to be displayed according to the results of the search
	 */
	public String performSearch(String category, String field, String value) {
		if(category == null || field == null || value == null || !isValidCategory(category) || !isValidField(category, field)) {
			return languageHandler.getResultsFoundMsg();
		}
		
		//Gets the relevant data from the datasource
		List<JsonObject> matchingObjects = dataSearcher.search(Category.getEnum(category), field, value);
		
		//Creates a string builder which will contain the results to be displayed
		StringBuilder sb = new StringBuilder();
		
		sb.append(outputIntro);
		
		JsonObjectStringGenerator jsonObjectStringGenerator = new JsonObjectStringGeneratorImpl();
		
		//Append the string builder with the output of each found entity
		for(JsonObject jsonObject : matchingObjects) {
			sb.append(jsonObjectStringGenerator.write(jsonObject));
		}
		
		return sb.toString();
	}

}
