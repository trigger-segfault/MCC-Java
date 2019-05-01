/*
 * Class Name: StringUtils
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: Utility functions for strings and characters.
 */
package trigger.finalproject.utilities;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Utility methods for the String and Character classes. Implements functionality
 * like that in C#.
 */
public class StringUtils {
	// <editor-fold defaultstate="expanded" desc="Trim">
	/**
	 * Trims all whitespace from the start of the string.
	 * @param s The string to trim.
	 * @return The trimmed string.
	 */
	public static String trimStart(String s) {
		return s.replaceAll("^\\s+", "");
	}
	/**
	 * Trims all whitespace at the end of the string.
	 * @param s The string to trim.
	 * @return The trimmed string.
	 */
	public static String trimEnd(String s) {
		return s.replaceAll("\\s+$", "");
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Pad">
	/**
	 * Pads the left side of the string with whitespace until it is count length.
	 * @param s The string to pad.
	 * @param count The minimum length of the string after padding.
	 * @return The padded string.
	 */
	public static String padLeft(String s, int count) {
		return padLeft(s, count, ' ');
	}
	/**
	 * Pads the left side of the string with whitespace until it is count length.
	 * @param s The string to pad.
	 * @param count The minimum length of the string after padding.
	 * @param padding The character to pad with.
	 * @return The padded string.
	 */
	public static String padLeft(String s, int count, char padding) {
		count -= s.length();
		if (count > 0)
			return repeat(padding, count) + s;
		return s;
	}
	/**
	 * Pads the right side of the string with whitespace until it is count length.
	 * @param s The string to pad.
	 * @param count The minimum length of the string after padding.
	 * @return The padded string.
	 */
	public static String padRight(String s, int count) {
		return padRight(s, count, ' ');
	}
	/**
	 * Pads the right side of the string with whitespace until it is count length.
	 * @param s The string to pad.
	 * @param count The minimum length of the string after padding.
	 * @param padding The character to pad with.
	 * @return The padded string.
	 */
	public static String padRight(String s, int count, char padding) {
		count -= s.length();
		if (count > 0)
			return s + repeat(padding, count);
		return s;
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Repeat">
	/**
	 * Returns a string with the character repeated count times.
	 * @param c The character to repeat.
	 * @param count The number of times to repeat the character.
	 * @return The repeated string.
	 */
	public static String repeat(char c, int count) {
		char[] chars = new char[count];
		Arrays.fill(chars, ' ');
		return new String(chars);
	}
	/**
	 * Returns a string with the string repeated count times.
	 * @param s The string to repeat.
	 * @param count The number of times to repeat the string.
	 * @return The repeated string.
	 */
	public static String repeat(String s, int count) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < count; i++) {
			str.append(s);
		}
		return str.toString();
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Alignment">
	/**
	 * Pads the left side of the string with whitespace to center it in a console
	 * of the supplied width.
	 * @param line The line to center.
	 * @param width The width of the console.
	 * @return The aligned string.
	 */
	public static String center(String line, int width) {
		int offset = (width - line.length()) / 2;
		if (offset <= 0)
			return line;
		char[] spaces = new char[offset];
		Arrays.fill(spaces, ' ');
		return new String(spaces) + line;
	}
	/**
	 * Splits the string into lines by words using the supplied width. Optionally
	 * trims each line.
	 * @param line The line to split.
	 * @param maxWidth The max width of the console lines.
	 * @param trim True if each line should be trimmed.
	 * @return An array of lines split from a single line.
	 */
	public static String[] wordSplit(String line, int maxWidth, boolean trim) {
		ArrayList<String> lines = new ArrayList<>();

		// Only trim lines when word ellipses is performed
		if (line.length() > maxWidth && trim)
			line = line.trim();

		// Split the text into multiple lines until they are all <= MenuWidth
		while (line.length() > maxWidth) {
			int index;
			for (index = maxWidth; index > 0; index--) {
				if (Character.isWhitespace(line.charAt(index)))
					break;
			}
			if (index == 0) {
				// Word is too long, perform character ellipses
				lines.add(line.substring(0, maxWidth - 1) + "-");
				line = line.substring(maxWidth - 1);
			}
			else {
				// Whitespace found, perform word ellipses
				// Only trim lines when word ellipses is performed
				String lineToAdd = line.substring(0, index);
				line = trimStart(line.substring(index));
				if (trim) {
					lineToAdd = lineToAdd.trim();
				}
				lines.add(lineToAdd);
			}
		}

		// Add the final line
		if (line.length() == 0 && lines.size() > 0) {
			// Line is empty and word ellipses was performed, add nothing
		}
		else if (line.trim().length() == 0)
			lines.add(""); // We can always trim whitespace when it's JUST whitespace
		else
			lines.add(line);

		return lines.toArray(new String[lines.size()]);
	}
	/**
	 * Pads the line with whitespace to match the specified alignment and width.
	 * @param line The line to align.
	 * @param width The width to align to.
	 * @param align The alignment to use.
	 * @return The aligned line.
	 */
	public static String align(String line, int width, Align align) {
		switch (align) {
			case LEFT: return line;
			case CENTER: return center(line, width);
			case RIGHT: return padLeft(line, width);
		}
		return line;
	}
	// </editor-fold>
}
