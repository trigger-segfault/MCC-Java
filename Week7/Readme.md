# Week 7 - Temperature File Converter

**Author:** Robert Jordan

## Code Walkthrough

For this week I used plenty of code from my previous TemperatureConverter package. This time I added a `TemperaturesFileIO` class that handles generating and writing files with text-written temperatures, and reading text files with temperatures.

For the `Temperature` class I added `valueOf(String)` and `valueOfUnti(String)` for parsing temperatures and temperature units with ease and outputting clear exception messages.

`TemperatureUnit` stayed exactly the same as last week`.

`Menu` now has numerous methods for reading different input types and cycling until the user gets it right. Each input type returns `null` if the user requests to exit so the user can exit from anywhere in the program. A lot of these reading methods were modified from the Job Application assignment. Now the menu class has two modes, generating random temperatures to convert with the `gen` option. Or converting existing temperatures with the `conv` option. Each of these options is split into sub `run` methods, `runGenerator()` and `runConverter()`. These methods return true if the user has requested to exit. In hindsight, I should really implement an `ExitRequestedException` class that the `main` method can catch but I've been trying to reduce my typing load lately due to aching hands so I decided to shelve it for later. All of the string manipulation with `+` was switched to `String.format()` and `printf`. Honestly I hate having to type out `String.format` every time instead of using C#'s method of string interpolation. C# string interpolation also saves much more column space than `String.format()`.

## What I Learned this Week

This week I learned that Java lambdas need an interface to declare them which sounds like a pain compared to declaring delegates.

I also learned that most path manipulation has to be done through the `new File(...)` constructor which seems excessive when you could just operate on the string path.

I learned that Java has no `printlnf()` method which is also frustrating that you either need to `println(string.Format(...));` or `printf("...%n");`. Seems like a ridiculous lack of quality of life functionality.

While testing the week selector with my temperature file program I discovered that starting a new process in Java does not automatically set the working directory which is frustrating. I had to clean up both week selector projects to fix that.

### Video Presentation: [Link](https://www.youtube.com/watch?v=zrxgCCT6fB4)