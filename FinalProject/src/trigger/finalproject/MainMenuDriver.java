/*
 * Class Name: MainMenuDriver
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: The overridden final project Menu Driver with all required menus
 *           built in.
 */
package trigger.finalproject;

import trigger.finalproject.helloworld.*;
import trigger.finalproject.menus.*;
import trigger.finalproject.utilities.*;

/**
 * The overridden final project Menu Driver with all required menus built in.
 */
public class MainMenuDriver extends MenuDriver {
	// <editor-fold defaultstate="expanded" desc="Menus">
	public Menu MAIN_MENU = new Menu("resources/MainMenu.txt");
	public MissingFilesMenu MISSING_FILES_MENU = new MissingFilesMenu("resources/MissingFilesMenu.txt");
	public ExceptionMenu EXCEPTION_MENU = new ExceptionMenu("resources/ExceptionMenu.txt");
	
	public Menu HELLO_WORLD_MENU = new Menu("resources/HelloWorldMenu.txt");
	public Menu JOB_APPLICATION_MENU = new Menu("resources/JobApplicationMenu.txt");
	public Menu TEMPERATURE_MENU = new Menu("resources/TemperatureMenu.txt");
	public Menu CURRENCY_MENU = new Menu("resources/CurrencyMenu.txt");
	
	// HELLO WORLD:
	private static final String HELLO_WORLD_DRAW_PATH = "resources/HelloWorldDrawMenu.txt";
	public Menu HELLO_WORLD_STANDARD = new HelloWorldStandard(HELLO_WORLD_DRAW_PATH);
	public Menu HELLO_WORLD_DIVERGENCE = new HelloWorldDivergence(HELLO_WORLD_DRAW_PATH);
	
	// JOB APPLICATION:
	
	// TEMPERATURE CONVERTER:
	
	// CURRENCY CONVERTER:
	
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Final Project Menu Driver and sets up menus and choices.
	 */
	public MainMenuDriver() {
		setMainMenu(MAIN_MENU);
		setMissingFilesMenu(MISSING_FILES_MENU);
		setExceptionMenu(EXCEPTION_MENU);
		
		addScreen(JOB_APPLICATION_MENU);
		addScreen(TEMPERATURE_MENU);
		addScreen(CURRENCY_MENU);
		
		MAIN_MENU.choices = new Screen[] {
			HELLO_WORLD_MENU,
			JOB_APPLICATION_MENU,
			TEMPERATURE_MENU,
			CURRENCY_MENU,
			Screen.EXIT,
		};
		HELLO_WORLD_MENU.choices = new Screen[] {
			HELLO_WORLD_STANDARD,
			HELLO_WORLD_DIVERGENCE,
			Screen.MAIN,
		};
		// TODO: Job Application Menu Choices
		JOB_APPLICATION_MENU.choices = new Screen[] {
			Screen.MAIN,
		};
		TEMPERATURE_MENU.choices = new Screen[] {
			Screen.CURRENT,// Temperatures From Input
			Screen.CURRENT,// Temperatures From File
			Screen.MAIN,
		};
		CURRENCY_MENU.choices = new Screen[] {
			Screen.CURRENT,// Currencies From Input
			Screen.CURRENT,// Currencies From File
			Screen.MAIN,
		};
	}
	// </editor-fold>
}
