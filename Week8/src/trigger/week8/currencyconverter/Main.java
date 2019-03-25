/*
 * Class Name: Main
 * Author: Robert Jordan
 * Date Created: Mar 25, 2019
 * Synopsis: The main class to run the currency file converter program.
 */
package trigger.week8.currencyconverter;

/**
 * The menu class to run logic for the currency file converter program.
 */
public class Main {
	/**
	 * The main method for the temperature file converter program.
	 * @param args Unused
	 */
	public static void main(String[] args) {
		Menu.printTitle();
		try {
			while (true) {
				try {
				Menu.run();
				} catch (RequestBackException ex) {
					System.out.println();
					// Continue the loop
				}
			}
		} catch (RequestExitException ex) {
			// Return placed here incase anything is
			// ever added after this try/catch.
			return;
		}
	}
}
