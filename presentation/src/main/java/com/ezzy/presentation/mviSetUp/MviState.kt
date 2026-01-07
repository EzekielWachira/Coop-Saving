package com.ezzy.presentation.mviSetUp

/**
 * Marker interface representing the UI State in the MVI architecture.
 *
 * A State describes the **entire UI state** for a given screen at any moment in time.
 * It must be:
 *
 * - **Immutable**: every update creates a new instance
 * - **Serializable (optional)**: useful for saving/restoring screen state
 * - **Complete**: contains everything needed to render the UI with no additional queries
 *
 * States are produced exclusively by reducers in the ViewModel.
 * The UI observes a `StateFlow<State>` and re-renders whenever the state changes.
 *
 * Example:
 * ```
 * data class LoginState(
 *     val email: String = "",
 *     val password: String = "",
 *     val isLoading: Boolean = false,
 *     val error: String? = null
 * ) : MviState
 * ```
 *
 * In summary:
 * - **State = the "single source of truth" of the UI**
 * - **UI only reads state — never mutates it**
 * - **Reducers create new states based on Actions**
 */
interface MviState