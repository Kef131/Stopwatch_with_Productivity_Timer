const val THREE = 3
fun main() {
    val multiList = MutableList(THREE) { MutableList(THREE) { MutableList(THREE) { 0 } } }
    println(multiList)
}
