/*
 * Class Name: JobApplicationSubmitMenu
 * Author: Robert Jordan
 * Date Created: May 2, 2019
 * Synopsis: A screen to submit a new job application.
 */
package trigger.finalproject.projects.jobapplication;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import trigger.finalproject.utilities.*;
import trigger.finalproject.utilities.menus.*;

/**
 * A screen to submit a new job application.
 */
public class JobApplicationSubmitMenu extends Menu {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The file path to read job applications from.
	 */
	public static final String FORMS_FILE = "JobApplications.txt";
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * The text file to read the submitted text from.
	 */
	private final String submittedTextFile;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the  Menu with the specified text file path to display.
	 * @param textFile The path to the text file to print.
	 * @param submittedTextFile The path to the submitted text to display.
	 */
	public JobApplicationSubmitMenu(String textFile, String submittedTextFile) {
		super(textFile);
		this.submittedTextFile = submittedTextFile;
		this.choices = new Screen[] { ScreenAction.LAST };
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Menu Overrides">
	@Override
	public Screen run(ScreenModule owner) throws RequestException, Exception {
		JobApplicationForm form = new JobApplicationForm();
		
		// indent (p stands for padding)
		String p = StringUtils.repeat(' ', 10);
		
		OutputUtils.printLine("== Personal Information ==", WIDTH, alignment);
		Console.printLine();
		form.firstName	= InputUtils.nextLine(p + "First Name");
		form.lastName	= InputUtils.nextLine(p + " Last Name");
		form.address	= InputUtils.nextLine(p + "   Address");
		form.city		= InputUtils.nextLine(p + "      City");
		form.state		= InputUtils.nextLine(p + "     State");
		form.zip		= InputUtils.nextUInt(p + "  Zip Code");
		form.email		= InputUtils.nextLine(p + "     Email");
		form.phone		= InputUtils.nextLine(p + "     Phone");
		
		// indent (p stands for padding)
		p = StringUtils.repeat(' ', 5);
		
		//Console.printLine();
		Console.printLine();
		OutputUtils.printLine("== Work Experience ==", WIDTH, alignment);
		Console.printLine();
		form.workedHereBefore = InputUtils.nextBool(p + "Have you worked with us before");
		if (form.workedHereBefore)
			form.previousJob = InputUtils.nextLine(p + "What was your previous position with us");
		form.yearsExperience = InputUtils.nextUInt(p + "Years of experience");
		
		form.timestamp = Calendar.getInstance();
		Console.printLine();
		Console.printLine(p + "Submitted at %s", form.getTimestamp());
		Console.printLine();
		
		//JobApplicationForm form = JobApplicationForm.nextForm();
		
		try {
			JobApplicationForm.appendForm(FORMS_FILE, form);
		} catch (IOException ex) {
			Console.printError("Failed to submit Job Application!");
			Console.printError(ex.getMessage());
			return super.run(owner);
		}
		
		OutputUtils.printFile(submittedTextFile, WIDTH, alignment);
		Console.printLine();
		return super.run(owner);
	}
	@Override
	public void getMissingFiles(Collection<String> files) {
		super.getMissingFiles(files);
		addMissingFile(submittedTextFile, files);
	}
	// </editor-fold>
}
