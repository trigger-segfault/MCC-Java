/*
 * Class Name: MenuDrive
 * Author: Robert Jordan
 * Date Created: Apr 2, 2019
 * Synopsis: A class that handles the menu transition and exception logic.
 */
package trigger.week9.finalproj;

/**
 * A class that handles the menu transition and exception logic.
 */
public class MenuDriver {
	public final Menu mainMenu = new Menu("resources/MainMenu.txt");
	public final Menu lettersetMenu = new Menu("resources/LettersetMenu.txt");
	public final Menu encryptionMenu = new Menu("resources/EncryptionMenu.txt");
	public final Menu decryptionMenu = new Menu("resources/DecryptionMenu.txt");
	public final Menu plugboardMenu = new Menu("resources/PlugboardMenu.txt");
	public final Menu encryptionDirectionsMenu = new Menu("resources/EncryptionDirectionsMenu.txt");
	public final Menu rotorMenu = new Menu("resources/RotorMenu.txt");
	public final Menu rotorDirectionsMenu = new Menu("resources/RotorDirectionsMenu.txt");
	public final Menu htmlMenu = new Menu("resources/HTMLMenu.txt");
	public final Menu creditsMenu = new Menu("resources/CreditsMenu.txt");
	public final Menu missingFilesMenu = new Menu("resources/MissingFilesMenu.txt");
	public final Menu exceptionMenu = new Menu("resources/ExceptionMenu.txt");
	
	private Screen currentScreen;
	private Screen lastScreen;
	
	public MenuDriver() {
		mainMenu.choices = new Screen[] {
			encryptionMenu,
			decryptionMenu,
			plugboardMenu,
			lettersetMenu,
			htmlMenu,
			rotorMenu,
			creditsMenu,
			Screen.EXIT,
		};
		lettersetMenu.choices = new Screen[] {
			Screen.CURRENT, // letterSetConfigureScreen
			mainMenu,
		};
		creditsMenu.choices = new Screen[] {
			mainMenu,
		};
		encryptionMenu.choices = new Screen[] {
			Screen.CURRENT, // encipherMessageScreen
			Screen.CURRENT, // encipherMessagePasteScreen
			encryptionDirectionsMenu,
			mainMenu,
		};
		encryptionDirectionsMenu.choices = new Screen[] {
			mainMenu,
		};
		decryptionMenu.choices = new Screen[] {
			Screen.CURRENT, // decipherMessageScreen
			Screen.CURRENT, // decipherMessagePasteScreen
			Screen.CURRENT, // decipherMessageHtmlScreen
			mainMenu,
		};
		plugboardMenu.choices = new Screen[] {
			Screen.CURRENT, // plugboardConfigureScreen
			Screen.CURRENT, // plugboardRandomizeScreen
			mainMenu,
		};
		rotorMenu.choices = new Screen[] {
			Screen.CURRENT, // rotorConfigureScreen
			Screen.CURRENT, // rotorConfigurePasteScreen
			Screen.CURRENT, // CopyRotorKeys.Action
			Screen.CURRENT, // rotorRandomizeScreen
			rotorDirectionsMenu,
			mainMenu,
		};
		rotorDirectionsMenu.choices = new Screen[] {
			mainMenu,
		};
		htmlMenu.choices = new Screen[] {
			mainMenu,
		};
		missingFilesMenu.choices = new Screen[] {
			Screen.EXIT,
		};
		exceptionMenu.choices = new Screen[] {
			Screen.EXIT,
		};
	}
	
	public void run() throws Exception {
		try {
			currentScreen = mainMenu;
			lastScreen = currentScreen;
			
			do {
				MenuUtils.clearScreen();
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
			//exceptionMenu.exception = ex;
			MenuUtils.clearScreen();
			throw ex;
			//exceptionMenu.print();
			//exceptionMenu.run();
		}
	}
}
