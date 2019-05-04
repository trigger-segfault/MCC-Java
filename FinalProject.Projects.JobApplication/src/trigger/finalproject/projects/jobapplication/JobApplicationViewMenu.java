/*
 * Class Name: JobApplicationViewMenu
 * Author: Robert Jordan
 * Date Created: May 2, 2019
 * Synopsis: A screen to view all submitted job applications.
 */
package trigger.finalproject.projects.jobapplication;

import java.io.IOException;
import java.util.Collection;
import trigger.finalproject.utilities.*;
import trigger.finalproject.utilities.menus.*;

/**
 * A screen to view all submitted job applications.
 */
public class JobApplicationViewMenu extends Menu {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The file path to read job applications from.
	 */
	public static final String FORMS_FILE = JobApplicationSubmitMenu.FORMS_FILE;
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * The text file to display the print form menu.
	 */
	private final String printTextFile;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Menu with the specified text file path to display.
	 * @param textFile The path to the text file to print.
	 */
	public JobApplicationViewMenu(String textFile, String printTextFile) {
		super(textFile);
		this.printTextFile = printTextFile;
		this.choices = new Screen[] { ScreenAction.LAST };
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Menu Overrides">
	@Override
	public Screen run(ScreenModule owner) throws RequestException, Exception {
		JobApplicationForm[] forms = new JobApplicationForm[0];
		if (FileUtils.isFile(FORMS_FILE)) {
			try {
				forms = JobApplicationForm.readAllForms(FORMS_FILE);
			} catch (IOException ex) {
				Console.printError("Failed to read available job applications!");
				this.choices = new Screen[] { ScreenAction.LAST };
				return super.run(owner);
			}
		}
		
		// Get the max number of digits in the week numbers
		final int numberPadding = Math.max(1, (int) Math.log10(forms.length + 1) + 1);
				
		choices = new Screen[forms.length + 1];
		for (int i = 0; i < forms.length; i++) {
			JobApplicationForm form = forms[i];
			Console.printLine("%s. %s submitted at %s",
					StringUtils.padLeft(String.valueOf(i + 1), numberPadding),
					StringUtils.padRight(form.toString(), 40),
					form.getTimestamp());
			choices[i] = new JobApplicationPrintMenu(printTextFile, form);
		}
		Console.printLine("%s. Back to Job Application Menu",
				StringUtils.padLeft(String.valueOf(forms.length + 1), numberPadding));
		choices[forms.length] = ((ScreenModuleJobApplication) owner).MENU;
		
		Console.printLine();
		return super.run(owner);
	}
	@Override
	public void getMissingFiles(Collection<String> files) {
		super.getMissingFiles(files);
		addMissingFile(printTextFile, files);
	}
	// </editor-fold>
}
