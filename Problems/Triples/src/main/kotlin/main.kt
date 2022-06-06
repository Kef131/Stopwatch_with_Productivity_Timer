fun main() {
    val numbers = List(readLine()!!.toInt()) { readLine()!!.toInt() }
    var triple = 0
    for (i in 0..numbers.lastIndex - 2) {
        if (numbers[i] + 1 == numbers[i + 1] && numbers[i] + 2 == numbers[i + 2]) triple++
    }
    println(triple)
}