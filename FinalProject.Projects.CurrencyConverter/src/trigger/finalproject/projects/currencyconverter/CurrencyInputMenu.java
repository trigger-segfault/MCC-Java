/*
 * Class Name: CurrencyInputMenu
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: A screen for converting a user-input currency.
 */
package trigger.finalproject.projects.currencyconverter;

import trigger.finalproject.utilities.*;
import trigger.finalproject.utilities.menus.*;

/**
 * A screen for converting a user-input currency.
 */
public class CurrencyInputMenu extends Menu {
	// <editor-fold defaultstate="expanded" desc="Fields">
	/**
	 * The last answer during temperature conversion.
	 */
	private Currency ans = null;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 */
	public CurrencyInputMenu(String textFile) {
		super(textFile);
		this.choices = new Screen[] { ScreenAction.LAST };
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Menu Overrides">
	@Override
	public Screen run(ScreenModule owner) throws RequestException, Exception {
		CurrencyUtils.printUnits();
		Console.printLine();
		
		while (true) {
			// Ask for the temperature to convert
			String message = "Enter a currency";
			if (ans != null) {
				 message = String.format("Enter a currency or \"%s\" for last answer",
						 CurrencyUtils.LAST_ANSWER);
			}
			Currency currency = CurrencyUtils.nextCurrencyOrAns(message, ans);

			// Ask for the new unit to convert to
			CurrencyUnit newUnit = CurrencyUtils.nextUnit("Enter a new currency ISO code");

			// Convert to the new unit
			Currency newCurrency = currency.convert(newUnit);
			ans = newCurrency;

			// Output the result
			Console.printLine("Result: %12s -> %12s",
					currency.toString(),
					newCurrency.toString());
			Console.printLine();
		}
		
		//return super.run(owner);
	}
	// </editor-fold>
}
