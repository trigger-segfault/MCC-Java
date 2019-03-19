# Week 6 - Temperature Converter v2

**Author:** Robert Jordan

## Code Walkthrough

For this week, I reused a lot of the code from Week 7 and mashed it together with the code from Week 5.

The `Temperature` class's `toString` methods now use `String.format`.

The input methods in `Menu` have been compacted and the `run` method is much easier to look at, and well-commented.

I finally bothered to implement `RequestExitException` so that the `Menu` class does not need to have a large chain of checks to keep seeing if the user requests to exit the program. Now `Main` just has to let the program knows that it throws the `RequestExitException`, along with all the input helper methods. `RequestExitException` may cause problems in the future if I ever try to catch `Exception` inside a block that uses an input helper method. If I don't catch `RequestExitException` beforehand, then the request by the user to exit will be treated as a normal error and likely output in some way that it shouldn't be. Just like how many posts on r/softwaregore are of message boxes displaying undesirable error messages. `RequestExitException` must always be caught somewhere in the program and processed so that the program knows to exit from there. In almost any case, I'll catch it in the `Main` class and then just return in the catch statement.

Because of `RequestExitException`, `Main` no longer has conditions on its loop and instead uses a `while (true)` loop inside the try catch for the `RequestExitException`.

I decided to keep the `nextUint` and `nextLine` methods in the `Menu` class and apply the new `RequestExitException` functionality so that they can be quickly picked up for future use. Even if they aren't used at all in this week's assignment.

For the hell of it, I added documentation to the `Temperature` class's final `value` field and final `unit` field. In the event that I ever decide `Temperature.value` is easier than calling `Temperature.getValue()`, then I can switch things up and be done with it. Also depends how much the standard encourages *not* using public fields to access constant variables. I'm still always back and forth on the same thing in C# between static readonly fields vs constant static properties. The C# API uses both so it's a little hard to figure out which one is best.

### Video Presentation: [Link](https://www.youtube.com/watch?v=QA0qz5KQgvc)