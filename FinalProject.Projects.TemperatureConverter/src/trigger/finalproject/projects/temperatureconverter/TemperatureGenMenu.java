/*
 * Class Name: TemperatureGenMenu
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: A screen for generating temperatures files.
 */
package trigger.finalproject.projects.temperatureconverter;

import trigger.finalproject.utilities.*;
import trigger.finalproject.utilities.menus.*;

/**
 * A screen for generating temperatures files.
 */
public class TemperatureGenMenu extends Menu {
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 */
	public TemperatureGenMenu(String textFile) {
		super(textFile);
		this.choices = new Screen[] { ScreenAction.LAST };
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Menu Overrides">
	@Override
	public Screen run(ScreenModule owner) throws RequestException, Exception {
		Temperature minTemp = null, maxTemp = null;
		//Integer minCount = null, maxCount = null;
		String filePath;
		do {
			if (minTemp != null)
				Console.printError("Maximum temperature cannot be less than minimum temperature!");
			minTemp = TemperatureUtils.nextTemperature("Minimum temperature");
			maxTemp = TemperatureUtils.nextTemperature("Maximum temperature");
		} while (maxTemp.convert(minTemp.unit).value < minTemp.value);
		
		/*do {
			if (minCount != null)
				Console.printError("Maximum count cannot be less than minimum count!");
			minCount = InputUtils.nextUInt("Minimum count");
			maxCount = InputUtils.nextUInt("Maximum count");
		} while (maxCount < minCount);*/
		
		do {
			filePath = InputUtils.nextLine("File path to save temperates to");
			
			try {
				TemperatureFileIO.genTemperaturesFile(filePath,
						minTemp, maxTemp);
			} catch (Exception ex) {
				Console.printError("An error occurred while trying to save the " + 
						"temperatures to file. The file path may be invalid.");
				filePath = null;
			}
		} while (filePath == null);
		
		Console.printLine();
		Console.printLine("Temperatures file successfully generated.");
		Console.printLine();
		
		return super.run(owner);
	}
	// </editor-fold>
}
