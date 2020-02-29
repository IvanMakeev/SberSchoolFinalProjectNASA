package com.example.presentation.utils

import com.example.presentation.utils.DateUtils.getDateOffset
import com.example.presentation.utils.DateUtils.lengthOfYear
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateUtilsTest {

    companion object {
        private const val DEFAULT_CURRENT_POSITION = 0
        private var DAYS_OF_YEAR = 0
    }

    private var currentDate: String? = null
    @Before
    fun setUp() {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        currentDate = dateFormat.format(calendar.time)
        DAYS_OF_YEAR = calendar.getActualMaximum(Calendar.DAY_OF_YEAR)
    }

    @Test
    fun testGetDateOffset() {
        Assert.assertEquals(currentDate, getDateOffset(DEFAULT_CURRENT_POSITION))
    }

    @Test
    fun testGetLengthOfYear() {
        Assert.assertEquals(DAYS_OF_YEAR.toLong(), lengthOfYear().toLong())
    }
}