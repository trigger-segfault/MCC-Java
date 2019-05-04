/*
 * Class Name: ScreenAction
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: A special Screen type that performs an action based on the
 *           ScreenActionType enum type.
 */
package trigger.finalproject.utilities.menus;

/**
 * A special Screen type that performs an action based on the ScreenActionType
 * enum type.
 */
public final class ScreenAction extends Screen {
	// <editor-fold defaultstate="expanded" desc="Actions">
	/**
	 * A screen action to exit the application.
	 */
	public static final ScreenAction EXIT = new ScreenAction(ScreenActionType.EXIT);
	/**
	 * A screen action to go to the main menu.
	 */
	public static final ScreenAction MAIN = new ScreenAction(ScreenActionType.MAIN);
	/**
	 * A screen action to go to the last screen.
	 */
	public static final ScreenAction LAST = new ScreenAction(ScreenActionType.LAST);
	/**
	 * A screen action to go to the current screen again.
	 */
	public static final ScreenAction CURRENT = new ScreenAction(ScreenActionType.CURRENT);
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * The type of special action performed by the screen.
	 */
	public final ScreenActionType type;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Screen Action with the specified action type.
	 * @param type The type of action for this screen.
	 */
	public ScreenAction(ScreenActionType type) {
		this.type = type;
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Screen Overrides">
	@Override
	public void print(ScreenModule owner) {
	}
	@Override
	public Screen run(ScreenModule owner) {
		return null;
	}
	// </editor-fold>
}
