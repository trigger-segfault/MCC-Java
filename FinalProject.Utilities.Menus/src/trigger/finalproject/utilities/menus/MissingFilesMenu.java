/*
 * Class Name: MissingFilesMenu
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: The menu to display when required files are missing from the
 *           program's search paths.
 */
package trigger.finalproject.utilities.menus;

import trigger.finalproject.utilities.*;

/**
 * The menu to display when required files are missing from the program's search
 * paths.
 */
public class MissingFilesMenu extends Menu {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The token to replace with the list of missing files.
	 */
	private static final String MISSING_FILES_TOKEN = "MISSINGFILES";
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * The list of missing files that must be seeded before printing the menu.
	 */
	public String[] missingFiles;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Missing Files Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 */
	public MissingFilesMenu(String textFile) {
		this(textFile, Align.CENTER);
	}
	/**
	 * Constructs the Missing Files Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 * @param alignment The default alignment to use when printing the menu.
	 * This is CENTER by default.
	 */
	public MissingFilesMenu(String textFile, Align alignment) {
		super(textFile, alignment);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Menu Overrides">
	@Override
	public void print(ScreenModule owner) throws Exception {
		if (!FileUtils.isFile(textFile)) {
			Console.clear();
			// Missing MissingFilesMenu text file, print a hardcoded menu instead
			Console.printLine();
			OutputUtils.printLine("Missing Files!", WIDTH, alignment);
			Console.printLine();
			OutputUtils.printLine("The following files required for runtime " +
					"are missing, including the file to display this error menu!",
					WIDTH, alignment);
			Console.printLine();
			for (int i = 0; i < missingFiles.length; i++) {
				String file = missingFiles[i];
				OutputUtils.printLine(file, WIDTH, alignment);
			}
			Console.printLine();
		}
		else {
			super.print(owner);
		}
	}
	@Override
	public Screen run(ScreenModule owner) {
		Console.pressEnterToExit();
		return ScreenAction.EXIT;
	}
	@Override
	protected boolean handleToken(String token, Align align) {
		if (token.equals(MISSING_FILES_TOKEN)) {
			for (int i = 0; i < missingFiles.length; i++) {
				String file = missingFiles[i];
				OutputUtils.printLine(file, WIDTH, align);
			}
			return true;
		}
		return false;
	}
	// </editor-fold>
}
