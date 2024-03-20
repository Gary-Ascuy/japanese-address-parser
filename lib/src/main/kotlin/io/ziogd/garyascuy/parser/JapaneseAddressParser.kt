package io.ziogd.garyascuy.parser

import java.util.regex.Pattern

class JapaneseAddressParser {
  // https://regex101.com/r/DnxUSN/1
  private val postalCodeRegExp = "〒(\\d{3}-\\d{4})(.*)"
  private val postalCodePattern = Pattern.compile(postalCodeRegExp)

  // https://regex101.com/r/TScMrm/1
  private val prefectureRegExp = "([\\p{InHiragana}\\p{InKatakana}\\p{IsHan}]+[県都府道])(.*)"
  private val prefecturePattern = Pattern.compile(prefectureRegExp)

  // https://regex101.com/r/QnBJmT/1
  private val cityRegExp = "([\\p{InHiragana}\\p{InKatakana}\\p{IsHan}]+[市町村区])(.*)"
  private val cityPattern = Pattern.compile(cityRegExp)

  // https://regex101.com/r/iHiWAn/1 (TODO: Fix this regex to match the correct town name)
  private val townRegExp = "([\\p{InHiragana}\\p{InKatakana}\\p{IsHan}]+[町村区田])(.*)"
  private val townPattern = Pattern.compile(townRegExp)

  private val chomeBanGoRegExp = "(\\d+)−(\\d+)−(\\d+)"
  private val chomeBanGoPattern = Pattern.compile(chomeBanGoRegExp)

  fun parse(textAddress: String): JapaneseAddress {
    if (textAddress.isEmpty()) {
      return JapaneseAddress()
    }

    val jp = JapaneseAddress()

    val (postalCode, text1) = parsePostalCode(textAddress)
    jp.postalCode = postalCode

    val (prefecture, text2) = parsePrefecture(text1)
    jp.prefecture = prefecture

    val (city, text3) = parseCity(text2)
    jp.city = city

    val (town, text4) = parseTown(text3)
    jp.town = town

    val (chome, ban, go) = parseChomeBanGo(text4)
    jp.chome = chome
    jp.ban = ban
    jp.go = go

    //    jp.left = left

    return jp
  }

  private fun parsePostalCode(textAddress: String): Pair<String, String> {
    val postalCodeMatcher = postalCodePattern.matcher(textAddress)
    if (postalCodeMatcher.find()) {
      return postalCodeMatcher.group(1) to postalCodeMatcher.group(2)
    }

    return "" to textAddress
  }

  private fun parsePrefecture(textAddress: String): Pair<String, String> {
    val prefectureMatcher = prefecturePattern.matcher(textAddress)
    if (prefectureMatcher.find()) {
      return prefectureMatcher.group(1) to prefectureMatcher.group(2)
    }

    return "" to textAddress
  }

  private fun parseCity(textAddress: String): Pair<String, String> {
    val cityMatcher = cityPattern.matcher(textAddress)
    if (cityMatcher.find()) {
      return cityMatcher.group(1) to cityMatcher.group(2)
    }

    return "" to textAddress
  }

  private fun parseTown(textAddress: String): Pair<String, String> {
    val townMatcher = townPattern.matcher(textAddress)
    if (townMatcher.find()) {
      return townMatcher.group(1) to townMatcher.group(2)
    }

    return "" to textAddress
  }

  private fun parseChomeBanGo(textAddress: String): Triple<String, String, String> {
    val chomeBanGoMatcher = chomeBanGoPattern.matcher(textAddress)
    if (chomeBanGoMatcher.find()) {
      return Triple(
          chomeBanGoMatcher.group(1), chomeBanGoMatcher.group(2), chomeBanGoMatcher.group(3))
    }

    return Triple("", "", "")
  }

  companion object {
    fun parse(address: String): JapaneseAddress {
      return JapaneseAddressParser().parse(address)
    }
  }
}
