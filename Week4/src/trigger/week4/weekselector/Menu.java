/*
 * Class Name: Menu
 * Author: Robert Jordan
 * Date Created: Mar 11, 2019
 * Synopsis: Displays the program title and runs the menu display and input
 *           logic.
 */
package trigger.week4.weekselector;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The menu class to run logic for the week selector program.
 */
class Menu {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The input value used when the user requests to exit the program.
	 */
	private static final String EXIT = "exit";
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Static Fields">
	private static final Scanner scanner = new Scanner(System.in);
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Print Title">
	/**
	 * Prints the title for the program along with help information.
	 */
	public static void printTitle() {
		System.out.println("=========== Week Selector  v2 ===========");
		System.out.println("           -- Robert Jordan --           ");
		System.out.println();
		System.out.println("Run a different week's project.");
		System.out.println();
	}
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="Run">
	/**
	 * Runs the menu logic and selects the week then runs it.
	 * @return The true if the program has requested to exit.
	 */
	public static boolean run(WeekJarsConfig weekJars) {
		Integer weekNum = null;
		do {
			if (printWeeks(weekJars))
				return true;
			
			System.out.print("Enter a week number between 1-" + weekJars.count() +
					" or \"" + EXIT + "\" to exit: ");
			String input = scanner.nextLine();
			
			// Does the user want to get off MR. BONES WILD RIDE?
			if (input.equalsIgnoreCase(EXIT))
				return true;
			
			try {
				weekNum = Integer.valueOf(input);
			} catch (NumberFormatException ex) {
				// You done goofed. Tell em' like it is.
				printErr("  Invalid input!");
				continue;
			}
			
			// Are we selecting a valid week?
			if (weekNum < 1 || weekNum > weekJars.count()) {
				printErr("Week number must be between 1-" + weekJars.count() + "!");
				weekNum = null;
				continue;
			}
			WeekJar week = weekJars.getWeek(weekNum);

			// Make sure the week exists
			if (!week.exists()) {
				printErr("Week number does not exist!");
				weekNum = null;
				continue;
			}

			// Attempt to run the week's jar file0
			try {
				System.out.println();
				int exitCode = week.run();

				// Wait for user input before asking for a new week.
				// This way we can see final the output of the program.
				System.out.println();
				System.out.print(week.getName() + " exited with code " +
						exitCode + ". Press any key to continue...");
				scanner.nextLine();
			} catch (Exception ex) {
				printErr("Error while trying to run " + week.getName() + "!");
				printErr(ex.toString());
				weekNum = null;
			}
		} while (weekNum == null);
		return false;
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Print Weeks">
	/**
	 * Prints all weeks in the week collection.
	 * @param weekJars The week jar collection and configuration settings.
	 * @return True if no weeks were found and the program should exit.
	 */
	private static boolean printWeeks(WeekJarsConfig weekJars) {
		System.out.println("Available Weeks:");
		
		// Configuration settings
		final int namePadding = weekJars.getNamePadding();
		// Get the max number of digits in the week numbers
		final int numberPadding = Math.max(1, (int) Math.log10(weekJars.count()) + 1);
		final int weeksPerRow = weekJars.getWeeksPerRow();
		
		// The number of existing jars that were printed
		int counted = 0;
		// True if the last week hasn't had a newline printed after it yet.
		boolean needsNewLine = false;
		
		for (int i = 1; i <= weekJars.count(); i++) {
			WeekJar week = weekJars.getWeek(i);
			if (!week.exists())
				continue;
			
			// Write the padding week number.
			System.out.print(" " + padLeft(String.valueOf(i), numberPadding) + ") ");
			
			// Pad name to keep nice and fancy alignment
			System.out.print(padRight(week.getName(), namePadding));
			
			// End line after every two entries or end of week list.
			counted++;
			if (counted % weeksPerRow == 0) {
				System.out.println();
				needsNewLine = false;
			}
			else {
				needsNewLine = true;
			}
		}
		if (counted == 0) {
			// Well that sucks, too bad.
			printErr("No weeks were found to be printed!");
			return true;
		}
		else if (needsNewLine) {
			System.out.println();
		}
		
		System.out.println();
		return false;
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Helper Methods">
	public static void printErr(String message) {
		System.err.println("  " + message);
		try {
			Thread.sleep(100);
		} catch (InterruptedException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	private static String padRight(String s, int n) {
		return String.format("%-" + n + "s", s);  
	}
	private static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s);  
	}
	// </editor-fold>
}
