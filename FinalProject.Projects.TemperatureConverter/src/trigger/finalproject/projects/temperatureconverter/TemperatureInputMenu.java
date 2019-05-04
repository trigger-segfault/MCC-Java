/*
 * Class Name: TemperatureInputMenu
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: A screen for converting a user-input temperature.
 */
package trigger.finalproject.projects.temperatureconverter;

import trigger.finalproject.utilities.*;
import trigger.finalproject.utilities.menus.*;

/**
 * A screen for converting a user-input temperature.
 */
public class TemperatureInputMenu extends Menu {
	// <editor-fold defaultstate="expanded" desc="Fields">
	/**
	 * The last answer during temperature conversion.
	 */
	private Temperature ans = null;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 */
	public TemperatureInputMenu(String textFile) {
		super(textFile);
		this.choices = new Screen[] { ScreenAction.LAST };
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Menu Overrides">
	@Override
	public Screen run(ScreenModule owner) throws RequestException, Exception {
		while (true) {
			// Ask for the temperature to convert
			String message = "Enter a temperature";
			if (ans != null) {
				 message = String.format("Enter a temperature or \"%s\" for last answer",
						 TemperatureUtils.LAST_ANSWER);
			}
			Temperature temp = TemperatureUtils.nextTemperatureOrAns(message, ans);

			// Ask for the new unit to convert to
			TemperatureUnit newUnit = TemperatureUtils.nextUnit("Enter a new temperature unit (C/F/K)");

			// Convert to the new unit
			Temperature newTemp = temp.convert(newUnit);
			ans = newTemp;

			// Output the result
			Console.printLine("Result: %9s -> %9s",
					temp.toString(),
					newTemp.toString());
			Console.printLine();
		}
		//return super.run(owner);
	}
	// </editor-fold>
}
