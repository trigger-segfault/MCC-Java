/*
 * Class Name: Main
 * Author: Robert Jordan
 * Date Created: Mar 12, 2019
 * Synopsis: The main class to run the temperature file converter program.
 */
package trigger.week6.temperatureconverter.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * The main class for the temperature file converter program.
 */
public class Main {
	/**
	 * The main method for the temperature file converter program.
	 * @param args Unused
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static void main(String[] args)
			throws FileNotFoundException, UnsupportedEncodingException, IOException
	{
		boolean exit;
		do {
			// Reprint the title every time because a lot of output may have
			// passed since last seeing the title.
			Menu.printTitle();
			// The Menu class will tell us if the user requested to exit.
			exit = Menu.run();
		} while (!exit);
	}
}
