package com.example.awesomeapp.utils

import org.junit.Assert.*
import org.junit.Test

/**
 * @author Aeri on 10/20/2021.
 */

class UtilityTest {

    @Test
    fun checkIsNull() {
        val expected = "....."
        assertSame(expected, Utility.checkIsNull(null))
        assertSame(expected, Utility.checkIsNull(""))
        assertSame(expected, Utility.checkIsNull("   "))
        assertSame(expected, Utility.checkIsNull("\n"))
    }

    @Test
    fun formatUsername() {
        val pexelUrl = "https://www.pexels.com/"
        val expected = "Jhony"
        assertEquals(expected, Utility.formatUsername(pexelUrl + "Jhony"))
    }
}