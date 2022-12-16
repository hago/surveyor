package com.hagoapp.surveyor.rule

import com.hagoapp.surveyor.utils.datetime.DateUtils
import com.hagoapp.surveyor.utils.datetime.TimeAnchor
import com.hagoapp.surveyor.utils.datetime.TimeDiff
import java.time.Instant
import java.time.ZonedDateTime
import java.util.*

data class RelativeTimeBoundary(
    val anchor: TimeAnchor,
    val diff: TimeDiff,
    val timeZoneName: String = "UTC"
) {
    fun calculateTime(anchor: TimeAnchor, offset: Long): ZonedDateTime {
        val it = Instant.ofEpochMilli(Instant.now().toEpochMilli() + offset)
        val aTime = ZonedDateTime.ofInstant(it, TimeZone.getTimeZone(timeZoneName).toZoneId())
        return when (anchor) {
            TimeAnchor.Now -> aTime
            TimeAnchor.BeginOfToday -> DateUtils.beginOfZonedDay(aTime)
            TimeAnchor.EndOfToday -> DateUtils.endOfZonedDay(aTime)
            TimeAnchor.BeginOfThisWeek -> DateUtils.beginOfZonedWeek(aTime)
            TimeAnchor.EndOfThisWeek -> DateUtils.endOfZonedWeek(aTime)
            TimeAnchor.BeginOfThisMonth -> DateUtils.beginOfZonedMonth(aTime)
            TimeAnchor.EndOfThisMonth -> DateUtils.endOfZonedMonth(aTime).minusNanos(1)
            TimeAnchor.BeginOfThisQuarter -> DateUtils.beginOfZonedQuarter(aTime)
            TimeAnchor.EndOfThisQuarter -> DateUtils.endOfZonedQuarter(aTime)
            TimeAnchor.BeginOfThisYear -> DateUtils.beginOfZonedYear(aTime)
            TimeAnchor.EndOfThisYear -> DateUtils.endOfZonedYear(aTime)
        }
    }

    fun calculateEpochMilli(anchor: TimeAnchor, offset: Long): Long {
        return calculateTime(anchor, offset).toInstant().toEpochMilli()
    }
}
