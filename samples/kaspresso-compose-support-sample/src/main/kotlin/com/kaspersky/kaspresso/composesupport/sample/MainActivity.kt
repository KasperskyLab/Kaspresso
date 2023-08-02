package com.kaspersky.kaspresso.composesupport.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kaspersky.kaspresso.composesupport.sample.features.flaky.SimpleFlakyScreen
import com.kaspersky.kaspresso.composesupport.sample.features.flaky.SimpleFlakyViewModel
import com.kaspersky.kaspresso.composesupport.sample.features.main.MainScreen
import com.kaspersky.kaspresso.composesupport.sample.features.sanityflaky.SanityFlakyScreen
import com.kaspersky.kaspresso.composesupport.sample.features.sanityflaky.SanityFlakyViewModel
import com.kaspersky.kaspresso.composesupport.sample.features.scroll.ScrollScreen
import com.kaspersky.kaspresso.composesupport.sample.resources.C

class MainActivity : AppCompatActivity() {

    private val simpleFlakyViewModel: SimpleFlakyViewModel by viewModels()
    private val sanityFlakyViewModel: SanityFlakyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartNavigation()
        }
    }

    @Composable
    private fun StartNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = C.Screen.main_screen) {
            composable(C.Screen.main_screen) {
                MainScreen(
                    simpleFlakyClick = { navController.navigate(C.Screen.simple_flaky_screen) },
                    sanityFlakyClick = { navController.navigate(C.Screen.sanity_flaky_screen) },
                    scrollClick = { navController.navigate(C.Screen.scroll_screen) }
                )
            }

            composable(C.Screen.simple_flaky_screen) {
                SimpleFlakyScreen(
                    simpleFlakyStateLiveData = simpleFlakyViewModel.simpleFlakyStateLiveData,
                    firstButtonClick = { simpleFlakyViewModel.firstButtonClick() },
                    secondButtonClick = { simpleFlakyViewModel.secondButtonClick() },
                    editTextChange = { simpleFlakyViewModel.editTextChange(it) }
                )
            }

            composable(C.Screen.scroll_screen) {
                ScrollScreen()
            }

            composable(C.Screen.sanity_flaky_screen) {
                SanityFlakyScreen(sanityFlakyStateLiveData =
                    sanityFlakyViewModel.sanityFlakyStateLiveData, firstButtonClick = { sanityFlakyViewModel.firstButtonClick() })
            }
        }
    }
}
