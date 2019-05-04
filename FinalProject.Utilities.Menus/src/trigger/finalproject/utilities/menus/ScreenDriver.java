/*
 * Class Name: ScreenDriver
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: A class that handles the screen transition and exception logic.
 */
package trigger.finalproject.utilities.menus;

import java.util.ArrayList;
import trigger.finalproject.utilities.*;

/**
 * A class that handles the screen transition and exception logic.
 */
public abstract class ScreenDriver extends ScreenModule {
	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * The main menu to display first.
	 */
	private Menu mainMenu;
	/**
	 * The menu to display missing required files.
	 */
	private MissingFilesMenu missingFilesMenu;
	/**
	 * The menu for displaying an unhandled exception.
	 */
	private ExceptionMenu exceptionMenu;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the base Screen Driver.
	 */
	public ScreenDriver() {
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Setup">
	/**
	 * Sets the main menu to use for the driver. You must not also call addScreen
	 * for this menu.
	 * @param menu The main menu to register.
	 */
	protected final void setMainMenu(Menu menu) {
		if (mainMenu != null)
			removeScreen(mainMenu);
		mainMenu = menu;
		addScreen(mainMenu);
	}
	/**
	 * Sets the missing files menu to use for the driver. You must not also call
	 * addScreen for this menu.
	 * @param menu The missing files menu to register.
	 */
	protected final void setMissingFilesMenu(MissingFilesMenu menu) {
		if (missingFilesMenu != null)
			removeScreen(missingFilesMenu);
		missingFilesMenu = menu;
		addScreen(missingFilesMenu);
	}
	/**
	 * Sets the unhandled exception menu to use for the driver. You must not
	 * also call addScreen for this menu.
	 * @param menu The exception menu to register.
	 */
	protected final void setExceptionMenu(ExceptionMenu menu) {
		if (exceptionMenu != null)
			removeScreen(exceptionMenu);
		exceptionMenu = menu;
		addScreen(exceptionMenu);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Run">
	/**
	 * Runs the menu driver logic.
	 */
	public void run() throws Exception {
		try {
			ArrayList<String> missingFiles = new ArrayList<>();
			getMissingFiles(missingFiles);
			if (!missingFiles.isEmpty()) {
				missingFilesMenu.missingFiles =
						missingFiles.toArray(new String[missingFiles.size()]);
				missingFilesMenu.print(this);
				missingFilesMenu.run(this);
				return;
			}
			
			Screen currentScreen = mainMenu;
			Screen lastScreen = currentScreen;
			
			do {
				ScreenModule owner = findOwner(currentScreen);
				currentScreen.print(owner);
				Screen nextScreen = null;
				try {
					nextScreen = currentScreen.run(owner);
				} catch (RequestException ex) {
					nextScreen = currentScreen.onRequst(owner, ex.type);
				}
				if (nextScreen instanceof ScreenAction) {
					ScreenAction action = (ScreenAction) nextScreen;
					switch (action.type) {
						case LAST:
							Screen swap = lastScreen;
							lastScreen = currentScreen;
							currentScreen = swap;
							break;
						case MAIN:
							lastScreen = currentScreen;
							currentScreen = mainMenu;
							break;
						case CURRENT: /* Do nothing  */ break;
						case EXIT: /* Leave the loop */ return;
					}
				}
				else if (nextScreen != currentScreen) {
					lastScreen = currentScreen;
					currentScreen = nextScreen;
				}
			} while (currentScreen != null);
		} catch (Exception ex) {
			exceptionMenu.exception = ex;
			exceptionMenu.print(this);
			exceptionMenu.run(this);
		}
	}
	// </editor-fold>
}
