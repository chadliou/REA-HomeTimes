package com.example.reahometime.utiltest

import com.example.reahometime.util.dateFromDotNetDate
import com.example.reahometime.util.formatDateToString
import com.example.reahometime.util.getEstimateTime
import com.example.reahometime.util.getTimeFromDate
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import java.util.*

/**
 * Test Converter class
 */
class ConverterUnitTest {

    @Test
    fun getTimeFromDate_fakeString_isCorrect() {
        val expectString = "20:36"
        assertEquals(expectString, getTimeFromDate(FAKE_STRING))
    }

    @Test
    fun getTimeFromDate_emptyString_isCorrect() {
        assertEquals("N/A", getTimeFromDate(""))
    }

    @Test
    fun formatDateToString_fakeDate_isCorrect() {
        val fakeDate = dateFromDotNetDate(FAKE_STRING)
        val expectString = "20:36"
        fakeDate?.let {
            assertEquals(expectString, formatDateToString(it))
        }
    }

    @Test
    fun getEstimateTime_fakeDate_isCorrect() {
        val expectString = "(19m)"
        val fakeDate = 1620209808000L
        assertEquals(expectString, getEstimateTime(FAKE_STRING, fakeDate))
    }

    companion object {
        const val FAKE_STRING = "/Date(1620210960000+1000)/"
    }
}