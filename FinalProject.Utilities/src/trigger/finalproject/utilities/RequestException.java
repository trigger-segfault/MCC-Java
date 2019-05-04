/*
 * Class Name: RequestException
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: An exception thrown when the user has requested to perform some
 *           special action other than what was requested by the input.
 */
package trigger.finalproject.utilities;

/**
 * An exception thrown when the user has requested to perform some special
 * action other than what was requested by the input.
 * Make sure to always catch this exception before Exception is caught!
 */
public class RequestException extends Exception {
	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * The type of action requested by the user.
	 */
	public final RequestType type;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the exception thrown to request an action of the program.
	 * @param type The type of action requested by the user.
	 */
	public RequestException(RequestType type) {
		super("User has requested a special action.");
		this.type = type;
	}
	// </editor-fold>
}

