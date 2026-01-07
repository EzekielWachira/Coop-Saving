package com.ezzy.presentation.mviSetUp
/**
 * Marker interface representing a one-time UI event in the MVI architecture.
 *
 * Events are used for actions that:
 * - Should NOT be persisted in state
 * - Should happen only once
 * - Should not replay when configuration changes (rotation, recomposition)
 *
 * Common examples:
 * - Navigation actions
 * - Showing a toast or snackbar
 * - Displaying a dialog
 * - Opening external apps (browser, email, etc.)
 *
 * Events are exposed as a `Flow<Event>` from the ViewModel and collected in the UI.
 *
 * Example:
 * ```
 * sealed interface LoginEvent : MviEvent {
 *     data class ShowError(val message: String) : LoginEvent
 *     data object NavigateToHome : LoginEvent
 * }
 * ```
 *
 * Why use Events?
 * - Avoid polluting the State with one-off actions
 * - Prevents replaying effects on recomposition
 * - Aligns with unidirectional data flow principles
 */
interface MviEvent