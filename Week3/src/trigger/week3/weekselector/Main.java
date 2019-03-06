/*
 * Class Name: Main
 * Author: Robert Jordan
 * Date Created: Mar 6, 2019
 * Synopsis: The main class to run the week selector.
 */
package trigger.week3.weekselector;

/**
 * The main class for the week selector program.
 */
public class Main {
	/**
	 * The main method for the week selector program.
	 * @param args Unused
	 */
	public static void main(String[] args) {
		boolean exit;
		do {
			// Reprint the title every time because a lot of output may have
			// passed since last seeing the title.
			Menu.printTitle();
			// The Menu class will tell us if the user requested to exit.
			exit = Menu.run();
		} while (!exit);
	}
}
