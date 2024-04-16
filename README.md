# Mall Android Library

This is a Kotlin library for Android development. It is designed to handle complex data flows using Kotlin's coroutines and the Flow API.

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
