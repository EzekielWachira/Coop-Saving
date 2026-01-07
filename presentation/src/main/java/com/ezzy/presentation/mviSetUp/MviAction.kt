package com.ezzy.presentation.mviSetUp
/**
 * Marker interface representing an Action (or Intent) in the MVI architecture.
 *
 * Actions describe **what the user or system wants to do**, but they do not contain
 * any business logic themselves. They are simply messages expressing intent.
 *
 * Examples of actions:
 * - User clicks a button → `LoginAction.SubmitCredentials`
 * - Screen initializes → `HomeAction.LoadInitialData`
 * - Retry pressed → `QuoteAction.RetryFetch`
 *
 * Actions are consumed by reducers inside the ViewModel, where they are translated
 * into new state or trigger side effects.
 *
 * In summary:
 * - **UI → dispatch(Action)**
 * - ViewModel receives the Action
 * - A reducer transforms it into new state
 *
 * All action types used by a screen should implement this interface.
 */
interface MviAction