/*
 * Class Name: TemperatureUtils
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: Utilities for getting temperature input.
 */
package trigger.finalproject.projects.temperatureconverter;

import trigger.finalproject.utilities.*;

/**
 * Utilities for getting temperature input.
 */
public class TemperatureUtils {
	
	public static final String LAST_ANSWER = "ans";
	
	public static Temperature nextTemperature(String message)
			throws RequestException
	{
		return nextTemperatureOrAns(message, null);
	}
	public static Temperature nextTemperatureDef(String message, Temperature defaultValue)
			throws RequestException
	{
		return nextTemperatureOrAnsDef(message, null, defaultValue);
	}
	public static Temperature nextTemperatureOrAns(String message, Temperature ans)
			throws RequestException
	{
		Temperature temp = null;
		do {
			String input = InputUtils.nextLine(message);
			
			if (input.equalsIgnoreCase(LAST_ANSWER)) {
				if (ans != null)
					temp = ans;
				else
					Console.printError("Cannot input last answer when there is none!");
			}
			else {
				try {
					temp = Temperature.valueOf(input);
				} catch (NumberFormatException ex) {
					Console.printError(ex.getMessage());
				}
			}
		} while (temp == null);
		return temp;
	}
	public static Temperature nextTemperatureOrAnsDef(String message, Temperature ans, Temperature defaultValue)
			throws RequestException
	{
		Temperature temp = null;
		do {
			String input = InputUtils.nextLineDef(message, defaultValue.toString());
			
			if (input.equalsIgnoreCase(LAST_ANSWER)) {
				if (ans != null)
					temp = ans;
				else
					Console.printError("Cannot input last answer when there is none!");
			}
			else {
				try {
					temp = Temperature.valueOf(input);
				} catch (NumberFormatException ex) {
					Console.printError(ex.getMessage());
				}
			}
		} while (temp == null);
		return temp;
	}
	public static TemperatureUnit nextUnit(String message)
			throws RequestException
	{
		TemperatureUnit unit = null;
		do {
			String input = InputUtils.nextLine(message);
			
			try {
				unit = Temperature.valueOfUnit(input);
			} catch (NumberFormatException ex) {
				Console.printError(ex.getMessage());
			}
		} while (unit == null);
		return unit;
	}
	public static TemperatureUnit nextUnitDef(String message, TemperatureUnit defaultValue)
			throws RequestException
	{
		TemperatureUnit unit = null;
		do {
			String input = InputUtils.nextLineDef(message, String.valueOf(defaultValue));
			
			try {
				unit = Temperature.valueOfUnit(input);
			} catch (NumberFormatException ex) {
				Console.printError(ex.getMessage());
			}
		} while (unit == null);
		return unit;
	}
}
