/*
 * Class Name: OutputUtils
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: Utility methods for special output handling.
 */
package trigger.finalproject.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Utility methods for special output handling.
 */
public class OutputUtils {
	// <editor-fold defaultstate="expanded" desc="Print Alignment">
	/**
	 * Reads a file and prints it to the screen.
	 * @param path The path of the file.
	 * @param maxWidth The maximum width of the console line.
	 * @param align The alignment for the line.
	 * @throws FileNotFoundException The file could not be found.
	 * @throws IOException An error occurred while reading the file.
	 */
	public static void printFile(String path, int maxWidth, Align align)
			throws FileNotFoundException, IOException
	{
		String[] contents = FileUtils.readAllLines(path);
		printLines(contents, maxWidth, align);
	}
	/**
	 * Prints the lines to the screen.
	 * @param contents The lines to prints.
	 * @param maxWidth The maximum width of the console line.
	 * @param align The alignment for the line.
	 */
	public static void printLines(String[] contents, int maxWidth, Align align) {
		for (int i = 0; i < contents.length; i++) {
			printLine(contents[i], maxWidth, align);
		}
	}
	/**
	 * Prints the line to the screen.
	 * @param content The line to prints.
	 * @param maxWidth The maximum width of the console line.
	 * @param align The alignment for the line.
	 */
	public static void printLine(String content, int maxWidth, Align align) {
		if (maxWidth == 0 || maxWidth == -1) {
			System.out.println(content);
		}
		else {
			String[] lines = StringUtils.wordSplit(content, maxWidth, false);
			for (int i = 0; i < lines.length; i++) {
				String line = lines[i];
				System.out.println(StringUtils.align(line, maxWidth, align));
			}
		}
	}
	/**
	 * Prints an indented message line to the error stream then waits a moment
	 * to fix NetBeans continuing before the message is output.
	 * @param message The message to output.
	 * @param maxWidth The maximum width of the console line.
	 * @param align The alignment for the line.
	 */
	public static void printError(String message, int maxWidth, Align align) {
		if (maxWidth == 0 || maxWidth == -1) {
			System.err.println(message);
		}
		else {
			String[] lines = StringUtils.wordSplit(message, maxWidth, false);
			for (int i = 0; i < lines.length; i++) {
				String line = lines[i];
				System.err.println(StringUtils.align(line, maxWidth, align));
			}
		}
		try {
			// This is here because NetBeans is terrible with error stream
			// race conditions. Please ignore.
			Thread.sleep(100);
		} catch (InterruptedException ex) {
			// Nothing game-breaking, just move along
		}
	}
	// </editor-fold>
}
