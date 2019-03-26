/*
 * Class Name: Menu
 * Author: Robert Jordan
 * Date Created: Mar 19, 2019
 * Synopsis: Displays the program title and runs the menu display and input
 *           logic and outputs the converted temperature.
 */
package trigger.week8.currencyconverter;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
	 * The input value used when the user requests to go back to the menu.
	 */
	private static final String BACK = "back";
	/**
	 * The input value used to use the last answer for temperature conversion.
	 */
	//private static final String LAST_ANSWER = "ans";
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Static Fields">
	private static final Scanner scanner = new Scanner(System.in);
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="PrintTitle">
	/**
	 * Prints the title for the program along with help information.
	 */
	public static void printTitle() {
		System.out.println("========== Currency Converter ===========");
		System.out.println("           -- Robert Jordan --           ");
		System.out.println();
		System.out.println("Type \"" + EXIT + "\" during any input to quit.");
		System.out.println("Type \"" + BACK + "\" during any input to go back to the menu.");
		//System.out.println("Type \"" + LAST_ANSWER + "\" convert the last answer.");
		System.out.println("Currency Input Examples: 20USD (United States Dollar)");
		System.out.println("         50.01 EUR (Euro), 100000 JPY (Japanese Yen)");
		System.out.println();
		printUnits();
		System.out.println();
	}
	/**
	 * Prints all available currency units for the user to see.
	 */
	public static void printUnits() {
		System.out.println("The available currency units are:");
		CurrencyUnit[] units = CurrencyUnit.values();
		for (int i = 0; i < units.length; i++) {
			CurrencyUnit unit = units[i];
			System.out.println(String.format("  %-3s : %s", unit.iso, unit.name));
		}
	}
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="Run">
	/**
	 * Runs the menu logic and converts a currencies.
	 * @throws RequestExitException The user requested to exit the program.
	 * @throws RequestBackException The user requested to go back to the menu.
	 */
	public static void run() throws RequestExitException, RequestBackException {
		// Parse the new temperature unit to convert to.
		String input;
		while (true) {
			System.out.println("Generate a currenties file, convert an existing file, or list supported currencies:");
			input = nextLine("Enter choice (gen/conv/list)");
			
			if (input.equalsIgnoreCase("gen")) {
				runGenerate();
				break;
			}
			if (input.equalsIgnoreCase("conv")) {
				runConvert();
				break;
			}
			if (input.equalsIgnoreCase("list")) {
				printUnits();
				break;
			}
			printErr("Invalid choice!");
		}
		
		System.out.println();
	}
	/**
	 * Runs the generate choice of the temperature file converter.
	 * @return True if the user requested to exit.
	 */
	private static boolean runGenerate() throws RequestExitException, RequestBackException {
		Double minValue = null;
		Double maxValue;
		String filePath;
		do {
			if (minValue != null)
				printErr("Maximum value cannot be less than minimum value!");
			minValue = nextUDouble("Minimum value");
			maxValue = nextUDouble("Maximum value");
		} while (maxValue < minValue);
		
		do {
			filePath = nextLine("File path to save currencies to");
			
			try {
				CurrencyFileIO.genCurrenciesFile(filePath,
						minValue, maxValue);
			} catch (Exception ex) {
				printErr("An error occurred while trying to save the " + 
						"currencies to file. The file path may be invalid.");
				filePath = null;
			}
		} while (filePath == null || filePath.length() == 0);
		
		return false;
	}
	/**
	 * Runs the convert choice of the currency file converter.
	 */
	private static void runConvert() throws RequestExitException, RequestBackException {
		Double[] currencies = null;
		//CurrencyUnit defaultUnit;
		CurrencyUnit newUnit;
		String filePath;
		String reportPath;
		CurrencyUnit[] units = CurrencyUnit.values();
		do {
			//defaultUnit = nextUnit("Default currency unit when none is present");
			
			filePath = nextLine("File path to read currencies from");
			
			try {
				//currencies = CurrencyFileIO.readCurrenciesFile(filePath, defaultUnit);
				currencies = CurrencyFileIO.readUnmarkedCurrenciesFile(filePath);
				
				System.out.println(String.format("Found %d currencies to convert.", currencies.length));

				// Parse the new currency unit to convert to.
				newUnit = nextUnit("New currency unit");
				
				// Get the max number of digits in the week numbers
				final int numberPadding = Math.max(1, (int) Math.log10(currencies.length) + 1);
		
				ArrayList<String> lines = new ArrayList<>();
				
				
				lines.add("==== Currency Conversion Report ====");
				lines.add(String.format("New Currency: %-3s : %s", newUnit.iso, newUnit.name));
				lines.add("");
				
				for (int j = 0; j < units.length; j++) {
					CurrencyUnit unit = units[j];
					// Don't convert to the same new unit
					if (unit == newUnit)
						continue;
					
					// Print header for currency type
					String line = String.format("%-3s : %s", unit.iso, unit.name);
					System.out.println(line);
					lines.add(line);
					
					// Read the array and convert each temperature in it.
					for (int i = 0; i < currencies.length; i++) {
						Currency currency = new Currency(currencies[i], unit);
						Currency newCurrency = currency.convert(newUnit);
						line = String.format("%" + numberPadding + "s) %16s -> %16s",
								String.valueOf(i + 1),
								currency.toString(),
								newCurrency.toString());
						System.out.println(line);
						lines.add(line);
					}
					System.out.println();
					lines.add("");
				}
				if (!nextBool("Save report to file"))
					break;
				
				reportPath = nextLine("File path to save report to");
				
				try {
					String[] lineArray = new String[lines.size()];
					lines.toArray(lineArray);
					FileUtils.writeAllLines(reportPath, lineArray);
				} catch (Exception ex) {
					printErr("An error occurred while trying to write the " + 
							"currencies report file. The file path may be invalid.");
				}
				
			} catch (Exception ex) {
				printErr("An error occurred while trying to read the " + 
						"currencies from file. The file path may be invalid.");
			}
		} while (currencies == null);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Helper Methods">
	private static Currency nextCurrency(String display) throws RequestExitException, RequestBackException {
		Currency currency = null;
		do {
			String input = nextLine(display);
			// Throw on exit
			if (input.equalsIgnoreCase(EXIT))
				throw new RequestExitException();
			
			try {
				currency = Currency.valueOf(input);
			} catch (NumberFormatException ex) {
				printErr(ex.getMessage());
			}
		} while (currency == null);
		return currency;
	}
	private static CurrencyUnit nextUnit(String display) throws RequestExitException, RequestBackException {
		printUnits();
		CurrencyUnit unit = null;
		do {
			String input = nextLine(display);

			try {
				unit = Currency.valueOfUnit(input);
			} catch (NumberFormatException ex) {
				printErr(ex.getMessage());
			}
		} while (unit == null);
		return unit;
	}
	private static String nextLine(String display) throws RequestExitException, RequestBackException {
		String input = null;
		do {
			if (input != null && input.length() == 0)
				printErr("Input cannot be empty!");
			System.out.printf("%s: ", display);
			input = scanner.nextLine();
			// Throw on exit
			if (input.equalsIgnoreCase(EXIT))
				throw new RequestExitException();
			// Throw on back
			if (input.equalsIgnoreCase(BACK))
				throw new RequestBackException();
		} while (input.length() == 0);
		return input;
	}
	private static Double nextUDouble(String display) throws RequestExitException, RequestBackException {
		Double value = null;
		do {
			String input = nextLine(display);
			
			try {
				value = Double.valueOf(input);
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
	private static Integer nextUInt(String display) throws RequestExitException, RequestBackException {
		Integer value = null;
		do {
			String input = nextLine(display);
			
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
	private static boolean nextBool(String display) throws RequestExitException, RequestBackException {
		display += " (y/n)";
		String input = nextLine(display).toLowerCase();
		while (!input.equals("yes") && !input.equals("y") && !input.equals("no") && !input.equals("n")) {
			if (input.length() == 0)
				printErr("Input cannot be empty!");
			else
				printErr("Invalid yes/no input!");
			input = nextLine(display).toLowerCase();
		}
		return input.equals("yes") || input.equals("y");
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
