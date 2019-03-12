/*
 * Class Name: WeekJars
 * Author: Robert Jordan
 * Date Created: Mar 11, 2019
 * Synopsis: The class for executing a week's jar file based on a path.
 */
package trigger.week4.weekselector;

import java.io.File;
import java.io.IOException;

/**
 * Storage for a specific week's jar file.
 */
public class WeekJar {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * Returned by WeekJar.run() if an error occurred while trying to run the
	 * week jar.
	 */
	public static final int RUN_ERROR = 0xFFFFFFFF;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Fields">
	private final String name;
	private final String jarPath;
	private final int number;
	//private final boolean jarExists;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the week jar with the specified information.
	 * @param name The name of this week's assignment.
	 * @param number The number of this assignment's week.
	 * @param jarPath The path to this week's jar file.
	 */
	public WeekJar(String name, int number, String jarPath) {
		this.name = (name != null ? name : "Week " + number);
		this.jarPath = jarPath;
		this.number = number;
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Accessors">
	/**
	 * Gets the name of the week assignment.
	 * @return The week assignment's name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Gets the number of this week's assignment.
	 * @return The number of this week's assignment.
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * Gets the path to the week's jar file.
	 * @return The week's jar file path.
	 */
	public String getJarPath() {
		return jarPath;
	}
	/**
	 * Checks if the path to this week's jar file exists.
	 * @return True if the week jar file exists.
	 */
	public boolean exists() {
		if (jarPath == null)
			return false;
		//return jarExists;
		return new File(jarPath).exists();
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Run">
	/**
	 * Runs the week's jar file.
	 * @return The exit code of the run jar file, or WeekJar.RUN_ERROR if an
	 *         exception occurred while running.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public int run() throws InterruptedException, IOException {
		String[] args = new String[]{"java", "-jar", jarPath};
		ProcessBuilder builder = new ProcessBuilder(args);
		// Set the working directory to that of the jar file
		builder.directory(new File(jarPath).getParentFile());
		// Make sure the console I/O is being redirected to this process.
		builder.redirectError(ProcessBuilder.Redirect.INHERIT);
		builder.redirectInput(ProcessBuilder.Redirect.INHERIT);
		builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
		// Run the jar file
		Process process = builder.start();
		// Wait for the jar file to finish before returning.
		process.waitFor();
		// Return the exit code
		return process.exitValue();
	}
	// </editor-fold>
}
