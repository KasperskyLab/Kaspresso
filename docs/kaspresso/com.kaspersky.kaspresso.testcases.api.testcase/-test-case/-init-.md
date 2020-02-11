[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.api.testcase](../index.md) / [TestCase](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`TestCase(kaspressoBuilder: `[`Kaspresso.Builder`](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md)` = Kaspresso.Builder.advanced())`

The base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase](index.md) as a
parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an
exception caused by re-initialization of the [Kaspresso](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/index.md), use
[com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead.

