/*
 * Class Name: Menu
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: A basic implementation of screen that prints a text file and gives
 *           the user a range of choices.
 */
package trigger.finalproject.utilities.menus;

import java.util.Collection;
import trigger.finalproject.utilities.*;

/**
 * A basic implementation of screen that prints a text file and gives the user
 * a range of choices.
 */
public class Menu extends Screen {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The pattern to use at the beginning of the line to left align.
	 */
	public static final String LEFT_ALIGN = "<";
	/**
	 * The pattern to use at the beginning of the line to center align.
	 */
	public static final String CENTER_ALIGN = "!";
	/**
	 * The pattern to use at the beginning of the line to right align.
	 */
	public static final String RIGHT_ALIGN = ">";
	/**
	 * The pattern to surround a line with to signify a token.
	 */
	public static final String TOKEN_SURROUND = "$";
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * The text file that this menu reads and displays.
	 */
	public final String textFile;
	/**
	 * The default alignment to use when printing the menu textFile.
	 */
	public final Align alignment;
	/**
	 * True if no input equates to selecting the last choice in the menu.
	 */
	public final boolean acceptNoInput;
	/**
	 * The choices available for this menu.
	 */
	public Screen[] choices;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Menu with the specified text file path to display.
	 * @param textFile The path to the text file to print.
	 */
	public Menu(String textFile) {
		this(textFile, Align.CENTER, true);
	}
	/**
	 * Constructs the Menu with the specified text file path to display.
	 * @param textFile The path to the text file to print.
	 * @param alignment The default alignment to use when printing the menu.
	 * This is CENTER by default.
	 */
	public Menu(String textFile, Align alignment) {
		this(textFile, alignment, true);
	}
	/**
	 * Constructs the Menu with the specified text file path to display.
	 * @param textFile The path to the text file to print.
	 * @param acceptNoInput True if no input equates to selecting the last
	 * choice in the menu. This is true by default.
	 */
	public Menu(String textFile, boolean acceptNoInput) {
		this(textFile, Align.CENTER, acceptNoInput);
	}
	/**
	 * Constructs the Menu with the specified text file path to display.
	 * @param textFile The path to the text file to print.
	 * @param alignment The default alignment to use when printing the menu.
	 * This is CENTER by default.
	 * @param acceptNoInput True if no input equates to selecting the last
	 * choice in the menu. This is true by default.
	 */
	public Menu(String textFile, Align alignment, boolean acceptNoInput) {
		this.textFile = textFile;
		this.alignment = alignment;
		this.acceptNoInput = acceptNoInput;
		this.choices = new Screen[] { ScreenAction.MAIN };
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Screen Overrides">
	@Override
	public void print(ScreenModule owner) throws Exception {
		Console.clear();
		String[] lines = FileUtils.readAllLines(textFile);
		
		// Format each line as specified by the text file.
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			Align align = Align.CENTER;
			if (line.startsWith(LEFT_ALIGN)) {
				align = Align.LEFT;
				line = line.substring(LEFT_ALIGN.length());
			}
			else if (line.startsWith(CENTER_ALIGN)) {
				align = Align.CENTER;
				line = line.substring(CENTER_ALIGN.length());
			}
			else if (line.startsWith(RIGHT_ALIGN)) {
				align = Align.RIGHT;
				line = line.substring(RIGHT_ALIGN.length());
			}
			// Make sure to support escaping this so we can print it normally.
			else if (line.startsWith("\\" + LEFT_ALIGN)) {
				line = line.substring(LEFT_ALIGN.length() + 1);
			}
			else if (line.startsWith("\\" + CENTER_ALIGN)) {
				line = line.substring(CENTER_ALIGN.length() + 1);
			}
			else if (line.startsWith("\\" + RIGHT_ALIGN)) {
				line = line.substring(RIGHT_ALIGN.length() + 1);
			}
			
			if (line.startsWith(TOKEN_SURROUND)) {
				if (line.endsWith(TOKEN_SURROUND) &&
					line.length() >= TOKEN_SURROUND.length() * 2)
				{
					// Check if token has been handled so we don't need to print it.
					String token = line.substring(TOKEN_SURROUND.length(),
						line.length() - TOKEN_SURROUND.length());
					if (handleToken(token, align))
						continue;
				}
			}
			// Make sure to support escaping this so we can print it normally.
			else if (line.startsWith("\\" + TOKEN_SURROUND)) {
				line = line.substring(TOKEN_SURROUND.length() + 1);
			}
			if (line.isEmpty())
				Console.printLine();
			else
				OutputUtils.printLine(line, WIDTH, align);
		}
		Console.printLine();
	}
	@Override
	public Screen run(ScreenModule owner) throws RequestException, Exception {
		if (choices.length == 1) {
			InputUtils.waitForInput("Press Enter");
			return choices[0];
		}
		else {
			int choice;
			if (acceptNoInput) {
				choice = InputUtils.nextRangeDef("Enter choice", 1,
												choices.length, choices.length);
			}
			else {
				choice = InputUtils.nextRange("Enter choice", 1, choices.length);
			}
			return choices[choice - 1];
		}
	}
	/**
	 * Checks if this screen has any missing required files and adds them to the
	 * list.
	 * @param files The list of files to add missing files to.
	 */
	@Override
	public void getMissingFiles(Collection<String> files) {
		addMissingFile(textFile, files);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Virtual Methods">
	/**
	 * Called to handle the printing of any special token that is caught in the
	 * text file.
	 * @param token The token that was caught between TOKEN_SURROUND's
	 * @param align The alignment to print the line with.
	 * @return True if the token was handled, and should not be printed normally.
	 */
	protected boolean handleToken(String token, Align align) throws Exception {
		return false;
	}
	// </editor-fold>
}
