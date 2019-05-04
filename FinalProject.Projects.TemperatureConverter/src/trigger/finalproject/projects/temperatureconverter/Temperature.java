/*
 * Class Name: Temperature
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: A class containing both a temperature value and unit, which allows
 *           conversion between different units.
 */
package trigger.finalproject.projects.temperatureconverter;

import java.text.DecimalFormat;

/**
 * An immutable class for a Temperature with a numeric value and unit.
 */
public class Temperature {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The default decimal format used for toString().
	 */
	private static final DecimalFormat df = new DecimalFormat("0.##");
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * The numeric value of the temperature.
	 */
	public final double value;
	/**
	 * The unit type of the temperature.
	 */
	public final TemperatureUnit unit;
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Construct a temperature with the specified value and unit.
	 * @param value The numeric value of the temperature.
	 * @param unit The unit type of the temperature.
	 */
	public Temperature(double value, TemperatureUnit unit) {
		this.value = value;
		this.unit = unit;
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="ToString">
	/**
	 * Gets the string representation of the temperature with default formatting.
	 * @return The string representation of the temperature.
	 */
	@Override
	public String toString() {
		return toString(df, false);
	}
	/**
	 * Gets the string representation of the temperature with spaced formatting.
	 * @param noUnit True if no unit should be displayed with the temperature.
	 * @return The string representation of the temperature.
	 */
	public String toString(boolean noUnit) {
		return toString(df, noUnit);
	}
	/**
	 * Gets the string representation of the temperature with specified formatting.
	 * @param df The decimal format to print the number in.
	 * @return The formatted string representation of the temperature.
	 */
	public String toString(DecimalFormat df) {
		return toString(df, false);
	}
	/**
	 * Gets the string representation of the temperature with specified formatting.
	 * @param df The decimal format to print the number in.
	 * @param noUnit True if no unit should be displayed with the temperature.
	 * @return The formatted string representation of the temperature.
	 */
	public String toString(DecimalFormat df, boolean noUnit) {
		if (noUnit)
			return df.format(value);
		else
			return String.format("%s%s", df.format(value), unit);
	}
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="ValueOf">
	/**
	 * Parses the temperature from the string.
	 * @param s The temperature string representation.
	 * @return The parsed temperature.
	 * @throws NumberFormatException The temperature was input incorrectly.
	 */
	public static Temperature valueOf(String s) throws NumberFormatException {
		if (s.length() == 0)
			throw new NumberFormatException("Invalid temperature, input is empty!");
		TemperatureUnit unit;
		char unitChar = s.charAt(s.length() - 1);
		if (!Character.isDigit(unitChar) && unitChar != '.') {
			unit = valueOfUnit(s.substring(s.length() - 1));
			
			try {
				double value = Double.valueOf(s.substring(0, s.length() - 1));
				return new Temperature(value, unit);
			} catch (NumberFormatException ex) {
				throw new NumberFormatException("Invalid temperature value!");
			}
		}
		else {
			throw new NumberFormatException("Invalid temperature input, expected a unit (F/C/K) at the end!");
		}
	}
	/**
	 * Parses the temperature unit from the string.
	 * @param s The string representation of the temperature unit. (F/C/K)
	 * @return The parsed temperature unit.
	 * @throws NumberFormatException The temperature unit string was invalid.
	 */
	public static TemperatureUnit valueOfUnit(String s) throws NumberFormatException {
		if (s.length() == 0)
			throw new NumberFormatException("Invalid temperature unit, input is empty!");
		try {
			return TemperatureUnit.valueOf(s);
		} catch (IllegalArgumentException ex) {
			throw new NumberFormatException("Invalid temperature unit '" + s + "'!");
		}
	}
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="Convert">
	/**
	 * Converts and returns a new temperature class with the new unit.
	 * @param unit The new unit to convert the temperature to.
	 * @return The newly created converted temperature.
	 */
	public Temperature convert(TemperatureUnit unit) {
		// Don't waste time converting, we're already the right unit
		if (this.unit == unit)
			return this;

		// Convert to celcius first so we don't need to write conversion
		// formulas for each possible unit combination.
		double celcius = value;
		switch (this.unit) {
			case F: celcius = (value - 32) * 5.0 / 9; break;
			case K: celcius = (value - 273.15); break;
		}
		// Now convert Celsius to the correct unit and return a Temperature
		switch (unit) {
			case C: return new Temperature(celcius, unit);
			case F: return new Temperature((celcius * 9.0 / 5.0) + 32, unit);
			case K: return new Temperature(celcius + 273.15, unit);
			default: return null;
		}
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Rounding">
	/**
	 * Returns the temperature, floored to the previous whole number.
	 * @return The newly constructed floored temperature.
	 */
	public Temperature floor() {
		return new Temperature(Math.floor(value), unit);
	}
	/**
	 * Returns the temperature, ceilinged to the next whole number.
	 * @return The newly constructed ceilinged temperature.
	 */
	public Temperature ceil() {
		return new Temperature(Math.ceil(value), unit);
	}
	/**
	 * Returns the temperature, rounded to the nearest whole number.
	 * @return The newly constructed rounded temperature.
	 */
	public Temperature round() {
		return new Temperature(Math.round(value), unit);
	}
	// </editor-fold>
}
