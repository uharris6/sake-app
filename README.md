# SAKE App 
Sake App is an Android app that fetch a list of Sake restaurants in Japan, you can see the rating of the restaurant.
If you press the items of list you will navigate to the detail screen of the restaurant, in this screen
you could know the address of the sake restaurant, description, also it will let you navigate to maps and to the
website of the restaurant.
Built with Jetpack Compose, Kotlin, Koin, and Clean architecture — fully local, no backend, with MVVM.

---

## 📱 Features

- List of Sake restaurant with image, name and rating.
- Detail Screen with more information as, description, address and website.
- Redirection to Google Maps
- Redirection to Browser with website.

---

## 🚀 Requirements

| Tool                   | Version     |
|------------------------|-------------|
| Android Studio         | **Narwhal** |
| Gradle                 | **8.13**    |
| Android Gradle Plugin | **8.11**    |
| Java                   | **17**      |
| Compile SDK            | **36**      |
| Minimum SDK            | **28**      |
| Target SDK             | **36**      |

---

## 📦 Dependencies

- **Jetpack Compose**
- **AndroidX Libraries** (`core-ktx`, `navigation-compose`, `material3`, etc.)
- **Koin** for DI
- **Kotlinx Serialization** for JSON
- **Coil 3** for image loading
- **JUnit, Espresso, MockK, Turbine** for testing

Full dependency versions managed via **Version Catalog** (`libs.versions.toml`).

---

## 🛠️ Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/uharris6/sake-app.git
   cd sake-app
   ```
2. **Open in Android Studio:**

 - Use Android Studio Giraffe or newer
 - Make sure JDK 17 is configured
 - Let Gradle sync and download dependencies

---

## ▶️ Run the App
To build and install the debug version:

   ```bash
   ./gradlew assembleDebug
   ```
Or run directly from Android Studio using the Run button.

---

## 🧪 Run Tests
To run unit test
   ```bash
   ./gradlew test
   ```

To run Android Test
   ```bash
   ./gradlew connectedAndroidTest
   ```

---

## Architecture
Sake app implements clean architecture without use cases due to the small it is, that why it has the next layers:
 - Data Layer:  Is composed by the implementation of the Service and the DTO of the Shop. To get the JSON we decide to 
use the assets folder because of more easy access. Finally for serialization, the decision was to use Kotlin 
Serialization to keep using Kotlin instead advantages.
 - Domain Layer: It has the Service interface with the fetch and detail methods and the UI Data to send clean data to the
UI layer.
 - UI Layer: Finally the UI Layer is the ViewModel with states flows that communicate the data to the Compose component.

To get the detail information we are saving the list of Sake Restaurants in the Service Implementation 
because it is a Singleton.

Also was decided to user Koin as DI because of the easy implementation.

Finally in the UI Layer we implement Navigation compose to have a better and easy navigation between Screens.

---

## TODOs
- If in the future the app starts to grow in more functionalities, like search, filter, buy, profile, etc, it will be time to
star creating use cases to be re-use in different features.

- Add retrofit, ktor or graphql when the project starts to grow and a backend is implemented. With that in mind
it will be good to add a local database to show information even if the user not have internet.

- We are not a fan of Android Navigation Compose 2, so it will be good to migrate to Navigation3 when it is stable, because
of all the new advantages (mostly of usability and the readability) that will add to new projects.

- Finally, it will be good implement a linter like Ktlint or detekt to keep a good architect and the same parameters
of coding in the team, this could be complemented with a pre-commit hook to avoid pushing or commiting code without
test and lint verification. All of this could be also implemented as a CI/CD Job with github actions or Bitrise.
