[kaspresso](../../index.md) / [com.kaspersky.kaspresso.proxy](../index.md) / [ViewAssertionProxy](index.md) / [check](./check.md)

# check

`fun check(view: View?, noViewFoundException: NoMatchingViewException?): Unit`

Calls watcher interceptors before [ViewAssertion.check](#) on wrapped [viewAssertion](#) is called.

### Parameters

`view` - the view, if one was found during the view interaction or null if it was not (which
    may be an acceptable option for an assertion).

`noViewFoundException` - an exception detailing why the view could not be found or null if
    the view was found.