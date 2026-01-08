package com.ezzy.presentation.mviSetUp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezzy.presentation.mviSetUp.ConsumableEvent
import com.ezzy.presentation.mviSetUp.MviAction
import com.ezzy.presentation.mviSetUp.MviEvent
import com.ezzy.presentation.mviSetUp.MviState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

/**
 * Base ViewModel implementation for MVI (Model–View–Intent) pattern.
 *
 * This class coordinates three core components:
 *
 * - **State**: A single immutable UI state represented as a [StateFlow].
 * - **Action**: User or system intents that request a state transition.
 * - **Event**: One-time UI effects such as navigation or showing a toast/snackbar.
 *
 * It enforces a **unidirectional data flow**:
 *
 *     UI → Action → Reducer → State → UI
 *
 * and provides a structured mechanism for registering reducers, updating state,
 * and sending UI events.
 *
 * @param State The UI state type, extending [MviState].
 * @param Action The actions/intents the ViewModel can handle, extending [MviAction].
 * @param Event The one-off UI effects, extending [MviEvent].
 * @param initialState The starting state of the ViewModel.
 */
abstract class BaseMviViewModel<State : MviState, Action : MviAction, Event : MviEvent>(
    initialState: State
) : ViewModel() {

    /**
     * Internal mutable state holder. The state is updated immutably using reducers.
     */
    private val _state = MutableStateFlow(initialState)

    /**
     * Public read-only stream of UI state.
     *
     * Observed by the UI layer (Compose/Fragment) to render the current state.
     */
    val state: StateFlow<State> = _state.asStateFlow()

    /**
     * Internal buffered channel for sending one-off UI events
     * such as navigation or dialogs.
     */
    private val _events = Channel<ConsumableEvent<Event>>(Channel.BUFFERED)

    /**
     * Public Flow of UI events, exposed to the UI layer.
     *
     * Collected in a lifecycle-aware manner to handle events exactly once.
     */
    val events: Flow<ConsumableEvent<Event>> = _events.receiveAsFlow()

    /**
     * Convenience accessor for the current UI state.
     *
     * Always returns the **latest** state from the StateFlow.
     */
    protected val currentState: State
        get() = _state.value

    /**
     * Map storing reducers for each action type.
     *
     * A reducer is a function that takes the current state and an action,
     * and returns a new state.
     */
    private val reducers: MutableMap<KClass<out Action>, State.(Action) -> State> = mutableMapOf()

    override fun onCleared() {
        super.onCleared()
        _events.close()
    }

    /**
     * Registers a reducer function for a specific [Action] type.
     *
     * @param actionClass The KClass of the action to handle.
     * @param reducer A pure reducer function that produces a new state from the old state.
     */
    protected fun <A : Action> registerReducer(
        actionClass: KClass<A>,
        reducer: State.(A) -> State
    ) {
        reducers[actionClass] = { action ->
            @Suppress("UNCHECKED_CAST")
            reducer(this, action as A)
        }
    }

    /**
     * Dispatches an [Action] to the ViewModel.
     *
     * The corresponding reducer (if registered) is executed to transform the state.
     * If no reducer is found, [onUnhandledAction] is invoked.
     *
     * @param action The action to process.
     */
    fun dispatch(action: Action) {
        val reducer = reducers[action::class]

        if (reducer != null) {
            reduce { reducer(this, action) }
        } else {
            onUnhandledAction(action)
        }
    }

    /**
     * Called when an action is dispatched but no reducer has been registered for it.
     *
     * Override this to handle actions that trigger **side-effects only**,
     * such as API calls, navigation decisions, or analytics.
     */
    protected open fun onUnhandledAction(action: Action) {}

    /**
     * Updates the state using an immutable reducer block.
     *
     * This guarantees state is changed predictably with a single, atomic update.
     *
     * @param block Lambda that transforms the previous state into a new state.
     */
    protected fun reduce(block: State.() -> State) {
        _state.update { old -> block(old) }
    }

    /**
     * Sends a one-time UI [Event] to be collected by the UI layer.
     *
     * Events are sent through a buffered channel to avoid loss during rapid emissions.
     *
     * @param event The UI event to emit.
     */
    protected fun sendEvent(event: Event) {
        viewModelScope.launch {
            _events.send(ConsumableEvent(event))
        }
    }
}