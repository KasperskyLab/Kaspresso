package com.kaspersky.kaspressample.screen

import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.sharedtest.SharedTestActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton
import java.util.*
import kotlin.collections.ArrayDeque

object SharedTestScreen : KScreen<SharedTestScreen>() {

    override val layoutId: Int = R.layout.activity_sharedtest
    override val viewClass: Class<*> = SharedTestActivity::class.java

    val findMeButton = KButton { withId(R.id.findMeButton) }

    val firstNameEditText = KEditText { withId(R.id.firstName) }
    val lastNameEditText = KEditText { withId(R.id.lastName) }
    val ageEditText = KEditText { withId(R.id.age) }

    val maleButton = KButton { withId(R.id.male) }
}

class Solution {
    fun findAnagrams(s: String, p: String): List<Int> {
        val result = mutableListOf<Int>()

        var leftIndex = 0
        var rightIndex = 0
        val origMap = hashMapOf<Char, Int>()
        p.toCharArray().forEach {
            origMap[it] = (origMap[it] ?: 0) + 1
        }
        var tempMap = origMap.toMutableMap()

        while (leftIndex < s.length && rightIndex < s.length) {
            val letter = s[rightIndex]
            if (!tempMap.contains(letter)) {
                leftIndex = rightIndex + 1
                rightIndex += 1
                tempMap = origMap.toMutableMap()
                continue
            }

            tempMap[letter] = tempMap[letter]!! - 1
            while (tempMap[letter]!! < 0) {
                val leftLetter = s[leftIndex]
                tempMap[leftLetter] = tempMap[leftLetter]!! + 1
                leftIndex++
            }
            if (rightIndex - leftIndex + 1 == p.length) result.add(leftIndex)
            rightIndex++
        }

        return result
    }
}

fun main() {
    val result = Solution().findAnagrams("cbaebabacd", "abc")
    val stack: Deque<Char> = java.util.ArrayDeque()
    val proxy = StringBuilder()
    val map = hashMapOf<String, Int>()
    map.forEach { s, i ->
    }
    proxy.append("s")
    proxy.deleteCharAt(proxy.lastIndex)
    println(result)
}
