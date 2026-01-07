package com.ezzy.presentation.mviSetUp

class ConsumableEvent<out T>(private val content: T) {
    private var isConsumed = false

    fun consume(): T? {
        return if (isConsumed) null else {
            isConsumed = true
            content
        }
    }
}