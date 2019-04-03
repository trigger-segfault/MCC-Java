/*
 * Class Name: FileUtils
 * Author: Robert Jordan
 * Date Created: Apr 2, 2019
 * Synopsis: Utility functions for files.
 */
package trigger.week9.finalproj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

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
		try (FileInputStream fs = new FileInputStream(path)) {
		try (UnicodeBOMInputStream ubs = new UnicodeBOMInputStream(fs)) {
		try (InputStreamReader sr = new InputStreamReader(ubs)) {
		try (BufferedReader reader = new BufferedReader(sr)) {
			// Screw you too Java. <3
			ubs.skipBOM();
			
			String s;
			while ((s = reader.readLine()) != null) {
				lines.add(s);
			}
		}}}}
		return lines.toArray(new String[lines.size()]);
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
	public static String readAllText(String path)
			throws FileNotFoundException, IOException
	{
		StringBuilder str = new StringBuilder();
		try (FileInputStream fs = new FileInputStream(path)) {
		try (UnicodeBOMInputStream ubs = new UnicodeBOMInputStream(fs)) {
		try (InputStreamReader sr = new InputStreamReader(ubs)) {
		try (BufferedReader reader = new BufferedReader(sr)) {
			// Screw you too Java. <3
			ubs.skipBOM();
			
			int c;
			while ((c = reader.read()) != -1) {
				str.append((char) c);
			}
		}}}}
		return str.toString();
	}
	public static void writeAllText(String path, String content)
			throws FileNotFoundException
	{
		try (PrintWriter writer = new PrintWriter(path)) {
			writer.print(content);
		}
	}
}
