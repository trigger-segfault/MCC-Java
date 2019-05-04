/*
 * Class Name: ScreenModuleCurrencyConverter
 * Author: Robert Jordan
 * Date Created: May 3, 2019
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
	public Menu INPUT = new CurrencyInputMenu("resources/CurrencyInputMenu.txt");
	public Menu FILE = new CurrencyFileMenu("resources/CurrencyFileMenu.txt");
	public Menu GENERATE = new CurrencyGenMenu("resources/CurrencyGenMenu.txt");
	public Menu LIST = new CurrencyListMenu("resources/CurrencyListMenu.txt");
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Screen Module and sets up menu choices.
	 */
	public ScreenModuleCurrency() {
		addScreen(MENU);
		addScreen(INPUT);
		addScreen(FILE);
		addScreen(GENERATE);
		addScreen(LIST);
		
		MENU.choices = new Screen[] {
			INPUT,
			FILE,
			GENERATE,
			LIST,
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
