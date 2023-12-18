# Доступные классы Matcher, Action и Assertion

Kaspresso основан на Espresso (если вы не знакомы с Espresso, ознакомьтесь с [официальной документацией](https://developer.android.com/training/testing/espresso)).
В [официальной документации](https://developer.android.com/training/testing/espresso/basics) указаны следующие основные компоненты:

1. **Espresso** – точка входа для взаимодействия с view (через `onView()` и `onData()`). Также предоставляет API, которые не обязательно привязаны к какому-либо элементу интерфейса, например, `pressBack()`.
2. ***ViewMatchers*** – набор объектов, реализующих интерфейс `Matcher<? super View>`. Вы можете передать один или несколько из них методу `onView()`, чтобы найти нужный элемент в текущей иерархии экрана.
3. ***ViewActions*** – коллекция объектов ViewAction, которые можно передать методу `ViewInteraction.perform()`, например, `click()`. Набор действий, которые могут быть выполнены с UI-элементами.
4. ***ViewAssertions*** – коллекция объектов ViewAssertion, которые можно передать методу `ViewInteraction.check()`. Набор проверок, которые могут быть выполнены для различных UI-элементов. В большинстве случаев используют проверки, которые принимают Matcher-ы, для проверки состояния view.

```kotlin
// withId(R.id.my_view) является ViewMatcher
// click() является ViewAction
// matches(isDisplayed()) является ViewAssertion
onView(withId(R.id.my_view))
    .perform(click())
    .check(matches(isDisplayed()))
```

Наиболее востребованные экземпляры Matcher, ViewActions и ViewAssertions можно найти в [шпаргалке Google](https://developer.android.com/training/testing/espresso/cheat-sheet).
<img src="../Images/Matchers_actions_assertions/Espresso_cheat_sheet.png" alt="Шпаргалка по эспрессо"/>

Результаты вызова метода `onView()` (`ViewInteractors`) могут быть закэшированы. В [Kakao](https://github.com/KakaoCup/Kakao) вы можете получить ссылку на ViewInteractor и повторно использовать ее в своем коде. Это делает ваш код в тестах более читабельным и понятным.
Паттерн PageObject позволяет разделить поиск элемента и действия над ним. Kakao представила KView и различные реализации для самых доступных виджетов Android. Этот KView реализует интерфейсы BaseAssertions и BaseActions с некоторыми дополнительными методами. Каждый наследник KView реализует свои собственные интерфейсы для некоторых методов, специфичных для каждого виджета.

<br>Поскольку Kaspresso наследует все лучшее от этих двух фреймворков, вам доступно все, что описано выше.
