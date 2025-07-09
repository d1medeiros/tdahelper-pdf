package com.example.tdahelper.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DurationTest {

    @Test
    fun `duration parse 1 HOUR`() {
        val result = Duration(1, DurationType.HOUR).parse()
        assertEquals(1, result)
    }

    @Test
    fun `duration parse 1 DAY`() {
        val result = Duration(1, DurationType.DAY).parse()
        assertEquals(12, result)
    }

    @Test
    fun `duration parse 2 DAY`() {
        val result = Duration(2, DurationType.DAY).parse()
        assertEquals(24, result)
    }

    @Test
    fun `duration parse 1 DAY plus 4 HOURS`() {
        val result = Duration(
            1,
            DurationType.DAY,
            Duration(4, DurationType.HOUR)
        ).parse()
        assertEquals(16, result)
    }

    @Test
    fun `duration parse 1 WEEK`() {
        val result = Duration(1, DurationType.WEEK).parse()
        assertEquals(84, result)
    }

    @Test
    fun `duration parse 2 WEEK`() {
        val result = Duration(2, DurationType.WEEK).parse()
        assertEquals(168, result)
    }

    @Test
    fun `duration parse 1 WEEK plus 4 HOURS`() {
        val result = Duration(
            1,
            DurationType.WEEK,
            Duration(4, DurationType.HOUR)
        ).parse()
        assertEquals(88, result)
    }

    @Test
    fun `duration parse 1 MONTH`() {
        val result = Duration(1, DurationType.MONTH).parse()
        assertEquals(336, result)
    }

    @Test
    fun `duration parse 2 MONTH`() {
        val result = Duration(2, DurationType.MONTH).parse()
        assertEquals(672, result)
    }

}