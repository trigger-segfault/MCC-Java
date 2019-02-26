/*
 * Class Name: HelloWorld
 * Author: Robert Jordan
 * Date Created: Feb 26, 2019
 * Synopsis: The main class to run the hello world display.
 */
package trigger.week1.helloworld;

import java.util.Scanner;

/**
 * The main class for the hello world program.
 */
public class Main {
	/**
	 * The main method for the hello world program.
	 * @param args Unused
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("What does the fo-..programmer say!?");
		System.out.println("   ~~~~~~~~~~~~~~~~~~~~~~~~~~~   ");
		System.out.println("   /  .-------------------.  \\   ");
		System.out.println("   /  |    Hello World!   |  \\   ");
		System.out.println("   /  `-------------------'  \\   ");
		System.out.println("   ~~~~~~~~~~~~~~~~~~~~~~~~~~~   ");
		System.out.println();
		System.out.print("Press any key to continue...");
		scanner.nextLine();
	}
}
