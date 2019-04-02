/*
 * Class Name: Menu
 * Author: Robert Jordan
 * Date Created: Apr 2, 2019
 * Synopsis: Utility methods for menus.
 */
package trigger.week9.finalproj;

import trigger.week9.finalproj.FileUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import trigger.week9.finalproj.Main;

/**
 * The menu class to run logic for the temperature converter program.
 */
public class MenuUtils {
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

	// <editor-fold defaultstate="collapsed" desc="Static Fields">
	private static final Scanner scanner = new Scanner(System.in);
	// </editor-fold>

	public static void clearScreen() {
		//Clears Screen in java
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (IOException | InterruptedException ex) {}
	}
	
	// <editor-fold defaultstate="expanded" desc="Print">
	public static void printErr(String message) {
		System.err.println(String.format("  %s", message));
		try {
			Thread.sleep(100);
		} catch (InterruptedException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public static void printErr(String message, int maxWidth, boolean centered) {
		System.err.println(String.format("  %s", message));
		try {
			Thread.sleep(100);
		} catch (InterruptedException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public static void printFile(String path, int maxWidth, boolean centered)
			throws FileNotFoundException, IOException
	{
		String[] contents = FileUtils.readAllLines(path);
		printLines(contents, maxWidth, centered);
	}
	public static void printLines(String[] contents, int maxWidth, boolean centered) {
		for (int i = 0; i < contents.length; i++) {
			printLine(contents[i], maxWidth, centered);
		}
	}
	public static void printLine(String content, int maxWidth, boolean centered) {
		if (maxWidth == 0 || maxWidth == -1) {
			if (centered)
				System.out.println(StringUtils.center(content, maxWidth));
			else
				System.out.println(content);
		}
		else {
			String[] lines = StringUtils.wordEllipsesSplit(content, maxWidth, centered);
			for (int i = 0; i < lines.length; i++) {
				if (centered)
					System.out.println(StringUtils.center(lines[i], maxWidth));
				else
					System.out.println(lines[i]);
			}
		}
	}
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="WaitForInput">
	public static void waitForInput() throws RequestExitException, RequestBackException {
		next();
	}
	public static void waitForInput(String message) throws RequestExitException, RequestBackException {
		next(message);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Next">
	public static String next() throws RequestExitException, RequestBackException {
		String input = scanner.nextLine();
		// Throw on exit
		if (input.equalsIgnoreCase(EXIT))
			throw new RequestExitException();
		// Throw on back
		if (input.equalsIgnoreCase(BACK))
			throw new RequestBackException();
		return input;
	}
	public static String next(String message) throws RequestExitException, RequestBackException {
		System.out.printf("%s: ", message);
		return next();
	}
	public static String nextLine(String message) throws RequestExitException, RequestBackException {
		String input = null;
		do {
			if (input != null && input.length() == 0)
				printErr("Input cannot be empty!");
			input = next(message);
		} while (input.length() == 0);
		return input;
	}
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
					printErr("Input cannot be empty!");
				else
					printErr("Invalid number!");
			}
		} while (value == null);
		return value;
	}
	public static Integer nextUInt(String message)
			throws RequestExitException, RequestBackException
	{
		Integer value = null;
		do {
			value = nextInt(message);
			if (value < 0) {
				printErr("Invalid number, cannot be less than zero!");
				value = null;
			}
		} while (value == null);
		return value;
	}
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
					printErr("Input cannot be empty!");
				else
					printErr("Invalid number!");
			}
		} while (value == null);
		return value;
	}
	public static Double nextUDouble(String message)
			throws RequestExitException, RequestBackException
	{
		Double value = null;
		do {
			value = nextDouble(message);
			if (value < 0) {
				printErr("Invalid number, cannot be less than zero!");
				value = null;
			}
		} while (value == null);
		return value;
	}
	public static Boolean nextBool(String message)
			throws RequestExitException, RequestBackException
	{
		message += " (y/n)";
		String input = nextLine(message).toLowerCase();
		while (!input.equals("yes") && !input.equals("y") && !input.equals("no") && !input.equals("n")) {
			if (input.length() == 0)
				printErr("Input cannot be empty!");
			else
				printErr("Invalid yes/no input!");
			input = nextLine(message).toLowerCase();
		}
		return input.equals("yes") || input.equals("y");
	}
	public static Integer nextRange(String message, int min, int max)
			throws RequestExitException, RequestBackException
	{
		message += String.format(" (%s-%s)", min, max);
		Integer choice = null;
		do {
			choice = nextInt(message);
			if (choice < min) {
				printErr(String.format("Choice cannot be less than %s!", min));
				choice = null;
			}
			else if (choice > max) {
				printErr(String.format("Choice cannot be greater than %s!", max));
				choice = null;
			}
		} while (choice == null);
		return choice;
	}
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
				printErr("Invalid choice!");
		} while (choice == null);
		return choice;
	}
	// </editor-fold>
}
