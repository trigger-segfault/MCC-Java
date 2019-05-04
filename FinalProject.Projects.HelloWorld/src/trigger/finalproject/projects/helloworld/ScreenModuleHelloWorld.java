/*
 * Class Name: ScreenModuleHelloWorld
 * Author: Robert Jordan
 * Date Created: May 2, 2019
 * Synopsis: The screen module for the Hello World.
 */
package trigger.finalproject.projects.helloworld;

import trigger.finalproject.utilities.menus.*;

/**
 * The screen module for the Hello World.
 */
public class ScreenModuleHelloWorld extends ScreenModule {
	// <editor-fold defaultstate="expanded" desc="Menus">
	public Menu MENU = new Menu("resources/HelloWorldMenu.txt");
	
	private static final String DRAW_PATH = "resources/HelloWorldDrawMenu.txt";
	public Menu STANDARD = new HelloWorldStandard(DRAW_PATH);
	public Menu DIVERGENCE = new HelloWorldDivergence(DRAW_PATH);
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Screen Module and sets up menu choices.
	 */
	public ScreenModuleHelloWorld() {
		addScreen(MENU);
		addScreen(STANDARD);
		addScreen(DIVERGENCE);
		
		MENU.choices = new Screen[] {
			STANDARD,
			DIVERGENCE,
			ScreenAction.MAIN,
		};
	}
	// </editor-fold>
}
