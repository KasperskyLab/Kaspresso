[kaspresso](../../index.md) / [com.kaspersky.kaspresso.enricher.impl](../index.md) / [BaseMainSectionEnricher](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`BaseMainSectionEnricher()`

Base implementation of [com.kaspersky.kaspresso.enricher.MainSectionEnricher](../../com.kaspersky.kaspresso.enricher/-main-section-enricher/index.md) interface for supporting DSL-style
of enriching main section.

In [enrichMainSection](enrich-main-section.md) method you should add before/after actions into your enricher, like this:

```
        override fun enrichMainSection() {
            beforeMainSectionRun { testInfo ->
                // here, this == TestContext<Data>, so we can use all properties and functions of TestContext
                step("My before main section run action | test info: $testInfo") {
                    // do something
                }
            }

            afterMainSectionRun { testInfo ->
                // here, this == TestContext<Data>, so we can use all properties and functions of TestContext
            }
        }

    }
```

### Parameters

`Data` -
* The same data type as in your [com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-base-test-case/index.md).
