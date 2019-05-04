/*
 * Class Name: JobApplicationForm
 * Author: Robert Jordan
 * Date Created: May 2, 2019
 * Synopsis: A form with all fields to fill out in a Job Application.
 */
package trigger.finalproject.projects.jobapplication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import trigger.finalproject.utilities.*;

/**
 * A form with all fields to fill out in a Job Application.
 */
public class JobApplicationForm {
	// <editor-fold defaultstate="collapsed" desc="Static Fields">
	public static final SimpleDateFormat DATE_FORMAT =
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Fields">
	public String firstName;
	public String lastName;
	public String address;
	public String city;
	public String state;
	public Integer zip;
	public String email;
	public String phone;
	public Integer yearsExperience;
	public Boolean workedHereBefore;
	public String previousJob;
	public Calendar timestamp;
	// </editor-fold>
	
	public String getTimestamp() {
		return DATE_FORMAT.format(timestamp.getTime());
	}
	
	// <editor-fold defaultstate="expanded" desc="ToString">
	@Override
	public String toString() {
		return String.format("%s %s", firstName, lastName);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Parse">
	public static JobApplicationForm valueOf(String s) throws ParseException {
		String[] args = StringUtils.splitArgs(s);
		if (args.length < 12)
			throw new IllegalArgumentException("Expected at least 12 arguments!");
		
		JobApplicationForm form = new JobApplicationForm();
		form.firstName = args[0];
		form.lastName = args[1];
		form.address = args[2];
		form.city = args[3];
		form.state = args[4];
		form.zip = Integer.valueOf(args[5]);
		form.email = args[6];
		form.phone = args[7];
		form.yearsExperience = Integer.valueOf(args[8]);
		form.workedHereBefore = Boolean.valueOf(args[9]);
		if (form.workedHereBefore)
			form.previousJob = args[10];
		
		form.timestamp = Calendar.getInstance();
		form.timestamp.setTime(DATE_FORMAT.parse(args[11]));
		return form;
	}
	// </editor-fold>
	// <editor-fold defaultstate="expanded" desc="I/O">
	
	public void print() {
		print(0);
	}
	public void print(int indent) {
		String p = StringUtils.repeat(' ', indent);
		printArg(p + "Submitted at", getTimestamp());
		Console.printLine();
		
		Console.printLine(p + "== Personal Information ==");
		printArg(p + "First Name", firstName);
		printArg(p + " Last Name", lastName);
		printArg(p + " Address", address);
		printArg(p + "    City", city);
		printArg(p + "   State", state);
		printArg(p + "Zip Code", zip);
		printArg(p + "   Email", email);
		printArg(p + "   Phone", phone);
		Console.printLine();
		
		Console.printLine(p + "== Work Experience ==");
		printArg(p + "Worked with us before", workedHereBefore);
		if (workedHereBefore)
			printArg(p + "Previous Job", previousJob);
		printArg(p + "Years of experience", workedHereBefore);
	}
	public static JobApplicationForm nextForm() throws RequestExitException, RequestBackException {
		JobApplicationForm form = new JobApplicationForm();
		
		Console.printLine("== Personal Information ==");
		form.firstName	= InputUtils.nextLine("First Name");
		form.lastName	= InputUtils.nextLine(" Last Name");
		form.address	= InputUtils.nextLine(" Address");
		form.city		= InputUtils.nextLine("    City");
		form.state		= InputUtils.nextLine("   State");
		form.zip		= InputUtils.nextUInt("Zip Code");
		form.email		= InputUtils.nextLine("   Email");
		form.phone		= InputUtils.nextLine("   Phone");
		
		Console.printLine();
		Console.printLine("== Work Experience ==");
		form.workedHereBefore = InputUtils.nextBool("Have you worked with us before");
		if (form.workedHereBefore)
			form.previousJob = InputUtils.nextLine("What was your previous position with us");
		form.yearsExperience = InputUtils.nextUInt("Years of experience");
		
		form.timestamp = Calendar.getInstance();
		Console.printLine();
		printArg("Submitted at", form.getTimestamp());
		return form;
	}
	
	private static void printArg(String title, Object arg) {
		if (arg instanceof Boolean) {
			Boolean b = (Boolean) arg;
			arg = (b ? "yes" : "no");
		}
		Console.printLine("%s: %s", title, arg);
	}
	
	public static JobApplicationForm[] readAllForms(String path)
			throws FileNotFoundException, IOException
	{
		String[] lines = FileUtils.readAllLines(path);
		ArrayList<JobApplicationForm> forms = new ArrayList<>();
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			// Ignore commented or empty lines
			if (line.startsWith("#") || line.isEmpty()) continue;
			try {
				forms.add(valueOf(line));
			} catch (Exception ex) { }
		}
		return forms.toArray(new JobApplicationForm[forms.size()]);
	}
	
	public static void appendForm(String path, JobApplicationForm form)
			throws FileNotFoundException, IOException
	{
		// Create a file and start with a comment header.
		if (!FileUtils.exists(path))
			FileUtils.appendLine(path, "#FirstName LastName Address City State Zip Email Phone YearsExperience WorkedHereBefore PreviousJob");
		
		String[] args = {
			form.firstName,
			form.lastName,
			form.address,
			form.city,
			form.state,
			String.valueOf(form.zip),
			form.email,
			form.phone,
			String.valueOf(form.yearsExperience),
			String.valueOf(form.workedHereBefore),
			(form.workedHereBefore ? form.previousJob : null),
			form.getTimestamp(),
		};
		String line = StringUtils.joinArgs(args);
		FileUtils.appendLine(path, line);
	}
	
	// </editor-fold>
}
