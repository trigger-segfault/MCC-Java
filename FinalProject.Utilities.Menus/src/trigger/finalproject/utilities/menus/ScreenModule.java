/*
 * Class Name: MenuDriverModule
 * Author: Robert Jordan
 * Date Created: May 2, 2019
 * Synopsis: The base module class that can contain other modules and screens,
 *           or be extended for the ScreenDriver class.
 */
package trigger.finalproject.utilities.menus;

import java.util.ArrayList;
import java.util.Collection;
import trigger.finalproject.utilities.FileUtils;

/**
 * The base module class that can contain other modules and screens, or be
 * extended for the ScreenDriver class.
 */
public abstract class ScreenModule {
	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * The list of Screens registered with the this module.
	 */
	private final ArrayList<Screen> screens;
	/**
	 * The list of ScreenModules registered with this module.
	 */
	private final ArrayList<ScreenModule> modules;
	// </editor-fold>
	
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the base Screen Module.
	 */
	public ScreenModule() {
		screens = new ArrayList<>();
		modules = new ArrayList<>();
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Setup">
	/**
	 * Adds a screen to the module.
	 * @param screen The screen to register.
	 */
	protected final void addScreen(Screen screen) {
		screens.add(screen);
	}
	/**
	 * Removes the screen from the module.
	 * @param screen The screen to unregister.
	 */
	protected final void removeScreen(Screen screen) {
		screens.remove(screen);
	}
	/**
	 * Adds a screen module with all its screens and submodules to the module.
	 * @param module The screen module to register.
	 */
	protected final void addModule(ScreenModule module) {
		modules.add(module);
	}
	/**
	 * Removes the screen module with all its screens and submodules from the
	 * module.
	 * @param module The screen module to unregister.
	 */
	protected final void removeModule(ScreenModule module) {
		modules.remove(module);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Accessors">
	/**
	 * Finds the module owning this screen.
	 * @param screen The screen to look for the owner of.
	 * @return The Screen Module owner, or null, if not found.
	 */
	public final ScreenModule findOwner(Screen screen) {
		ScreenModule owner = null;
		if (screens.contains(screen))
			owner = this;
		for (int i = 0; i < modules.size() && owner == null; i++) {
			owner = modules.get(i).findOwner(screen);
		}
		return owner;
	}
	/**
	 * Gets all screens registered with this module.
	 * @return The list of screens registered with this module.
	 */
	public final Screen[] getScreens() {
		return screens.toArray(new Screen[screens.size()]);
	}
	/**
	 * Gets all modules registered with this module.
	 * @return The list of modules registered with this module.
	 */
	public final ScreenModule[] getModules() {
		return modules.toArray(new ScreenModule[modules.size()]);
	}
	/**
	 * Gets all screens and subscreens registered with this module.
	 * @return The list of screens subscreens registered with this module.
	 */
	public final Screen[] getAllScreens() {
		ArrayList<Screen> list = new ArrayList<>();
		for (int i = 0; i < screens.size(); i++) {
			list.add(screens.get(i));
		}
		for (int i = 0; i < modules.size(); i++) {
			Screen[] subscreens = modules.get(i).getAllScreens();
			for (int j = 0; j < subscreens.length; j++)
				list.add(subscreens[j]);
		}
		return list.toArray(new Screen[list.size()]);
	}
	/**
	 * Gets all modules and submodules registered with this module.
	 * @return The list of modules and submodules registered with this module.
	 */
	public final ScreenModule[] getAllModules() {
		ArrayList<ScreenModule> list = new ArrayList<>();
		for (int i = 0; i < modules.size(); i++) {
			ScreenModule module = modules.get(i);
			list.add(module);
			ScreenModule[] submodules = module.getAllModules();
			for (int j = 0; j < submodules.length; j++)
				list.add(submodules[j]);
		}
		return list.toArray(new ScreenModule[list.size()]);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Virtual Methods">
	/**
	 * Adds the file to the missing files list if it does not exist.
	 * @param path The file to check for.
	 * @param files The missing files list.
	 */
	protected void addMissingFile(String path, Collection<String> files) {
		if (!FileUtils.isFile(path) && !files.contains(path))
			files.add(path);
	}
	/**
	 * Checks if this screen module has any missing required files and adds them
	 * to the list.
	 * @param files The list of files to add missing files to.
	 */
	public void getMissingFiles(Collection<String> files) {
		for (int i = 0; i < screens.size(); i++)
			screens.get(i).getMissingFiles(files);
		for (int i = 0; i < modules.size(); i++)
			modules.get(i).getMissingFiles(files);
	}
	// </editor-fold>
}
