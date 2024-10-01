# FetchRewardsProject #
This is an Android application built using MVVM Clean Architecture with Jetpack Compose.

>>> NOTE: This is a take-home project for interviwing purposes only. <<<

## Features: ##

- MVVM architecture for clean separation of concerns
- Clean Architecture for independent and testable layers
- Jetpack Compose for a modern and declarative UI
- Hilt for dependency injection
- Hilt ViewModel for simplified ViewModel creation
- Android Navigation for seamless app navigation
- Unit tests for core logic
- Integration tests for remote endpoint interaction

## Requirements: ##

Project requirements (Link is not shared here. See email for details)
Android Studio (latest version recommended)
Java 19 (tested with Azul Zulu 19.0.2)

### Getting Started: ###

1. Clone the repository: `git clone https://github.com/IDMCI/FetchRewardsProject.git`
2. Open the project in Android Studio.
3. Run the app on an Android device or emulator with API level 35 or higher.

## Project Structure: ##

The project is organized following the clean architecture principles, with separate folders for:

- domain: Business logic independent of Android frameworks.
- data: Data access layer (repositories) with abstractions for local and remote data sources.
- di: Dependency injection configuration using Hilt.
- presentation: UI logic with Jetpack Compose and ViewModels.

## Testing: ##

The project includes unit tests for the domain and datasource layers and an integration test for the remote endpoint interaction.

### ScreenShot: ###
![final_list](https://github.com/user-attachments/assets/b3c1730b-cf24-4f57-91bf-6bf794f235d7)
