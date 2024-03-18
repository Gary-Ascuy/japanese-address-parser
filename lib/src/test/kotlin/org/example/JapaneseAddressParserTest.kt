package org.example

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class JapaneseAddressParserTest {
    @Test fun shouldParseJapaneseAddress() {
        val parser = JapaneseAddressParser()
        val address = parser.parse("")

        assertFalse(address.prefecture.isEmpty())
        assertTrue(address.city.isEmpty())
        assertTrue(address.town.isEmpty())

        assertTrue(address.chome.isEmpty())
        assertTrue(address.ban.isEmpty())
        assertTrue(address.go.isEmpty())

        assertTrue(address.left.isEmpty())
    }
}
