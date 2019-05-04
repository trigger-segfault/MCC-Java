/*
 * Class Name: ScreenModuleJobApplication
 * Author: Robert Jordan
 * Date Created: May 2, 2019
 * Synopsis: The screen module for the Temperature Converter.
 */
package trigger.finalproject.projects.temperatureconverter;

import trigger.finalproject.utilities.menus.*;

/**
 * The screen module for the Temperature Converter.
 */
public class ScreenModuleTemperature extends ScreenModule {
	// <editor-fold defaultstate="expanded" desc="Menus">
	public Menu MENU = new Menu("resources/TemperatureMenu.txt");
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Screen Module and sets up menu choices.
	 */
	public ScreenModuleTemperature() {
		addScreen(MENU);
		
		MENU.choices = new Screen[] {
			ScreenAction.CURRENT,
			ScreenAction.CURRENT,
			ScreenAction.MAIN,
		};
	}
	// </editor-fold>
}