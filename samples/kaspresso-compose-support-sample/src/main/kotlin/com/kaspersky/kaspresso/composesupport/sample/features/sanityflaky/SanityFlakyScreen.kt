package com.kaspersky.kaspresso.composesupport.sample.features.sanityflaky

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kaspersky.kaspresso.composesupport.sample.R
import com.kaspersky.kaspresso.composesupport.sample.resources.C

@Composable
fun SanityFlakyScreen(
    sanityFlakyStateLiveData: LiveData<SanityFlakyState>,
    firstButtonClick: () -> Unit
) {
    val sanityFlakyState: SanityFlakyState = sanityFlakyStateLiveData.observeAsState().value!!

    Column(
        modifier = Modifier
            .padding(8.dp)
            .semantics { testTag = C.Tag.sanity_flaky_screen_container }
    ) {
        if (sanityFlakyState.firstButtonVisibility) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { testTag = C.Tag.sanity_flaky_screen_simple_first_button },
                content = {
                    Text(text = stringResource(id = R.string.simple_flaky_screen_first_button))
                },
                onClick = firstButtonClick,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (sanityFlakyState.secondButtonVisibility) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { testTag = C.Tag.sanity_flaky_screen_simple_second_button },
                content = {
                    Text(text = stringResource(id = R.string.simple_flaky_screen_second_button))
                },
                onClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun MainScreenPreview() {
    MaterialTheme {
        SanityFlakyScreen(
            sanityFlakyStateLiveData = MutableLiveData(
                SanityFlakyState(
                    firstButtonVisibility = true,
                    secondButtonVisibility = true
                )
            ),
            firstButtonClick = { }
        )
    }
}
