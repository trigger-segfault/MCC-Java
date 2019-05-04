/*
 * Class Name: Screen
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: Abstract console interface.
 */
package trigger.finalproject.utilities.menus;

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
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Abstract/Virtual Methods">
	/**
	 * Prints the menu to the screen.
	 * @param owner The screen module containing this screen.
	 * @throws Exception An exception occurred.
	 */
	public abstract void print(ScreenModule owner) throws Exception;
	/**
	 * Runs the menu to the screen and returns the next Screen or ScreenAction.
	 * @param owner The screen module containing this screen.
	 * @return The next Screen or ScreenAction.
	 * @throws RequestException User requested a special action from the program.
	 * @throws Exception An exception occurred.
	 */
	public abstract Screen run(ScreenModule owner) throws RequestException, Exception;
	/**
	 * Called when a Request exception is raised.
	 * @param owner The screen module containing this screen.
	 * @param type The type of the request exception.
	 * @return The new screen to navigate to.
	 */
	public Screen onRequst(ScreenModule owner, RequestType type) {
		return owner.onRequst(type);
	}
	/**
	 * Adds the file to the missing files list if it does not exist.
	 * @param path The file to check for.
	 * @param files The missing files list.
	 */
	protected final void addMissingFile(String path, Collection<String> files) {
		if (!FileUtils.isFile(path) && !files.contains(path))
			files.add(path);
	}
	/**
	 * Checks if this screen has any missing required files and adds them to the
	 * list.
	 * @param files The list of files to add missing files to.
	 */
	public void getMissingFiles(Collection<String> files) {
	}
	// </editor-fold>
}
