import com.kaspersky.gradle.thirdpartyreport.model.CveAction

// Please do not remove this file. This file is used for new releases.
plugins {
    id("com.kaspersky.gradle.third-party-report-plugin")
    id("com.kaspersky.gradle.third-party-report-include-plugin")
}

thirdPartyReport {
}

thirdPartyReportInclude {
    includeCve(
        parentId = "org.jsoup:jsoup",
        name = "CVE-2021-37714",
        action = CveAction.NotApplicable,
        description = "CVE про denial of service вэб страниц. Kaspresso - это фреймворк для UI тестирования Android приложений. " +
                "В рамках его функциональности не используется парсинг html страниц. В production коде Kaspresso не может использоваться"
    )
    includeCve(
        parentId = "org.jsoup:jsoup",
        name = "CVE-2022-36033",
        action = CveAction.NotApplicable,
        description = "Согласно описанию CVE есть риск атаки XSS. В случае мобильных приложений это возможно только в случае тестирования элементов WebView. " +
                "Однако, библиотеку org.jsoup:jsoup.1.15.1 использует Accessibility Test Framework for Android https://github.com/google/Accessibility-Test-Framework-for-Android. " +
                "Он используется для осуществления тестовых проверок для элементов пользовательского интерфейса, однако, в случае WebView Accessibility не может получить html сайта. " +
                "А это значит, что и XSS осуществить невозможно"
    )
}
