# Goals & Savings Android App

An Android application for creating savings goals, tracking contributions, and monitoring progress.
Built using **Jetpack Compose**, **MVVM + MVI**, **Room**, and **Clean Architecture** principles.

> **Note:** This project is not yet complete due to time constraints. Please complete the remaining features and improvements at a later time.

---

## How to Run the App

### Prerequisites

- Android Studio Hedgehog or newer
- JDK 17
- Android SDK 24+
- Kotlin 2.x

### Steps

1. Clone the repository:
```bash
   git clone <repository-url>
```

2. Open the project in Android Studio

3. Allow Gradle to sync and download dependencies

4. Run the app on:
    - An Android emulator
    - A physical Android device

No environment variables or additional setup are required.

---

## Architecture Overview

The project follows Clean Architecture combined with a hybrid **MVVM + MVI** pattern and a feature-based UI structure.

### Modules & Responsibilities

#### Presentation Layer

- Built with Jetpack Compose
- Feature-based structure:
    - goal
    - home
    - deposit
    - withdraw
- Uses MVVM + MVI hybrid approach:
    - **ViewModels** – manage UI logic and business logic coordination (MVVM)
    - **State** – single source of truth for UI (MVI)
    - **Action** – user intent (MVI)
    - **Event** – one-off effects (navigation, toasts) (MVI)
- ViewModels depend only on domain interfaces

#### Domain Layer

- Pure Kotlin module
- Contains:
    - Domain models (Goal, Contribution)
    - Repository interfaces
    - Business rules
- No Android framework dependencies

#### Data Layer

- Implements repository interfaces
- Uses Room for local persistence
- Handles:
    - Entity ↔ Domain mapping
    - Data composition using Flow
    - Aggregation queries to avoid N+1 problems

#### Design System

- Centralized UI styling
- Includes:
    - Colors
    - Typography
    - Reusable UI components
- Ensures a consistent look and feel across the app

---

## Assumptions Made

- The app is offline-first
- Only local storage is used (no backend integration)
- A single currency (KES) is supported
- Contributions are treated as positive values
- A goal is considered completed when:
    - `totalSaved >= targetAmount`
- Only active goals are shown by default
- Authentication and user accounts are out of scope

---

## Trade-offs and Limitations

### Trade-offs

- **MVVM + MVI hybrid approach**
    - Combines the familiarity of MVVM with the predictable state management of MVI
    - Provides clear separation of concerns while maintaining unidirectional data flow
    - Adds some boilerplate but improves testability and maintainability

- **Room-only persistence**
    - Simplifies the app and keeps scope focused, but limits multi-device sync.

- **Material 3 BottomSheet APIs**
    - Some UI components rely on experimental APIs.

### Limitations

- No pagination for goals or transactions
- No advanced transaction type handling
- No remote sync or backup
- No encryption for local database
- Limited edge-case error handling
- Unit tests are not included (architecture is test-ready)
- **Incomplete features due to time constraints** – additional work required

---

## Future Work

Due to time allocation constraints, the following areas need completion:

- Complete all pending features and UI screens
- Add comprehensive unit and integration tests
- Implement error handling for edge cases
- Add data validation and input sanitization
- Optimize performance and user experience
- Complete documentation for all modules

**Please allocate time to complete these remaining tasks.**