/*
 * Class Name: RequestType
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: A type of request used in InputUtils next() operations with
 *           RequestException.
 */
package trigger.finalproject.utilities;

import java.util.ArrayList;

/**
 * A type of request used in InputUtils next() operations with RequestException.
 */
public class RequestType {
	// <editor-fold defaultstate="expanded" desc="Constants">
	/**
	 * A request to exit the program.
	 */
	public static final RequestType EXIT = new RequestType("exit");
	/**
	 * A request to cancel the current operation.
	 */
	public static final RequestType CANCEL = new RequestType("cancel");
	/**
	 * A request to restart the current operation.
	 */
	public static final RequestType RESTART = new RequestType("restart");
	/**
	 * The prefix used for all commands.
	 */
	public static final String COMMAND_PREFIX = ""; // Empty
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Static Fields">
	/**
	 * The list of registered request types.
	 */
	private static final ArrayList<RequestType> types = new ArrayList<>();
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Static Constructor">
	/**
	 * Initializes the default RequestTypes by adding them to the list of types.
	 */
	static {
		add(EXIT);
		add(CANCEL);
		add(RESTART);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Setup">
	/**
	 * Creates and adds the request type to the list.
	 * @param name The name of the request.
	 * @return The created request type.
	 */
	public static RequestType add(String name) {
		return add(new RequestType(name));
	}
	/**
	 * Adds the request type to the list.
	 * @param type The request type to add.
	 * @return The passed request type.
	 */
	public static RequestType add(RequestType type) {
		if (!types.contains(type))
			types.add(type);
		return type;
	}
	/**
	 * Adds the default request types if they are not present.
	 * @param exit Add the exit request type.
	 * @param cancel Add the cancel request type.
	 * @param restart Add the restart request type.
	 */
	public static void addDefaults(boolean exit, boolean cancel, boolean restart) {
		if (exit) add(EXIT);
		if (cancel) add(CANCEL);
		if (restart) add(RESTART);
	}
	/**
	 * Clears all registered request types.
	 */
	public static void clear() {
		types.clear();
	}
	/**
	 * Gets an array of all registered request types.
	 * @return The list of registered request types.
	 */
	public static RequestType[] values() {
		return types.toArray(new RequestType[types.size()]);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Fields">
	/**
	 * The case-insensitive name of the request type.
	 */
	public final String name;
	/**
	 * The case-insensitive request type input command.
	 */
	public final String command;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs a new request type with the specified name and command.
	 * @param name The name and command to use. (Will be converted to lowercase)
	 */
	public RequestType(String name) {
		this.name = name.toLowerCase();
		this.command = COMMAND_PREFIX + this.name;
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Accessors">
	/**
	 * Checks if the input is equal to the command and ignores casing.
	 * @param input The input to check.
	 * @return True if the input matches the command.
	 */
	public boolean equalsCommand(String input) {
		return input.equalsIgnoreCase(command);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Object Overrides">
	@Override
	public String toString() {
		return name;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RequestType) {
			RequestType type = (RequestType) obj;
			return name.equalsIgnoreCase(type.name);
		}
		return false;
	}
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	// </editor-fold>
}
