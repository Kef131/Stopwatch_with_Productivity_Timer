fun main() {
    val list2x3 = List(2) { MutableList(3) { "" } }
    for ((idxRow, row) in list2x3.withIndex())
        for (idxCol in row.indices)
            list2x3[idxRow][idxCol] = "[$idxRow][$idxCol]"
    println(list2x3)
}