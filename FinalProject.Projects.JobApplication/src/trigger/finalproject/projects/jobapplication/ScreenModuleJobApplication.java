/*
 * Class Name: ScreenModuleJobApplication
 * Author: Robert Jordan
 * Date Created: May 2, 2019
 * Synopsis: The screen module for the Job Application.
 */
package trigger.finalproject.projects.jobapplication;

import trigger.finalproject.utilities.menus.*;

/**
 * The screen module for the Job Application.
 */
public class ScreenModuleJobApplication extends ScreenModule {
	// <editor-fold defaultstate="expanded" desc="Menus">
	public Menu MENU = new Menu("resources/JobApplicationMenu.txt");
	public Menu SUBMIT = new JobApplicationSubmitMenu("resources/JobApplicationSubmitMenu.txt",
													"resources/JobApplicationSubmittedMenu.txt");
	public Menu VIEW = new JobApplicationViewMenu("resources/JobApplicationViewMenu.txt",
													"resources/JobApplicationPrintMenu.txt");
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Screen Module and sets up menu choices.
	 */
	public ScreenModuleJobApplication() {
		addScreen(MENU);
		addScreen(SUBMIT);
		addScreen(VIEW);
		
		MENU.choices = new Screen[] {
			SUBMIT,
			VIEW,
			ScreenAction.MAIN,
		};
		
		SUBMIT.choices = new Screen[] {
			MENU,
		};
	}
	// </editor-fold>
}