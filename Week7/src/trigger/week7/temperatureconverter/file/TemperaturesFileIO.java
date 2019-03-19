/*
 * Class Name: TemperaturesFileIO
 * Author: Robert Jordan
 * Date Created: Mar 12, 2019
 * Synopsis: A class for writing random temperatures to file or reading temperatures
 *           from file.
 */
package trigger.week6.temperatureconverter.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class for writing random temperatures to file or reading temperatures
 * from file.
 */
public class TemperaturesFileIO {
	
	// <editor-fold defaultstate="expanded" desc="GenTemperaturesFile">
	/**
	 * Generates a random set of temperatures and writes it to the file path.
	 * @param path The file path to write the temperatures to.
	 * @param minTemp The minimum temperature to generate.
	 * @param maxTemp The maximum temperature to generate.
	 * @param minCount The minimum number of temperatures.
	 * @param maxCount The maximum number of temperatures.
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException 
	 */
	public static void genTemperaturesFile(String path, Temperature minTemp, Temperature maxTemp,
		int minCount, int maxCount) throws FileNotFoundException, UnsupportedEncodingException
	{
		Random random = new Random();
		int count = random.nextInt(maxCount + 1 - minCount) + minCount;
		double minC = minTemp.convert(TemperatureUnit.C).getValue();
		double maxC = maxTemp.convert(TemperatureUnit.C).getValue();
		try (PrintWriter writer = new PrintWriter(path, "UTF-8")) {
			for (int i = 0; i < count; i++) {
				double c = random.nextDouble() * (maxC - minC) + minC;
				TemperatureUnit unit = TemperatureUnit.F;
				switch (random.nextInt(3)) {
					//case 0: unit = TemperatureUnit.F; break;
					case 1: unit = TemperatureUnit.C; break;
					case 2: unit = TemperatureUnit.K; break;
				}
				Temperature temp = new Temperature(c, TemperatureUnit.C).convert(unit);
				writer.println(temp.toString());
			}
		}
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="ReadTemperaturesFile">
	/**
	 * Reads the temperatures from a file.
	 * @param path The path to the temperatures file.
	 * @return An array of read temperatures. Empty lines are returned as null
	 *         to preserve line number.
	 * @throws FileNotFoundException The file at path did not exist.
	 * @throws IOException An I/O error occurred.
	 * @throws NumberFormatException A temperature had an invalid format.
	 */
	public static Temperature[] readTemperaturesFile(String path)
			throws FileNotFoundException, IOException, NumberFormatException
	{
		ArrayList<Temperature> temps = new ArrayList<>();
		try (FileReader fileReader = new FileReader(path)) {
		try (BufferedReader reader = new BufferedReader(fileReader)) {
			String s;
			int lineNumber = 0;
			while ((s = reader.readLine()) != null) {
				lineNumber++;
				if (s.length() == 0) {
					// Skip empty lines but add null so we can keep track of the line number.
					temps.add(null);
					continue;
				}
				try {
					temps.add(Temperature.valueOf(s));
				} catch (NumberFormatException ex) {
					throw new NumberFormatException(ex.getMessage() + " at line " + lineNumber);
				}
			}
		}
		}
		Temperature[] tempsArray = new Temperature[temps.size()];
		temps.toArray(tempsArray);
		return tempsArray;
	}
	// </editor-fold>
}
