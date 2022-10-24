# Espresso as the basis
Kaspresso is based on Google testing framework Espresso (if you're not familiar with Espresso, check out the [official docs](https://developer.android.com/training/testing/espresso)
<br>Espresso allows you to work with the elements of your application as a white box ([white box testing](https://en.wikipedia.org/wiki/White-box_testing)). You can find the desired element on the screen using matchers, perform different actions or checks.
<br>This framework has a lot of drawbacks and not all things in Android autotesting can be done with Espresso alone.
## What do we want:
<br>* `Good readability`. Espresso has a problem with this because of the huge hierarchy of matchers. When we have a lot of matches, the code becomes difficult to read. Poor readability means difficult to maintain
<br>* `Hight stability`. Espresso does not work well with interfaces whose elements are displayed asynchronously. You can configure Idling, but that still won't solve all problems. 
<br>* `Logging`. After completing the test with Espresso, you do not have a step-by-step workflow sequence of actions.
<br>* `Screenshots`. We also want to have some screenshots for the test report.
<br>* `Working with Android OS`. In some cases, we need to interact with the device. In this case you need UiAutomator (as a variant).
<br>* `Сode architecture`. We want to have a clean code architecture in our tests, the ability to reuse code, move some blocks in abstractions. One code style for all developers.
