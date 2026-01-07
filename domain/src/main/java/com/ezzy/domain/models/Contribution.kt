package com.ezzy.domain.models

import java.time.LocalDate

data class Contribution(
    val id: Long,
    val amount: Double,
    val date: LocalDate
)