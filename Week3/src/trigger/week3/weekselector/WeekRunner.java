/*
 * Class Name: WeekRunner
 * Author: Robert Jordan
 * Date Created: Mar 6, 2019
 * Synopsis: The class for executing a week's jar file based on the number.
 */
package trigger.week3.weekselector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.ProcessBuilder.Redirect;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Executes a week's jar file based on the number.
 */
class WeekRunner {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	public static final int NOT_FOUND = 0xFFFFFFFF;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="runWeek">
	/**
	 * Runs the week of the specified number.
	 * @return The exit code of the program or NOT_FOUND if the program failed
	 *         to execute.
	 */
	public static int runWeek(int number) {
		// Read the file that gives as the path to the jar files.
		// This is useful especially when running from the project directory.
		String jarPath = readFile("./WeekPath.txt");
		// '#' token is substituted with the week number of the jar file.
		jarPath = jarPath.replaceAll("#", String.valueOf(number));
		try {
			ProcessBuilder builder = new ProcessBuilder(new String[]{"java", "-jar", jarPath});
			// Make sure the console I/O is being redirected to this process.
			builder.redirectError(Redirect.INHERIT);
			builder.redirectInput(Redirect.INHERIT);
			builder.redirectOutput(Redirect.INHERIT);
			// Run the jar file
			Process process = builder.start();
			// Wait for the har file to finish before returning.
			process.waitFor();
			return process.exitValue();
		} catch (InterruptedException ex) {
			Logger.getLogger(WeekRunner.class.getName()).log(Level.SEVERE, null, ex);
			return -1; // Oh well, sucks to be you
		} catch (IOException ex) {
			//System.out.println(ex.toString());
			return NOT_FOUND; // Likely file not found, so we can just return this.
		}
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Helper Methods">
	private static String readFile(String file) {
		try {
			FileInputStream is = new FileInputStream(file);
			StringBuilder sb = new StringBuilder(512);
			try {
				Reader r = new InputStreamReader(is, "UTF-8");
				int c;
				while ((c = r.read()) != -1) {
					sb.append((char) c);
				}
			} catch (IOException ex) {
			is.close();
				throw new RuntimeException(ex);
			}
			is.close();
			return sb.toString();
		} catch (FileNotFoundException ex) {
			return "./Week#.jar";
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	// </editor-fold>
}
