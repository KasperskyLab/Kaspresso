[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view](../index.md) / [AtomWatcherInterceptor](index.md) / [intercept](./intercept.md)

# intercept

`abstract fun intercept(atomProxy: `[`AtomProxy`](../../com.kaspersky.kaspresso.proxy/-atom-proxy/index.md)`<*>, evaluation: Evaluation?): Unit`

Called to do some stuff before [androidx.test.espresso.web.model.Atom.transform](#) is actually called.

### Parameters

`evaluation` - represents the results of a Javascript execution.