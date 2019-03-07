package com.kaspersky.uitest_framework.kakao.proxy

import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.model.Evaluation
import com.kaspersky.uitest_framework.kakao.interceptors.AtomInterceptor

class AtomProxy<R>(
        private val atom: Atom<R>,
        private val interceptors: List<AtomInterceptor>
): Atom<R> {

    override fun getArguments(elementContext: ElementReference?): MutableList<Any> {
        return atom.getArguments(elementContext)
    }

    override fun transform(evaluation: Evaluation?): R {

        interceptors.forEach { it.intercept(evaluation) }

        return atom.transform(evaluation)
    }

    override fun getScript(): String = atom.script
}