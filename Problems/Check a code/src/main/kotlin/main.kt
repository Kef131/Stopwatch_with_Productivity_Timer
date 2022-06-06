class Event(private val id: Int, private val x: Int, private val y: Int, private val isLongClick: Boolean) {
    operator fun component1(): Int = id
    operator fun component2(): Int = x
    operator fun component3(): Int = y
    operator fun component4(): Boolean = isLongClick
}

fun isEventLongClicked(events: MutableList<Event>, eventId: Int): Boolean? {
    for ((id, x, y, isLongClick) in events) {
        if (id == eventId) return isLongClick
    }
    return null
}