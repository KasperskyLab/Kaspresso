[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases](../index.md) / [TestCase](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`TestCase(configBuilder: `[`Configurator.Builder`](../../com.kaspersky.kaspresso.configurator/-configurator/-builder/index.md)` = Configurator.Builder.default())`

A base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase](index.md) as a
parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an
exception caused by re-initialization of the [Configurator](../../com.kaspersky.kaspresso.configurator/-configurator/index.md), use [Scenario](../-scenario/index.md) instead.

