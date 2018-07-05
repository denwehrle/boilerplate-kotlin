[![Build Status](https://travis-ci.org/denwehrle/boilerplate-kotlin.svg?branch=master)](https://travis-ci.org/denwehrle/boilerplate-kotlin)

# Boilerplate Kotlin

Languages, libraries and tools used

- [Kotlin](https://kotlinlang.org/)
- Support libraries
- RecyclerViews and CardViews 
- [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid) 
- Dependency Injection with [Dagger 2](http://google.github.io/dagger/)
- [Room](https://developer.android.com/topic/libraries/architecture/room.html) Persistence Library
- Network communication with [Retrofit 2](http://square.github.io/retrofit/)
- [Stetho](http://facebook.github.io/stetho/) as debug bridge
- Image loading and caching with [Glide](https://github.com/bumptech/glide)
- [Timber](https://github.com/JakeWharton/timber)
- [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/index.html)
- [Robolectric](http://robolectric.org/)
- [Mockito](http://mockito.org/)
- [DexOpener](https://github.com/tmurakami/dexopener)


## Features

* Welcome Screens with ViewPager
* Login Screen with Account + Sync
* Offline first approach with cache logic
* RecyclerView with dummy data from RestAPI
* Detail Screens with additional data
* Background sync + Notifications
* Homescreen Widget

## Requirements

- JDK 1.8
- Android Studio 3
- [Android SDK](http://developer.android.com/sdk/index.html)
- Android Oreo [(API 27)](http://developer.android.com/tools/revisions/platforms.html)
- Latest Android SDK Tools and build tools.


## Architecture

This project follows Android architecture guidelines that are based on [MVP (Model View Presenter)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter).

![Architecture Diagram](images/architecture_diagram.png)

## Code Quality

This project integrates a combination of unit and functional tests. 

### Tests

To run **unit** tests on your machine:

``` 
./gradlew test
``` 

To run **functional** tests on connected devices:

``` 
./gradlew connectedAndroidTest
``` 

Note: For Android Studio to use syntax highlighting for Automated tests and Unit tests you **must** switch the Build Variant to the desired mode.


## License

```
    Copyright 2018 Dennis Wehrle

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```

## Inspiration & Credits
A special thanks to the authors involved in these repositories:
* https://github.com/googlesamples/android-architecture
* https://github.com/android10/Android-CleanArchitecture
* https://github.com/bufferapp/android-clean-architecture-boilerplate
* https://github.com/ribot/android-boilerplate