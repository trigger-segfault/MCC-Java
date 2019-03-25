/*
 * Class Name: FileUtils
 * Author: Robert Jordan
 * Date Created: Mar 25, 2019
 * Synopsis: Utility functions for files.
 */
package trigger.week8.currencyconverter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Utility functions for files.
 */
public class FileUtils {
	/**
	 * Reads all lines in a file and returns them as an array.
	 * @param path The path to the file to read.
	 * @return An array of all lines in the file.
	 * @throws FileNotFoundException The file was not found.
	 * @throws IOException An I/O error occurred.
	 */
	public static String[] readAllLines(String path)
			throws FileNotFoundException, IOException
	{
		ArrayList<String> lines = new ArrayList<>();
		try (FileReader fileReader = new FileReader(path)) {
		try (BufferedReader reader = new BufferedReader(fileReader)) {
			String s;
			while ((s = reader.readLine()) != null) {
				lines.add(s);
			}
		}
		}
		return Arrays.copyOf(lines.toArray(), lines.size(), String[].class);
	}
	/**
	 * Writes all lines in to the file.
	 * @param path The path to the file to write to.
	 * @param contents The lines of the file to write.
	 * @throws FileNotFoundException The file could not be found for writing.
	 */
	public static void writeAllLines(String path, String[] contents)
			throws FileNotFoundException
	{
		try (PrintWriter writer = new PrintWriter(path)) {
			for (int i = 0; i < contents.length; i++) {
				writer.println(contents[i]);
			}
		}
	}
}
