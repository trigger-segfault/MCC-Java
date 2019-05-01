/*
 * Class Name: HelloWorldStandard
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: A Screen for drawing Hello World in a completely normal fashion.
 */
package trigger.finalproject.helloworld;

import trigger.finalproject.menus.*;
import trigger.finalproject.utilities.*;

/**
 * A Screen for drawing Hello World in a completely normal fashion.
 */
public class HelloWorldStandard extends HelloWorldDrawMenu {
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Hello World Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 */
	public HelloWorldStandard(String textFile) {
		super(textFile);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Draw Override">
	@Override
	protected void draw() {
		OutputUtils.printLine("Hello World!", WIDTH, alignment);
	}
	// </editor-fold>
}
