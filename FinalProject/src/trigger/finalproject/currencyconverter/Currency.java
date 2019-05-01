/*
 * Class Name: Currency
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: A class containing both a currency value and unit, which allows
 *           conversion between different currency units.
 */
package trigger.finalproject.currencyconverter;

import java.text.DecimalFormat;

/**
 * An immutable class for a Currency with a numeric value and unit.
 */
public class Currency {
	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * The value of the currency per the unit.
	 */
	public final double value;
	/**
	 * The currency unit and information about it.
	 */
	public final CurrencyUnit unit;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Construct a currency with the specified value and unit.
	 * @param value The numeric value of the currency.
	 * @param unit The unit type of the currency.
	 */
	public Currency(double value, CurrencyUnit unit) {
		this.value = value;
		this.unit = unit;
	}
	/**
	 * Construct a currency with the specified value and unit.
	 * @param value The numeric value of the currency.
	 * @param unitISO The ISO code of the currency unit type.
	 * @throws IllegalArgumentException The ISO code was not found.
	 */
	public Currency(double value, String unitISO) throws IllegalArgumentException {
		this.value = value;
		this.unit = CurrencyUnit.valueOf(unitISO);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="ToString">
	/**
	 * Gets the string representation of the currency with default formatting.
	 * @return The string representation of the currency.
	 */
	@Override
	public String toString() {
		return toString(unit.df, false);
	}
	/**
	 * Gets the string representation of the currency with spaced formatting.
	 * @param noUnit True if no unit should be displayed with the currency.
	 * @return The string representation of the currency.
	 */
	public String toString(boolean noUnit) {
		return toString(unit.df, noUnit);
	}
	/**
	 * Gets the string representation of the currency with specified formatting.
	 * @param df The decimal format to print the number in.
	 * @return The formatted string representation of the currency.
	 */
	public String toString(DecimalFormat df) {
		return toString(df, false);
	}
	/**
	 * Gets the string representation of the currency with specified formatting.
	 * @param df The decimal format to print the number in.
	 * @param noUnit True if no unit should be displayed with the currency.
	 * @return The formatted string representation of the currency.
	 */
	public String toString(DecimalFormat df, boolean noUnit) {
		if (noUnit)
			return df.format(value);
		else
			return String.format("%s %s", df.format(value), unit);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="ValueOf">
	/**
	 * Parses the currency from the string.
	 * @param s The currency string representation.
	 * @return The parsed currency.
	 * @throws NumberFormatException The currency was input incorrectly.
	 */
	public static Currency valueOf(String s) throws NumberFormatException {
		if (s.length() == 0)
			throw new NumberFormatException("Invalid currency, input is empty!");
		
		int valueEndIndex = -1;
		int unitStartIndex = -1;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c) || c == '.') {
				// We're still in the value, keep going
			}
			else if (Character.isLetter(c)) {
				if (i == 0)
					throw new NumberFormatException("Invalid currency, no value!");
				if (valueEndIndex == -1)
					valueEndIndex = i;
				unitStartIndex = i;
				// We've got our info, end the loop
				break;
			}
			else if (Character.isWhitespace(c)) {
				if (i == 0)
					throw new NumberFormatException("Invalid currency, no value!");
				if (valueEndIndex == -1)
					valueEndIndex = i;
			}
			else {
				throw new NumberFormatException("Invalid currency, unexpected character '" + c + "'!");
			}
		}
		if (valueEndIndex == -1)
			throw new NumberFormatException("Invalid currency, no value or unit!");
		else if (unitStartIndex == -1)
			throw new NumberFormatException("Invalid currency, no unit!");
		
		String valueStr = s.substring(0, valueEndIndex);
		String unitStr = s.substring(unitStartIndex);
		
		CurrencyUnit unit = valueOfUnit(unitStr);
		try {
			double value = Double.valueOf(valueStr);
			return new Currency(value, unit);
		} catch (NumberFormatException ex) {
			throw new NumberFormatException("Invalid currency value '" + valueStr + "'!");
		}
	}
	/**
	 * Parses the currency unit from the string.
	 * @param s The string representation of the currency unit.
	 * @return The parsed currency unit.
	 * @throws NumberFormatException The currency unit string was invalid.
	 */
	public static CurrencyUnit valueOfUnit(String s) throws NumberFormatException {
		if (s.length() == 0)
			throw new NumberFormatException("Invalid currency unit, input is empty!");
		try {
			return CurrencyUnit.valueOf(s);
		} catch (IllegalArgumentException ex) {
			throw new NumberFormatException("Invalid currency unit '" + s + "'!");
		}
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Convert">
	/**
	 * Converts and returns a new currency class with the new unit.
	 * @param unit The new unit to convert the currency to.
	 * @return The newly created converted currency.
	 */
	public Currency convert(CurrencyUnit unit) {
		// Don't waste time converting, we're already the right unit
		if (this.unit == unit)
			return this;
		
		double rate = this.unit.exchangeRate(unit);
		return new Currency(value * rate, unit);
	}
	/**
	 * Converts and returns a new currency class with the new unit.
	 * @param unitISO The ISO code new unit to convert the currency to.
	 * @return The newly created converted currency.
	 * @throws IllegalArgumentException The unitISO does not exist.
	 */
	public Currency convert(String unitISO) throws IllegalArgumentException {
		return convert(CurrencyUnit.valueOf(unitISO));
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Rounding">
	/**
	 * Returns the currency, floored to the previous whole number.
	 * @return The newly constructed floored currency.
	 */
	public Currency floor() {
		return new Currency(Math.floor(value), unit);
	}
	/**
	 * Returns the currency, ceilinged to the next whole number.
	 * @return The newly constructed ceilinged currency.
	 */
	public Currency ceil() {
		return new Currency(Math.ceil(value), unit);
	}
	/**
	 * Returns the currency, rounded to the nearest whole number.
	 * @return The newly constructed rounded currency.
	 */
	public Currency round() {
		return new Currency(Math.round(value), unit);
	}
	/**
	 * Returns the currency, floored to the previous fractional.
	 * @return The newly constructed floored fractional currency.
	 */
	public Currency floorFrac() {
		if (unit.fractional == 1)
			return this;
		return new Currency(unit.floor(value), unit);
	}
	/**
	 * Returns the currency, ceilinged to the next fractional.
	 * @return The newly constructed ceilinged fractional currency.
	 */
	public Currency ceilFrac() {
		if (unit.fractional == 1)
			return this;
		return new Currency(unit.ceil(value), unit);
	}
	/**
	 * Returns the currency, rounded to the nearest fractional.
	 * @return The newly constructed rounded fractional currency.
	 */
	public Currency roundFrac() {
		if (unit.fractional == 1)
			return this;
		return new Currency(unit.round(value), unit);
	}
	// </editor-fold>
}
