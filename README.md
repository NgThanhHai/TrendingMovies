# An App with some features to view movie information from TMDB
## Prerequisites
Create the `local.properties` file in the App's Module folder. This file contains your API key, Image base URL and API base URL endpoint. Example:
```
API_KEY=47aa75b56464da7a186b813xxxxxxxxx
BASE_URL=https://api.themoviedb.org/3/
IMAGE_URL=https://image.tmdb.org/t/p/w500
```
## Screenshots Demo
<img src="https://github.com/NgThanhHai/TrendingMovies/blob/main/Screenshot_20250622_161830.png" width="280" /> <img src="https://github.com/NgThanhHai/TrendingMovies/blob/main/Screenshot_20250622_161842.png" width="280" />

## Architecture
- KMP - Kotlin Multiplatform is a Kotlin technology that allows sharing logic, business rules, models across multiple platforms while maintaining platform specific code for UI, APIs or other platform specific works.
- Shared Code base for Domain & Data layer
- Presentation Layer is developed in native language (Compose for Android, SwiftUI for iOS (later) )

## Tech Stack
- Jetpack Compose - Jetpack Compose is a modern toolkit for building native Android UI, enabling developers to create beautiful and responsive applications using a declarative and intuitive Kotlin-based API.
- Kotlin - Officially supported programming language for Android development.
- Kotlin Multiplatform
- MVVM - MVVM (Model-View-ViewModel) is a software architectural pattern that separates the user interface (View), business logic (Model), and the UI logic (ViewModel) to enhance the modularity, testability, and maintainability of the application.
- Hilt - Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project
- Coroutines - Coroutines are a concurrency design pattern in Kotlin that simplify asynchronous programming by allowing you to write code in a sequential manner while performing non-blocking operations.
- RoomDB - For local storage & caching data.
- Android Architecture Components -Android Architecture Components are a set of libraries provided by Google that help you design robust, testable, and maintainable Android applications.
- Flow - provides a type-safe way to work with asynchronous sequences of values
- Ktor Client - Ktor Client is a Kotlin Library which supports making asynchronous HTTP client, includes make requests and handle responses
- GlideImage - Glide is a library that support load image with URL. It supports fetching, decoding, caching, display images, videos, animated GIFs.
- Cloudy - Cloudy supports blur everything, it is helpful since Modifier.blur() supports only for android above 12.

SDK levels supported
--------------------
- Minimum SDK 24
- Target SDK 35