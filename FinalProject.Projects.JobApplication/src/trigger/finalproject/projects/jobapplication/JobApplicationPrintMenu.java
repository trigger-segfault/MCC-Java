/*
 * Class Name: JobApplicationPrintMenu
 * Author: Robert Jordan
 * Date Created: May 2, 2019
 * Synopsis: A screen to print A job application form.
 */
package trigger.finalproject.projects.jobapplication;

import trigger.finalproject.utilities.*;
import trigger.finalproject.utilities.menus.*;

/**
 * A screen to print A job application form.
 */
public class JobApplicationPrintMenu extends Menu {
	// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * The token to replace with various Job application form.
	 */
	private static final String FORM_TOKEN = "FORM";
	// </editor-fold>
	
	
	// <editor-fold defaultstate="collapsed" desc="Fields">
	/**
	 * The job application form to print.
	 */
	private final JobApplicationForm form;
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 * @param form The form to print.
	 */
	public JobApplicationPrintMenu(String textFile, JobApplicationForm form) {
		super(textFile);
		this.form = form;
		this.choices = new Screen[] { ScreenAction.LAST };
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Menu Overrides">
	@Override
	protected boolean handleToken(String token, Align align) {
		if (token.equals(FORM_TOKEN)) {
			//form.print();
			
			// indent (p stands for padding)
			String p = StringUtils.repeat(' ', 10);
			OutputUtils.printLine("== Personal Information ==", WIDTH, alignment);
			Console.printLine();
			Console.printLine(p + "  Applicant: %s %s", form.firstName, form.lastName);
			//Console.printLine();
			Console.printLine(p + "    Address: %s", form.address);
			Console.printLine(p + "             %s, %s, %s", form.city, form.state, form.zip);
			//Console.printLine();
			Console.printLine(p + "      Email: %s", form.email);
			Console.printLine(p + "      Phone: %s", form.phone);
			//Console.printLine();
			Console.printLine();

			OutputUtils.printLine("== Work Experience ==", WIDTH, alignment);
			Console.printLine();
			Console.printLine(p + "Worked with us before: %s", form.workedHereBefore ? "yes" : "no");
			Console.printLine(p + "         Previous job: %s", form.previousJob);
			//Console.printLine();
			Console.printLine(p + "  Years of experience: %s", form.workedHereBefore);
			
			Console.printLine();
			OutputUtils.printLine("Submitted at " + form.getTimestamp(), WIDTH, alignment);
			Console.printLine();
			return true;
		}
		return false;
	}
	private static void printArg(String title, Object arg) {
		if (arg instanceof Boolean) {
			Boolean b = (Boolean) arg;
			arg = (b ? "yes" : "no");
		}
		Console.printLine("%s: %s", title, arg);
	}
	// </editor-fold>
}
