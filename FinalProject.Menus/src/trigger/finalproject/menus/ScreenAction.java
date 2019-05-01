/*
 * Class Name: ScreenAction
 * Author: Robert Jordan
 * Date Created: May 1, 2019
 * Synopsis: A special Screen type that performs an action based on the
 *           ScreenActionType enum type.
 */
package trigger.finalproject.menus;

import java.io.FileNotFoundException;
import java.io.IOException;
import trigger.finalproject.utilities.*;

/**
 * A special Screen type that performs an action based on the ScreenActionType
 * enum type.
 */
public final class ScreenAction extends Screen {
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
	public void print() throws FileNotFoundException, IOException {
	}
	@Override
	public Screen run() throws RequestExitException, RequestBackException {
		return null;
	}
	// </editor-fold>
}
