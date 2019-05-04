/*
 * Class Name: MainScreenDriver
 * Author: Robert Jordan
 * Date Created: May 2, 2019
 * Synopsis: The overridden final project Screen Driver with all required menus
 *           built in.
 */
package trigger.finalproject;

import trigger.finalproject.projects.helloworld.*;
import trigger.finalproject.projects.jobapplication.*;
import trigger.finalproject.projects.temperatureconverter.*;
import trigger.finalproject.projects.currencyconverter.*;
import trigger.finalproject.utilities.*;
import trigger.finalproject.utilities.menus.*;

/**
 * The overridden final project Screen Driver with all required menus built in.
 */
public class MainScreenDriver extends ScreenDriver {
	// <editor-fold defaultstate="expanded" desc="Modules">
	public ScreenModuleHelloWorld HELLO_WORLD = new ScreenModuleHelloWorld();
	public ScreenModuleJobApplication JOB_APPLICATION = new ScreenModuleJobApplication();
	public ScreenModuleTemperature TEMPERATURE = new ScreenModuleTemperature();
	public ScreenModuleCurrency CURRENCY = new ScreenModuleCurrency();
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Menus">
	public Menu MAIN_MENU = new Menu("resources/MainMenu.txt", false);
	public MissingFilesMenu MISSING_FILES_MENU = new MissingFilesMenu("resources/MissingFilesMenu.txt");
	public ExceptionMenu EXCEPTION_MENU = new ExceptionMenu("resources/ExceptionMenu.txt");
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Final Project Screen Driver and sets up menus and choices.
	 */
	public MainScreenDriver() {
		addModule(HELLO_WORLD);
		addModule(JOB_APPLICATION);
		addModule(TEMPERATURE);
		addModule(CURRENCY);
		
		setMainMenu(MAIN_MENU);
		setMissingFilesMenu(MISSING_FILES_MENU);
		setExceptionMenu(EXCEPTION_MENU);
		
		MAIN_MENU.choices = new Screen[] {
			HELLO_WORLD.MENU,
			JOB_APPLICATION.MENU,
			TEMPERATURE.MENU,
			CURRENCY.MENU,
			ScreenAction.EXIT,
		};
	}
	// </editor-fold>
}
