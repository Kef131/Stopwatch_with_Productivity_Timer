fun main() {
    val backFromTheWall = readLine()!!.split(", ").map { it }.toMutableList()
    val returnedWatchman = readLine()!!
    backFromTheWall.add(returnedWatchman)
    println(backFromTheWall.joinToString())
}