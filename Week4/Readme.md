# Week 4 - Week Selector v2

**Author:** Robert Jordan

## Code Walkthrough

For the code, I created a 4-class setup of `Main`, `Menu`, (Like Week 3) and `WeekJar` and `WeekJarConfig`.

`Main` performs the standard loop of `Menu.run(WeekJarConfig)` and checks if the user `Menu` returned with the signal to exit. It also loads the `WeekJarConfig` settings before running the menu and prints an error when the config files cannot be found.

`Menu` has `printTitle()` to print the program header and `run(WeekJarConfig)` to execute the input and output logic such as asking for input, validating input, and then following through on said input. Like with my Temperature Converter program, typing in "exit" will terminate the input loop. `Menu` also has `printWeeks(WeekJarConfig)` which will print all existing weeks and return true if the program should exit because no weeks were found.

`WeekJar` is essentially the same as Week 3's `WeekRunner` except it's an instance non-static class that encapsulates a single week. It can tell you if it exists. It knows it's number. It knows it's path. And it knows it's name.

`WeekJarConfig` is a collection for all loaded `WeekJars` as well as settings loaded from `WeekInfo.ini` and `WeekPath.ini`. These ini files were separated for one reason, and that's the fact that the paths change depending on where you execute the program, while info and config settings do not need to change often at all.

## What I Learned this Week

I learned that Java does have a C# using-like keyword syntactic sugar for disposing of Closeable objects. Surrounding an object with `try (obj) { }` will make sure it is disposed of by the end of the try block.

I also experimented with referencing external libraries with Maven this week and learned it's too much of a headache to bother for this class. It requires so much underlying modification of the xml files to reference things and guarantee that the program even ends up with a manifest for its main method. It's a nightmare and looking up how to do it took quite awhile. The fact that it at least downloads things like NuGet instead of requiring local references is nice, but the fact that you need a special project format just to support this is absurd.

I also learned Java doesn't have interfaces for Immutable lists like C#'s `IReadOnlyList<>`, while reading the posts about this, some people even complained that simple arrays are overkill in Java because of their implementation. I don't plan on worrying about that though.

### Video Presentation: [Link](https://www.youtube.com/watch?v=6Jg87jOCPtA)