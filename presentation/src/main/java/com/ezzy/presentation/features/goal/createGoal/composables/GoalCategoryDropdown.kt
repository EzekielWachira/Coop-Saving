package com.ezzy.presentation.features.goal.createGoal.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ezzy.designsystem.theme.CoopSavingTheme
import com.ezzy.domain.enums.GoalCategory

@Composable
fun GoalCategoryDropdown(
    selected: GoalCategory?,
    onSelected: (GoalCategory) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = selected?.name ?: "",
        onValueChange = {},
        readOnly = true,
        label = { Text("Goal Category") },
        trailingIcon = {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.ArrowDropDown, null)
            }
        }
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        GoalCategory.entries.forEach { category ->
            DropdownMenuItem(
                text = { Text(category.name) },
                onClick = {
                    expanded = false
                    onSelected(category)
                }
            )
        }
    }
}

@Preview
@Composable
private fun GoalCategoryDropdownPreview() {
    CoopSavingTheme {
        GoalCategoryDropdown(
            selected = GoalCategory.EMERGENCY
        )
    }
}