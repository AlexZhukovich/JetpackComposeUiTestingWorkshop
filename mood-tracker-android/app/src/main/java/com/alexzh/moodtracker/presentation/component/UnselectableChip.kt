package com.alexzh.moodtracker.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UnselectableChip(
    modifier: Modifier = Modifier,
    text: String,
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier.padding(end = 4.dp, bottom = 4.dp),
        color = Color.Transparent,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}

@Preview
@Composable
fun Preview_UnselectableChip() {
    LazyRow {
        items((1..3).toList()) {
            UnselectableChip(
                text = "Category $it"
            )
        }
    }
}