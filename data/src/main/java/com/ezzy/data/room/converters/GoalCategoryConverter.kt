package com.ezzy.data.room.converters

import androidx.room.TypeConverter
import com.ezzy.domain.enums.GoalCategory

class GoalCategoryConverter {

    @TypeConverter
    fun fromCategory(category: GoalCategory): String =
        category.name

    @TypeConverter
    fun toCategory(value: String): GoalCategory =
        runCatching { GoalCategory.valueOf(value) }
            .getOrDefault(GoalCategory.KIDS)
}