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
    
    @Before
    fun setup() {
        // common after
    }
}
```
## Структура модуля с тестами

Стуктура модуля с тестами для ```feature1``` и ```feature2```

```
.
├─── advice
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
├─── app
│     ├─ di
│     │   ├─ feature1
│     │   └─ feature2
│     └─ stub
│         ├─ feature1
│         └─ feature2
├─── common
│     └─ ProductTestCase.kt
├─── screens
│     ├─ feature1
│     │  ├─ FirstFeatureScreen1.kt
│     │  └─ FirstFeatureScreen2.kt  
│     ├─ feature2
│     │  └─ SecondFeatureMainScreen.kt  
│     └─ frw
│        ├─ FrwMainScreen.kt
│        ├─ FrwSomeStepScreen.kt
│        └─ FrwFinishScreen.kt  
├─── subcases   
│     ├─ FrwSubCase.kt              - Простое прохождение FRW.
│     └─ FrwCustomSubCase.kt        - Прохождение FRW с дополнительными действиями.
├─── tests
│     ├─ feature1
│     │   ├─ Feature1_123456.kt
│     │   └─ Feature1TestData.kt    - Аккаунты для теста и т.п.
│     └─ feature2
│         └─ Feature2_654321.kt
└─── views                          - KView для кастомных views проекта
      └─ CustomView.kt
```
