/*
 * Class Name: Menu
 * Author: Robert Jordan
 * Date Created: Mar 6, 2019
 * Synopsis: Displays the program title and runs the menu display and input
 *           logic.
 */
package trigger.week3.weekselector;

import java.util.Scanner;

/**
 * The menu class to run logic for the week selector program.
 */
class Menu {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	private static final String EXIT = "exit";
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Static Fields">
	private static final Scanner scanner = new Scanner(System.in);
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="printTitle">
	/**
	 * Prints the title for the program along with help information.
	 */
	public static void printTitle() {
		System.out.println("============= Week Selector =============");
		System.out.println("           -- Robert Jordan --           ");
		System.out.println();
		System.out.println("Run a different week's project.");
		System.out.println();
	}
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="run">
	/**
	 * Runs the menu logic and selects the week then runs it.
	 * @return The true if the program has requested to exit.
	 */
	public static boolean run() {
		Integer weekNum = null;
		do {
			System.out.print("Enter a week number between 1-13 or \"exit\" to exit: ");
			String input = scanner.nextLine();
			
			// Does the user want to get off MR. BONES WILD RIDE?
			if (input.equalsIgnoreCase(EXIT))
				return true;
			
			try {
				weekNum = Integer.valueOf(input);
				// Are we selecting a valid week?
				if (weekNum < 1 || weekNum > 13) {
					System.out.println("  Week number must be between 1-13!");
					weekNum = null;
				}
				else {
					// Attempt to run the week's jar file
					int result = WeekRunner.runWeek(weekNum);
					if (result == WeekRunner.NOT_FOUND) {
						// Let the user know that we can't find the week
						System.out.println("  Week jar file not found!");
						weekNum = null;
					}
					else {
						// Wait for user input before asking for a new week.
						// This way we can see final the output of the program.
						System.out.println();
						System.out.print("Week" + weekNum.toString() + " exited " +
								String.valueOf(result) + ". Press any key to continue...");
						scanner.nextLine();
					}
				}
			} catch (NumberFormatException ex) {
				// You done goofed. Tell em' like it is.
				System.out.println("  Invalid input!");
			}
		} while (weekNum == null);
		return false;
	}
	// </editor-fold>
}
