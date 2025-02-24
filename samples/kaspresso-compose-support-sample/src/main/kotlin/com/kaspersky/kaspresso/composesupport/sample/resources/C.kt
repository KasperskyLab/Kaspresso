package com.kaspersky.kaspresso.composesupport.sample.resources

// Like R, but C
object C {
    object Tag {
        const val main_screen_container = "main_screen_container"
        const val main_screen_simple_flaky_button = "main_screen_simple_flaky_button"
        const val main_screen_scroll_button = "main_screen_scroll_button"
        const val main_screen_sanity_flaky_button = "main_screen_sanity_flaky_button"
        const val main_screen_lazy_list_button = "main_screen_lazy_list_button"

        const val simple_flaky_screen_container = "simple_flaky_screen_container"
        const val simple_flaky_screen_simple_first_button = "simple_flaky_screen_simple_first_button"
        const val simple_flaky_screen_simple_second_button = "simple_flaky_screen_simple_second_button"
        const val simple_flaky_screen_simple_edittext = "simple_flaky_screen_simple_edittext"

        const val sanity_flaky_screen_container = "sanity_flaky_screen_container"
        const val sanity_flaky_screen_simple_first_button = "sanity_flaky_screen_simple_first_button"
        const val sanity_flaky_screen_simple_second_button = "sanity_flaky_screen_simple_second_button"

        const val scroll_screen_container = "scroll_screen_container"
        const val scroll_screen_multi_text = "scroll_screen_multi_text"
        const val scroll_screen_multi_text_list = "scroll_screen_multi_text_list"

        const val multi_text_text1 = "multi_text_text1"
        const val multi_text_text2 = "multi_text_text2"
        const val multi_text_text3 = "multi_text_text3"

        val scroll_screen_buttons = MutableList(30) {
            "button_${it}"
        }

        val scroll_screen_multi_text_items =  MutableList(30) {
            MultiTextTestData( "text1_${it}", "text2_${it}", "text3_${it}")
        }
    }

    data class MultiTextTestData(
        val text1: String,
        val text2: String,
        val text3: String
    )

    object Screen {
        const val main_screen = "main_screen"
        const val simple_flaky_screen = "simple_flaky_screen"
        const val sanity_flaky_screen = "sanity_flaky_screen"
        const val scroll_screen = "scroll_screen"
        const val lazy_list = "lazy_list"
    }
}
