package com.ezzy.data.room.converters

import androidx.room.TypeConverter
import com.ezzy.domain.enums.GoalStatus

class GoalStatusConverter {

    @TypeConverter
    fun fromStatus(status: GoalStatus): String =
        status.name

    @TypeConverter
    fun toStatus(value: String): GoalStatus =
        runCatching { GoalStatus.valueOf(value) }
            .getOrDefault(GoalStatus.ACTIVE)
}