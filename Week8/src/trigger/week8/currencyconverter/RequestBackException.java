/*
 * Class Name: RequestBackException
 * Author: Robert Jordan
 * Date Created: Mar 25, 2019
 * Synopsis: An exception thrown when the user has requested to go back to the
             menu.
 */
package trigger.week8.currencyconverter;

/**
 * An exception thrown when the user has requested to go back to the menu.
 * Make sure to always catch this exception before Exception is caught!
 */
public class RequestBackException extends Exception {
	/**
	 * Constructs the exception thrown to request going back to the menu.
	 */
	public RequestBackException() {
		super("User has requested to go back to the menu.");
	}
}
