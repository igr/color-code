package ac.obl.pectopah.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import kotlinx.serialization.Serializable
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@Serializable
data class ReservationRequestInput(
    val restaurantId: String,
    val people: Int,
    val day: LocalDate,
    val time: String,
    val duration: Int
)

val convertReservationRequestInputToModel: (ReservationRequestInput) -> ReservationRequest = {
    ReservationRequest(
        restaurantId = RestaurantId(UUID.fromString(it.restaurantId)),
        places = it.people,
        slot = ReservationSlot(
            day = it.day.toJavaLocalDate(),
            time = LocalTime.parse(it.time, DateTimeFormatter.ISO_LOCAL_TIME),
            duration = Duration.ofMinutes(it.duration.toLong())
        )
    )
}
