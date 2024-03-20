package io.ziogd.garyascuy.parser

import kotlin.test.Test
import kotlin.test.assertEquals
import net.joshka.junit.json.params.JsonFileSource
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.converter.ConvertWith

class JapaneseAddressParserTest {
  private val name = "JapaneseAddressParserTest"
  private val parser = JapaneseAddressParser()

  @Test
  fun shouldParseEmptyJapaneseAddress() {
    val actual = parser.parse("〒100-0001 東京都千代田区千代田10−4−66")
    val expected =
        JapaneseAddress(
            postalCode = "100-0001",
            prefecture = "東京都",
            city = "千代田区",
            town = "千代田",
            chome = "10",
            ban = "4",
            go = "66",
            left = "")

    assertEquals(expected.postalCode, actual.postalCode, "Postal Code should be 100-0001")

    assertEquals(expected.prefecture, actual.prefecture, "Prefecture should be 東京都")
    assertEquals(expected.city, actual.city, "City should be 千代田区")
    assertEquals(expected.town, actual.town, "Town should be 千代田")

    assertEquals(expected.chome, actual.chome, "Chome should be 10")
    assertEquals(expected.ban, actual.ban, "Ban should be 4")
    assertEquals(expected.go, actual.go, "Go should be 66")

    assertEquals(expected.left, actual.left, "Left should be empty")
  }

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
