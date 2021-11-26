package com.kaspersky.kaspresso.composesupport.sample.features.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun MainScreen(
    simpleFlakyClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .semantics { testTag = C.Tag.main_screen_container }
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { testTag = C.Tag.main_screen_simple_flaky_button },
            content = {
                Text(text = stringResource(id = R.string.main_screen_simple_flaky_button))
            },
            onClick = simpleFlakyClick,
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun MainScreenPreview() {
    MaterialTheme {
        MainScreen(
            simpleFlakyClick = { }
        )
    }
}
