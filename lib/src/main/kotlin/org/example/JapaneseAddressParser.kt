package org.example

class JapaneseAddressParser {
  fun parse(address: String): JapaneseAddress {
    return JapaneseAddress()
  }

  companion object {
    fun parse(address: String): JapaneseAddress {
      return JapaneseAddressParser().parse(address)
    }
  }
}
