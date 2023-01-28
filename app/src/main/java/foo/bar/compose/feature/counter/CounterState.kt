package foo.bar.compose.feature.counter

import kotlinx.serialization.*

/**
 * Copyright © 2015-2023 early.co. All rights reserved.
 */

private const val MAX_AMOUNT = 9

@Serializable
data class CounterState(
    val amount: Int = 5,
    @Transient
    val loading: Boolean = false,
) {
    fun canIncrease(): Boolean = !loading && amount < MAX_AMOUNT
    fun canDecrease(): Boolean = !loading && amount > 0
}
