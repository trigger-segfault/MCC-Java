/*
 * Class Name: WeekJarsConfig
 * Author: Robert Jordan
 * Date Created: Mar 11, 2019
 * Synopsis: The class for containing the collection of executable weeks and
 *           other configuration settings loaded from the properties file.
 */
package trigger.week4.weekselector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * The class for containing the collection of executable weeks and other
 * configuration properties.
 */
public class WeekJarsConfig {
	// <editor-fold defaultstate="expanded" desc="Fields">
	/**
	 * The array of weeks in the collection.
	 */
	private final WeekJar[] weekJars;
	/**
	 * The padding for week jar names when printing in the menu.
	 */
	private final int namePadding;
	/**
	 * The number of weeks that can be printed on a single row.
	 */
	private final int weeksPerRow;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the WeekJarCollection with the specified properties path to
	 * locate the weeks.
	 * @param pathPath The properties path for week jar paths
	 * @param infoPath The properties path for all other info that is not
	 *                 path-specific.
	 * @throws FileNotFoundException A config file could not be found.
	 * @throws IOException An I/O error occurred.
	 */
	public WeekJarsConfig(String pathPath, String infoPath) throws IOException{
		Properties pathProps = loadProperties(pathPath);
		Properties infoProps = loadProperties(infoPath);
		
		// Load settings
		int weekCount = Integer.valueOf(infoProps.getProperty("WeekCount", "13"));
		this.weekJars = new WeekJar[weekCount];
		this.namePadding = Integer.valueOf(infoProps.getProperty("NamePadding", "20"));
		this.weeksPerRow = Integer.valueOf(infoProps.getProperty("WeeksPerRow", "3"));
		if (this.namePadding < 0)
			throw new IllegalArgumentException("NamePadding");
		if (this.weeksPerRow <= 0)
			throw new IllegalArgumentException("WeeksPerRow");

		// Load weeks
		String defaultPath = pathProps.getProperty("DefaultPath");
		for (int i = 1; i <= weekCount; i++) {
			String name = infoProps.getProperty("Week" + i + ".Name", "Week " + i);
			String defaultWeekPath = defaultPath.replaceAll("#", String.valueOf(i));
			String path = pathProps.getProperty("Week" + i + ".Path", defaultWeekPath);
			this.weekJars[i - 1] = new WeekJar(name, i, path);
		}
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Accessors">
	/**
	 * Gets the WeekJar with the specified number.
	 * @param number The 1-indexed number of the week.
	 * @return The WeekJar associated with the week number.
	 */
	public WeekJar getWeek(int number) {
		return weekJars[number - 1];
	}
	/**
	 * Gets the padding used when printing names.
	 * @return The name padding loaded from the properties file.
	 */
	public int getNamePadding() {
		return namePadding;
	}
	/**
	 * Gets the number of weeks that can be printed on a single row.
	 * @return The number of weeks that can be printed on a single row.
	 */
	public int getWeeksPerRow() {
		return weeksPerRow;
	}
	/**
	 * Gets the number of WeekJars in the collection.
	 * @return The number of WeekJars.
	 */
	public int count() {
		return weekJars.length;
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Load Properties">
	/**
	 * Loads the ini file at the specified path.
	 * @param path The path to the ini file.
	 * @return The loaded properties.
	 * @throws FileNotFoundException The config file could not be found.
	 * @throws IOException An I/O error occurred.
	 */
	private static Properties loadProperties(String path) throws FileNotFoundException, IOException {
		try (FileInputStream stream = new FileInputStream(path)) {
			Properties props = new Properties();
			props.load(stream);
			return props;
		} catch (FileNotFoundException ex) {
			throw new FileNotFoundException("Could not find config file \"" + path + "\"!");
		}
	}
	// </editor-fold>
}
