fun main() {
    val inputList: MutableList<MutableList<String>> = mutableListOf()
    val n = readLine()!!.toInt()
    for (i in 0 until n) {
        val strings = readLine()!!.split(' ').toMutableList()
        inputList.add(strings)
    }
    println("""
        ${inputList.first().first()} ${inputList.first().last()}
        ${inputList.last().first()} ${inputList.last().last()}
    """.trimIndent())
}