package io.ziogd.garyascuy.parser

import jakarta.json.JsonObject
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.params.converter.ArgumentConverter

class TestDataConverter : ArgumentConverter {
  override fun convert(source: Any?, context: ParameterContext?): Any {
    val it = source as JsonObject
    val result = it.getJsonObject("result")
    return JapaneseAddressTestData(
        text = it.getString("text").orEmpty(),
        result =
            JapaneseAddress(
                prefecture = result.getString("prefecture").orEmpty(),
                city = result.getString("city").orEmpty(),
                town = result.getString("town").orEmpty(),
                chome = result.getString("chome").orEmpty(),
                ban = result.getString("ban").orEmpty(),
                go = result.getString("go").orEmpty(),
                left = result.getString("left").orEmpty(),
            ))
  }
}
