package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.extensions.other.getException
import com.kaspersky.kaspresso.interceptors.TestRunInterceptor
import com.kaspersky.kaspresso.interceptors.impl.composite.TestRunCompositeInterceptor
import com.kaspersky.kaspresso.testcases.models.RunMainTestSectionResult
import com.kaspersky.kaspresso.testcases.models.TestBody
import com.kaspersky.kaspresso.testcases.models.TestInfo

internal class TestRunner<BeforeSectionData, MainSectionData> {

    fun run(testBody: TestBody<BeforeSectionData, MainSectionData>) {
        val exceptions: MutableList<Throwable> = mutableListOf()
        val testRunInterceptor: TestRunInterceptor =
            TestRunCompositeInterceptor(
                Configurator.testRunInterceptors,
                exceptions
            )
        val stepsManager = StepsManager(testBody.testName)
        var currentTestInfo = TestInfo(testBody.testName)
        var testPassed = true
        val resultException: Throwable?

        try {
            testRunInterceptor.onTestStarted(currentTestInfo)

            val mainSectionData = runBeforeTestSection(
                currentTestInfo,
                testBody.beforeTestActions,
                testBody.initialisationSection,
                testBody.transformationsList,
                testRunInterceptor,
                testBody.mainDataProducer
            )

            val runMainTestSectionResult = runMainTestSection(
                currentTestInfo,
                testBody.mainSection,
                testRunInterceptor,
                stepsManager,
                mainSectionData
            )
            currentTestInfo = runMainTestSectionResult.testInfo
            runMainTestSectionResult.throwable?.let { throw it }
        } catch (e: Throwable) {
            testPassed = false
            exceptions.add(e)
        } finally {
            try {
                runAfterTestSection(currentTestInfo, testBody.afterTestActions, testRunInterceptor)
            } catch (e: Throwable) {
                testPassed = false
                exceptions.add(e)
            } finally {
                resultException = exceptions.getException()
                currentTestInfo = currentTestInfo.copy(
                    throwable = resultException
                )
                testRunInterceptor.onTestFinished(currentTestInfo, testPassed)
            }
        }

        resultException?.let { throw it }
    }

    private fun runBeforeTestSection(
        currentTestInfo: TestInfo,
        beforeTestActions: () -> Unit,
        initialisation: (BeforeSectionData.() -> Unit)?,
        dataTransformationList: List<MainSectionData.() -> Unit>,
        testRunInterceptor: TestRunInterceptor,
        mainDataProducer: ((BeforeSectionData.() -> Unit)?) -> MainSectionData
    ): MainSectionData {
        try {
            testRunInterceptor.onBeforeSectionStarted(currentTestInfo)
            beforeTestActions.invoke()
            val mainData = mainDataProducer.invoke(initialisation)
            for (transformation in dataTransformationList) {
                transformation.invoke(mainData)
            }
            testRunInterceptor.onBeforeSectionFinishedSuccess(currentTestInfo)
            return mainData
        } catch (e: Throwable) {
            testRunInterceptor.onBeforeSectionFinishedFailed(currentTestInfo, e)
            throw e
        }
    }

    private fun runMainTestSection(
        currentTestInfo: TestInfo,
        mainSection: TestContext<MainSectionData>.() -> Unit,
        testRunInterceptor: TestRunInterceptor,
        stepsManager: StepsManager,
        mainSectionData: MainSectionData
    ): RunMainTestSectionResult {
        var runMainTestSectionResult: RunMainTestSectionResult
        checkTestInfoOnFinishAllSteps(currentTestInfo)
        try {
            testRunInterceptor.onMainSectionStarted(currentTestInfo)

            mainSection.invoke(TestContext(stepsManager, mainSectionData))

            val testResultInSteps = stepsManager.onAllStepsFinishedAndGetResultInSteps()
            val updatedTestInfo = currentTestInfo.copy(steps = testResultInSteps)
            runMainTestSectionResult = RunMainTestSectionResult(updatedTestInfo)

            testRunInterceptor.onMainSectionFinishedSuccess(updatedTestInfo)
        } catch (e: Throwable) {
            val testResultInSteps = stepsManager.onAllStepsFinishedAndGetResultInSteps()
            val updatedTestInfo = currentTestInfo.copy(steps = testResultInSteps)
            runMainTestSectionResult = RunMainTestSectionResult(updatedTestInfo, e)

            testRunInterceptor.onMainSectionFinishedFailed(updatedTestInfo, e)
        }
        return runMainTestSectionResult
    }

    private fun checkTestInfoOnFinishAllSteps(testInfo: TestInfo) {
        if (testInfo.steps.isNotEmpty()) {
            throw AssertionError("onAllStepsFinishedAndGetResultInSteps called on already finished test")
        }
    }

    private fun runAfterTestSection(
        currentTestInfo: TestInfo,
        afterTestActions: () -> Unit,
        testRunInterceptor: TestRunInterceptor
    ) {
        try {
            testRunInterceptor.onAfterSectionStarted(currentTestInfo)
            afterTestActions.invoke()
            testRunInterceptor.onAfterSectionFinishedSuccess(currentTestInfo)
        } catch (e: Throwable) {
            testRunInterceptor.onAfterSectionFinishedFailed(currentTestInfo, e)
            throw e
        }
    }

}
