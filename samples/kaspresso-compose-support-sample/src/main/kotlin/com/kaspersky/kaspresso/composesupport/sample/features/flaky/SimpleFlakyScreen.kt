package com.kaspersky.kaspresso.composesupport.sample.features.flaky

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
import androidx.compose.material.TextField
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
fun SimpleFlakyScreen(
    simpleFlakyStateLiveData: LiveData<SimpleFlakyState>,
    firstButtonClick: () -> Unit,
    secondButtonClick: () -> Unit,
    editTextChange: (String) -> Unit,
) {
    val simpleFlakyState: SimpleFlakyState = simpleFlakyStateLiveData.observeAsState().value!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
            .semantics { testTag = C.Tag.simple_flaky_screen_container }
    ) {
        Spacer(modifier = Modifier.height(1000.dp))

        if (simpleFlakyState.firstButtonVisibility) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { testTag = C.Tag.simple_flaky_screen_simple_first_button },
                content = {
                    Text(text = stringResource(id = R.string.simple_flaky_screen_first_button))
                },
                onClick = firstButtonClick,
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        if (simpleFlakyState.secondButtonVisibility) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { testTag = C.Tag.simple_flaky_screen_simple_second_button },
                content = {
                    Text(text = stringResource(id = R.string.simple_flaky_screen_second_button))
                },
                onClick = secondButtonClick,
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        if (simpleFlakyState.editTextVisibility) {
            TextField(
                value = simpleFlakyState.editText,
                onValueChange = editTextChange,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { testTag = C.Tag.simple_flaky_screen_simple_edittext }
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun MainScreenPreview() {
    MaterialTheme {
        SimpleFlakyScreen(
            simpleFlakyStateLiveData = MutableLiveData(
                SimpleFlakyState(
                    firstButtonVisibility = true,
                    secondButtonVisibility = true,
                    editTextVisibility = true,
                    editText = "Some text"
                )
            ),
            firstButtonClick = { },
            secondButtonClick = { },
            editTextChange = { }
        )
    }
}
