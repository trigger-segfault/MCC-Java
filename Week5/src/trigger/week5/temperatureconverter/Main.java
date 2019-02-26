/*
 * Class Name: TemperatureConverterMain
 * Author: Robert Jordan
 * Date Created: Feb 20, 2019
 * Synopsis: The main class to run the temperature converter program loop.
 */
package trigger.week5.temperatureconverter;

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
		do {
			lastAnswer = Menu.run(lastAnswer);
		} while (lastAnswer != null);
	}
}
