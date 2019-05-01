/*
 * Class Name: HelloWorldScreen
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: A screen to print Hello World in different fun ways.
 */
package trigger.finalproject.helloworld;

import trigger.finalproject.menus.*;
import trigger.finalproject.utilities.*;

/**
 * A screen to print Hello World in different fun ways.
 */
public abstract class HelloWorldDrawMenu extends Menu {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The token to replace with various "Hello World"'s.
	 */
	private static final String HELLO_WORLD_TOKEN = "HELLOWORLD";
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Hello World Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 */
	public HelloWorldDrawMenu(String textFile) {
		super(textFile);
		this.choices = new Screen[] { Screen.LAST };
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Menu Overrides">
	@Override
	protected boolean handleToken(String token, Align align) {
		if (token.equals(HELLO_WORLD_TOKEN)) {
			// Play around with draw() this and make it ~fancy~
			draw();
			return true;
		}
		return false;
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Abstract Methods">
	/**
	 * Draw Hello World in a fun way.
	 */
	protected abstract void draw();
	// </editor-fold>
}
