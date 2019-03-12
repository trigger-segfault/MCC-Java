/*
 * Class Name: Main
 * Author: Robert Jordan
 * Date Created: Mar 11, 2019
 * Synopsis: The main class to run the week selector v2.
 */
package trigger.week4.weekselector;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main class for the week selector v2 program.
 */
public class Main {
	/**
	 * The main method for the week selector v2 program.
	 * @param args Unused
	 * @throws IOException An I/O error occurred.
	 */
	public static void main(String[] args) throws IOException {
		final String pathPath = "WeekPath.ini";
		final String infoPath = "WeekInfo.ini";
		WeekJarsConfig weekJars = null;
		try {
			weekJars = new WeekJarsConfig(pathPath, infoPath);
		} catch (FileNotFoundException ex) {
			Menu.printErr(ex.getMessage());
			System.exit(1);
		}
		
		boolean exit;
		do {
			// Reprint the title every time because a lot of output may have
			// passed since last seeing the title.
			Menu.printTitle();
			// The Menu class will tell us if the user requested to exit.
			exit = Menu.run(weekJars);
		} while (!exit);
	}
}
