/*
 * Class Name: JobApplication
 * Author: Robert Jordan
 * Date Created: Feb 26, 2019
 * Synopsis: The main class to run the job application form.
 */
package trigger.week2.jobapplication;

import java.io.BufferedReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main class for the job application form.
 */
public class Main {
	// <editor-fold defaultstate="collapsed" desc="Static Fields">
	private static final Scanner scanner = new Scanner(System.in);
	// </editor-fold>
	/**
	 * The main method for the job application form.
	 * @param args Unused
	 */
	public static void main(String[] args) {
		System.out.println("Please fill out this job application:");
		
		String firstName, lastName, address, city, state, email, phone;
		int zip, yearsOfExperience;
		boolean workedWithUsBefore;
		String previousJob;
		
		System.out.println();
		System.out.println("==Personal Information==");
		firstName = nextLine("First Name");
		lastName = nextLine("Last Name");
		address = nextLine("Address");
		city = nextLine("City");
		state = nextLine("State");
		zip = nextUInt("Zip Code");
		email = nextLine("Email");
		phone = nextLine("Phone");
		
		System.out.println();
		System.out.println("==Work Experience==");
		workedWithUsBefore = nextBool("Have you worked with us before");
		if (workedWithUsBefore)
			previousJob = nextLine("What was your previous position with us");
		yearsOfExperience = nextUInt("Years of experience");
		
		System.out.println();
		System.out.println("Your application has been submitted will be reviewed and we will contact you if needed.");
		System.out.println();
		System.out.print("Press any key to continue...");
		scanner.nextLine();
	}
	
	// <editor-fold defaultstate="expanded" desc="Static Input Methods">
	private static String nextLine(String display) {
		System.out.print(display + ": ");
		String input = scanner.nextLine();
		while (input.length() == 0) {
			printErr("Input cannot be empty!");
			System.out.print(display + ": ");
			input = scanner.nextLine();
		}
		return input;
	}
	private static int nextUInt(String display) {
		System.out.print(display + ": ");
		String input = scanner.nextLine();
		Integer value = null;
		try {
			value = Integer.valueOf(input);
		} catch (NumberFormatException ex) { }
		while (input.length() == 0 || value == null || value < 0) {
			if (input.length() == 0)
				printErr("Input cannot be empty!");
			else
				printErr("Invalid number!");
			System.out.print(display + ": ");
			input = scanner.nextLine();
			try {
				value = Integer.valueOf(input);
			} catch (NumberFormatException ex) { }
		}
		return value;
	}
	private static boolean nextBool(String display) {
		System.out.print(display + " (y/n): ");
		String input = scanner.nextLine();
		while (!input.equals("yes") && !input.equals("y") && !input.equals("no") && !input.equals("n")) {
			if (input.length() == 0)
				printErr("Input cannot be empty!");
			else
				printErr("Invalid yes/no input!");
			System.out.print(display + " (y/n): ");
			input = scanner.nextLine();
		}
		return input.equals("yes") || input.equals("y");
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Static Output Methods">
	private static void printErr(String message) {
		System.err.println("  " + message);
		try {
			Thread.sleep(100);
		} catch (InterruptedException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	// </editor-fold>
}
