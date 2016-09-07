package display;

public interface IOHandler {

	/**
	 * Outputs the given string
	 * 
	 * @param output a string which needs to be displayed
	 */
	public void output(String output);

	/**
	 * Waits and gets the input from the input source
	 * 
	 * @return the string which was inputed
	 */
	public String getInput();
	
}
