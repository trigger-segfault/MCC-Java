/*
 * Class Name: Screen
 * Author: Robert Jordan
 * Date Created: Apr 2, 2019
 * Synopsis: Abstract console interface
 */
package trigger.week9.finalproj;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Abstract console interface class.
 */
public abstract class Screen {
	public static final int WIDTH = 78;
	public static final int HEIGHT = 24;
	
	public static final Screen EXIT = new ScreenAction(ScreenActionType.EXIT);
	public static final Screen MAIN = new ScreenAction(ScreenActionType.MAIN);
	public static final Screen LAST = new ScreenAction(ScreenActionType.LAST);
	public static final Screen CURRENT = new ScreenAction(ScreenActionType.CURRENT);
	
	public abstract void print() throws FileNotFoundException, IOException;
	public abstract Screen run() throws RequestExitException, RequestBackException;
}
