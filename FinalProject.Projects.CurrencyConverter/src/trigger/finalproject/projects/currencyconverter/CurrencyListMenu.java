/*
 * Class Name: CurrencyListMenu
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: A screen to list all supported currency types.
 */
package trigger.finalproject.projects.currencyconverter;

import trigger.finalproject.utilities.*;
import trigger.finalproject.utilities.menus.*;

/**
 * A screen to list all supported currency types.
 */
public class CurrencyListMenu extends Menu {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The token to replace with all known currencies.
	 */
	private static final String CURRENCIES_TOKEN = "CURRENCIES";
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Hello World Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 */
	public CurrencyListMenu(String textFile) {
		super(textFile);
		this.choices = new Screen[] { ScreenAction.LAST };
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Menu Overrides">
	@Override
	protected boolean handleToken(String token, Align align) throws Exception {
		if (token.equals(CURRENCIES_TOKEN)) {
			CurrencyUtils.printUnits();
			Console.printLine();
			return true;
		}
		return false;
	}
	// </editor-fold>
}
