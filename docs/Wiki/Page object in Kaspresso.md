# Page object pattern in Kaspresso.

Page object pattern is explained well by Martin Fowler in [this article](https://martinfowler.com/bliki/PageObject.html). Long in short this is a test abstraction that   
describes the screen with some view elements. These view items can be interacted with during tests. As a result the description of the screen elements will be in a separate
class. You no longer need to constantly look for the desired UI element with several matchers in tests. This can be done once by saving a link to the screen.

Page object gives you an ability to reuse Screens and views in different tests, more flexibility with a small redesign.

Kaspresso is a wrapper over Espresso and UI Automator. It uses Kakao as a wrapper over Espresso (All detailed information is here). For UIAutomator Kaspresso provides
Kautomator with UiScreen class. So you can implement this page object pattern by extending UiScreen or KScreen.
