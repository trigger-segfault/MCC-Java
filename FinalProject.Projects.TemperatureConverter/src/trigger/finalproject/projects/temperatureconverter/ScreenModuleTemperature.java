/*
 * Class Name: ScreenModuleJobApplication
 * Author: Robert Jordan
 * Date Created: May 3, 2019
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
	public Menu INPUT = new TemperatureInputMenu("resources/TemperatureInputMenu.txt");
	public Menu FILE = new TemperatureFileMenu("resources/TemperatureFileMenu.txt");
	public Menu GENERATE = new TemperatureGenMenu("resources/TemperatureGenMenu.txt");
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Screen Module and sets up menu choices.
	 */
	public ScreenModuleTemperature() {
		addScreen(MENU);
		addScreen(INPUT);
		addScreen(FILE);
		addScreen(GENERATE);
		
		MENU.choices = new Screen[] {
			INPUT,
			FILE,
			GENERATE,
			ScreenAction.MAIN,
		};
	}
	// </editor-fold>
}