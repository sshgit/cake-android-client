Coding Test (fictitious)


***
NOTE: PLEASE DO NOT USE THIRD PARTY LIBRARIES. However, feel free to state which third party libraries you might have used.
***


Attached you’ll find the code for a simple mobile app to browse cakes. The developer who wrote this code was a summer intern and unfortunately did not manage to finish the project.  The project compiles but the app crashes as soon as it runs.

The app loads a JSON feed containing a repeated list of cakes, with title, image and description from a URL, then displays the contents of the feed in a scrollable list.

We would like you to fix the crash bug, ensure the functionality of the app works as expected (all images display correctly on the table, all text is readable) and performs smoothly.  Use of 3rd party libraries are prohibited for this project due to its sensitive nature.

Additionally, feel free to refactor and optimise the code where appropriate. Ideally, what we’d be looking for is:

* Simple fixes for crash bug(s)
* Application to support rotation
* Safe and efficient loading of images
* Removal of any redundant code
* Refactoring of code to best practices

This is your opportunity to show us how you think and Android app should be architected, please make any changes you feel would benefit the app.

The test should take around 2 hours, certainly no longer than 3 hours. Good luck!
# Note
I have not concentrated on UI at all, and also handling of error condition 
Tried to use clean code methods and MVP(M) model for UI and did little bit of refactoring.

# Librararies/Languages/extensions which can be used
* [Kotlin](https://kotlinlang.org/docs/reference/android-overview.html) - Modern language which is now officially supported & advocated by Google
* [RxJava](https://github.com/ReactiveX/RxJava)/[Android architecture components](https://developer.android.com/topic/libraries/architecture/) - reactive programming, this example is perfect example to follow reactive pattern
* [Glide](https://github.com/codepath/android_guides/wiki/Displaying-Images-with-the-Glide-Library) - Image loader library
* [Retrofit 2](https://square.github.io/retrofit/) - Http client for java
