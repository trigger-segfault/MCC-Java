/*
 * Class Name: CurrencyUtils
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: Utilities for getting currency input.
 */
package trigger.finalproject.projects.currencyconverter;

import trigger.finalproject.utilities.*;

/**
 * Utilities for getting currency input.
 */
public class CurrencyUtils {
	
	public static final String LAST_ANSWER = "ans";
	
	public static Currency nextCurrency(String message)
			throws RequestException
	{
		return nextCurrencyOrAns(message, null);
	}
	public static Currency nextCurrencyDef(String message, Currency defaultValue)
			throws RequestException
	{
		return nextCurrencyOrAnsDef(message, null, defaultValue);
	}
	public static Currency nextCurrencyOrAns(String message, Currency ans)
			throws RequestException
	{
		Currency temp = null;
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
					temp = Currency.valueOf(input);
				} catch (NumberFormatException ex) {
					Console.printError(ex.getMessage());
				}
			}
		} while (temp == null);
		return temp;
	}
	public static Currency nextCurrencyOrAnsDef(String message, Currency ans, Currency defaultValue)
			throws RequestException
	{
		Currency temp = null;
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
					temp = Currency.valueOf(input);
				} catch (NumberFormatException ex) {
					Console.printError(ex.getMessage());
				}
			}
		} while (temp == null);
		return temp;
	}
	public static CurrencyUnit nextUnit(String message)
			throws RequestException
	{
		CurrencyUnit unit = null;
		do {
			String input = InputUtils.nextLine(message);
			
			try {
				unit = Currency.valueOfUnit(input);
			} catch (NumberFormatException ex) {
				Console.printError(ex.getMessage());
			}
		} while (unit == null);
		return unit;
	}
	public static CurrencyUnit nextUnitDef(String message, CurrencyUnit defaultValue)
			throws RequestException
	{
		CurrencyUnit unit = null;
		do {
			String input = InputUtils.nextLineDef(message, String.valueOf(defaultValue));
			
			try {
				unit = Currency.valueOfUnit(input);
			} catch (NumberFormatException ex) {
				Console.printError(ex.getMessage());
			}
		} while (unit == null);
		return unit;
	}
	
	public static void printUnits() {
		Console.printLine("The available currency units are:");
		CurrencyUnit[] units = CurrencyUnit.values();
		for (int i = 0; i < units.length; i++) {
			CurrencyUnit unit = units[i];
			Console.printLine(String.format("  %-3s : %s", unit.iso, unit.name));
		}
	}
}
