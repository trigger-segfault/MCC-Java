# Week 9 - Final Project v1

**Author:** Robert Jordan

## Code Walkthrough

For this week, I basically ported most of my C# final project to Java in anticipation for when we finally do get the assignment and know what menus to make.

Instead of extension classes, I created `Utils` classes for `string.Center()` and `string.wordEllipses.split()`. Along with those two methods, the `StringUtils` class got many more helper methods for defining methods that Java has failed to implement themselves.

I was hoping to create an `ArrayUtils` class for easy casting to a typed array but I learned pretty quickly that Java generics are all a facade for casting hell that's done during runtime. No only is it hard to implement any kind of generics within the compiler, they'll probably end up being inneficient during runtime once you do get them working (if you manage to get them working).

As always, I have kept around the `RequestExitException` and `RequestBackException` for line input, and I have changed the `Menu` class into the `MenuUtils` class which has tons of `next` methods for reading different types of input. I also added a `clearScreen()` method and `waitForInput()` methods.

The `FileUtils` class from last week now skips Unicode Byte Order Marks (BOM) because the menu text files I was reading for this week had them and I didn't feel like stripping them. This is thanks to a StackOverflow answer that's been working it's magic for over a decade to solve a problem that Java just seems to ignore entirely.

## What I Learned this Week

I've already worked with UMLs in the Object Oriented Programming class and I wasn't much of a fan of them back then either. Thanks are still the same although I did layout a few extra functions that I would like thanks to this. In the end I still ended up developing more methods and helper classes than what I originally planned in the UML. The reason I dislike working with UMLs has to do with the fact that working with a mouse is difficult for me, and the UML designer requires a lot of mouse usage compared to other kinds of programming. It's much more efficient for me to lay things out in a text document and it also reduces the never-ending stress on my hands.

### Video Presentation: [Link](https://www.youtube.com/watch?v=Ok93T52MeQA)