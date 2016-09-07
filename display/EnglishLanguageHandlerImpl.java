package display;

public class EnglishLanguageHandlerImpl implements LanguageHandler {
	private static String NEW_LINE_PLACEHOLDER = "%n";
	private static String USAGE_INSTRUCTIONS = "Select search options:%n* Enter (1) to search Zendesk%n* Enter (2) to view a list of searchable fields%n* Type 'quit' at any time to exit%n";
	private static String CATEGORY_SELECT_MSG = "Enter category: ";
	private static String INVALID_CATEGORY_MSG = "The category you selected was invalid. Please enter a valid category.";
	private static String FIELD_SELECT_MSG = "Enter field:";
	private static String INVALID_FIELD_MSG = "The field you selected was invalid. Please enter a valid field.";
	private static String ENTER_VALUE_MSG = "Enter search value (Press Enter to search fields with empty value):";
	private static String INVALID_INPUT_MSG = "Sorry, the command you entered is invalid.";
	private static String NO_RESULTS_FOUND_MSG = "No Results Found!%n";
	private static String RESULTS_FOUND_MSG = "Results Found:%n";
	private static String END_DATA_OBJECT_STRING = "----------------------------------------------------%n";
	
	/**
	 * @return the usage instructions string
	 */
	public String getUsageInstructions() {
		return formatNewLine(USAGE_INSTRUCTIONS);
	}
	 
	/**
	 * @return the category selection instructions string
	 */
	public String getCategorySelectMsg() {
		return formatNewLine(CATEGORY_SELECT_MSG);
	}
	
	/**
	 * @return the invalid category message string
	 */
	public String getInvalidCategoryMsg() {
		return formatNewLine(INVALID_CATEGORY_MSG);
	}

	/**
	 * @return the field selection instructions string
	 */
	public String getFieldSelectMsg() {
		return formatNewLine(FIELD_SELECT_MSG);
	}

	/**
	 * @return the invalid field message string
	 */
	public String getInvalidFieldMsg() {
		return formatNewLine(INVALID_FIELD_MSG);
	}

	/**
	 * @return the enter a value to search message string
	 */
	public String getEnterValueMsg() {
		return formatNewLine(ENTER_VALUE_MSG);
	}

	/**
	 * @return the invalid input message string
	 */
	public String getInvalidInputMsg() {
		return formatNewLine(INVALID_INPUT_MSG);
	}

	/**
	 * @return the no results found message string
	 */
	public String getNoResultsFoundMsg() {
		return formatNewLine(NO_RESULTS_FOUND_MSG);
	}

	/**
	 * @return the results successfully found message string
	 */
	public String getResultsFoundMsg() {
		return formatNewLine(RESULTS_FOUND_MSG);
	}

	/**
	 * @return the string which denotes the end of a data object in the display
	 */
	public Object getEndDataObjectString() {
		return formatNewLine(END_DATA_OBJECT_STRING);
	}

	/**
	 * Formats the string to have platform dependent new line characters where appropriate
	 * 
	 * @param input a input string which needs placeholders replaced with platform dependent new line characters
	 * @return the input string with platform dependent newline characters where appropriate
	 */
	private String formatNewLine(String input) {
		return input.replace(NEW_LINE_PLACEHOLDER, System.getProperty("line.separator"));
	}
}
