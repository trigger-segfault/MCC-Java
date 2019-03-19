/*
 * Class Name: Main
 * Author: Robert Jordan
 * Date Created: Mar 19, 2019
 * Synopsis: The main class to run the temperature converter program loop.
 *           Also catches the exception requsting to exit the program.
 */
package trigger.week6.temperatureconverter;

/**
 * The main class for the temperature converter program.
 */
public class Main {
	/**
	 * The main method for the temperature converter program.
	 * @param args Unused
	 */
	public static void main(String[] args) {
		Menu.printTitle();
		Temperature lastAnswer = null;
		try {
			while (true) {
				lastAnswer = Menu.run(lastAnswer);
			}
		} catch (RequestExitException ex) {
			// Return placed here incase anything is
			// ever added after this try/catch.
			return;
		}
	}
}
