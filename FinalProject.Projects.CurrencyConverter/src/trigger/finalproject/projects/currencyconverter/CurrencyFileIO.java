/*
 * Class Name: CurrencyFileIO
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: File I/O operations for currencies and currency units.
 */
package trigger.finalproject.projects.currencyconverter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import trigger.finalproject.utilities.*;

/**
 * File I/O operations for currencies and currency units.
 */
class CurrencyFileIO {
	// <editor-fold defaultstate="expanded" desc="ReadCurrencyUnits">
	/**
	 * Construct the currency database and adds all listed currency types in "CurrencyUnits.txt".
	 * @return A HashMap of CurrencyUnits mapped to their iso.
	 * @throws FileNotFoundException "CurrencyUnits.txt" could not be found.
	 * @throws IOException An I/O error occurred while reading "CurrencyUnits.txt".
	 * @throws IllegalArgumentException Error during parsing of "CurrencyUnits.txt".
	 */
	public static HashMap<String, CurrencyUnit> readCurrencyUnits(String unitsFile)
			throws FileNotFoundException, IOException, IllegalArgumentException
{
		HashMap<String, CurrencyUnit> units = new HashMap<>();
		String[] lines = FileUtils.readAllLines(unitsFile);
		for (int i = 0; i < lines.length; i++) {
			readCurrencyUnit(units, lines[i], i);
		}
		return units;
	}
	/**
	 * Reads the currency unit for the current line. Skips empty or lines starting with '#'.
	 * @param line The line to parse.
	 * @param i The line number.
	 * @throws IllegalArgumentException The line is invalid.
	 */
	private static void readCurrencyUnit(HashMap<String, CurrencyUnit> units, String line, int i)
			throws IllegalArgumentException
	{
		line = line.trim();
		// Comment -or- Nothing in this line, possibly just whitespace
		if (line.isEmpty() || line.startsWith("#"))
			return; // Comment
		String[] parts = splitLine(line);
		if (parts.length != 4) {
			throw new IllegalArgumentException("Expected 4 parts in Currency at line " + i + "!");
		}
		String iso = parts[0];
		String name = parts[1];
		String rateStr = parts[2];
		String fracStr = parts[3];

		double rate = 0;
		int fractional = 1;

		try {
			rate = Double.valueOf(rateStr);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid exchange rate '" + rateStr + "' at line " + i + "!");
		}
		try {
			// "-" means there is no fractional, same as value of 1.
			if (!fracStr.equals("-"))
				fractional = Integer.valueOf(fracStr);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid exchange fractional '" + fracStr + "' at line " + i + "!");
		}
		
		// Add to the list of units
		units.put(iso, new CurrencyUnit(iso, name, rate, fractional));
	}
	private static String[] splitLine(String line) {
		line = line.trim();
		ArrayList<String> parts = new ArrayList<>();
		boolean started = true;
		boolean escaped = false;
		boolean quoted = false;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (escaped) {
				str.append(c);
				escaped = false;
			}
			else if (c == '\\') {
				escaped = true;
			}
			else if (c == '"') {
				quoted = !quoted;
			}
			else if (!quoted && Character.isWhitespace(c)) {
				// Don't add empty gaps between whitespace
				if (started) {
					parts.add(str.toString());
					str.setLength(0); // Clears the string builder
					started = false;
				}
			}
			else {
				str.append(c);
			}
			
			// Signal the part has started parsing
			if (!started && !Character.isWhitespace(c))
				started = true;
		}
		
		// Add the final part to the list
		if (started)
			parts.add(str.toString());
		
		// Cast the array
		String[] partArray = new String[parts.size()];
		return parts.toArray(partArray);
	}
	// </editor-fold>
	
	
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
	public static void genCurrenciesFile(String path, double minValue, double maxValue)
			throws FileNotFoundException
	{
		Random random = new Random();
		final int width = 5;
		final int height = 5;
		try (PrintWriter writer = new PrintWriter(path)) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					double value = random.nextDouble() * (maxValue - minValue) + minValue;
					writer.print(value);
					if (j + 1 < width)
						writer.print(",");
					else
						writer.println();
				}
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
	public static Double[] readUnmarkedCurrenciesFile(String path)
			throws FileNotFoundException, IOException, NumberFormatException
	{
		ArrayList<Double> currencies = new ArrayList<>();
		String[] lines = FileUtils.readAllLines(path);
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();
			if (line.isEmpty())
				continue;
			String[] parts = line.split(",");
			for (int j = 0; j < parts.length; j++) {
				String part = parts[j].trim();
				try {
					//try {
						currencies.add(Double.valueOf(part));
						//double value = Double.valueOf(part);
						//currencies.add(new Currency(value, defaultUnit));
					/*} catch (NumberFormatException ex) {
						currencies.add(Currency.valueOf(part));
					}*/
				} catch (NumberFormatException ex) {
					throw new NumberFormatException(ex.getMessage() + " at line " + i);
				}
			}
		}
		Double[] currenciesArray = new Double[currencies.size()];
		return currencies.toArray(currenciesArray);
	}
	// </editor-fold>
}
