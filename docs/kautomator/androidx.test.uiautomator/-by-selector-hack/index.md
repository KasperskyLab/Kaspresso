[kautomator](../../index.md) / [androidx.test.uiautomator](../index.md) / [BySelectorHack](./index.md)

# BySelectorHack

`object BySelectorHack`

Workaround for creating [BySelector](#) instances with package-private constructor.
Replaces default [By](#) factory and completely safe to use due to member function invocation after
instance creation.

### Functions

| Name | Summary |
|---|---|
| [newInstance](new-instance.md) | `fun newInstance(function: BySelector.() -> BySelector): BySelector` |
