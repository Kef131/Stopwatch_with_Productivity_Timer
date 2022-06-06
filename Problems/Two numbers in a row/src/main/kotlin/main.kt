fun main() {
    val numbers = List(readLine()!!.toInt()) { readLine()!!.toInt() }
    val (p, m) = readLine()!!.split(" ").map { it.toInt() }
    val found = numbers.zipWithNext { first, second -> first == p && second == m || first == m && second == p }
    println(if (found.contains(true)) "NO" else "YES")
}