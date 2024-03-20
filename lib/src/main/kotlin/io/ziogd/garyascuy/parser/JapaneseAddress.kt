package io.ziogd.garyascuy.parser

data class JapaneseAddress(
    var postalCode: String = "",
    var prefecture: String = "",
    var city: String = "",
    var town: String = "",
    var chome: String = "",
    var ban: String = "",
    var go: String = "",
    var left: String = "",
)
