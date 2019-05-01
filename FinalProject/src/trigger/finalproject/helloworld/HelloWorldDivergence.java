/*
 * Class Name: HelloWorldDivergence
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: A Screen for timing and drawing fancy Hello World divergence
 *           animations.
 */
package trigger.finalproject.helloworld;

import trigger.finalproject.menus.*;
import trigger.finalproject.utilities.*;

/**
 * A Screen for timing and drawing fancy Hello World divergence animations.
 */
public class HelloWorldDivergence extends HelloWorldDrawMenu {
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Hello World Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 */
	public HelloWorldDivergence(String textFile) {
		super(textFile);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Draw Override">
	@Override
	protected void draw() {
		OutputUtils.printLine("TODO: Hello World!", WIDTH, alignment);
		OutputUtils.printLine("~~ A N I M A T E D ~~", WIDTH, alignment);
	}
	// </editor-fold>
}
