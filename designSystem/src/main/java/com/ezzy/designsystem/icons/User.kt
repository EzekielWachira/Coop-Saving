package com.ezzy.designsystem.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val AppIcons.User: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "User",
        defaultWidth = 42.dp,
        defaultHeight = 42.dp,
        viewportWidth = 42f,
        viewportHeight = 42f
    ).apply {
        path(fill = SolidColor(Color(0xFFF4F4F4))) {
            moveTo(21f, 0f)
            arcTo(21f, 21f, 0f, isMoreThanHalf = true, isPositiveArc = false, 42f, 21f)
            arcTo(21f, 21f, 0f, isMoreThanHalf = false, isPositiveArc = false, 21f, 0f)
            close()
            moveTo(21f, 40.253f)
            arcToRelative(19.223f, 19.223f, 0f, isMoreThanHalf = false, isPositiveArc = true, -14.75f, -6.9f)
            curveToRelative(1.8f, -0.777f, 6.28f, -2.393f, 9.117f, -3.221f)
            curveToRelative(0.222f, -0.071f, 0.263f, -0.081f, 0.263f, -1.08f)
            arcToRelative(5.64f, 5.64f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.384f, -2.383f)
            arcToRelative(12.1f, 12.1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.929f, -3.19f)
            arcToRelative(7.2f, 7.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.373f, -3.322f)
            arcToRelative(4.431f, 4.431f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.04f, -2.787f)
            arcToRelative(1.279f, 1.279f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.061f, -0.182f)
            arcToRelative(18.193f, 18.193f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.313f, -3.917f)
            arcTo(7.183f, 7.183f, 0f, isMoreThanHalf = false, isPositiveArc = true, 14.246f, 8.1f)
            arcTo(7.688f, 7.688f, 0f, isMoreThanHalf = false, isPositiveArc = true, 20.1f, 5.27f)
            horizontalLineToRelative(1.767f)
            arcTo(7.717f, 7.717f, 0f, isMoreThanHalf = false, isPositiveArc = true, 27.775f, 8.1f)
            arcToRelative(7.118f, 7.118f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1.5f, 5.179f)
            arcToRelative(18.156f, 18.156f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.313f, 3.917f)
            arcToRelative(0.858f, 0.858f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.061f, 0.172f)
            arcToRelative(4.356f, 4.356f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.04f, 2.787f)
            arcToRelative(7.2f, 7.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1.373f, 3.322f)
            arcToRelative(12.22f, 12.22f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.929f, 3.19f)
            arcToRelative(5.658f, 5.658f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.666f, 2.352f)
            curveToRelative(0f, 1f, 0.04f, 1.01f, 0.273f, 1.08f)
            curveToRelative(2.7f, 0.8f, 7.34f, 2.4f, 9.389f, 3.241f)
            arcTo(19.213f, 19.213f, 0f, isMoreThanHalf = false, isPositiveArc = true, 21f, 40.253f)
            close()
        }
    }.build()
}
