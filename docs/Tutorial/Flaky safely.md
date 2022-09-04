# Нестабильный список. Сontinuously, compose.

<br/> Kaspresso позволяет увеличить детерминированность и стабильность тестов. Рассмотрим следующий пример. 

```` Kotlin
class FlakyActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FlakyActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sixthElementBtn.setOnClickListener {
            startActivity(Intent(this, ResultActivity::class.java))
        }

        Handler(mainLooper)
            .apply {
                postDelayed(
                    { binding.secondElementTv.visibility = View.VISIBLE },
                    VISIBILITY_DELAY
                )
            }
            .apply {
                postDelayed(
                    { binding.secondElementTv.text = "new second text" },
                    VISIBILITY_DELAY + TEXT_DELAY
                )
            }
            .apply {
                postDelayed(
                    { binding.sixthElementBtn.visibility = View.VISIBLE },
                    VISIBILITY_DELAY * 2 + TEXT_DELAY
                )
            }
            .apply {
                postDelayed(
                    { binding.sixthElementBtn.text = "new sixth text" },
                    VISIBILITY_DELAY * 2 + TEXT_DELAY * 2
                )
            }
    }

    companion object {
        private val VISIBILITY_DELAY = TimeUnit.SECONDS.toMillis(1)
        private val TEXT_DELAY = TimeUnit.SECONDS.toMillis(3)
    }
}
````
<br> Этот пример воспроизводит ситуацию, при которой контент экрана может меняться в короткие промежутки времени в результате каких-либо операций. Экран состоит из списка элементов `Button` и `TextView` в виде `Linear layout`, обернутый в `ScrollView`. Второй элемент списка с задержкой меняет свой статус на видимый, а после и текст. Затем, аналогичные изменения происходят и с шестым элементом экрана, по нажатию на который открывается новый экран. 

## Начнем с реализации Screen-объектов
```` Kotlin
object FlakyScreen : Screen<FlakyScreen>() {

    val scrollView = KScrollView { withId(R.id.scroll_view_flaky) }

    val flakyBtn = KButton { withId(R.id.sixth_element_btn) }

    val flakyTextView = KButton { withId(R.id.second_element_tv) }
}

object ResultScreen: KScreen<ResultScreen>() {
    override val layoutId: Int = R.layout.result_activity
    override val viewClass: Class<*> = ResultActivity::class.java

    val title = KTextView { withId(R.id.result_title) }
}
```` 
## Реализация теста
```` Kotlin

class FlakyViewsTest : TestCase() {

    @get:Rule
    val activityTestRule = activityScenarioRule<FlakyActivity>()

    @Test
    fun flakyViewsTest() = run {
            step("Open Flaky screen") {

            step("Check ScrollView screen is visible") {
                FlakyScreen {
                    scrollView.isVisible()
                }
            }

            step("Check flaky text view is visible") {
                FlakyScreen {
                    flakyTextView {
                        flakySafely(timeoutMs = 3000) { isVisible() }
                        hasText("second element text")
                    }
                }
            }

            step("Check flaky text view's text") {
                FlakyScreen {
                    flakySafely(timeoutMs = TimeUnit.SECONDS.toMillis(4)) {
                        flakyTextView.hasText("new second element text")
                    }
                }
            }

            step("Check flaky button is visible") {
                FlakyScreen {
                    flakyBtn {
                        isVisible()
                        hasText("sixth element text")
                    }
                }
            }

            step("Check flaky button's text") {
                FlakyScreen {
                    flakySafely(timeoutMs = TimeUnit.SECONDS.toMillis(5)) {
                        flakyBtn {
                            hasText("new sixth element text")
                            click()
                        }
                    }
                }
            }

            step("Check success") {
                ResultScreen {
                    title {
                        isVisible()
                        hasText("Result activity")
                    }
                }
            }
        }
    }
}
````

## Описание работы теста
<br> Начнем с тест-кейса:
<br/> 1. Проверить отображение списка
<br/> 2. Проверить отображение второго элемента и его изначальный текст
<br/> 3. Проверить новый текст второго элемента
<br/> 4. Проверить отображение шестого элемента и его изначальный текст
<br/> 5. Проверить новый текст шестого элемента и нажать на него
<br/> 6. Проверить переход на другой экран

<br/> Здесь используется метод `flakySafely`. Он позволяет увеличить время ожидания для конкретного набора действий и проверок. Этот метод не увеличивает время прохождения тестов при положительном сценарии, но позволяет повысить стабильность флекающих тестов.


