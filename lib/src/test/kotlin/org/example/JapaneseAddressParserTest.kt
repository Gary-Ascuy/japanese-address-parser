package org.example

import net.joshka.junit.json.params.JsonFileSource
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.converter.ConvertWith
import kotlin.test.assertEquals

class JapaneseAddressParserTest {
    private val parser = JapaneseAddressParser()

    @ParameterizedTest(name = "{index} - {0}")
    @JsonFileSource(resources = ["/json/parse-japanese-address-data-val.json"])
    fun shouldParseJapaneseAddress(@ConvertWith(TestDataConverter::class) data: JapaneseAddressTestData) {
        val address = parser.parse(data.text)
        assertEquals(expected = data.result, actual = address)
    }
}
