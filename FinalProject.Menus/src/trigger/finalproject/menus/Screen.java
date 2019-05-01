/*
 * Class Name: Screen
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: Abstract console interface.
 */
package trigger.finalproject.menus;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import trigger.finalproject.utilities.*;

/**
 * Abstract console interface class.
 */
public abstract class Screen {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The maximum width of the console based on project specifications.
	 */
	public static final int WIDTH = 78;
	/**
	 * The maximum height of the console based on project specifications.
	 */
	public static final int HEIGHT = 24;
	
	/**
	 * A screen action to exit the application.
	 */
	public static final Screen EXIT = new ScreenAction(ScreenActionType.EXIT);
	/**
	 * A screen action to go to the main menu.
	 */
	public static final Screen MAIN = new ScreenAction(ScreenActionType.MAIN);
	/**
	 * A screen action to go to the last screen.
	 */
	public static final Screen LAST = new ScreenAction(ScreenActionType.LAST);
	/**
	 * A screen action to go to the current screen again.
	 */
	public static final Screen CURRENT = new ScreenAction(ScreenActionType.CURRENT);
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Abstract/Virtual Methods">
	/**
	 * Prints the menu to the screen.
	 * @throws FileNotFoundException The menu text file was not found.
	 * @throws IOException An error occurred while reading the menu file.
	 */
	public abstract void print() throws FileNotFoundException, IOException;
	/**
	 * Runs the menu to the screen and returns the next Screen or ScreenAction.
	 * @return The next Screen or ScreenAction.
	 * @throws RequestExitException User requested to exit program.
	 * @throws RequestBackException User requested to go back one level.
	 */
	public abstract Screen run() throws RequestExitException, RequestBackException;
	/**
	 * Checks if this screen has any missing required files and adds them to the
	 * list.
	 * @param files The list of files to add missing files to.
	 */
	public void getMissingFiles(Collection<String> files) {
	}
	// </editor-fold>
}
