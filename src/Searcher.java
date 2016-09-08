import controller.SearchEngine;
import controller.SearchEngineImpl;
import data.JsonDataLoaderImpl;
import data.JsonDataSearcherImpl;
import data.JsonDataSourceImpl;
import data.JsonFileReaderImpl;
import display.EnglishLanguageHandlerImpl;
import display.IOHandler;
import display.IOHandlerImpl;
import display.LanguageHandler;

public class Searcher {

	private static final String SEARCH_KEYWORD = "1";
	private static final String INFO_KEYWORD = "2";
	private static final String QUIT_KEYWORD = "quit";
	
	public static void main(String[] args) {
		IOHandler ioHandler = new IOHandlerImpl();
		
		try {
			LanguageHandler languageHandler = new EnglishLanguageHandlerImpl();
			SearchEngine searchEngine = new SearchEngineImpl(languageHandler, new JsonDataSearcherImpl(new JsonDataSourceImpl(new JsonDataLoaderImpl(new JsonFileReaderImpl()))));
		
			//Loop terminates only if user enters the quit keyword
			while(true) {
				//Print usage instructions
				ioHandler.output(languageHandler.getUsageInstructions());
				
				//Get Users input
				String input = getInput(ioHandler);
				
				if(input.equals(SEARCH_KEYWORD)) {
					//If user entered search keyword, begin search process
					beginSearchProcess(ioHandler, languageHandler, searchEngine);
				}
				else if(input.equals(INFO_KEYWORD)) {
					//If user entered info keyword, print possible search options
					ioHandler.output(searchEngine.getSearchableFieldsString());
				}
				else {
					//If user entered invalid input, alert user
					ioHandler.output(languageHandler.getInvalidInputMsg());
				}
			}
		}
		catch (Exception e) {
			ioHandler.output(System.getProperty("line.separator") + "A fatal error occured! The program will now terminate" + System.getProperty("line.separator"));
			throw e;
		}
	}
	
	/**
	 * Gets the input the user entered into the application
	 * and terminates the application if the quit keyword is entered.
	 * 
	 * @param ioHandler an ioHandler from which input can be retrieved
	 * @return the input retrieved from the user as a String
	 */
	private static String getInput(IOHandler ioHandler) {
		String input = ioHandler.getInput();
		
		//If the user entered the quit keyword, immediately terminate the application
		if(input.equals(QUIT_KEYWORD)) {
			terminate();
		}
		
		return input;
	}

	
	/**
	 * Runs the search process which the user can use to search for data. 
	 * Outputs instructions for each step of the search process and once all input
	 * is retrieved, performs the search and outputs the results.
	 * 
	 * @param ioHandler  an ioHandler from which input can be retrieved and output can be displayed
	 * @param searchEngine a search engine which is used to determine if input is valid and perform the search
	 */
	private static void beginSearchProcess(IOHandler ioHandler, LanguageHandler languageHandler, SearchEngine searchEngine) {
		//Prompt the user to choose a search category
		String searchableCategories = searchEngine.getSearchableCategoriesString();
		ioHandler.output(languageHandler.getCategorySelectMsg() + searchableCategories);
		
		//Get the user's selection
		String category = getInput(ioHandler);
		
		//If the user has not selected an available category, alert the user and allow them to try again
		while(!searchEngine.isValidCategory(category)) {
			ioHandler.output(languageHandler.getInvalidCategoryMsg());
			category = ioHandler.getInput();
		}
		
		//Prompt the user to choose a field to search on
		ioHandler.output(languageHandler.getFieldSelectMsg());
		
		//Get the user's selection
		String field = ioHandler.getInput();
		
		//If the user has not selected an available field, alert the user and allow them to try again
		while(!searchEngine.isValidField(category, field)) {
			ioHandler.output(languageHandler.getInvalidFieldMsg());
			field = ioHandler.getInput();
		}
		
		//Prompt the user to enter their search value
		ioHandler.output(languageHandler.getEnterValueMsg());
		
		//Get the user's inputed search value
		String value = ioHandler.getInput();
		
		//Perform the search and output the results
		ioHandler.output(searchEngine.performSearch(category, field, value));
		
	}
	
	//Terminate the application
	/**
	 * Terminates the application
	 */
	private static void terminate() {
		System.exit(0);
	}
}
