class ListUtils {
    companion object Info {
        fun <T> info(list: List<T>): String = list.toString()
    }
}

fun main() {
    println(ListUtils.info(emptyList<String>()))
    val num = MutableList(5) { readln()!!.toInt() }
    println(num)
}
