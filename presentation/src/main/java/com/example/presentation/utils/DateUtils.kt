package com.example.presentation.utils

import android.os.Build
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.util.*

/**
 * Утилитный класс
 */
object DateUtils {
    /**
     * Метод для получения отформатированной текущей даты
     *
     * @param currentPosition текущая позиция элемента на экрана, по которй идет вычесление сдвига текущей даты
     * @return отформатированная дата
     */
    @JvmStatic
    fun getDateOffset(currentPosition: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -currentPosition)
        val dateWithOffset = calendar.time
        return formattingDate(dateWithOffset)
    }

    /**
     * Для получения количества дней в году
     *
     * @return количество дней в году
     */
    @JvmStatic
    fun lengthOfYear(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            YearMonth.now().lengthOfYear()
        } else {
            val calendar = Calendar.getInstance()
            val day = calendar[Calendar.DAY_OF_MONTH]
            val month = calendar[Calendar.MONTH]
            val year = calendar[Calendar.YEAR]
            val gregorianCalendar: Calendar = GregorianCalendar(year, month, day)
            gregorianCalendar.getActualMaximum(Calendar.DAY_OF_YEAR)
        }
    }

    @JvmStatic
    private fun formattingDate(date: Date): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(date)
    }
}