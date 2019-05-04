/*
 * Class Name: ScreenActionType
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: Types of screen actions that are performed by the ScreenAction
 *           class.
 */
package trigger.finalproject.utilities.menus;

/**
 * Types of screen actions that are performed by the ScreenAction class.
 */
public enum ScreenActionType {
	/**
	 * Exit the application.
	 */
	EXIT,
	/**
	 * Go to the main menu.
	 */
	MAIN,
	/**
	 * Go to the previous menu.
	 */
	LAST,
	/**
	 * Go to the current menu again.
	 */
	CURRENT,
}
