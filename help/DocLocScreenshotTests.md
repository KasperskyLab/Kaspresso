## Main purpose

Sometimes when developing new features, there is a need to check if the application works properly in all supported languages. Manual locale setting changes could take a long time and require the efforts of developers, QA engineers, and etc. 
Also it could increase the duration of the localisation process. 

In order to avoid that, Kaspresso provides ```DocLocScreenshotTestCase``` ([com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase]) which allows to take screenshots in all locales you specified.
`DocLocScreenshotTestCase` extends default Kaspresso `TestCase` and offers the opportunity to make screenshots out the box by calling `DocLocScreenshotTestCase#captureScreenshot(String)` method. 
        
## Usage

