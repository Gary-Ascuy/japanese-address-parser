# japanese-address-parser

### Example - Code Usage

```kotlin
val parser = JapaneseAddressParser()
val address = parser.parse("〒100-0001 東京都千代田区千代田10−4−66")

// Output
val expected = JapaneseAddress(
  postalCode = "100-0001",
  prefecture = "東京都",
  city = "千代田区",
  town = "千代田",
  chome = "10",
  ban = "4",
  go = "66",
  left = "")

assertEquals(expected, address, "Address should be parsed correctly")
```

### Dev Environment Setup

**Generated Code by Copilot**

Get started with the following steps:
- Install [JDK 21](https://adoptopenjdk.net/)
- Install [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
- Install [Gradle](https://gradle.org/install/)
- Clone the repository 
- Open the project in IntelliJ IDEA 
- Run the tests 
- Start coding
