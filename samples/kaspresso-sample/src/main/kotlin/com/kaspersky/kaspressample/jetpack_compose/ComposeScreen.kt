package com.kaspersky.kaspressample.jetpack_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .semantics { testTag = "MainScreen" }
    ) {
        Text(
            text = "Simple text 1",
            modifier = Modifier
                .padding(8.dp)
                .semantics { testTag = "mySimpleText" }
        )

        Text(
            text = "Simple text 2",
            modifier = Modifier
                .padding(8.dp)
                .semantics { testTag = "mySimpleText" }
        )

        Button(
            content = {
                Text(text = "Button 1")
            },
            onClick = {
                // nothing
            },
            modifier = Modifier
                .padding(8.dp)
                .semantics { testTag = "myTestButton" }
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun MainScreenPreview() {
    MaterialTheme {
        ComposeScreen()
    }
}
