package controller;

public interface SearchEngine {

	/**
	 * Determines if the given category is valid to be searched upon
	 * 
	 * @param category a category as a string which needs to be validated as a category available for searching
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
	 * Creates a comma separated string of the available categories
	 * 
	 * @return a comma separated string of the categories which are available for searching
	 */
	public String getSearchableCategoriesString();

	/**
	 * Creates a string of the available categories and fields within those categories which can be searched upon
	 * 
	 * @return a string of the available categories and fields within those categories
	 */
	public String getSearchableFieldsString();
	
	/**
	 * Performs the search through the data for the given category, field and value
	 * and returns a string of the output to be displayed according to the results of the search
	 * 
	 * @param category a category as a string to be searched upon
	 * @param field a field as a string to be searched upon within the given category
	 * @param value a value to be matched against by the value of the fields
	 * @return a string with the output to be displayed according to the results of the search
	 */
	public String performSearch(String category, String field, String value);
	
}
