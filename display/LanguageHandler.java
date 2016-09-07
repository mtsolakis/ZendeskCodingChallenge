package display;

public interface LanguageHandler {
	
	/**
	 * @return the usage instructions string
	 */
	public String getUsageInstructions();
	 
	/**
	 * @return the category selection instructions string
	 */
	public String getCategorySelectMsg();
	
	/**
	 * @return the invalid category message string
	 */
	public String getInvalidCategoryMsg();

	/**
	 * @return the field selection instructions string
	 */
	public String getFieldSelectMsg();

	/**
	 * @return the invalid field message string
	 */
	public String getInvalidFieldMsg();

	/**
	 * @return the enter a value to search message string
	 */
	public String getEnterValueMsg();

	/**
	 * @return the invalid input message string
	 */
	public String getInvalidInputMsg();

	/**
	 * @return the no results found message string
	 */
	public String getNoResultsFoundMsg();

	/**
	 * @return the results successfully found message string
	 */
	public String getResultsFoundMsg();

	/**
	 * @return the string which denotes the end of a data object in the display
	 */
	public Object getEndDataObjectString();
	
}
