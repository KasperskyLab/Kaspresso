package com.kaspersky.kaspresso.composesupport.sample.resources

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

// Like R, but C
object C {
    object Tag {
        const val main_screen_container = "main_screen_container"
        const val main_screen_simple_flaky_button = "main_screen_simple_flaky_button"
        const val main_screen_scroll_button = "main_screen_scroll_button"
        const val main_screen_sanity_flaky_button = "main_screen_sanity_flaky_button"

        const val simple_flaky_screen_container = "simple_flaky_screen_container"
        const val simple_flaky_screen_simple_first_button = "simple_flaky_screen_simple_first_button"
        const val simple_flaky_screen_simple_second_button = "simple_flaky_screen_simple_second_button"
        const val simple_flaky_screen_simple_edittext = "simple_flaky_screen_simple_edittext"

        const val sanity_flaky_screen_container = "sanity_flaky_screen_container"
        const val sanity_flaky_screen_simple_first_button = "sanity_flaky_screen_simple_first_button"
        const val sanity_flaky_screen_simple_second_button = "sanity_flaky_screen_simple_second_button"

        const val scroll_screen_container = "scroll_screen_container"
        val scroll_screen_buttons = MutableList(30) {
            "button_${it}"
        }
    }

    object Screen {
        const val main_screen = "main_screen"
        const val simple_flaky_screen = "simple_flaky_screen"
        const val sanity_flaky_screen = "sanity_flaky_screen"
        const val scroll_screen = "scroll_screen"
    }
}
