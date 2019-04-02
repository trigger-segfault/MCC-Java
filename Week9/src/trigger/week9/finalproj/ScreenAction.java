/*
 * Class Name: Menu
 * Author: Robert Jordan
 * Date Created: Apr 2, 2019
 * Synopsis: A special Screen type that performs an action based on the
 *           ScreenActionType enum type.
 */
package trigger.week9.finalproj;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A special Screen type that performs an action based on the ScreenActionType
 * enum type.
 */
public final class ScreenAction extends Screen {
	
	public final ScreenActionType type;
	
	public ScreenAction(ScreenActionType type) {
		this.type = type;
	}
	
	@Override
	public void print() throws FileNotFoundException, IOException {
	}
	@Override
	public Screen run() throws RequestExitException, RequestBackException {
		return null;
	}
}
