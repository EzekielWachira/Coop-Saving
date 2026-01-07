package com.ezzy.data.room.mappers

import com.ezzy.data.room.entities.ContributionEntity
import com.ezzy.domain.models.Contribution
import java.time.Instant
import java.time.ZoneId

fun ContributionEntity.toDomain(): Contribution =
    Contribution(
        id = id,
        amount = amount,
        date = Instant.ofEpochMilli(date)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    )