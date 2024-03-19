package io.ziogd.garyascuy.parser

import kotlin.test.Test
import kotlin.test.assertEquals
import net.joshka.junit.json.params.JsonFileSource
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.converter.ConvertWith

class JapaneseAddressParserTest {
  private val parser = JapaneseAddressParser()

  @ParameterizedTest(name = "{index} - {0}")
  @JsonFileSource(resources = ["/json/parse-japanese-address-data-val.json"])
  fun shouldParseJapaneseAddress(
      @ConvertWith(TestDataConverter::class) data: JapaneseAddressTestData
  ) {
    val actual = parser.parse(data.text)
    assertEquals(expected = data.result, actual, "Failed to parse address: ${data.text}")
  }

  @Test
  fun shouldParseJapaneseAddressUsingClassMethod() {
    val actual = JapaneseAddressParser.parse("")
    assertEquals(expected = JapaneseAddress(), actual, "Failed to parse empty address")
  }
}
