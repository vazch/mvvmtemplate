# MVVM Android Template (Hilt • Retrofit • Room)

A minimalist starter for Android apps that follow MVVM with a clean, testable structure. It uses Hilt for dependency injection, Retrofit for networking, Room for local storage, and Kotlin coroutines/Flow for asynchronous data and observable DB queries. This template aims to be small, readable, and ready to extend.

## Stack

* Kotlin + Coroutines/Flow for async and streams.
* Hilt for DI.
* Retrofit for REST APIs.
* Room for persistence with `Flow` in DAOs.

## Project structure

```
data/          // Retrofit API, DTOs, Room DB/DAO, repository
domain/        // Use cases, domain models
ui/            // Activities/ViewModels (UiState via StateFlow)
di/            // Hilt modules (Network, Database)
core/          // Small shared helpers (e.g., AppResult, safeApiCall)
```

## How it works

* **Network**: Retrofit interfaces live under `data/network`. Calls are wrapped by a small `AppResult` (Success/Error) via `safeApiCall`, so the rest of the app sees a clean result type.
* **Database**: Room DAOs return `Flow<T>`. Room re-emits when tables change, so the UI updates automatically.
* **Domain**: Use cases expose simple operations (e.g., observe, refresh).
* **UI**: ViewModels expose a single `UiState` as `StateFlow`. Activities/Fragments collect it with lifecycle awareness (e.g., `repeatOnLifecycle`) to avoid leaks and wasted work.


## Setup

1. **Base URL**: Set `BuildConfig.BASE_URL` in your `build.gradle` buildTypes and use it in the Retrofit builder.
2. **Hilt**: Annotate your `Application` with `@HiltAndroidApp` and keep modules in `di/`.
3. **Room**: Define entities and DAOs; prefer `Flow` for reads.
4. **Run**: Sync Gradle and build. The sample feature (quotes) demonstrates end-to-end flow.

## Reusing the template

* Replace the sample **DTO/Entity/Model** trio with your own types.
* Update `QuoteApiClient` and related service calls for your API.
* Keep the **use cases** as the only dependency of your ViewModels and preserve a single `UiState`.
* Add tests around repositories/use cases as you extend.

## References

* Android app architecture & recommendations.
* Coroutines, Flow, and lifecycle-aware collection.
* Room with Flow.
* Hilt (DI).
* Retrofit.