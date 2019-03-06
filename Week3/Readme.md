# Week 3 - Week Selector

**Author:** Robert Jordan

## Code Walkthrough

For the code, I created a 3-class setup of `Main`, `Menu`, and `WeekRunner`.

`Main` performs the standard loop of `Menu.run()` and checks if the user `Menu` returned with the signal to exit.

`Menu` has `printTitle()` to print the program header and `run()` to execute the input and output logic such as asking for input, validating input, and then following through on said input. Like with my Temperature Converter program, typing in "exit" will terminate the input loop.

`WeekRunner` has two functions: A) Get the location of the jar file, and B) Obviously to run the jar file. The first is performed by reading the `WeekPath.txt` file located in the same directory. It has a path to all jar files with a # token to represent each jar file's week number that is replaced at runtime. The second, runs the jar file with `ProcessBuilder` and calls `redirect_____(Redirect.INHERIT)` to redirect all I/O, input. That was a pain to find.

## What I Learned this Week

I learned a lot about the huge lack of quality of life features in Java when compared to C#. As put by someone on Stack Overflow for a solution: "[As far as I know], there is no one-liner with standard libraries.". The one-liner I in question I was looking for was the Java equivalent of C#'s `File.ReadAllText(string)`. What could be one line, ended up as a method with at least 10 lines and plenty of try-catches. Try-catches are another thing that really bother me in Java, the fact that you have to catch everything or tell the method that it doesn't catch *X* and that the method calling it needs to catch it. The only benefit of this is that it guarantees that methods display all possible exceptions. But this is also incorrect because the parsing methods I've used in the past threw `NumberFormatException` without labeling that in the exception list of the method. So I can't really see any benefits. That, or it's another issue with NetBeans.

One annoying thing about Java is it's supposed to be cross platform, yet so many actions that you'd need to take that differ between system are not present. Looking up how to get the current directory only lead me to an answer that told you how to get the current directory of the program *when it started*. Other things like this include total lack of console functionality such as clear screen or color of any kind. The fact that there are no static methods for the console to read a line says a lot.

NetBeans quality of life issue number 0xFFFF, after typing a `.` after a variable, you have to wait a split second before the autocomplete list drops down. This drives me crazy because if you start typing before then, then the list will never open. I've known the autocomplete list was finicky for awhile, but I only just learned why that was today.

### Video Presentation: [Link](https://www.youtube.com/watch?v=kiiMD1iELik)