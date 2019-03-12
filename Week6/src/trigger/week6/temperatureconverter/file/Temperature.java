/*
 * Class Name: Temperature
 * Author: Robert Jordan
 * Date Created: Mar 12, 2019
 * Synopsis: A class containing both a temperature value and unit, which allows
 *           conversion between different units.
 */
package trigger.week6.temperatureconverter.file;

import java.text.DecimalFormat;

/**
 * An immutable class for a Temperature with a numeric value and unit.
 */
public class Temperature {
	// <editor-fold defaultstate="collapsed" desc="Fields">
	private final double value;
	private final TemperatureUnit unit;
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
		return String.valueOf(value) + /*"º" + */String.valueOf(unit);
	}
	/**
	 * Gets the string representation of the temperature with specified formatting.
	 * @param df The decimal format to print the number in.
	 * @return The formatted string representation of the temperature.
	 */
	public String toString(DecimalFormat df) {
		return df.format(value) + /*"º" + */String.valueOf(unit);
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
	
	// <editor-fold defaultstate="collapsed" desc="Accessors">
	/**
	 * Gets the numeric value of the temperature.
	 * @return The numeric value of the temperature.
	 */
	public double getValue() {
		return value;
	}
	/**
	 * Gets the unit type of the temperature.
	 * @return The unit type of the temperature.
	 */
	public TemperatureUnit getUnit() {
		return unit;
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
}
