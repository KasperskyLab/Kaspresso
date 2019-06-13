# Кодстайл написания автотестов на Kaspresso

## TestCase

Каждый проект заводит тест-кейс продукта - ```<product>TestCase```, который наследуется от ```TestCase``` фреймворка.
Все тесты проекта наследуются от созданного ```<product>TestCase```.
В тест-кейсе продукта выносятся общие вещи со всех тестов, в ```setup``` и ```teardown``` прописываются действия, выполняемые во всех тестах.

``` kotlin
open class ProductTestCase : TestCase() {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(FirstActivity::class.java, true, false)  
        
    @Before
    fun setup() {
        // common before
    }
    
    @After
    fun teardown() {
        // common after
    }
}
```
## Структура модуля с тестами

Стуктура модуля с тестами для ```feature1``` и ```feature2```

```
.
├─ advice
│     ├─ common
│     │   ├─ Advice.kt              - Интерфейс для общих шагов для тестов.
│     │   ├─ AbstractFakeAdvice.kt  - Имплементация общих шагов для интеграционного теста.
│     │   └─ AbstractRealAdvice.kt  - Имплементация общих шагов для е2е теста.
│     ├─ feature1
│     │   ├─ Feature1Advice.kt
│     │   ├─ Feature1AdviceFake.kt
│     │   └─ Feature1AdviceReal.kt
│     └─ feature2
│         ├─ Feature2Advice.kt
│         ├─ Feature2AdviceFake.kt
│         └─ Feature2AdviceReal.kt
├─ app
│     ├─ di
│     │   ├─ feature1
│     │   └─ feature2
│     └─ stub
│         ├─ feature1
│         └─ feature2
├─ common
│     └─ ProductTestCase.kt
├─ screens
│     ├─ feature1
│     │  ├─ FirstFeatureScreen1.kt
│     │  └─ FirstFeatureScreen2.kt  
│     ├─ feature2
│     │  └─ SecondFeatureMainScreen.kt  
│     └─ frw
│        ├─ FrwMainScreen.kt
│        ├─ FrwSomeStepScreen.kt
│        └─ FrwFinishScreen.kt  
├─ subcases   
│     ├─ FrwSubCase.kt              - Простое прохождение FRW.
│     └─ FrwCustomSubCase.kt        - Прохождение FRW с дополнительными действиями.
├─ tests
│     ├─ feature1
│     │   ├─ Feature1_123456.kt
│     │   └─ Feature1TestData.kt    - Аккаунты для теста и т.п.
│     └─ feature2
│         └─ Feature2_654321.kt
├─ screenshot_tests                 - Скриншотилка
│     ├─ feature1
│     │   ├─ Screen1Screenshot.kt  
│     │   └─ Screen2Screenshot.kt    
│     └─ feature2
│         └─ Screen3Screenshot.kt
└─ views                            - KView для кастомных views проекта
      └─ CustomView.kt
```

# Структура Теста

+ Тесты лежат в пакете ```tests```
+ Тест наследует ```ProductTestCase```
+ Если тест является авто-тестом из TFS, то в названии должен фигурировать номер тест-кейса
  + На один тест-кейс из TFS заводится один класс с тестом 
+ Тест может содержать вспомогательные методы для повышения читабельности, которые не оперируют скринаими. 
+ Метод, в котором описаны шаги состоит из трех обязательных частей, тело которых может быть пустым:
  + ```beforeTest```
  + ```afterTest```
  + ```runSteps```
+ Внутри ```runSteps``` описываются шаги, используя ```step``` 
  + Внутри ```step``` тест оперирует с ```Screen``` или ```Scenario```
  
``` kotlin
  @Test
  fun testFeature() = 
      beforeTest {
        // some preparing actions
      }
      .afterTest {
        // some clearing actions
      }
      .runSteps{ 
          
          step("Pass first step") {
              //...
              FrwScenario.run()
          }
          
          step("Pass second step") {
              FeatureScreen1 {
                 //actions & assertions
              }
              FeatureScreen2 {
                 //actions & assertions
              }
          }
          //...
      }
```
# Скрины

+ Скрины лежат в пакете ```screens``` и разбиты по фичам
+ Все скрины наследуются от ```KScreen```
+ ```KScreen``` имплементирует паттерн PageObject
+ Все скрины, должны переопределить необходимые свойства из ```KScreen```. В случаях, когда это невозможно, присваивается ```null```     
+ Скрины описывают конкретный экран приложения
+ Скрины могут содержать вспомогательные методы, которые не используют другие скрины
+ Скрины могут содержать !вспомогательные проверки (ассерты)!. Но данные методы должны начинаться с ключевого слова ```assert```.
Например, ```assertDetectsCount```.
+ Скрины не должны содержать состояние
+ Скрины обязательно должны содержать указание класса реализующего этот экран (viewClass) и ID разметки(layoutId) - заполняется разработчиком

```kotlin
object FeatureScreen : KScreen<FeatureScreen> {

    override val layoutId = R.layout.fragment_feature
    override val viewClass = FeatureFragment::class.java
    
    val nextButton = KButton { withId(R.id.button_next) }
    val emailText = KEditText { withId(R.id.edit_text) }

    fun assertSomething() {
        //
    }
    
    fun performSomething() {
        // 
    }
}   
```
