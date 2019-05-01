/*
 * Class Name: Console
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: Utility methods for the console.
 */
package trigger.finalproject.utilities;

import java.io.IOException;
import java.util.Scanner;

/**
 * Utility console functions, shortcuts, and aliases.
 */
public class Console {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The message displayed when asking the user to press enter to continue.
	 */
	static final String PRESS_ENTER_TO_CONTINUE = "Press enter to continue...";
	/**
	 * The message displayed when asking the user to press enter to exit.
	 */
	static final String PRESS_ENTER_TO_EXIT = "Press enter to exit...";
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Static Fields">
	/**
	 * The class used to read from the console.
	 */
	static final Scanner scanner = new Scanner(System.in);
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="Clear">
	/**
	 * Clears the console screen. Except in NetBeans because... NetBeans.
	 */
	public static void clear() {
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (IOException | InterruptedException ex) {}
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Print">
	/**
	 * An alias for System.out.print(String)
	 * @param message The message to print.
	 */
	public static void print(String message) {
		System.out.print(message);
	}
	/**
	 * An alias for System.out.printf(String)
	 * @param message The message to print.
	 * @param os The objects used to format the message.
	 */
	public static void print(String message, Object... os) {
		System.out.printf(message, os);
	}
	/**
	 * An alias for System.out.println()
	 */
	public static void printLine() {
		System.out.println();
	}
	/**
	 * An alias for System.out.println(String)
	 * @param message The message to print.
	 */
	public static void printLine(String message) {
		System.out.println(message);
	}
	/**
	 * A shortcut for System.out.println(String.Format(String, Object...))
	 * @param message The message to print.
	 * @param os The objects used to format the message.
	 */
	public static void printLine(String message, Object... os) {
		System.out.println(String.format(message, os));
	}
	/**
	 * Prints an indented message to the error stream then waits a moment to fix
	 * NetBeans continuing before the message is output.
	 * @param message The message to output.
	 */
	public static void printError(String message) {
		printLine("  %s", message);
		try {
			// This is here because NetBeans is terrible with error stream
			// race conditions. Please ignore.
			Thread.sleep(100);
		} catch (InterruptedException ex) {
			// Nothing game-breaking, just move along
		}
	}
	/**
	 * Prints a formatted indented message to the error stream then waits a
	 * moment to fix NetBeans continuing before the message is output.
	 * @param message The message to output.
	 * @param os The objects used to format the message.
	 */
	public static void printError(String message, Object... os) {
		printError(String.format(message, os));
	}
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="Wait For Input">
	/**
	 * Waits for the user to press enter.
	 */
	public static void waitForInput() {
		scanner.nextLine();
	}
	/**
	 * Leaves a message and waits for the user to press enter
	 * @param message The message to display before waiting for enter.
	 */
	public static void waitForInput(String message) {
		System.out.print(message);
		scanner.nextLine();
	}
	/**
	 * Leaves a formatted message and waits for the user to press enter
	 * @param message The message to display before waiting for enter.
	 * @param os The objects used to format the message.
	 */
	public static void waitForInput(String message, Object... os) {
		System.out.printf(message, os);
		scanner.nextLine();
	}
	/**
	 * A waitForInput shortcut that leaves the message "Press enter to continue...".
	 */
	public static void pressEnterToContinue() {
		System.out.print(PRESS_ENTER_TO_CONTINUE);
		scanner.nextLine();
	}
	/**
	 * A waitForInput shortcut that leaves the message "Press enter to exit...".
	 */
	public static void pressEnterToExit() {
		System.out.print(PRESS_ENTER_TO_EXIT);
		scanner.nextLine();
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Read">
	/**
	 * Reads the next line from the console input stream.
	 * @return The read line.
	 */
	public static String readLine() {
		return scanner.nextLine();
	}
	// </editor-fold>
}
