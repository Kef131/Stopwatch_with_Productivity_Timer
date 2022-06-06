fun main() {
    val numbers = List(readLine()!!.toInt()) { readLine()!!.toInt() }
    println(if (numbers.contains(readLine()!!.toInt())) "YES" else "NO")
}