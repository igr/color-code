package ac.obl.pectopah.model

import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

data class ReservationSlot(val day: LocalDate, val time: LocalTime, val duration: Duration) {
    // example of calculated property
    val endTime: LocalTime = time.plus(duration)
}
