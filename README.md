# Mall Android Library

This is a Kotlin Android library for structured reactive development. It is designed to handle complex data flows using Kotlin's coroutines and the Flow API. Clients define a `MallNode` for each data item tracked in the application's state, and `op`/`opp`/etc. operators to transform data from input nodes into other nodes. As such, the state and its update logic form, fundamentally, a bipartite graph of data and operators. The graph is additionally configured to control under what conditions updates happen, and in which coroutine context.

More technical details can be found in the following paper:
> S. Levkowitz. (15-18 April 2024). Theory and method for reactive semantics in application development. Presented at the 18th IEEE International Systems Conference (SysCon), Montreal, Canada. [Online]. Available: https://edas.info/showManuscript.php?m=1570977000&type=final&ext=pdf&title=PDF+file


<p align="center">
    <a href="https://kotlinlang.org/"><img alt="Kotlin 1.8.10" src="https://img.shields.io/badge/kotlin-1.8.10-8A2BE2.svg?style=flat"></a>
    <a href="https://github.com/resonai/mall-android-lib/releases"><img alt="Mall Release" src="https://img.shields.io/github/v/release/resonai/mall-android-lib"></a>
</p>

## Features

- Coroutine operations with multiple inputs.

## Getting Started

### Gradle configuration

To integrate Mall library into your Android project using gradle:
on your root build.gradle:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

Add the dependency to your module

```groovy
dependencies {
    implementation 'com.resonai.mall:mall-android-lib:$latest_version'
}
```
