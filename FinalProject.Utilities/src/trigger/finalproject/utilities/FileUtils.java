/*
 * Class Name: FileUtils
 * Author: Robert Jordan
 * Date Created: May 2, 2019
 * Synopsis: Utility functions for files.
 */
package trigger.finalproject.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Utility functions for files.
 */
public class FileUtils {
	// <editor-fold defaultstate="expanded" desc="Read All">
	/**
	 * Reads all text in a file and returns it as a single String.
	 * @param path The path to the file to read.
	 * @return An String of the file's text.
	 * @throws FileNotFoundException The file was not found.
	 * @throws IOException An I/O error occurred.
	 */
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
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Write All">
	/**
	 * Writes all text in to the file.
	 * @param path The path to the file to write to.
	 * @param content The text to write to the file.
	 * @throws FileNotFoundException The file could not be found for writing.
	 */
	public static void writeAllText(String path, String content)
			throws FileNotFoundException
	{
		try (PrintWriter writer = new PrintWriter(path)) {
			writer.print(content);
		}
	}
	/**
	 * Writes all lines in to the file.
	 * @param path The path to the file to write to.
	 * @param contents The lines to write to the file.
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
	/**
	 * Appends the text to the end of the file.
	 * @param path The path to the file to write to.
	 * @param content The text to append to the file.
	 * @throws FileNotFoundException The file could not be found for writing.
	 * @throws IOException An I/O error occurred.
	 */
	public static void appendText(String path, String content)
			throws FileNotFoundException, IOException
	{
		try (FileWriter fileWriter = new FileWriter(path, true)) {
		try (PrintWriter writer = new PrintWriter(fileWriter)) {
			writer.print(content);
		}}
	}
	/**
	 * Appends the line to the end of the file.
	 * @param path The path to the file to write to.
	 * @param content The line to append to the file.
	 * @throws FileNotFoundException The file could not be found for writing.
	 * @throws IOException An I/O error occurred.
	 */
	public static void appendLine(String path, String content)
			throws FileNotFoundException, IOException
	{
		try (FileWriter fileWriter = new FileWriter(path, true)) {
		try (PrintWriter writer = new PrintWriter(fileWriter)) {
			writer.println(content);
		}}
	}
	/**
	 * Appends the lines to the end of the file.
	 * @param path The path to the file to write to.
	 * @param contents The lines to append to the file.
	 * @throws FileNotFoundException The file could not be found for writing.
	 * @throws IOException An I/O error occurred.
	 */
	public static void appendLines(String path, String[] contents)
			throws FileNotFoundException, IOException
	{
		try (FileWriter fileWriter = new FileWriter(path, true)) {
		try (PrintWriter writer = new PrintWriter(fileWriter)) {
			for (int i = 0; i < contents.length; i++) {
				writer.println(contents[i]);
			}
		}}
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Checks">
	/**
	 * Returns true if a file or directory with the specified path exists.
	 * @param path The path of the file or directory to check for.
	 * @return True if the path exists.
	 */
	public static boolean exists(String path) {
		return new File(path).exists();
	}
	/**
	 * Returns true if a file with the specified path exists.
	 * @param path The path of the file to check for.
	 * @return True if the file exists.
	 */
	public static boolean isFile(String path) {
		File file = new File(path);
		return file.exists() && file.isFile();
	}
	/**
	 * Returns true if a directory with the specified path exists.
	 * @param path The path of the directory to check for.
	 * @return True if the directory exists.
	 */
	public static boolean isDirectory(String path) {
		File file = new File(path);
		return file.exists() && file.isDirectory();
	}
	// </editor-fold>
}
