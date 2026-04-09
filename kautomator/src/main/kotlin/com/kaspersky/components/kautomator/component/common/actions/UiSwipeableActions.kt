@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.common.actions

import androidx.test.uiautomator.Direction
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType

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

/**
 * Provides swipeable actions for UiSwipeView
 */
interface UiSwipeableActions : UiBaseActions {

    /**
     * Swipes left on the view
     * @param percent The length of the swipe as a percentage of this object's size.
     */
    fun swipeLeft(percent: Float = 0.95f) {
        view.perform(UiSwipeableActionType.SWIPE_LEFT) { swipe(Direction.LEFT, percent) }
    }

    /**
     * Swipes right on the view
     * @param percent The length of the swipe as a percentage of this object's size.
     */
    fun swipeRight(percent: Float = 0.95f) {
        view.perform(UiSwipeableActionType.SWIPE_RIGHT) { swipe(Direction.RIGHT, percent) }
    }

    /**
     * Swipes up on the view
     * @param percent The length of the swipe as a percentage of this object's size.
     */
    fun swipeUp(percent: Float = 0.95f) {
        view.perform(UiSwipeableActionType.SWIPE_UP) { swipe(Direction.UP, percent) }
    }

    /**
     * Swipes down on the view
     * @param percent The length of the swipe as a percentage of this object's size.
     */
    fun swipeDown(percent: Float = 0.95f) {
        view.perform(UiSwipeableActionType.SWIPE_DOWN) { swipe(Direction.DOWN, percent) }
    }

    enum class UiSwipeableActionType : UiOperationType {
        SWIPE_LEFT,
        SWIPE_RIGHT,
        SWIPE_UP,
        SWIPE_DOWN
    }
}
