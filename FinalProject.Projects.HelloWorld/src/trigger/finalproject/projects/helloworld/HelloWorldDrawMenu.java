/*
 * Class Name: HelloWorldDrawMenu
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: A screen to print Hello World in different fun ways.
 */
package trigger.finalproject.projects.helloworld;

import java.util.Collection;
import trigger.finalproject.utilities.*;
import trigger.finalproject.utilities.menus.*;

/**
 * A screen to print Hello World in different fun ways.
 */
public class HelloWorldDrawMenu extends Menu {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The token to replace with various "Hello World"'s.
	 */
	private static final String HELLO_WORLD_TOKEN = "HELLOWORLD";
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * The optional second text file needed by the menu.
	 */
	protected final String secondTextFile;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Hello World Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 * @param secondTextFile The optional second text file needed by the menu.
	 */
	public HelloWorldDrawMenu(String textFile, String secondTextFile) {
		super(textFile);
		this.secondTextFile = secondTextFile;
		this.choices = new Screen[] { ScreenAction.LAST };
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Menu Overrides">
	@Override
	protected boolean handleToken(String token, Align align) throws Exception {
		if (token.equals(HELLO_WORLD_TOKEN)) {
			// Play around with draw() this and make it ~fancy~
			draw();
			return true;
		}
		return false;
	}
	@Override
	public void getMissingFiles(Collection<String> files) {
		super.getMissingFiles(files);
		if (secondTextFile != null)
			addMissingFile(secondTextFile, files);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Virtual Methods">
	/**
	 * Draw Hello World in a fun way.
	 * @throws Exception An error occurred while drawing.
	 */
	protected void draw() throws Exception {
		OutputUtils.printFile(secondTextFile, WIDTH, alignment);
	}
	// </editor-fold>
}
