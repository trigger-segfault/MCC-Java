/*
 * Class Name: CurrencyUnit
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: A currency unit and information pertaining to it.
 */
package trigger.finalproject.projects.currencyconverter;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A currency unit and information pertaining to it.
 */
public final class CurrencyUnit {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The path to the file documenting all supported Currency Units.
	 */
	public static final String UNITS_FILE = "CurrencyUnits.txt";
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Static Fields">
	/**
	 * The dictionary of currency units.
	 */
	private static HashMap<String, CurrencyUnit> unitMap = null;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Fields">
	/**
	 * The ISO code for the currency.
	 */
	public final String iso;
	/**
	 * The name of the currency.
	 */
	public final String name;
	/**
	 * The exchange rate to USD.
	 */
	public final double exchangeRateUSD;
	/**
	 * The fractional count for the currency.
	 */
	public final int fractional;
	/**
	 * The number of fractional digits for the currency.
	 */
	public final int fractionalDigits;
	/**
	 * The decimal format for the currency, derived from fractionalDigits.
	 */
	public final DecimalFormat df;
	// </editor-fold>

	// <editor-fold defaultstate="expanded" desc="Static Constructor">
	/**
	 * Initializes the map of CurrencyUnits by loading them from a file.
	 */
	static {
		try {
			unitMap = CurrencyFileIO.readCurrencyUnits(UNITS_FILE);
		} catch (IOException | IllegalArgumentException ex) {
			Logger.getLogger(CurrencyUnit.class.getName()).log(Level.SEVERE, null, ex);
			System.exit(1);
		}
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Construct currency unit with the specified information.
	 * @param iso The ISO code for the currency.
	 * @param name The name of the currency.
	 * @param rate The exchange rate to USD.
	 * @param fractional The fractional count for the currency.
	 */
	CurrencyUnit(String iso, String name, double rate, int fractional) {
		this.iso = iso;
		this.name = name;
		this.exchangeRateUSD = rate;
		this.fractional = fractional;
		// Calculate fractionalDigits
		if (fractional == 1)
			this.fractionalDigits = 0;
		else
			this.fractionalDigits = (int) Math.ceil(Math.log10(fractional));
		// Construct the decimal format from calculated fractionalDigits
		String formatStr = "#";
		for (int i = 0; i < this.fractionalDigits; i++) {
			if (i == 0)
				formatStr += ".";
			formatStr += "0";
		}
		this.df = new DecimalFormat(formatStr);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="ToString">
	/**
	 * Returns the string representation of the CurrencyUnit which is its ISO code.
	 * @return The ISO code of the CurrencyUnit.
	 */
	@Override
	public String toString() {
		return toString(false);
	}
	/**
	 * Returns the string representation of the CurrencyUnit with the specified
	 * formatting.
	 * @param fullName True if the name of the currency should be used instead of
	 *                 its ISO code.
	 * @return The string representation of the CurrencyUnit.
	 */
	public String toString(boolean fullName) {
		if (fullName)
			return name;
		else
			return iso;
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Values">
	/**
	 * Gets an array of all available currency units.
	 * @return An array of all available currency units.
	 */
	public static CurrencyUnit[] values() {
		CurrencyUnit[] unitArray = new CurrencyUnit[unitMap.size()];
		return unitMap.values().toArray(unitArray);
	}
	// </editor-fold>
	// <editor-fold defaultstate="expanded" desc="ValueOf">
	/**
	 * Returns the currency unit with the specified ISO code.
	 * @param iso The ISO code of the currency unit to get.
	 * @return The located currency unit.
	 * @throws IllegalArgumentException A currency with the ISO code was not found.
	 */
	public static CurrencyUnit valueOf(String iso) {
		if (unitMap.containsKey(iso))
			return unitMap.get(iso);
		throw new IllegalArgumentException("No ISO with value of '" + iso + "'!");
	}
	// </editor-fold>
	
	// These are NOT necissary because there will only ever be one
	// instance for any given currency unit. We can use the == operator.
	// <editor-fold defaultstate="collapsed" desc="Equality">
	/**
	 * Checks if obj is a CurrencyUnit and the same as this unit.
	 * @param obj The object to check for equality.
	 * @return True if obj is a CurrencyUnit and equal to this unit.
	 */
	/*@Override
	public boolean equals(Object obj) {
		if (CurrencyUnit.class.isInstance(obj)) {
			CurrencyUnit other = CurrencyUnit.class.cast(obj);
		}
		return false;
	}*/
	/**
	 * Checks if other is the same as this unit.
	 * @param other The CurrencyUnit to check for equality.
	 * @return True if other is equal to this unit.
	 */
	/*public boolean equals(CurrencyUnit other) {
		return iso.equals(other.iso) && name.equals(other.name);
	}*/
	/**
	 * Gets the hash code of the unit's ISO code and name.
	 * @return The hash code for the currency unit.
	 */
	/*@Override
	public int hashCode() {
		return iso.hashCode() ^ name.hashCode();
	}*/
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Accessors">
	/**
	 * Gets the exchange rate from this currency unit to unit.
	 * @param unit The new unit to get the exchange rate to.
	 * @return The exchange rate from this currency unit to unit.
	 */
	public double exchangeRate(CurrencyUnit unit) {
		return exchangeRateUSD / unit.exchangeRateUSD;
	}
	/**
	 * Gets the exchange rate from this currency unit to unit.
	 * @param unitISO The ISO code of the new unit to get the exchange rate to.
	 * @return The exchange rate from this currency unit to unitISO.
	 * @throws IllegalArgumentException The unitISO does not exist.
	 */
	public double exchangeRate(String unitISO) throws IllegalArgumentException {
		return exchangeRate(CurrencyUnit.valueOf(unitISO));
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Rounding">
	/**
	 * Floors the currency value to the previous fractional.
	 * @param value The value to floor.
	 * @return The floored currency unit value.
	 */
	public double floor(double value) {
		int cents = fractional;
		if (cents == 1)
			return value;
		return Math.floor(value * cents) / cents;
	}
	/**
	 * Ceilings the currency value to the next fractional.
	 * @param value The value to ceiling.
	 * @return The ceilinged currency unit value.
	 */
	public double ceil(double value) {
		int cents = fractional;
		if (cents == 1)
			return value;
		return Math.ceil(value * cents) / cents;
	}
	/**
	 * Rounds the currency value to the nearest fractional.
	 * @param value The value to round.
	 * @return The rounded currency unit value.
	 */
	public double round(double value) {
		int cents = fractional;
		if (cents == 1)
			return value;
		return Math.round(value * cents) / cents;
	}
	// </editor-fold>
}
