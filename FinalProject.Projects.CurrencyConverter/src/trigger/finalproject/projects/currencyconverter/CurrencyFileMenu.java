/*
 * Class Name: CurrencyFileMenu
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: A screen for converting a user-input currencies file.
 */
package trigger.finalproject.projects.currencyconverter;

import java.util.ArrayList;
import trigger.finalproject.utilities.*;
import trigger.finalproject.utilities.menus.*;

/**
 * A screen for converting a user-input currencies file.
 */
public class CurrencyFileMenu extends Menu {
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 */
	public CurrencyFileMenu(String textFile) {
		super(textFile);
		this.choices = new Screen[] { ScreenAction.LAST };
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Menu Overrides">
	@Override
	public Screen run(ScreenModule owner) throws RequestException, Exception {
		CurrencyUtils.printUnits();
		Console.printLine();
		
		String filePath = InputUtils.nextFile("Currencies File");
		Double[] currencies;
		
		try {
			currencies = CurrencyFileIO.readUnmarkedCurrenciesFile(filePath);

			Console.printLine("  Found %d currencies to convert.", currencies.length);
			Console.printLine();

		} catch (Exception ex) {
			Console.printError("An error occurred while trying to read the " + 
					"currencies from file. The file path may be invalid.");
			return super.run(owner);
		}
		
		CurrencyUnit newUnit = CurrencyUtils.nextUnit("New Currency ISO Code");
		Console.printLine();
		
		// Get the max number of digits in the week numbers
		final int numberPadding = Math.max(1, (int) Math.log10(currencies.length) + 1);

		
		ArrayList<String> lines = new ArrayList<>();
				
				
		lines.add("==== Currency Conversion Report ====");
		lines.add(String.format("New Currency: %-3s : %s", newUnit.iso, newUnit.name));
		lines.add("");

		CurrencyUnit[] units = CurrencyUnit.values();
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
		if (!InputUtils.nextBool("Save report to file")) {
			Console.printLine();
			return super.run(owner);
		}

		String reportPath = InputUtils.nextLine("File path to save report to");

		try {
			String[] lineArray = new String[lines.size()];
			lines.toArray(lineArray);
			FileUtils.writeAllLines(reportPath, lineArray);
		} catch (Exception ex) {
			Console.printError("An error occurred while trying to write the " + 
					"currencies report file. The file path may be invalid.");
		}
		
		Console.printLine();
		return super.run(owner);
	}
	// </editor-fold>
}
