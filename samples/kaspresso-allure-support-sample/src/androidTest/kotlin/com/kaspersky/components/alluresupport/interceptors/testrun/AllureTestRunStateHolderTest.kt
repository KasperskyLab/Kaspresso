package com.kaspersky.components.alluresupport.interceptors.testrun

import org.junit.Test
import java.io.File

class AllureTestRunStateHolderTest {

    @Test
    fun test() {
        val allureReportFile = JSON.byteInputStream()
        val holder = TestRunStateHolder()
        val stubFile = File("stub/path/video.mp4")
        val actualFile = File("actual/path/video.mp4")

        // holder.addAttachedVideo(allureReportInputStream = allureReportFile, attachedStubFile = stubFile, actualFile = actualFile)

        assert(holder.attachedVideos.size == 1)
        assert(holder.attachedVideos.contains(AttachedVideo(
            attachedStubFile = stubFile,
            actualFile = actualFile
        )))
    }
}

private const val ATTACHMENT_SOURCE = "3c56a71e-a04c-499e-acfb-5114f01cff27-attachment.mp4"
private const val JSON = """
{
  "name": "counter",
  "start": 1668071092146,
  "stop": 1668071100633,
  "stage": "finished",
  "description": null,
  "descriptionHtml": null,
  "status": "passed",
  "statusDetails": null,
  "steps": [
    {
      "name": "Launch the app",
      "start": 1668071095452,
      "stop": 1668071095476,
      "stage": "finished",
      "description": null,
      "descriptionHtml": null,
      "status": "passed",
      "statusDetails": null,
      "steps": [
      ],
      "attachments": [
      ],
      "parameters": [
      ]
    },
    {
      "name": "Check increase and decrease buttons",
      "start": 1668071095476,
      "stop": 1668071097477,
      "stage": "finished",
      "description": null,
      "descriptionHtml": null,
      "status": "passed",
      "statusDetails": null,
      "steps": [
        {
          "name": "Increase value up to five",
          "start": 1668071095476,
          "stop": 1668071096939,
          "stage": "finished",
          "description": null,
          "descriptionHtml": null,
          "status": "passed",
          "statusDetails": null,
          "steps": [
          ],
          "attachments": [
          ],
          "parameters": [
          ]
        },
        {
          "name": "Decrease value down to three",
          "start": 1668071096939,
          "stop": 1668071097477,
          "stage": "finished",
          "description": null,
          "descriptionHtml": null,
          "status": "passed",
          "statusDetails": null,
          "steps": [
          ],
          "attachments": [
          ],
          "parameters": [
          ]
        }
      ],
      "attachments": [
      ],
      "parameters": [
      ]
    },
    {
      "name": "Clear the value",
      "start": 1668071097477,
      "stop": 1668071097752,
      "stage": "finished",
      "description": null,
      "descriptionHtml": null,
      "status": "passed",
      "statusDetails": null,
      "steps": [
      ],
      "attachments": [
      ],
      "parameters": [
      ]
    }
  ],
  "attachments": [
    {
      "source": "3c56a71e-a04c-499e-acfb-5114f01cff27-attachment.mp4",
      "name": "video.mp4",
      "type": "video/mp4"
    }
  ],
  "parameters": [
  ],
  "uuid": "e2780adb-021c-417e-9a8e-097c28aaa880",
  "historyId": "699bb555b44a1f0c91565a15f746e566",
  "fullName": "com.kaspersky.kaspresso.alluresupport.sample.AllureSupportTest.counter",
  "labels": [
    {
      "name": "package",
      "value": "com.kaspersky.kaspresso.alluresupport.sample"
    },
    {
      "name": "testClass",
      "value": "com.kaspersky.kaspresso.alluresupport.sample.AllureSupportTest"
    },
    {
      "name": "testMethod",
      "value": "counter"
    },
    {
      "name": "suite",
      "value": "com.kaspersky.kaspresso.alluresupport.sample.AllureSupportTest"
    },
    {
      "name": "host",
      "value": "localhost"
    },
    {
      "name": "thread",
      "value": "Instr: io.qameta.allure.android.runners.AllureAndroidJUnitRunner(575)"
    },
    {
      "name": "framework",
      "value": "junit4"
    },
    {
      "name": "language",
      "value": "kotlin"
    }
  ]
}
"""
