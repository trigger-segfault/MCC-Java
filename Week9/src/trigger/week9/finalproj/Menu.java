/*
 * Class Name: Menu
 * Author: Robert Jordan
 * Date Created: Apr 2, 2019
 * Synopsis: A basic implementation of screen that prints a text file and gives
 *           the user a range of choices.
 */
package trigger.week9.finalproj;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A basic implementation of screen that prints a text file and gives the user
 * a range of choices.
 */
public class Menu extends Screen {
	public final String textFile;
	public Screen[] choices;
	
	public Menu(String textFile) {
		this.textFile = textFile;
	}
	
	@Override
	public void print() throws FileNotFoundException, IOException {
		MenuUtils.printFile(textFile, WIDTH, true);
	}
	@Override
	public Screen run() throws RequestExitException, RequestBackException {
		if (choices.length == 1) {
			MenuUtils.waitForInput("Presss Enter");
			return choices[0];
		}
		else {
			int choice = MenuUtils.nextRange("Enter choice", 1, choices.length);
			return choices[choice - 1];
		}
	}
}
