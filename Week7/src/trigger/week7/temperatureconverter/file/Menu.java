/*
 * Class Name: Menu
 * Author: Robert Jordan
 * Date Created: Mar 12, 2019
 * Synopsis: Displays the program title and runs the menu display and input
 *           logic and outputs the converted temperatures.
 */
package trigger.week7.temperatureconverter.file;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The menu class to run logic for the temperature file converter program.
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
	private static final DecimalFormat tempFormat = new DecimalFormat("#.00");
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="PrintTitle">
	/**
	 * Prints the title for the program along with help information.
	 */
	public static void printTitle() {
		System.out.println("====== Temperatures File Converter ======");
		System.out.println("           -- Robert Jordan --           ");
		System.out.println();
		System.out.println("Type \"" + EXIT + "\" during any input to quit.");
		System.out.println("Temperature Input Examples: 22C (Celsius) ");
		System.out.println("         101F (Fahrenheit), 4000K (Kelvin)");
		System.out.println();
	}
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="Run">
	/**
	 * Runs the menu logic and converts a temperature.
	 * @param temps The list of temperatures to convert.
	 */
	public static boolean run() {
		// Parse the new temperature unit to convert to.
		String input;
		while (true) {
			System.out.println("Generate a temperates file or convert an existing file:");
			if ((input = nextLine("Enter choice (gen/conv)")) == null) return true;
			
			if (input.equalsIgnoreCase("gen")) {
				if (runGenerate())
					return true;
				break;
			}
			if (input.equalsIgnoreCase("conv")) {
				if (runConvert())
					return true;
				break;
			}
			printErr("Invalid choice!");
		}
		
		System.out.println();
		return false;
	}
	/**
	 * Runs the generate choice of the temperature file converter.
	 * @return True if the user requested to exit.
	 */
	private static boolean runGenerate() {
		Temperature minTemp = null;
		Temperature maxTemp;
		Integer minCount = null;
		Integer maxCount;
		String filePath;
		do {
			if (minTemp != null)
				printErr("Maximum temperature cannot be less than minimum temperature!");
			if ((minTemp = nextTemperature("Minimum temperature")) == null) return true;
			if ((maxTemp = nextTemperature("Maximum temperature")) == null) return true;
		} while (maxTemp.convert(minTemp.getUnit()).getValue() < minTemp.getValue());
		
		do {
			if (minCount != null)
				printErr("Maximum count cannot be less than minimum count!");
			if ((minCount = nextUInt("Minimum count")) == null) return true;
			if ((maxCount = nextUInt("Maximum count")) == null) return true;
		} while (maxCount < minCount);
		
		do {
			if ((filePath = nextLine("File path to save temperates to")) == null) return true;
			
			try {
				TemperaturesFileIO.genTemperaturesFile(filePath,
						minTemp, maxTemp, minCount, maxCount);
			} catch (Exception ex) {
				printErr("An error occurred while trying to save the " + 
						"temperatures to file. The file path may be invalid.");
				filePath = null;
			}
		} while (filePath == null || filePath.length() == 0);
		
		return false;
	}
	/**
	 * Runs the convert choice of the temperature file converter.
	 * @return True if the user requested to exit.
	 */
	private static boolean runConvert() {
		Temperature[] temps = null;
		TemperatureUnit newUnit;
		String filePath;
		do {
			if ((filePath = nextLine("File path to read temperates from")) == null) return true;
			
			try {
				temps = TemperaturesFileIO.readTemperaturesFile(filePath);
				
				System.out.println(String.format("Found %d temperatures to convert.", temps.length));

				// Parse the new temperature unit to convert to.
				if ((newUnit = nextUnit("New temperature unit (C/F/K)")) == null) return true;
				
				// Get the max number of digits in the week numbers
				final int numberPadding = Math.max(1, (int) Math.log10(temps.length) + 1);
		
				// Read the array and convert each temperature in it.
				for (int i = 0; i < temps.length; i++) {
					Temperature temp = temps[i];
					if (temp == null)
						continue;
					Temperature newTemp = temp.convert(newUnit);
					System.out.println(String.format("%" + numberPadding + "s) %9s -> %9s",
							String.valueOf(i + 1),
							temp.toString(tempFormat),
							newTemp.toString(tempFormat)));
				}
			} catch (Exception ex) {
				printErr("An error occurred while trying to read the " + 
						"temperatures from file. The file path may be invalid.");
			}
		} while (temps == null);
		return false;
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Helper Methods">
	private static Temperature nextTemperature(String display) {
		Temperature temp = null;
		do {
			System.out.printf("%s: ", display);
			String input = scanner.nextLine();
			// Return null on exit
			if (input.equalsIgnoreCase(EXIT))
				return null;
			
			try {
				temp = Temperature.valueOf(input);
			} catch (NumberFormatException ex) {
				printErr(ex.getMessage());
			}
		} while (temp == null);
		return temp;
	}
	private static TemperatureUnit nextUnit(String display) {
		TemperatureUnit unit = null;
		do {
			System.out.printf("%s: ", display);
			String input = scanner.nextLine();
			// Return null on exit
			if (input.equalsIgnoreCase(EXIT))
				return null;

			try {
				unit = Temperature.valueOfUnit(input);
			} catch (NumberFormatException ex) {
				printErr(ex.getMessage());
			}
		} while (unit == null);
		return unit;
	}
	private static String nextLine(String display) {
		String input = null;
		do {
			if (input != null && input.length() == 0)
				printErr("Input cannot be empty!");
			System.out.printf("%s: ", display);
			input = scanner.nextLine();
			// Return null on exit
			if (input.equalsIgnoreCase(EXIT))
				return null;
		} while (input.length() == 0);
		return input;
	}
	private static Integer nextUInt(String display) {
		Integer value = null;
		do {
			System.out.printf("%s: ", display);
			String input = scanner.nextLine();
			// Return null on exit
			if (input.equalsIgnoreCase(EXIT))
				return null;
			
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
