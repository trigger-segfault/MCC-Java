/*
 * Class Name: TemperatureFileMenu
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: A screen for converting a user-input temperatures file.
 */
package trigger.finalproject.projects.temperatureconverter;

import trigger.finalproject.utilities.*;
import trigger.finalproject.utilities.menus.*;

/**
 * A screen for converting a user-input temperatures file.
 */
public class TemperatureFileMenu extends Menu {
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 */
	public TemperatureFileMenu(String textFile) {
		super(textFile);
		this.choices = new Screen[] { ScreenAction.LAST };
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Menu Overrides">
	@Override
	public Screen run(ScreenModule owner) throws RequestException, Exception {
		
		String tempsFile = InputUtils.nextFile("Temperatures File");
		Temperature[] temps;
		
		try {
			temps = TemperatureFileIO.readTemperaturesFile(tempsFile);

			Console.printLine("  Found %d temperatures to convert.", temps.length);
			Console.printLine();

		} catch (Exception ex) {
			Console.printError("An error occurred while trying to read the " + 
					"temperatures from file. The file path may be invalid.");
			return super.run(owner);
		}
		
		TemperatureUnit newUnit = TemperatureUtils.nextUnit("New Temperature Unit (C/F/K)");
		Console.printLine();
		
		// Get the max number of digits in the week numbers
		final int numberPadding = Math.max(1, (int) Math.log10(temps.length) + 1);

		// Read the array and convert each temperature in it.
		for (int i = 0; i < temps.length; i++) {
			Temperature temp = temps[i];
			if (temp == null)
				continue;
			Temperature newTemp = temp.convert(newUnit);
			Console.printLine("%" + numberPadding + "s) %9s -> %9s",
					String.valueOf(i + 1),
					temp.toString(),
					newTemp.toString());
		}
		
		Console.printLine();
		return super.run(owner);
	}
	// </editor-fold>
}
