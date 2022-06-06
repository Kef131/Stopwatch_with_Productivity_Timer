fun main() {
    val inputList: MutableList<MutableList<String>> = mutableListOf()
    val n = readLine()!!.toInt()
    for (i in 0 until n) {
        val strings = readLine()!!.split(' ').toMutableList()
        inputList.add(strings)
    }
    val newList: MutableList<MutableList<String>> = mutableListOf()
    newList.add(inputList.last())
    newList.add(inputList.first())
    println(newList)
}