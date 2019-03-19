/*
 * Class Name: Menu
 * Author: Robert Jordan
 * Date Created: Mar 19, 2019
 * Synopsis: Displays the program title and runs the menu display and input
 *           logic and outputs the converted temperature.
 */
package trigger.week6.temperatureconverter;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The menu class to run logic for the temperature converter program.
 */
class Menu {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The input value used when the user requests to exit the program.
	 */
	private static final String EXIT = "exit";
	/**
	 * The input value used to use the last answer for temperature conversion.
	 */
	private static final String LAST_ANSWER = "ans";
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Static Fields">
	private static final Scanner scanner = new Scanner(System.in);
	private static final DecimalFormat tempFormat = new DecimalFormat("#.00");
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="PrintTitle">
	/**
	 * Prints the title for the program along with help information.
	 */
	public static void printTitle() {
		System.out.println("========= Temperature Converter =========");
		System.out.println("           -- Robert Jordan --           ");
		System.out.println();
		System.out.println("Type \"" + EXIT + "\" during any input to quit.");
		System.out.println("Type \"" + LAST_ANSWER + "\" convert the last answer.");
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
	 * @throws RequestExitException User has requested to exit the program.
	 */
	public static Temperature run(Temperature lastAnswer) throws RequestExitException {
		Temperature temp, newTemp;
		TemperatureUnit newUnit;
		
		// Ask for the temperature to convert
		if (lastAnswer != null) {
			temp = nextTemperatureOrAnswer("Enter a temperature or \"" +
					LAST_ANSWER + "\" for last answer", lastAnswer);
		}
		else {
			temp = nextTemperatureOrAnswer("Enter a temperature", null);
		}
		
		// Ask for the new unit to convert to
		newUnit = nextUnit("Enter a new temperature unit (C/F/K)");
		
		// Convert to the new unit
		newTemp = temp.convert(newUnit);
		
		// Output the result
		System.out.println(String.format("Result: %9s -> %9s",
				temp.toString(tempFormat),
				newTemp.toString(tempFormat)));
		System.out.println();
		
		return newTemp;
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Helper Methods">
	private static Temperature nextTemperatureOrAnswer(String display, Temperature ans) throws RequestExitException {
		Temperature temp = null;
		do {
			System.out.printf("%s: ", display);
			String input = scanner.nextLine();
			// Throw on exit
			if (input.equalsIgnoreCase(EXIT))
				throw new RequestExitException();
			
			if (input.equalsIgnoreCase(LAST_ANSWER)) {
				if (ans != null)
					temp = ans;
				else
					printErr("Cannot input last answer when there is none!");
			}
			else {
				try {
					temp = Temperature.valueOf(input);
				} catch (NumberFormatException ex) {
					printErr(ex.getMessage());
				}
			}
		} while (temp == null);
		return temp;
	}
	private static TemperatureUnit nextUnit(String display) throws RequestExitException {
		TemperatureUnit unit = null;
		do {
			System.out.printf("%s: ", display);
			String input = scanner.nextLine();
			// Throw on exit
			if (input.equalsIgnoreCase(EXIT))
				throw new RequestExitException();

			try {
				unit = Temperature.valueOfUnit(input);
			} catch (NumberFormatException ex) {
				printErr(ex.getMessage());
			}
		} while (unit == null);
		return unit;
	}
	private static String nextLine(String display) throws RequestExitException {
		String input = null;
		do {
			if (input != null && input.length() == 0)
				printErr("Input cannot be empty!");
			System.out.printf("%s: ", display);
			input = scanner.nextLine();
			// Throw on exit
			if (input.equalsIgnoreCase(EXIT))
				throw new RequestExitException();
		} while (input.length() == 0);
		return input;
	}
	private static Integer nextUInt(String display) throws RequestExitException {
		Integer value = null;
		do {
			System.out.printf("%s: ", display);
			String input = scanner.nextLine();
			// Throw on exit
			if (input.equalsIgnoreCase(EXIT))
				throw new RequestExitException();
			
			try {
				value = Integer.valueOf(input);
				if (value < 0) {
					printErr("Invalid number, cannot be less than zero!");
					value = null;
				}
			} catch (NumberFormatException ex) {
				if (input.length() == 0)
					printErr("Input cannot be empty!");
				else
					printErr("Invalid number!");
			}
		} while (value == null);
		return value;
	}
	public static void printErr(String message) {
		System.err.println(String.format("  %s", message));
		try {
			Thread.sleep(100);
		} catch (InterruptedException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	// </editor-fold>
}
