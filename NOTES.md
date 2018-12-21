# Notes
* Updated gradle files to include relevant and latest android versions, and also did refactoring of gradle file for maintainability.
* Implemented slightly modified version MVP design pattern, note that control flows from UI -> ViewModel -> Model
* Restructured code to put in logical packages
* LRU in memmory is cache used for bitmap cacheing, note that this doesnt work for large number of bitmap, to do that we neeed to use disk cacheing.
* Cleaned code to reuse components & fixed crashes
* I have not concentrated on UI at all
* Error handling also not comprehensive.
* Not written any unit tests


# Librararies/Languages/extensions which can be used
* [Kotlin](https://kotlinlang.org/docs/reference/android-overview.html) - Modern language which is now officially supported & advocated by Google
* [RxJava](https://github.com/ReactiveX/RxJava)/[Android architecture components](https://developer.android.com/topic/libraries/architecture/) - reactive programming, this is perfect example to follow reactive pattern
* [Glide](https://github.com/codepath/android_guides/wiki/Displaying-Images-with-the-Glide-Library) - Image loader library
* [Retrofit 2](https://square.github.io/retrofit/) - Http client for java
* [Android X Test](https://developer.android.com/training/testing/set-up-project) - All in one wrapper for Andoroid testing, comprises of JUnit4, RoboElectric & Espresso libraries
