/*
 * Class Name: StringUtils
 * Author: Robert Jordan
 * Date Created: Apr 2, 2019
 * Synopsis: Utility functions for strings and characters.
 */
package trigger.week9.finalproj;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Utility methods for the String and Character classes. Implements functionality
 * like that in C#.
 */
public class StringUtils {
	public static String trimStart(String s) {
		return s.replaceAll("^\\s+", "");
	}
	public static String trimEnd(String s) {
		return s.replaceAll("\\s+$", "");
	}
	public static String padLeft(String s, int count) {
		return padLeft(s, count, ' ');
	}
	public static String padLeft(String s, int count, char padding) {
		count -= s.length();
		if (count > 0)
			return repeat(padding, count) + s;
		return s;
	}
	public static String padRight(String s, int count) {
		return padRight(s, count, ' ');
	}
	public static String padRight(String s, int count, char padding) {
		count -= s.length();
		if (count > 0)
			return s + repeat(padding, count);
		return s;
	}
	public static String repeat(char c, int count) {
		char[] chars = new char[count];
		Arrays.fill(chars, ' ');
		return new String(chars);
	}
	public static String repeat(String s, int count) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < count; i++) {
			str.append(s);
		}
		return str.toString();
	}
	public static String center(String line, int width) {
		int offset = (width - line.length()) / 2;
		char[] spaces = new char[offset];
		Arrays.fill(spaces, ' ');
		return new String(spaces) + line;
	}
	public static String[] wordEllipsesSplit(String line, int maxWidth, boolean trim) {
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
}
