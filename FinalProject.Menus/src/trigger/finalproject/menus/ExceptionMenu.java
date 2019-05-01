/*
 * Class Name: ExceptionMenu
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: The menu to display when an unhandled exception occurs.
 */
package trigger.finalproject.menus;

import java.io.FileNotFoundException;
import java.io.IOException;
import trigger.finalproject.utilities.*;

/**
 * The menu to display when an unhandled exception occurs.
 */
public class ExceptionMenu extends Menu {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The token to replace with the exception message
	 */
	private static final String EXCEPTION_TOKEN = "EXCEPTION";
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * The exception to display that must be seeded before printing the menu.
	 */
	public Exception exception;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Missing Files Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 */
	public ExceptionMenu(String textFile) {
		this(textFile, Align.CENTER);
	}
	/**
	 * Constructs the Missing Files Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 * @param alignment The default alignment to use when printing the menu.
	 * This is CENTER by default.
	 */
	public ExceptionMenu(String textFile, Align alignment) {
		super(textFile, alignment);
		this.choices = new Screen[] { Screen.EXIT };
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Menu Overrides">
	@Override
	public void print() throws FileNotFoundException, IOException {
		if (!FileUtils.isFile(textFile)) {
			//Console.clear();
			// Missing ExceptionMenu text file, print a hardcoded menu instead
			Console.printLine();
			OutputUtils.printLine("Unhandled Exception!", WIDTH, alignment);
			Console.printLine();
			OutputUtils.printLine("The following error occurred while running " +
					"the program:",
					WIDTH, alignment);
			Console.printLine();
			OutputUtils.printLine(exception.toString(), WIDTH, alignment);
			Console.printLine();
		}
		else {
			super.print();
		}
	}
	@Override
	protected boolean handleToken(String token, Align align) {
		if (token.equals(EXCEPTION_TOKEN)) {
			OutputUtils.printLine(exception.toString(), WIDTH, align);
			return true;
		}
		return false;
	}
	// </editor-fold>
}
