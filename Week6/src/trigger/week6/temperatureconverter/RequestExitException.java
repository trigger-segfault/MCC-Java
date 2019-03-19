/*
 * Class Name: RequestExitException
 * Author: Robert Jordan
 * Date Created: Mar 19, 2019
 * Synopsis: An exception thrown when the user has requested to exit the
             program.
 */
package trigger.week6.temperatureconverter;

/**
 * An exception thrown when the user has requested to exit the program.
 * Make sure to always catch this exception before Exception is caught!
 */
public class RequestExitException extends Exception {
	/**
	 * Constructs the exception thrown to request exiting the program.
	 */
	public RequestExitException() {
		super("User has requested to exit the program.");
	}
}
