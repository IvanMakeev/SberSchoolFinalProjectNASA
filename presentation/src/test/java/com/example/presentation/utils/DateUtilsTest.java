package com.example.presentation.utils;


import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static org.junit.Assert.*;

public class DateUtilsTest {

    private static final int DEFAULT_CURRENT_POSITION = 0;
    private static int DAYS_OF_YEAR = 0;
    private String mCurrentDate;

    @Before
    public void setUp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        mCurrentDate = dateFormat.format(calendar.getTime());
        DAYS_OF_YEAR = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    @Test
    public void testGetDateOffset() {
        assertEquals(mCurrentDate, DateUtils.getDateOffset(DEFAULT_CURRENT_POSITION));
    }

    @Test
    public void testGetLengthOfYear() {
        assertEquals(DAYS_OF_YEAR, DateUtils.lengthOfYear());
    }
}