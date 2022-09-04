//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.api.testcase](../index.md)/[DocLocScreenshotTestCase](index.md)



# DocLocScreenshotTestCase  
 [androidJvm] 



The base class for all docloc screenshot tests.



Project-wide ScreenshotTestCase should be implemented as following:

    open class ProductDocLocScreenshotTestCase(testName: String) : DocLocScreenshotTestCase(  
        File(testName), "comma-separated string of locales"  
    ) {  
  
        @get:Rule  
        val activityTestRule = ActivityTestRule(FragmentTestActivity::class.java, true, false)  
  
        protected lateinit var activity: FragmentTestActivity  
  
        @Before  
        open fun setUp() {  
            activity = activityTestRule.launchActivity(null)  
        }  
    }

Screenshoter test extends the project-wide class:

    @ScreenShooterTest  
    class FeatureScreenshot : ProductDocLocScreenshotTestCase("feature_screenshot") {  
  
        @Test  
        fun featureScreen() {  
            val featureView = FeatureFragment.newInstance()  
            activity.setFragment(featureView)  
            val view = getUiSafeProxy<FeatureView>(featureView) // Explicit type is important and must be interface  
  
            view.showLoading()  
            captureScreenshot("screenshot_description")  
        }  
    }

As you might have noticed, activity test rule is launched with ``FragmentTestActivity``. It's a special per-project empty activity for test with ``setFragment(Fragment)`` method. E.g:

    class FragmentTestActivity : AppCompatActivity() {  
  
        override fun onCreate(savedInstanceState: Bundle?) {  
            super.onCreate(savedInstanceState)  
            setContentView(R.layout.activity_fragment_container)  
        }  
  
        fun setFragment(fragment: Fragment) {  
            val fragmentTransaction = supportFragmentManager.beginTransaction()  
            fragmentTransaction.replace(R.id.content_container, fragment, "")  
            fragmentTransaction.build()  
        }  
    }

abstract class [DocLocScreenshotTestCase](index.md)(**screenshotsDirectory**: [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html), **screenshotDirectoryProvider**: [ScreenshotDirectoryProvider](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-directory-provider/index.md), **screenshotNameProvider**: [ScreenshotNameProvider](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-name-provider/index.md), **changeSystemLocale**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), **locales**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, **kaspressoBuilder**: [Kaspresso.Builder](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md)) : [TestCase](../-test-case/index.md)   


## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| changeSystemLocale| <br><br>change the system language, i.e. system dialogs (e.g. runtime permissions) will also be localized.     Need permission in manifest file for a target app android.permission.CHANGE_CONFIGURATION<br><br>
| locales| <br><br>comma-separated string with locales to run test with.<br><br>
| screenshotDirectoryProvider| <br><br>screenshot directory provider inside the root directory<br><br>
| screenshotNameProvider| <br><br>screenshot file name provider<br><br>
| screenshotsDirectory| <br><br>root directory to save screenshot.<br><br>
  


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [DocLocScreenshotTestCase](-doc-loc-screenshot-test-case.md)|  [androidJvm] <br><br>root directory to save screenshot.<br><br>fun [DocLocScreenshotTestCase](-doc-loc-screenshot-test-case.md)(screenshotsDirectory: [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html), screenshotDirectoryProvider: [ScreenshotDirectoryProvider](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-directory-provider/index.md), screenshotNameProvider: [ScreenshotNameProvider](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-name-provider/index.md), changeSystemLocale: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), locales: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, kaspressoBuilder: [Kaspresso.Builder](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| createBaseTestBodyBuilder| [androidJvm]  <br>Content  <br>override fun createBaseTestBodyBuilder(testName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): TestBody.Builder<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [getUiSafeProxy](get-ui-safe-proxy.md)| [androidJvm]  <br>Brief description  <br><br><br>Return a dynamic proxy for a given view. [I](get-ui-safe-proxy.md) must be interface.<br><br>  <br>Content  <br>inline fun <[I](get-ui-safe-proxy.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)> [getUiSafeProxy](get-ui-safe-proxy.md)(view: [I](get-ui-safe-proxy.md)): [I](get-ui-safe-proxy.md)  <br><br><br>
| [getUiSafeProxyFromImplementation](get-ui-safe-proxy-from-implementation.md)| [androidJvm]  <br>Brief description  <br><br><br>Return a dynamic proxy over all interfaces that view implements.<br><br>  <br>Content  <br>inline fun <[T](get-ui-safe-proxy-from-implementation.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)> [getUiSafeProxyFromImplementation](get-ui-safe-proxy-from-implementation.md)(view: [T](get-ui-safe-proxy-from-implementation.md)): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [setup](setup.md)| [androidJvm]  <br>Content  <br>fun [setup](setup.md)()  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [adbServer](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/adbServer/#/PointingToDeclaration/)|  [androidJvm] open override val [adbServer](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/adbServer/#/PointingToDeclaration/): [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)   <br>
| [dataProducer](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/dataProducer/#/PointingToDeclaration/)|  [androidJvm] override val [dataProducer](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/dataProducer/#/PointingToDeclaration/): ([Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)   <br>
| [device](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/device/#/PointingToDeclaration/)|  [androidJvm] open override val [device](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/device/#/PointingToDeclaration/): [Device](../../com.kaspersky.kaspresso.device/-device/index.md)   <br>
| [localeRule](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/localeRule/#/PointingToDeclaration/)|  [androidJvm] val [localeRule](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/localeRule/#/PointingToDeclaration/): [LocaleRule](../../com.kaspersky.kaspresso.docloc.rule/-locale-rule/index.md)   <br>
| [mainSectionEnrichers](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/mainSectionEnrichers/#/PointingToDeclaration/)|  [androidJvm] override val [mainSectionEnrichers](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/mainSectionEnrichers/#/PointingToDeclaration/): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>>   <br>
| [params](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/params/#/PointingToDeclaration/)|  [androidJvm] open override val [params](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/params/#/PointingToDeclaration/): [Params](../../com.kaspersky.kaspresso.params/-params/index.md)   <br>
| [storagePermissionRule](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/storagePermissionRule/#/PointingToDeclaration/)|  [androidJvm] val [storagePermissionRule](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/storagePermissionRule/#/PointingToDeclaration/): GrantPermissionRule   <br>
| [testAssistantsProvider](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/testAssistantsProvider/#/PointingToDeclaration/)|  [androidJvm] override val [testAssistantsProvider](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/testAssistantsProvider/#/PointingToDeclaration/): TestAssistantsProviderImpl   <br>
| [testCaseName](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/testCaseName/#/PointingToDeclaration/)|  [androidJvm] override val [testCaseName](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/testCaseName/#/PointingToDeclaration/): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>
| [testFailRule](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/testFailRule/#/PointingToDeclaration/)|  [androidJvm] val [testFailRule](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/testFailRule/#/PointingToDeclaration/): [TestFailRule](../../com.kaspersky.kaspresso.docloc.rule/-test-fail-rule/index.md)   <br>
| [testLogger](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/testLogger/#/PointingToDeclaration/)|  [androidJvm] open override val [testLogger](index.md#com.kaspersky.kaspresso.testcases.api.testcase/DocLocScreenshotTestCase/testLogger/#/PointingToDeclaration/): [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)   <br>

