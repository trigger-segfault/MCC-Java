/*
 * Class Name: InputUtils
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: Utility methods for special input handling.
 */
package trigger.finalproject.utilities;

/**
 * Utility methods for special input handling.
 */
public class InputUtils {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The input value used when the user requests to exit the program.
	 */
	private static final String EXIT = "exit";
	/**
	 * The input value used when the user requests to go back to the menu.
	 */
	private static final String BACK = "back";
	/**
	 * The input value used to use the last answer for temperature conversion.
	 */
	//private static final String LAST_ANSWER = "ans";
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="WaitForInput">
	/**
	 * Waits for the user to press enter.
	 * @throws RequestExitException
	 */
	public static void waitForInput() throws RequestExitException {
		try {
			next();
		} catch (RequestBackException ex) {
			// Ignore this
		}
	}
	/**
	 * Leaves a message and waits for the user to press enter
	 * @param message The message to display before waiting for enter.
	 * @throws RequestExitException
	 */
	public static void waitForInput(String message) throws RequestExitException {
		try {
			next(message);
		} catch (RequestBackException ex) {
			// Ignore this
		}
	}
	/**
	 * A waitForInput shortcut that leaves the message "Press enter to continue...".
	 * @throws RequestExitException
	 */
	public static void pressEnterToContinue() throws RequestExitException {
		try {
			System.out.print(Console.PRESS_ENTER_TO_CONTINUE);
			next();
		} catch (RequestBackException ex) {
			// Ignore this
		}
	}
	/**
	 * A waitForInput shortcut that leaves the message "Press enter to exit...".
	 * @throws RequestExitException
	 */
	public static void pressEnterToExit() throws RequestExitException {
		try {
			System.out.print(Console.PRESS_ENTER_TO_EXIT);
			next();
		} catch (RequestBackException ex) {
			// Ignore this
		}
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Next">
	/**
	 * Reads the next line, allowing the contents to be empty.
	 * @return The read line.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static String next()
			throws RequestExitException, RequestBackException
	{
		String input = Console.scanner.nextLine();
		// Throw on exit
		if (input.equalsIgnoreCase(EXIT))
			throw new RequestExitException();
		// Throw on back
		if (input.equalsIgnoreCase(BACK))
			throw new RequestBackException();
		return input;
	}
	/**
	 * Prints the message and reads the next line, allowing the contents to be
	 * empty.
	 * @param message The message to print before getting input.
	 * @return The read line.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static String next(String message)
			throws RequestExitException, RequestBackException
	{
		System.out.printf("%s: ", message);
		return next();
	}
	/**
	 * Reads the next line without allowing the contents to be empty.
	 * @return The read line.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static String nextLine()
			throws RequestExitException, RequestBackException
	{
		String input = null;
		do {
			if (input != null && input.length() == 0)
				Console.printError("Input cannot be empty!");
			input = next();
		} while (input.length() == 0);
		return input;
	}
	/**
	 * Prints the message and reads the next line without allowing the contents
	 * to be empty.
	 * @param message The message to print before getting input.
	 * @return The read line.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static String nextLine(String message)
			throws RequestExitException, RequestBackException
	{
		String input = null;
		do {
			if (input != null && input.length() == 0)
				Console.printError("Input cannot be empty!");
			input = next(message);
		} while (input.length() == 0);
		return input;
	}
	/**
	 * Prints the message and reads the next line.
	 * @param message The message to print before getting input.
	 * @param defaultValue The default value when the input is empty.
	 * @return The read line.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static String nextLineDef(String message, String defaultValue)
			throws RequestExitException, RequestBackException
	{
		String input = next(message);
		if (input.length() == 0)
			return defaultValue;
		return input;
	}
	/**
	 * Asks the user for a valid integer.
	 * @param message The message to display before waiting for input.
	 * @return The parsed integer.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static Integer nextInt(String message)
			throws RequestExitException, RequestBackException
	{
		Integer value = null;
		do {
			String input = nextLine(message);
			
			try {
				value = Integer.valueOf(input);
			} catch (NumberFormatException ex) {
				if (input.length() == 0)
					Console.printError("Input cannot be empty!");
				else
					Console.printError("Invalid number!");
			}
		} while (value == null);
		return value;
	}
	/**
	 * Asks the user for a valid integer.
	 * @param message The message to display before waiting for input.
	 * @param defaultValue The default value when the input is empty.
	 * @return The parsed integer.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static Integer nextIntDef(String message, int defaultValue)
			throws RequestExitException, RequestBackException
	{
		Integer value = null;
		do {
			String input = nextLineDef(message, String.valueOf(defaultValue));
			
			try {
				value = Integer.valueOf(input);
			} catch (NumberFormatException ex) {
				if (input.length() == 0)
					Console.printError("Input cannot be empty!");
				else
					Console.printError("Invalid number!");
			}
		} while (value == null);
		return value;
	}
	/**
	 * Asks the user for a valid integer greater than or equal to zero.
	 * @param message The message to display before waiting for input.
	 * @return The parsed integer.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static Integer nextUInt(String message)
			throws RequestExitException, RequestBackException
	{
		Integer value = null;
		do {
			value = nextInt(message);
			if (value < 0) {
				Console.printError("Invalid number, cannot be less than zero!");
				value = null;
			}
		} while (value == null);
		return value;
	}
	/**
	 * Asks the user for a valid integer greater than or equal to zero.
	 * @param message The message to display before waiting for input.
	 * @param defaultValue The default value when the input is empty.
	 * @return The parsed integer.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static Integer nextUIntDef(String message, int defaultValue)
			throws RequestExitException, RequestBackException
	{
		Integer value = null;
		do {
			value = nextIntDef(message, defaultValue);
			if (value < 0) {
				Console.printError("Invalid number, cannot be less than zero!");
				value = null;
			}
		} while (value == null);
		return value;
	}
	/**
	 * Asks the user for a valid double.
	 * @param message The message to display before waiting for input.
	 * @return The parsed double.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static Double nextDouble(String message)
			throws RequestExitException, RequestBackException
	{
		Double value = null;
		do {
			String input = nextLine(message);
			
			try {
				value = Double.valueOf(input);
			} catch (NumberFormatException ex) {
				if (input.length() == 0)
					Console.printError("Input cannot be empty!");
				else
					Console.printError("Invalid number!");
			}
		} while (value == null);
		return value;
	}
	/**
	 * Asks the user for a valid double.
	 * @param message The message to display before waiting for input.
	 * @param defaultValue The default value when the input is empty.
	 * @return The parsed double.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static Double nextDoubleDef(String message, double defaultValue)
			throws RequestExitException, RequestBackException
	{
		Double value = null;
		do {
			String input = nextLineDef(message, String.valueOf(defaultValue));
			
			try {
				value = Double.valueOf(input);
			} catch (NumberFormatException ex) {
				if (input.length() == 0)
					Console.printError("Input cannot be empty!");
				else
					Console.printError("Invalid number!");
			}
		} while (value == null);
		return value;
	}
	/**
	 * Asks the user for a valid double greater than or equal to zero.
	 * @param message The message to display before waiting for input.
	 * @return The parsed double.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static Double nextUDouble(String message)
			throws RequestExitException, RequestBackException
	{
		Double value = null;
		do {
			value = nextDouble(message);
			if (value < 0) {
				Console.printError("Invalid number, cannot be less than zero!");
				value = null;
			}
		} while (value == null);
		return value;
	}
	/**
	 * Asks the user for a valid double greater than or equal to zero.
	 * @param message The message to display before waiting for input.
	 * @param defaultValue The default value when the input is empty.
	 * @return The parsed double.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static Double nextUDoubleDef(String message, double defaultValue)
			throws RequestExitException, RequestBackException
	{
		Double value = null;
		do {
			value = nextDoubleDef(message, defaultValue);
			if (value < 0) {
				Console.printError("Invalid number, cannot be less than zero!");
				value = null;
			}
		} while (value == null);
		return value;
	}
	/**
	 * Asks the user for a valid boolean of yes or no.
	 * @param message The message to display before waiting for input.
	 * @return The parsed yes/no boolean.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static Boolean nextBool(String message)
			throws RequestExitException, RequestBackException
	{
		message += " (y/n)";
		String input = nextLine(message).toLowerCase();
		while (!input.equals("yes") && !input.equals("y") &&
				!input.equals("no") && !input.equals("n"))
		{
			if (input.length() == 0)
				Console.printError("Input cannot be empty!");
			else
				Console.printError("Invalid yes/no input!");
			input = nextLine(message).toLowerCase();
		}
		return input.equals("yes") || input.equals("y");
	}
	/**
	 * Asks the user for a valid boolean of yes or no.
	 * @param message The message to display before waiting for input.
	 * @param defaultValue The default value when the input is empty.
	 * @return The parsed yes/no boolean.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static Boolean nextBoolDef(String message, boolean defaultValue)
			throws RequestExitException, RequestBackException
	{
		message += " (y/n)";
		String input = next(message).toLowerCase();
		while (!input.equals("yes") && !input.equals("y") &&
				!input.equals("no") && !input.equals("n"))
		{
			if (input.length() == 0)
				return defaultValue;
			else
				Console.printError("Invalid yes/no input!");
			input = nextLine(message).toLowerCase();
		}
		return input.equals("yes") || input.equals("y");
	}
	/**
	 * Asks the user for a valid integer between min and max.
	 * @param message The message to display before waiting for input.
	 * @param min The minimum value.
	 * @param max The maximum value.
	 * @return The parsed integer inside the range.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static Integer nextRange(String message, int min, int max)
			throws RequestExitException, RequestBackException
	{
		message += String.format(" (%s-%s)", min, max);
		Integer choice = null;
		do {
			choice = nextInt(message);
			if (choice < min) {
				Console.printError(String.format("Choice cannot be less than %s!", min));
				choice = null;
			}
			else if (choice > max) {
				Console.printError(String.format("Choice cannot be greater than %s!", max));
				choice = null;
			}
		} while (choice == null);
		return choice;
	}
	/**
	 * Asks the user for a valid integer between min and max.
	 * @param message The message to display before waiting for input.
	 * @param min The minimum value.
	 * @param max The maximum value.
	 * @param defaultValue The default value when the input is empty.
	 * @return The parsed integer inside the range.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static Integer nextRangeDef(String message, int min, int max, int defaultValue)
			throws RequestExitException, RequestBackException
	{
		message += String.format(" (%s-%s)", min, max);
		Integer choice = null;
		do {
			choice = nextIntDef(message, defaultValue);
			if (choice < min) {
				Console.printError(String.format("Choice cannot be less than %s!", min));
				choice = null;
			}
			else if (choice > max) {
				Console.printError(String.format("Choice cannot be greater than %s!", max));
				choice = null;
			}
		} while (choice == null);
		return choice;
	}
	/**
	 * Asks the user for a valid string from the list of strings. Casing is
	 * ignored.
	 * @param message The message to display before waiting for input.
	 * @param choices The valid choice strings.
	 * @return A valid choice string.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static String nextChoice(String message, String... choices)
			throws RequestExitException, RequestBackException
	{
		String choice = null;
		do {
			String input = nextLine(message);
			for (int i = 0; i < choices.length; i++) {
				if (input.equalsIgnoreCase(choices[i])) {
					choice = input;
					break;
				}
			}
			if (choice == null)
				Console.printError("Invalid choice!");
		} while (choice == null);
		return choice;
	}
	/**
	 * Asks the user for a valid string from the list of strings. Casing is
	 * ignored.
	 * @param message The message to display before waiting for input.
	 * @param defaultValue The default value when the input is empty.
	 * @param choices The valid choice strings.
	 * @return A valid choice string.
	 * @throws RequestExitException
	 * @throws RequestBackException 
	 */
	public static String nextChoiceDef(String message, String defaultValue, String... choices)
			throws RequestExitException, RequestBackException
	{
		String choice = null;
		do {
			String input = next(message);
			if (input.length() == 0)
				return defaultValue;
			for (int i = 0; i < choices.length; i++) {
				if (input.equalsIgnoreCase(choices[i])) {
					choice = input;
					break;
				}
			}
			if (choice == null)
				Console.printError("Invalid choice!");
		} while (choice == null);
		return choice;
	}
	// </editor-fold>
}
