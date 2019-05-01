/*
 * Class Name: MenuDriver
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: A class that handles the menu transition and exception logic.
 */
package trigger.finalproject.menus;

import java.util.ArrayList;
import trigger.finalproject.utilities.*;

/**
 * A class that handles the menu transition and exception logic.
 */
public abstract class MenuDriver {
	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * The list of Screens registered with the menu driver.
	 */
	private final ArrayList<Screen> screens;
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
	 * Constructs the base Menu Driver.
	 */
	public MenuDriver() {
		screens = new ArrayList<>();
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Setup">
	/**
	 * Adds a normal screen or menu to the driver.
	 * @param screen The screen to register.
	 */
	protected void addScreen(Screen screen) {
		screens.add(screen);
	}
	/**
	 * Sets the main menu to use for the driver. You must not also call addScreen
	 * for this menu.
	 * @param menu The main menu to register.
	 */
	protected void setMainMenu(Menu menu) {
		if (mainMenu != null)
			screens.remove(mainMenu);
		screens.add(menu);
		mainMenu = menu;
	}
	/**
	 * Sets the missing files menu to use for the driver. You must not also call
	 * addScreen for this menu.
	 * @param menu The missing files menu to register.
	 */
	protected void setMissingFilesMenu(MissingFilesMenu menu) {
		if (missingFilesMenu != null)
			screens.remove(missingFilesMenu);
		screens.add(menu);
		missingFilesMenu = menu;
	}
	/**
	 * Sets the unhandled exception menu to use for the driver. You must not
	 * also call addScreen for this menu.
	 * @param menu The exception menu to register.
	 */
	protected void setExceptionMenu(ExceptionMenu menu) {
		if (exceptionMenu != null)
			screens.remove(exceptionMenu);
		screens.add(menu);
		exceptionMenu = menu;
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Run">
	/**
	 * Runs the menu driver logic.
	 */
	public void run() throws Exception {
		try {
			ArrayList<String> missingFiles = new ArrayList<>();
			for (int i = 0; i < screens.size(); i++) {
				screens.get(i).getMissingFiles(missingFiles);
			}
			if (!missingFiles.isEmpty()) {
				missingFilesMenu.missingFiles =
						missingFiles.toArray(new String[missingFiles.size()]);
				missingFilesMenu.print();
				missingFilesMenu.run();
				return;
			}
			
			Screen currentScreen = mainMenu;
			Screen lastScreen = currentScreen;
			
			do {
				Console.clear();
				currentScreen.print();
				Screen nextScreen = null;
				try {
					nextScreen = currentScreen.run();
					if (nextScreen instanceof ScreenAction) {
						ScreenAction action = (ScreenAction) nextScreen;
						switch (action.type) {
							case LAST:
								lastScreen = currentScreen;
								currentScreen = lastScreen;
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
				} catch (RequestBackException ex) {
					lastScreen = currentScreen;
					currentScreen = mainMenu;
				} catch (RequestExitException ex) {
					return;
				}
			} while (currentScreen != null);
		} catch (Exception ex) {
			exceptionMenu.exception = ex;
			exceptionMenu.print();
			exceptionMenu.run();
		}
	}
	// </editor-fold>
}
