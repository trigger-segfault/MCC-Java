# Week 8 - Currency File Converter

**Author:** Robert Jordan

## Code Walkthrough

For this week, I reused a lot of code from both Week 7 and Week 6. Both for the updated code of Week 6 and the File I/O of Week 7.

I completely reimagined the `CurrencyUnit` class in comparison to the `TemperatureUnit` class. Because there are endless types of currency, the currency units are not hardcoded, and are thus, not an enum. The `CurrencyUnit` class has a ton of added functionality, mostly through its stored public final fields. These fields include the ISO code (the identifier for the unit), the full name of the unit, the fractional (cents), the fractional number of decimal digits to represent, and a `DecimalFormat` class for formatting the double values. The fractional digits and `DecimalFormat` are both generated inside the constructor and do not need to be passed, as they are determined based on the fractional value.

The `Currency` class, like the `Temperature` class, contains both a value and unit. This time however, the `Currency.unit` field is much more powerful than `Temperature.unit` because it's not an enum. The unit contains all the information needed for the `Currency` class to function as needed. Also I finally switched away from getter methods and instead made the `Currency` fields public like I was considering with `Temperature` back in Week 6.

Along with the `RequestExitException` of Week 6, I added `RequestBackException` which will throw the user back to the main menu of the program. This feature is important because the menu options ask for a lot of input, and the user may want to cancel the operation half-way through. This is also caught in `Main` *inside* the while loop that calls `Menu.run()`.

Lastly, the `Menu.nextXXX` methods all rely on `Menu.nextLine` to handle the `exit` and `back` arguments. The `nextLine` method catches these so that all other `nextXXX` methods do not have to anymore. I also brought back the `nextBool` method from Week 2 and re-incorporated it into the new method styles.

## How my Skillset Improved

This week I learned a couple things about static implementation in Java. First I learned that Java *does* in-fact have a static initializer (I'm used to calling it a constructor). I used this to initialize a static field that needed to be loaded from a file. It's a little messy, but it was the best way to setup a singleton instance for the database of known currency units.

I also learned that Java compilers don't fully co-operate with the static initializer when it comes to uninitialized static final fields. Because of this. the database for currency units could not be made final, and had to be assigned as null, and then defined in the static initializer.

I'm also starting to understand that Java generics aren't very powerful in terms of return values. The `toArray` methods don't give an array of the input type unless you pass the correct type array to the method, which is frustrating, but I can deal with it.

### Video Presentation: [Link](https://www.youtube.com/watch?v=Pfv4R0A4cSI)