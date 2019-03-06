# Week 2 - Job Application

**Author:** Robert Jordan

## Code Walkthrough

The Job Application was a fun way to mess around with user input. I started by alerting the user to fill out said application like is done on most forms.

The form starts with a personal information section which contains: name, address, email, and phone number. Next is the work experience section which asks if you have worked at this company before and proceeds to ask an *additional* question if you answer `yes` to that, which is what your previous position was. Lastly it asks for your years of experience in the field of the job you are applying for.

After the user input you get a generic message saying your application has been submitted then the program asks for input to quit.

For the user inputk, I wrote helper functions for reading an unsigned integer (`nextInt`), a string (`nextLine`), and a boolean (`nextBool`). These methods make sure the input is valid and complain to the user and repeat the question when it is not. `nextInt` and `nextBool` both do additional operations to make sure the input can be parsed and isn't just an empty line.

The last helper function is `printErr` which prints to the error stream after indenting the message by two spaces, it then sleeps to make sure NetBeans' terrible console won't output the next line before the error is output.

## What do you think you will use most

All of our programs are console applications so we will very likely be using lots of user input which means the `Scanner` class will get lots of use. I'll probably also create a helper class for all these user input options to make reading from `System.in` quicker and easier.

## Where did you get lost, did you 'get found'

I had trouble with the fact that `System.err.println` was slower than `System.out.println` thus, you could call `err` first and `out` would still end up being printed before it. This race condition is ridiculous to be present in NetBean's terminal but I just hacked a way to fix it by sleeping for a hundred milliseconds.

I also was surprised when Java doesn't `Always` require Exceptions to be caught or documented. I'm still not sure how that works as Interger.valueOf(String) throws an exception without requiring a catch but `Thread.sleep(int)` does require it. This will make error handling a bit trickier as NetBeans hover intellisense and documentation display is pretty slow and unreliable.

### Video Presentation: [Link](https://www.youtube.com/watch?v=nMMJxFxTL_8)