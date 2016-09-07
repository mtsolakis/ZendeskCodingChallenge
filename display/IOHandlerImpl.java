package display;

import java.util.Scanner;

public class IOHandlerImpl implements IOHandler {
	private Scanner scanner;

	public IOHandlerImpl() {
		scanner = new Scanner(System.in);
	}

	/**
	 * Prints the given string
	 * 
	 * @param output a string which needs to be printed
	 */
	public void output(String output) {
		if(output == null) {
			return;
		}
		System.out.println(output);
	}

	/**
	 * Waits and gets the input from the user
	 * 
	 * @return the string the user inputed
	 */
	public String getInput() {
		String input = scanner.nextLine();
		return input;
	}
}
