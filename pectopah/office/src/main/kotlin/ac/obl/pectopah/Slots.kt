package ac.obl.pectopah

import ac.obl.pectopah.model.ReservationSlot

fun slotsDoNotOverlap(slotA: ReservationSlot, slotB: ReservationSlot): Boolean {
    if (slotA.day != slotB.day) {
        return true
    }
    if (slotA.time.isBefore(slotB.time) && slotA.endTime.isAfter(slotB.time)) {
        return false
    }
    if (slotA.time.isBefore(slotB.endTime) && slotA.endTime.isAfter(slotB.endTime)) {
        return false
    }
    return true
}
