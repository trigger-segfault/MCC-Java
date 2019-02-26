/*
 * Class Name: Menu
 * Author: Robert Jordan
 * Date Created: Feb 20, 2019
 * Synopsis: Displays the program title and runs the menu display and input
 *           logic.
 */
package trigger.week5.temperatureconverter;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * The menu class to run logic for the temperature converter program.
 */
class Menu {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	private static final String EXIT = "exit";
	private static final String LAST_ANSWER = "ans";
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Static Fields">
	private static final Scanner scanner = new Scanner(System.in);
	private static final DecimalFormat tempFormat = new DecimalFormat("#.##");
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="PrintTitle">
	/**
	 * Prints the title for the program along with help information.
	 */
	public static void printTitle() {
		System.out.println("========= Temperature Converter =========");
		System.out.println("           -- Robert Jordan --           ");
		System.out.println();
		System.out.println("Type \"exit\" during any input to quit.");
		System.out.println("Type \"ans\" convert the last answer.");
		System.out.println("Temperature Input Examples: 22C (Celsius) ");
		System.out.println("         101F (Fahrenheit), 4000K (Kelvin)");
		System.out.println();
	}
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="Run">
	/**
	 * Runs the menu logic and converts a temperature.
	 * @param lastAnswer The last converted temperature result. For use with
	 *                   "ans" input.
	 * @return The newly converted temperature.-or-null if the user requested to
	 *         exit the program.
	 */
	public static Temperature run(Temperature lastAnswer) {
		String input = null;
		Temperature temp = null;
		Temperature newTemp = null;
		while (temp == null) {
			if (lastAnswer != null)
				System.out.print("Enter a temperature or \"ans\" for last answer: ");
			else
				System.out.print("Enter a temperature: ");

			input = scanner.nextLine();
			if (input.equals(EXIT))
				return null;

			TemperatureUnit unit;
			if (lastAnswer != null && input.equals(LAST_ANSWER)) {
				temp = lastAnswer;
				break;
			}
			if (input.length() != 0 && !Character.isDigit(input.charAt(input.length() - 1)) &&
														  input.charAt(input.length() - 1) != '.')
			{
				try {
				unit = TemperatureUnit.valueOf(input.substring(input.length() - 1));
				} catch (IllegalArgumentException ex) {
					System.out.println("  Invalid temperature unit!");
					continue;
				}
			}
			else {
				System.out.println("  Invalid temperature input, expected a unit at the end!");
				continue;
			}
			try {
				double value = Double.valueOf(input.substring(0, input.length() - 1));
				temp = new Temperature(value, unit);
			} catch (NumberFormatException ex) {
				System.out.println("  Invalid temperature value!");
			}
		}
		while (newTemp == null) {
			System.out.print("Enter a new temperature unit (C/F/K): ");

			input = scanner.nextLine();
			if (input.equals(EXIT))
				return null;

			if (input.length() == 1) {
				try {
					TemperatureUnit newUnit = TemperatureUnit.valueOf(input);
					newTemp = temp.convert(newUnit);
				} catch (IllegalArgumentException ex) {
					System.out.println("  Invalid temperature unit!");
				}
			}
			else {
				System.out.println("  Invalid input, expected a temperature unit!");
			}
		}
		System.out.println("Result: " + temp.toString(tempFormat) + " -> " + newTemp.toString(tempFormat));
		System.out.println();
		return newTemp;
	}
	// </editor-fold>
}
