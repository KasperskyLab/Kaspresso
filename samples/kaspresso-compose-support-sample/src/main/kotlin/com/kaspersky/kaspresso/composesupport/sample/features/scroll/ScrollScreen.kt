package com.kaspersky.kaspresso.composesupport.sample.features.scroll

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaspersky.kaspresso.composesupport.sample.R
import com.kaspersky.kaspresso.composesupport.sample.resources.C

@Composable
fun ScrollScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
            .semantics { testTag = C.Tag.scroll_screen_container }
    ) {
        C.Tag.scroll_screen_buttons.forEach {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { testTag = it },
                content = {
                    Text(text = stringResource(id = R.string.scroll_screen_button))
                }, onClick = {}
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun ScrollScreenPreview() {
    MaterialTheme {
        ScrollScreen()
    }
}
