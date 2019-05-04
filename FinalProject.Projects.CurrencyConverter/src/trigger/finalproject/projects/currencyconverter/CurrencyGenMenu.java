/*
 * Class Name: CurrencyGenMenu
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: A screen for generating currencies files.
 */
package trigger.finalproject.projects.currencyconverter;

import trigger.finalproject.utilities.*;
import trigger.finalproject.utilities.menus.*;

/**
 * A screen for generating currencies files.
 */
public class CurrencyGenMenu extends Menu {
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 */
	public CurrencyGenMenu(String textFile) {
		super(textFile);
		this.choices = new Screen[] { ScreenAction.LAST };
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Menu Overrides">
	@Override
	public Screen run(ScreenModule owner) throws RequestException, Exception {
		/*Temperature minTemp = null, maxTemp = null;
		Integer minCount = null, maxCount = null;
		String filePath;
		do {
			if (minTemp != null)
				Console.printError("Maximum temperature cannot be less than minimum temperature!");
			minTemp = TemperatureUtils.nextTemperature("Minimum temperature");
			maxTemp = TemperatureUtils.nextTemperature("Maximum temperature");
		} while (maxTemp.convert(minTemp.unit).value < minTemp.value);
		
		do {
			if (minCount != null)
				Console.printError("Maximum count cannot be less than minimum count!");
			minCount = InputUtils.nextUInt("Minimum count");
			maxCount = InputUtils.nextUInt("Maximum count");
		} while (maxCount < minCount);
		
		do {
			filePath = InputUtils.nextLine("File path to save temperates to");
			
			try {
				TemperatureFileIO.genTemperaturesFile(filePath,
						minTemp, maxTemp, minCount, maxCount);
			} catch (Exception ex) {
				Console.printError("An error occurred while trying to save the " + 
						"temperatures to file. The file path may be invalid.");
				filePath = null;
			}
		} while (filePath == null);*/
		
		Double minValue = null;
		Double maxValue;
		String filePath;
		do {
			if (minValue != null)
				Console.printError("Maximum value cannot be less than minimum value!");
			minValue = InputUtils.nextUDouble("Minimum value");
			maxValue = InputUtils.nextUDouble("Maximum value");
		} while (maxValue < minValue);
		
		do {
			filePath = InputUtils.nextLine("File path to save currencies to");
			
			try {
				CurrencyFileIO.genCurrenciesFile(filePath,
						minValue, maxValue);
			} catch (Exception ex) {
				Console.printError("An error occurred while trying to save the " + 
						"currencies to file. The file path may be invalid.");
				filePath = null;
			}
		} while (filePath == null || filePath.length() == 0);
		
		Console.printLine();
		Console.printLine("Currencies file successfully generated.");
		Console.printLine();
		
		return super.run(owner);
	}
	// </editor-fold>
}
