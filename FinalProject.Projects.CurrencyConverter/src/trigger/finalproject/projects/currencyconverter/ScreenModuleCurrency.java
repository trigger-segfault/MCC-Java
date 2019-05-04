/*
 * Class Name: ScreenModuleCurrencyConverter
 * Author: Robert Jordan
 * Date Created: May 2, 2019
 * Synopsis: The screen module for the Currency Converter.
 */
package trigger.finalproject.projects.currencyconverter;

import java.util.Collection;
import trigger.finalproject.utilities.menus.*;

/**
 * The screen module for the Currency Converter.
 */
public class ScreenModuleCurrency extends ScreenModule {
	// <editor-fold defaultstate="expanded" desc="Menus">
	public Menu MENU = new Menu("resources/CurrencyMenu.txt");
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Screen Module and sets up menu choices.
	 */
	public ScreenModuleCurrency() {
		addScreen(MENU);
		
		MENU.choices = new Screen[] {
			ScreenAction.CURRENT,
			ScreenAction.CURRENT,
			ScreenAction.MAIN,
		};
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="ScreenModule Overrides">
	@Override
	public void getMissingFiles(Collection<String> files) {
		super.getMissingFiles(files);
		addMissingFile(CurrencyUnit.UNITS_FILE, files);
	}
	// </editor-fold>
}
